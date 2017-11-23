package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

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

    public List<Meal>getAll(int userId){
        log.info("getAll");
        return service.getAll(userId);
    }

    public List<MealWithExceed>getAllWithExceed(int userId){
        return MealsUtil.getWithExceeded(getAll(userId), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public Meal get(int userId, int mealId){
        log.info("get {}", mealId);
        return service.get(userId, mealId);
    }

    public Meal create(int userId, Meal meal){
        log.info("create {}", meal);
        checkNew(meal);
        return service.create(userId, meal);
    }

    public void delete(int userId, int mealId){
        log.info("delete {}", mealId);
        service.delete(userId, mealId);
    }

    public void update(int userId, Meal meal, int mealId){
        log.info("update {} with id {}", meal, mealId);
        assureIdConsistent(meal, mealId);
        service.update(userId, meal);
    }
}