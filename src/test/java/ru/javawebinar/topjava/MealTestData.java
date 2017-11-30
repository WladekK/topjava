package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {

    public static final int ADMIN_MEAL_ID_1 = 100002;
    public static final int ADMIN_MEAL_ID_2 = 100003;
    public static final int USER_MEAL_ID_1 = 100004;
    public static final int USER_MEAL_ID_2 = 100005;

    public static final Meal ADMIN_MEAL_1 = new Meal(LocalDateTime.of(1979,12,19,  00, 00, 00), "admin`s meal", 1000);
    public static final Meal ADMIN_MEAL_2 = new Meal(LocalDateTime.of(1979, 12, 20, 00, 00), "admin`s second meal", 1200);
    public static final Meal USER_MEAL_1 = new Meal(LocalDateTime.of(1981, 10, 6, 00, 00), "user`s meal", 1500);
    public static final Meal USER_MEAL_2 = new Meal(LocalDateTime.of(1981, 10, 5, 00, 00), "user`s second meal", 1700);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "id");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("id").isEqualTo(expected);
    }

}

