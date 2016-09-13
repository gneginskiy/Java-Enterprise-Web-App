package ru.javawebinar.topjava.repository.meal;// Created by Neginskiy Gregoriy.

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MapMealRepository extends MealRepository {
    private static final Map<UUID, Meal> mapStorage = new ConcurrentHashMap<>();
    static {

    }
    @Override
    public void add(Meal element) {
        mapStorage.put(element.getId(), element);
    }

    @Override
    public void remove(UUID idKey) {
        mapStorage.remove(idKey);
    }

    @Override
    public void update(Meal element) {
        mapStorage.put(element.getId(), element);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(mapStorage.values());
    }

    @Override
    public Meal getById(UUID idKey) {
        return mapStorage.get(idKey);
    }
}
