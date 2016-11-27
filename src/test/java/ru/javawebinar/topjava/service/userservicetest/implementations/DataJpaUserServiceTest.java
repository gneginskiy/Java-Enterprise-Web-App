package ru.javawebinar.topjava.service.userservicetest.implementations;// Created by Neginskiy Gregoriy.

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.profiles.RepoProfiles;
import ru.javawebinar.topjava.service.userservicetest.AbstractUserServiceTest;

@ActiveProfiles(RepoProfiles.DATA_JPA)
public class DataJpaUserServiceTest extends AbstractUserServiceTest { ; }