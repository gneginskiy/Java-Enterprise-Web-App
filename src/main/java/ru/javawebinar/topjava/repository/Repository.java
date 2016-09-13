package ru.javawebinar.topjava.repository;// Created by Neginskiy Gregoriy.

import java.util.List;

public interface Repository<T, K> {
    void add(T element);

    void remove(K idKey);

    void update(T element);

    List<T> getAll();

    T getById(K idKey);
}