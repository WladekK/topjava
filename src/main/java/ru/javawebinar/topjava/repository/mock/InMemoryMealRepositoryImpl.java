package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {

    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Meal save(int userId, Meal meal) {
        Map<Integer, Meal> userMeals = repository.get(userId);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        userMeals.put(meal.getId(), meal);
        repository.put(userId, userMeals);
        return meal;
    }

    @Override
    public boolean delete(int userId, int mealId) {
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
        Map<Integer, Meal> userMeals = repository.get(userId);
        return userMeals.get(mealId);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        Collection<Meal>userMeals = repository.get(userId).values();
        return userMeals.stream().sorted(Comparator.comparing(Meal::getDate)).collect(Collectors.toList());

    }
}

