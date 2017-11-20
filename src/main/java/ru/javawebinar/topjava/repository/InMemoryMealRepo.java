package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Map;

public interface InMemoryMealRepo {
    void create(Meal meal);
    void update(Long id, LocalDateTime dateTime, String description, int calories);
    void delete(Long id);
    Meal getMeal(Long id);
    Map<Long, Meal> getAllMeals();
}
