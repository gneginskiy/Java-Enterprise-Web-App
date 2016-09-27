package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.web.meal.MealRestController;

import java.util.Arrays;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));

//        UserRepository mealRestController = (UserRepository) appCtx.getBean("mockUserRepository");
        MealRestController mealRestController = appCtx.getBean(MealRestController.class);
        mealRestController.getAll().forEach(element-> System.out.println("\n"+element+"\n"));
        appCtx.close();
    }
}
