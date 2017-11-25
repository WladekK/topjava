package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MealService {
    Meal create(int userId, Meal meal);
    void delete(int userId, int mealId) throws NotFoundException;
    Meal get(int userId, int mealId) throws NotFoundException;
    void update(int userId, Meal meal);
    List<Meal>getAll(int userId);
    List<Meal>getAllByDate(int userId, LocalDate startDate, LocalDate endDate);
    List<Meal>getAllByTime(int userId, LocalTime startTime, LocalTime endTime);
}