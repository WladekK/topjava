package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.InMemoryMealRepo;
import ru.javawebinar.topjava.repository.InMemoryMealRepoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;


public class MealServlet extends HttpServlet {

    private static final Logger log = getLogger(MealServlet.class);
    private InMemoryMealRepo mealRepo = new InMemoryMealRepoImpl();
    private static String MEALS_LIST = "/mealslist.jsp";
    private static String CRUD = "/crud.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward using doGet");
//        List<Meal> meals = Arrays.asList(
//                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
//                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
//                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
//                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
//                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
//                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
//        );
//        List<MealWithExceed> mealsWithExceeded = getFilteredWithExceeded(meals, LocalTime.of(7, 0),
//                LocalTime.of(12, 0), 2000);
//        request.setAttribute("meals", mealsWithExceeded);
//        request.getRequestDispatcher("/meals.jsp").forward(request, response);
        String forward;
        String action = request.getParameter("action");
        Map<Long, Meal> meals = mealRepo.getAllMeals();
        request.setAttribute("meals", meals.entrySet());
        if (action.equalsIgnoreCase("delete")) {
            long mealId = mealId(request);
            mealRepo.delete(mealId);
            forward = MEALS_LIST;
            request.setAttribute("meals", mealRepo.getAllMeals().entrySet());

        } else if (action.equalsIgnoreCase("edit")) {
            forward = CRUD;
            long mealId = mealId(request);
            Meal editedMeal = mealRepo.getMeal(mealId);
            request.setAttribute("meal", editedMeal);
        } else if (action.equalsIgnoreCase("all_users")) {
            forward = MEALS_LIST;
            request.setAttribute("meals", mealRepo.getAllMeals().entrySet());
        } else forward = CRUD;

        request.getRequestDispatcher(forward).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward using doPost");
        LocalDateTime ldt = LocalDateTime.parse(request.getParameter("date"));
        int calories = Integer.parseInt(request.getParameter("calories"));
        Meal newMeal = new Meal(ldt, request.getParameter("description"), calories);
        mealRepo.create(newMeal);
    }
    private static Long mealId(HttpServletRequest request){
        if(request.getParameter("mealId") != null)
            return Long.parseLong(request.getParameter("mealId"));
        else throw new RuntimeException("There is no exist such ID");
    }
}


