package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

public interface MealRepository {
    Meal save(int userId, Meal meal);

    boolean delete(int userId, int mealId);

    Meal get(int userId, int mealId);

    Collection<Meal> getAll(int userId);

    Collection<Meal> getFilteredByDate(int userId, LocalDate startDate, LocalDate endDate);

    Collection<Meal> getFilteredByTime(int userId, LocalTime startTime, LocalTime endTime);
}
