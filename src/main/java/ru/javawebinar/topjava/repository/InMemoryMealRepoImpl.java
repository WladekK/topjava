package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemoryMealRepoImpl implements InMemoryMealRepo {

    private static ConcurrentMap<Long, Meal>mealRepository = new ConcurrentHashMap<>();

    @Override
    public void create(Meal meal) {
        mealRepository.put(Meal.getId().get(), meal);
    }

    @Override
    public void update(Long id, LocalDateTime dateTime, String description, int calories) {
            mealRepository.put(id, new Meal(dateTime, description, calories));
    }

    @Override
    public void delete(Long id) {
        if (mealRepository.containsKey(id)){
            mealRepository.remove(id, mealRepository.get(id));
        } else throw new RuntimeException("There is no meal with such id");
    }

    @Override
    public Meal getMeal(Long id) {
        Optional<Meal> meal = Optional.empty();
        return mealRepository.getOrDefault(id, meal.get());
    }

    @Override
    public Map<Long, Meal> getAllMeals() {
        return mealRepository;
    }
}
