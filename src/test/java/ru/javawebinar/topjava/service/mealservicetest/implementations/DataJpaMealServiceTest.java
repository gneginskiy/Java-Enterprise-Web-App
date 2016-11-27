package ru.javawebinar.topjava.service.mealservicetest.implementations;// Created by Neginskiy Gregoriy.

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.profiles.RepoProfiles;
import ru.javawebinar.topjava.service.mealservicetest.AbstractMealServiceTest;

@ActiveProfiles(RepoProfiles.DATA_JPA)
public class DataJpaMealServiceTest extends AbstractMealServiceTest { ; }