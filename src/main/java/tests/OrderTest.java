package tests;

import domain.*;
import exceptions.TicketsNotForTheSameScreeningException;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;


public class OrderTest {

    private MovieScreening workDayScreening = new MovieScreening(
            new Movie("Harry Potter"),
            LocalDateTime.of(2019, 1, 30, 18, 0, 0),
            1.0
    );

    private MovieScreening weekendScreening = new MovieScreening(
            new Movie("Harry Potter the second"),
            LocalDateTime.of(2019, 2, 2, 18, 0, 0),
            1.0
    );

    @Test
    public void theirCanOnlyBeOneKindOfMovieInAOrder() {
        MovieTicket movieTicketOne = new MovieTicket(workDayScreening, false, 1, 1);
        MovieTicket movieTicketTwo = new MovieTicket(weekendScreening, false, 1, 2);

        Order order = new Order(1, false);

        Assert.assertThrows(TicketsNotForTheSameScreeningException.class, () -> {
            order.addMovieTicket(movieTicketOne);
            order.addMovieTicket(movieTicketTwo);
        });
    }

    @Test
    public void secondTicketIsFreeOnAWorkDay() {
        MovieTicket movieTicketOne = new MovieTicket(workDayScreening, false, 1, 1);
        MovieTicket movieTicketTwo = new MovieTicket(workDayScreening, false, 1, 2);

        Order order = new Order(1, false);

        try {
            order.addMovieTicket(movieTicketOne);
            order.addMovieTicket(movieTicketTwo);
        } catch (Exception e) {

        }
        Assert.assertEquals( 1.00, order.calculatePrice(), 0);
    }

    @Test
    public void threeTicketOneIsFreeOnAWorkDay() {
        MovieTicket movieTicketOne = new MovieTicket(workDayScreening, false, 1, 1);
        MovieTicket movieTicketTwo = new MovieTicket(workDayScreening, false, 1, 2);
        MovieTicket movieTicketThree = new MovieTicket(workDayScreening, false, 1, 3);

        Order order = new Order(1, false);

        try {
            order.addMovieTicket(movieTicketOne);
            order.addMovieTicket(movieTicketTwo);
            order.addMovieTicket(movieTicketThree);
        } catch (Exception e) {

        }
        Assert.assertEquals( 2.00, order.calculatePrice(),0);
    }

    @Test
    public void fourthTicketTwoAreFreeOnAWorkDay() {
        MovieTicket movieTicketOne = new MovieTicket(workDayScreening, false, 1, 1);
        MovieTicket movieTicketTwo = new MovieTicket(workDayScreening, false, 1, 2);
        MovieTicket movieTicketThree = new MovieTicket(workDayScreening, false, 1, 3);
        MovieTicket movieTicketFour = new MovieTicket(workDayScreening, false, 1, 4);


        Order order = new Order(1, false);

        try {
            order.addMovieTicket(movieTicketOne);
            order.addMovieTicket(movieTicketTwo);
            order.addMovieTicket(movieTicketThree);
            order.addMovieTicket(movieTicketFour);
        } catch (Exception e) {

        }

        Assert.assertEquals( 2.00, order.calculatePrice(), 0);
    }

    @Test
    public void secondTicketIsFreeInTheWeekendForStudents() {
        MovieTicket movieTicketOne = new MovieTicket(weekendScreening, false, 1, 1);
        MovieTicket movieTicketTwo = new MovieTicket(weekendScreening, false, 1, 2);

        Order order = new Order(1, true);

        try {
            order.addMovieTicket(movieTicketOne);
            order.addMovieTicket(movieTicketTwo);
        } catch (Exception e) {

        }

        Assert.assertEquals(order.calculatePrice(), 1.00, 0);
    }

    @Test
    public void fourthTicketTwoAreFreeInTheWeekendForStudents() {
        MovieTicket movieTicketOne = new MovieTicket(weekendScreening, false, 1, 1);
        MovieTicket movieTicketTwo = new MovieTicket(weekendScreening, false, 1, 2);
        MovieTicket movieTicketThree = new MovieTicket(weekendScreening, false, 1, 3);
        MovieTicket movieTicketFour = new MovieTicket(weekendScreening, false, 1, 4);


        Order order = new Order(1, true);

        try {
            order.addMovieTicket(movieTicketOne);
            order.addMovieTicket(movieTicketTwo);
            order.addMovieTicket(movieTicketThree);
            order.addMovieTicket(movieTicketFour);
        } catch (Exception e) {

        }

        Assert.assertEquals( 2.00, order.calculatePrice(), 0);
    }

    @Test
    public void secondTicketIsNotFreeInTheWeekend() {
        MovieTicket movieTicketOne = new MovieTicket(weekendScreening, false, 1, 1);
        MovieTicket movieTicketTwo = new MovieTicket(weekendScreening, false, 1, 2);

        Order order = new Order(1, false);

        try {
            order.addMovieTicket(movieTicketOne);
            order.addMovieTicket(movieTicketTwo);
        } catch (Exception e) {

        }

        Assert.assertEquals( 2.00, order.calculatePrice(), 0);
    }

    @Test
    public void regularsGetTenPercentDiscountIfMoreThanSixTicketsInTheWeekend() {
        MovieTicket movieTicketOne = new MovieTicket(weekendScreening, false, 1, 1);
        MovieTicket movieTicketTwo = new MovieTicket(weekendScreening, false, 1, 2);
        MovieTicket movieTicketThree = new MovieTicket(weekendScreening, false, 1, 3);
        MovieTicket movieTicketFour = new MovieTicket(weekendScreening, false, 1, 4);
        MovieTicket movieTicketFive = new MovieTicket(weekendScreening, false, 1, 5);
        MovieTicket movieTicketSix = new MovieTicket(weekendScreening, false, 1, 6);


        Order order = new Order(1, false);

        try {
            order.addMovieTicket(movieTicketOne);
            order.addMovieTicket(movieTicketTwo);
            order.addMovieTicket(movieTicketThree);
            order.addMovieTicket(movieTicketFour);
            order.addMovieTicket(movieTicketFive);
            order.addMovieTicket(movieTicketSix);
        } catch (Exception e) {

        }

        Assert.assertEquals( 5.40, order.calculatePrice(), 0);
    }

    @Test
    public void premiumTicketForStudentsIsTwoEuroMore() {
        MovieTicket movieTicketOne = new MovieTicket(weekendScreening, true, 1, 1);

        Order order = new Order(1, true);

        try {
            order.addMovieTicket(movieTicketOne);
        } catch (Exception e) {

        }

        Assert.assertEquals( 3.00, order.calculatePrice(), 0);
    }

    @Test
    public void premiumTicketForRegularsIsThreeEuroMore() {
        MovieTicket movieTicketOne = new MovieTicket(weekendScreening, true, 1, 1);

        Order order = new Order(1, false);

        try {
            order.addMovieTicket(movieTicketOne);
        } catch (Exception e) {

        }

        Assert.assertEquals( 4.00, order.calculatePrice(), 0);
    }
}