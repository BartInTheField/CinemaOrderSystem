package domain;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class MovieScreening
{
    private Movie movie;

    private LocalDateTime dateAndTime;
    private double pricePerSeat;

    public MovieScreening(Movie movie, LocalDateTime dateAndTime, double pricePerSeat)
    {
        this.movie = movie;
        movie.addScreening(this);

        this.dateAndTime = dateAndTime;
        this.pricePerSeat = pricePerSeat;
    }

    public double getPricePerSeat()
    {
        return pricePerSeat;
    }

    public boolean isWorkDay() {
        boolean isWorkDay = true;
        DayOfWeek day = this.dateAndTime.getDayOfWeek();

        if(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            isWorkDay = false;
        }

        return isWorkDay;
    }

    @Override
    public String toString() {
        return movie.getTitle() + " - " + dateAndTime;
    }
}
