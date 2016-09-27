package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(
                meal->save(UserRepository.MOCK_USER_ID,meal)
        );
    }

    @Override
    public Meal save(int userId, Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        meal.setUserId(userId);
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public Meal delete(int userId, int id) {
        Meal meal = repository.get(id);
        return checkUserId(meal, userId) ? repository.remove(id) : null;
    }

    @Override
    public Meal get(int userId, int id) {
        Meal meal = repository.get(id);
        return checkUserId(meal,userId)?repository.get(id):null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.values().stream()
                .filter(um -> checkUserId(um, userId))
                .sorted(
                        (meal1, meal2) ->
                                meal2.getDateTime().compareTo(meal1.getDateTime())
                ).collect(Collectors.toList());
    }

    private boolean checkUserId(Meal meal, int userId) {
        return meal.getUserId() != null && meal.getUserId() == userId;
    }
}