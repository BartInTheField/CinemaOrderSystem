package tests;

import domain.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;


public class MovieScreeningTest {

    private double price = 1.0;
    private Movie movie = new Movie("Harry Potter");

    private MovieScreening mondayScreening = new MovieScreening(
            movie,
            LocalDateTime.of(2019, 1, 28, 18, 0, 0),
            price
    );

    private MovieScreening tuesdayScreening = new MovieScreening(
            movie,
            LocalDateTime.of(2019, 1, 29, 18, 0, 0),
            price
    );

    private MovieScreening wednesdayScreening = new MovieScreening(
            movie,
            LocalDateTime.of(2019, 1, 30, 18, 0, 0),
            price
    );

    private MovieScreening thursdayScreening = new MovieScreening(
            movie,
            LocalDateTime.of(2019, 1, 31, 18, 0, 0),
            price
    );

    private MovieScreening fridayScreening = new MovieScreening(
            movie,
            LocalDateTime.of(2019, 2, 1, 18, 0, 0),
            price
    );

    private MovieScreening saturdayScreening = new MovieScreening(
            movie,
            LocalDateTime.of(2019, 2, 2, 18, 0, 0),
            price
    );

    private MovieScreening sundayScreening = new MovieScreening(
            movie,
            LocalDateTime.of(2019, 2, 3, 18, 0, 0),
            price
    );

    @Test
    public void mondayIsWorkWeek() {
        Assert.assertTrue(mondayScreening.isWorkDay());
    }

    @Test
    public void tuesdayIsWorkWeek() {
        Assert.assertTrue(tuesdayScreening.isWorkDay());
    }

    @Test
    public void wednesdayIsWorkWeek() {
        Assert.assertTrue(wednesdayScreening.isWorkDay());
    }

    @Test
    public void thursdayIsWorkWeek() {
        Assert.assertTrue(thursdayScreening.isWorkDay());
    }

    @Test
    public void fridayIsWorkWeek() {
        Assert.assertTrue(fridayScreening.isWorkDay());
    }

    @Test
    public void saturdayIsWeekend() {
        Assert.assertFalse(saturdayScreening.isWorkDay());
    }

    @Test
    public void sundayIsWeekend() {
        Assert.assertFalse(sundayScreening.isWorkDay());
    }
}