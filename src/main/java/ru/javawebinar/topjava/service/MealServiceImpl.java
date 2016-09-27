package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.to.MealWithExceed;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class MealServiceImpl implements MealService {
    @Autowired
    private MealRepository repository;

    @Override
    public Meal get(int mealId, int userId) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.get(userId,mealId), mealId);
    }

    @Override
    public void delete(int mealId, int userId) throws NotFoundException {
        ExceptionUtil.checkNotFoundWithId(repository.delete(userId, mealId), mealId);
    }

    @Override
    public Meal save(Meal meal, int userId) {
        return repository.save(userId,meal);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public void update(Meal meal, int userId) {
        repository.save(userId,meal);
    }

    @Override
    public Collection<MealWithExceed> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime,
                                                                                                           int userId) {
        List<Meal> mealsBetweenDates = repository.getAll(userId).stream()
                .filter(
                        meal ->
                                TimeUtil.isBetween(meal.getDateTime().toLocalDate(),
                                                        startDateTime.toLocalDate(),
                                                        endDateTime.toLocalDate())
                ).collect(Collectors.toList());
        return MealsUtil.getFilteredWithExceeded(mealsBetweenDates,
                                                 startDateTime.toLocalTime(),
                                                 endDateTime.toLocalTime(),
                                                 MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }
}
