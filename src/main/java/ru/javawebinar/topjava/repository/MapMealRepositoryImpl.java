package ru.javawebinar.topjava.repository;// Created by Neginskiy Gregoriy.

import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapMealRepositoryImpl implements Repository<MealWithExceed, UUID> {

    private static final Map<UUID, MealWithExceed> mapStorage = new ConcurrentHashMap<>();
    private static final MapMealRepositoryImpl instance = new MapMealRepositoryImpl();

    public static Repository getInstance() {
        return instance;
    }

    private MapMealRepositoryImpl() {
        restoreInitial();
    }

    @Override
    public void add(MealWithExceed element) {
        mapStorage.put(element.getId(), element);
    }

    @Override
    public void remove(UUID idKey) {
        mapStorage.remove(idKey);
    }

    @Override
    public void update(MealWithExceed element) {
        mapStorage.put(element.getId(), element);
    }

    @Override
    public List<MealWithExceed> getAll() {
        return new ArrayList<>(mapStorage.values());
    }

    @Override
    public MealWithExceed getById(UUID idKey) {
        return mapStorage.get(idKey);
    }

    @Override
    public void restoreInitial() {
        mapStorage.clear();
        mapStorage.putAll(
                MealsUtil.getFilteredWithExceeded(MealsUtil.meals, LocalTime.MIN, LocalTime.MAX, 2000).stream()
                        .collect(Collectors.toMap(MealWithExceed::getId, Function.identity()))
        );
    }
}