package ru.javawebinar.topjava.web;// Created by Neginskiy Gregoriy.

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.repository.MapMealRepositoryImpl;
import ru.javawebinar.topjava.repository.Repository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("unchecked")
public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);
    private static final Repository<MealWithExceed,UUID> mealRepository = MapMealRepositoryImpl.getInstance();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    enum MealAction{ ADD, REMOVE, EDIT,VIEW, RESTORE_INITIAL,SAVE_NEW,SAVE_EXISTING}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletUtil.setEncoding(request, response, "utf-8");
        //retrieving meal data from form
        String description = ServletUtil.getOptionalParam("description", null, request);
        LocalDateTime dateTime = ServletUtil.getOptionalParam("dateTime", null, formatter, request);
        Integer calories = ServletUtil.getOptionalParam("calories", (Integer)null, request);
        boolean isExceeding = ServletUtil.getOptionalParam("exceeds", false, request); //implementation without exceed calculating yet
        boolean isInputDataEmpty = dateTime == null || calories == null;

        MealAction action = isInputDataEmpty ? MealAction.VIEW : getMealActionByName(request.getParameter("action"));
        switch (action) {
            case SAVE_NEW: {
                mealRepository.add(new MealWithExceed(dateTime, description, calories, isExceeding));
                break;
            }
            case SAVE_EXISTING: {
                UUID oldId = UUID.fromString(request.getParameter("id"));
                mealRepository.add(new MealWithExceed(oldId, dateTime, description, calories, isExceeding));
                break;
            }
            case VIEW: {
                /*nothing here*/
                break;
            }
        }
        showEntries(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletUtil.setEncoding(request, response, "utf-8");
        MealAction action = getMealActionByName(request.getParameter("action"));
        switch (action) {
            case ADD: {
                request.getRequestDispatcher("/addMeal.jsp").forward(request, response);
                break;
            }
            case EDIT: {
                UUID id = UUID.fromString(request.getParameter("id"));
                request.setAttribute("current", mealRepository.getById(id));
                request.getRequestDispatcher("/editMeal.jsp").forward(request, response);
                return;
            }
            case REMOVE: {
                UUID id = UUID.fromString(request.getParameter("id"));
                mealRepository.remove(id);
                break;
            }
            case RESTORE_INITIAL: {
                mealRepository.restoreInitial();
                break;
            }
            case VIEW: {
                /*nothing here*/
                break;
            }
        }
        showEntries(request, response);
    }

    private void showEntries(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MealWithExceed> all = mealRepository.getAll();
        //i've decided to sacrifice performance for sake of the beauty output :)
        all.sort((meal1, meal2) -> meal1.getDateTime().compareTo(meal2.getDateTime()));
        request.setAttribute("meals", all);
        request.getRequestDispatcher("/mealList.jsp").forward(request, response);
    }


    private MealAction getMealActionByName(String actionName) {
        try {
            return MealAction.valueOf(actionName.toUpperCase());
        } catch (IllegalArgumentException|NullPointerException e) {
            return MealAction.VIEW;
        }
    }
}