package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 27.03.2015.
 */
@Repository
public class DataJpaMealRepositoryImpl implements MealRepository {

    @Autowired
    private CrudMealRepository crudMealRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;


    @Override
    public Meal save(Meal meal, int userId) {
        boolean mealHasIdAndAbsentInDb = !meal.isNew() && get(meal.getId(), userId) == null;
        if (mealHasIdAndAbsentInDb) {
            return null;
        }
        User userToSet = crudUserRepository.getOne(userId);
        meal.setUser(userToSet);
        return crudMealRepository.save(meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudMealRepository.delete(id,userId) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        return crudMealRepository.getByIdAndUserId(id,userId);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudMealRepository.getByUserId(userId);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return crudMealRepository.getByUserIdBetweenStartDateAndEndDate(startDate,endDate,userId);
    }
}
