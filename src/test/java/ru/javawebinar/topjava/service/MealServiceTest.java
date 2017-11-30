package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService mealService;

    @Test
    public void get() throws Exception {
        Meal meal = mealService.get(ADMIN_MEAL_ID_1, ADMIN_ID);
        assertMatch(meal, ADMIN_MEAL_1);
    }

    @Test (expected = NotFoundException.class)
    public void delete() throws Exception {
        mealService.delete(USER_MEAL_ID_1, USER_ID);
        mealService.get(USER_MEAL_ID_1, USER_ID);
    }

    @Test
    public void getBetweenDates() throws Exception {
        List<Meal>betweenDates = mealService.getBetweenDates(LocalDate.of(1979, 12, 19),
                LocalDate.of(1979, 12, 19), ADMIN_ID);
        assertMatch(betweenDates, ADMIN_MEAL_1);
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
    }

    @Test
    public void getAll() throws Exception {
        List<Meal>all = mealService.getAll(ADMIN_ID);
        assertMatch(all, ADMIN_MEAL_2, ADMIN_MEAL_1);
    }

    @Test
    public void update() throws Exception {
        Meal updated = new Meal(USER_MEAL_1);
        updated.setId(USER_MEAL_ID_1);
        updated.setDescription("updated meal");
        updated.setCalories(1050);
        mealService.update(updated, USER_ID);
        assertMatch(mealService.get(USER_MEAL_ID_1, USER_ID), updated);
    }

    @Test
    public void create() throws Exception {
        Meal newMeal = new Meal(LocalDateTime.of(1999, 1, 1, 00, 00), "new meal", 1234);
        Meal created = mealService.create(newMeal, ADMIN_ID);
        newMeal.setId(created.getId());
        assertMatch(created, newMeal);
    }

}