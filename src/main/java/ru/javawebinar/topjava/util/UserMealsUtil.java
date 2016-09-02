package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

import static ru.javawebinar.topjava.util.TimeUtil.isBetween;

/**
 * Created by Greg Neginskiy on 02.09.2016
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        LocalTime startTime = LocalTime.of(7, 0);
        LocalTime endTime = LocalTime.of(12, 0);
        getFilteredWithExceeded(mealList, startTime, endTime, 2000);
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime,
                                                                                LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> result = new ArrayList<>();
        Map<LocalDate,Integer> dayCaloriesSums = getDayCaloriesSums(mealList);
        for (UserMeal userMeal : mealList) {
            LocalTime userMealTime = userMeal.getDateTime().toLocalTime();
            if (!isBetween(userMealTime, startTime, endTime)) {
                continue;
            }
            LocalDate userMealDay = userMeal.getDateTime().toLocalDate();
            boolean isDayExceeded = dayCaloriesSums.get(userMealDay) > caloriesPerDay;
            result.add(new UserMealWithExceed(userMeal,isDayExceeded));
        }
        return result;
    }

    private static Map<LocalDate, Integer> getDayCaloriesSums(List<UserMeal> mealList) {
        Map<LocalDate, Integer> result = new HashMap<>();
        for (UserMeal userMeal : mealList) {
            LocalDate mealDay = userMeal.getDateTime().toLocalDate();
            int daySum = result.containsKey(mealDay) ? result.get(mealDay) : 0;
            daySum += userMeal.getCalories();
            result.put(mealDay,daySum);
        }
        return result;
    }
}