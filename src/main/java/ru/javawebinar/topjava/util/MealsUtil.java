package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class MealsUtil {
    public static void main(String[] args) {
        List<Meal> meals = Arrays.asList(
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<MealTo> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

       System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<MealTo> filteredByCycles(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> calories = new HashMap<>();
        for (Meal meal : meals) {
            calories.merge(meal.getDateTime().toLocalDate(), meal.getCalories(), Integer::sum);
        }
        List<MealTo> list = new ArrayList<>();
        for (Meal meal : meals) {
            if (meal.getDateTime().toLocalTime().isAfter(startTime) && meal.getDateTime().toLocalTime().isBefore(endTime)) {
                list.add(new MealTo(meal.getDateTime(), meal.getDescription(), meal.getCalories(),
                        calories.get(meal.getDateTime().toLocalDate()) > caloriesPerDay));
            }
        }
        return list;
    }

    public static List<MealTo> filteredByStreams(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> calories = meals.stream()
                .collect(Collectors.toMap(meal -> meal.getDateTime().toLocalDate(), Meal::getCalories, Integer::sum));
    return  meals.stream().filter(m -> m.getDateTime().toLocalTime().isAfter(startTime) && m.getDateTime().toLocalTime().isBefore(endTime))
                .map(meal -> new MealTo(meal.getDateTime(), meal.getDescription(), meal.getCalories(),
                        calories.get(meal.getDateTime().toLocalDate()) > caloriesPerDay)).collect(Collectors.toList());

    }

}
