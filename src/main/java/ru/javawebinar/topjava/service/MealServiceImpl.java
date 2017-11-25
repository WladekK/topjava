package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {

    private final MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal create(int userId, Meal meal) {
        return repository.save(userId, meal);
    }

    @Override
    public void delete(int userId, int mealId) {
        checkNotFoundWithId(repository.delete(userId, mealId), mealId);
    }

    @Override
    public Meal get(int userId, int mealId) {
        return checkNotFoundWithId(repository.get(userId, mealId), mealId);
    }

    @Override
    public void update(int userId, Meal meal) {
        repository.save(userId, meal);
    }

    @Override
    public List<Meal> getAll(int userId) {
        List<Meal>userMeals = (List<Meal>) repository.getAll(userId);
        if (userMeals.isEmpty()){
            throw new NotFoundException("User with such id does not have meals yet");
        }
        return  userMeals;
    }

    @Override
    public List<Meal> getAllByDate(int userId, LocalDate startDate, LocalDate endDate) {
        List<Meal>userMeals = (List<Meal>) repository.getFilteredByDate(userId, startDate, endDate);
        if (userMeals.isEmpty()){
            throw new NotFoundException("User with such id does not have meals between this period");
        }
        return  userMeals;
    }

    @Override
    public List<Meal> getAllByTime(int userId, LocalTime startTime, LocalTime endTime) {
        List<Meal>userMeals = (List<Meal>) repository.getFilteredByTime(userId, startTime, endTime);
        if (userMeals.isEmpty()){
            throw new NotFoundException("User with such id does not have meals between this period");
        }
        return  userMeals;
    }
}