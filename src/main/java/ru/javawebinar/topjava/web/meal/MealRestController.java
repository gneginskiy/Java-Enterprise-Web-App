package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class MealRestController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    @Autowired
    private MealService service;

    public Collection<Meal> getAll() {
        LOG.info("getAll");
        return service.getAll(AuthorizedUser.id());
    }

    public Meal get(int mealId) {
        LOG.info("get meal with id:" + mealId+" | user id : " +AuthorizedUser.id());
        return service.get(mealId, AuthorizedUser.id());
    }

    public Meal create(Meal meal) {
        meal.setId(null);
        LOG.info("create " + meal +" | user id : " +AuthorizedUser.id());
        return service.save(meal, AuthorizedUser.id());
    }

    public Meal save(Meal meal) {
        LOG.info("save " + meal +" | user id : " +AuthorizedUser.id());
        return service.save(meal, AuthorizedUser.id());
    }

    public void delete(int mealId) {
        LOG.info("get meal with id:" + mealId+" | user id : " +AuthorizedUser.id());
        service.delete(mealId, AuthorizedUser.id());
    }

    public void update(Meal meal, int mealId) {
        meal.setId(mealId);
        LOG.info("update meal:" + meal+" | user id : " +AuthorizedUser.id());
        service.update(meal,AuthorizedUser.id());
    }
}