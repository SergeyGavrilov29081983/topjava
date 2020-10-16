package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import ru.javawebinar.topjava.repository.inmemory.InMemoryUserRepository;
import ru.javawebinar.topjava.web.SecurityUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management (ARM)
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            User user = adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ADMIN));
            InMemoryUserRepository userRepository = appCtx.getBean(InMemoryUserRepository.class);
            System.out.println(userRepository.getByEmail("email@mail.ru"));
            System.out.println(userRepository.getByEmail("email1@mail.ru"));
            MealRestController controller = appCtx.getBean(MealRestController.class);
            //MealRepository repository = appCtx.getBean(InMemoryMealRepository.class);

            //MealRestController controller = appCtx.getBean(MealRestController.class);

          controller.save();

            System.out.println(controller.getMeal(SecurityUtil.authUserId()));

        }

    }
}
