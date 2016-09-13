package ru.javawebinar.topjava.repository.meal;// Created by Neginskiy Gregoriy.

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.Repository;

import java.util.List;
import java.util.UUID;

public abstract class MealRepository implements Repository<Meal, UUID> {
    public abstract void add(Meal element);

    public abstract void remove(UUID idKey);

    public abstract void update(Meal element);

    public abstract List<Meal> getAll();

    public abstract Meal getById(UUID idKey);
}
