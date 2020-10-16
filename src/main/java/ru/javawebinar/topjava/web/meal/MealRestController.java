package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Controller
public class MealRestController {
    private MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal save() {
       return service.create(new Meal( LocalDateTime.now(),"завтрак",100),  SecurityUtil.authUserId());
    }

    public Meal getMeal(int userId) {
        try {
            return service.get(1, SecurityUtil.authUserId());
        } catch (Exception e) {
            throw new NotFoundException("not exist");
        }
    }
}