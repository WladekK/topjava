package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.DateTimeUtil.isBetween;
import static ru.javawebinar.topjava.util.DateTimeUtil.isBetweenDate;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {

    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepositoryImpl.class);
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(m -> save(0, m));
    }

    @Override
    public Meal save(int userId, Meal meal) {
        log.info("save {}", meal);
        Map<Integer, Meal> userMeals;
        if (repository.containsKey(userId)) {
            userMeals = repository.get(userId);
        }else userMeals = new HashMap<>();

        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        userMeals.put(meal.getId(), meal);
        repository.put(userId, userMeals);
        return meal;
    }

    @Override
    public boolean delete(int userId, int mealId) {
        log.info("delete {}", mealId);
        Map<Integer, Meal> userMeals = repository.get(userId);
        if (userMeals.containsKey(mealId)) {
            userMeals.remove(mealId);
            repository.put(userId, userMeals);
            return true;
        }
        return false;
    }

    @Override
    public Meal get(int userId, int mealId) {
        log.info("get {}", mealId);
        Map<Integer, Meal> userMeals = repository.get(userId);
        return userMeals.get(mealId);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        log.info("getAll");
        Collection<Meal>userMeals = repository.get(userId).values();
        return userMeals.stream().sorted(Comparator.comparing(Meal::getDate)).collect(Collectors.toList());

    }

    @Override
    public Collection<Meal> getFilteredByDate(int userId, LocalDate startDate, LocalDate endDate) {
        log.info("getFilteredByDate");
        Collection<Meal>userMeals = repository.get(userId).values();
        return userMeals.stream().filter(meal -> isBetweenDate(meal.getDate(), startDate, endDate))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Meal> getFilteredByTime(int userId, LocalTime startTime, LocalTime endTime) {
        log.info("getFilteredByTime");
        Collection<Meal>userMeals = repository.get(userId).values();
        return userMeals.stream().filter(meal -> isBetween(meal.getTime(), startTime, endTime))
                .collect(Collectors.toList());
    }
}

