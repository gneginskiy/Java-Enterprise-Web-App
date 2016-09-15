package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * GKislin
 * 11.01.2015.
 */
public class MealWithExceed {
    private final String description;
    private final LocalDateTime dateTime;
    private final int calories;
    private final UUID id;
    private final boolean exceed;

    public MealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this(UUID.randomUUID(),dateTime,description,calories,exceed);
    }

    public MealWithExceed(UUID id,LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }

    @Override
    public String toString() {
        return "UserMealWithExceed{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", exceed=" + exceed +
                '}';
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExceed() {
        return exceed;
    }

    public UUID getId() {
        return id;
    }
}
