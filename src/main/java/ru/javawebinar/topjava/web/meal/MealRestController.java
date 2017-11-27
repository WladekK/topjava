package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public List<Meal>getAll(){
        int userId = AuthorizedUser.id();
        log.info("getAll for User {}", userId);
        return service.getAll(userId);
    }

    public List<MealWithExceed>getAllWithExceed(){
        int userId = AuthorizedUser.id();
        log.info("getAllWithExceed for User {}", userId);
        return MealsUtil.getWithExceeded(service.getAll(userId), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public List<MealWithExceed>getAllWithExceedFilteredByTime(LocalTime startTime,
                                                              LocalTime endTime){
        int userId = AuthorizedUser.id();
        log.info("getAllWithExceedFilteredByTime for User {}", userId);
        return MealsUtil.getFilteredWithExceeded(service.getAll(userId), startTime, endTime,
                MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public Meal get(int mealId){
        int userId = AuthorizedUser.id();
        log.info("get meal {} for User {}", mealId, userId);
        return service.get(userId, mealId);
    }

    public Meal create(Meal meal){
        int userId = AuthorizedUser.id();
        log.info("create meal {} for User {}", meal, userId);
        checkNew(meal);
        return service.create(userId, meal);
    }

    public void delete(int mealId){
        int userId = AuthorizedUser.id();
        log.info("delete meal {} for User {}", mealId, userId);
        service.delete(userId, mealId);
    }

    public void update(Meal meal, int mealId){
        int userId = AuthorizedUser.id();
        log.info("update {} with id {} for User {}", meal, mealId, userId);
        assureIdConsistent(meal, mealId);
        service.update(userId, meal);
    }
}