package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.to.MealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

/**
 * GKislin
 * 15.06.2015.
 */
public interface MealService {
    Meal get(int mealId, int userId) throws NotFoundException;

    void delete(int mealId, int userId) throws NotFoundException;

    Meal save(Meal meal, int userId);

    Collection<Meal> getAll(int userId);

    void update(Meal meal, int userId);

    default Collection<MealWithExceed> getBetweenDates(LocalDate startDate, LocalDate endDate, int userId) {
        return getBetweenDateTimes(
                LocalDateTime.of(startDate, LocalTime.MIN), LocalDateTime.of(endDate, LocalTime.MAX),userId);
    }

    Collection<MealWithExceed> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);
}
