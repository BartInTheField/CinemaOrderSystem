package domain;

import com.google.gson.Gson;
import exceptions.TicketsNotForTheSameScreeningException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Order {
    private int orderNr;
    private boolean isStudentOrder;

    private ArrayList<MovieTicket> tickets;

    public Order(int orderNr, boolean isStudentOrder) {
        this.orderNr = orderNr;
        this.isStudentOrder = isStudentOrder;

        tickets = new ArrayList<MovieTicket>();
    }

    public int getOrderNr() {
        return orderNr;
    }

    public void addMovieTicket(MovieTicket ticket) throws TicketsNotForTheSameScreeningException {
        if (isTicketForSameMovieScreeningAsTheRest(ticket)) {
            tickets.add(ticket);
        } else {
            throw new TicketsNotForTheSameScreeningException("Not the same movie screening");
        }

    }

    private boolean isTicketForSameMovieScreeningAsTheRest(MovieTicket newMovieTicket) {
        boolean isTheSame = true;
        for (MovieTicket ticket : tickets) {
            if(!(newMovieTicket.getMovieScreening().equals(ticket.getMovieScreening()))) {
                isTheSame = false;
            }
        }

        return isTheSame;
    }

    public double calculatePrice() {
        double price = calculatePriceForAllTickets();

        price = addPremiumTicketPrice(price);
        price = addSalesToPrice(price);

        return price;
    }

    private double calculatePriceForAllTickets() {
        double price = 0.0;
        for (MovieTicket ticket : tickets ) {
            price = price + ticket.getPrice();
        }
        return price;
    }

    private double addPremiumTicketPrice(double currentPrice) {
        double price = currentPrice;

        for (MovieTicket ticket : tickets ){
            if(ticket.getisPremiumTicket()) {
                if(isStudentOrder) {
                    price = price + (tickets.size() * 2);
                } else {
                    price = price + (tickets.size() * 3);
                }
            }
        }

        return price;
    }

    private double addSalesToPrice(double currentPrice) {
        double price = currentPrice;
        price = addFreeTicketSaleToPrice(price);
        price = addGroupWeekendSale(price);
        return  price;
    }

    private double addFreeTicketSaleToPrice(double currentPrice) {
        double price = currentPrice;
        MovieScreening movieScreening = tickets.get(0).getMovieScreening();

        if(movieScreening.isWorkDay() || isStudentOrder) {
            int amountOfSecondTickets = tickets.size() / 2;
            price = price - (amountOfSecondTickets * movieScreening.getPricePerSeat());
        }

        return price;
    }

    private double addGroupWeekendSale(double currentPrice) {
        double price = currentPrice;
        MovieScreening movieScreening = tickets.get(0).getMovieScreening();

        if(!movieScreening.isWorkDay() && tickets.size() > 5 && !isStudentOrder) {
            price = price * 0.9;
        }

        return price;
    }

    public void export(TicketExportFormat exportFormat) {
        switch (exportFormat) {
            case JSON:
                exportOrderInJSON();
                break;
            case PLAINTEXT:
                exportOrderInPlainText();
                break;
            default:
                break;
        }
    }

    private void exportOrderInJSON() {
        Gson gson = new Gson();
        String text = gson.toJson(tickets.toArray());

        writeExportToFile(text, "json");
    }

    private void exportOrderInPlainText() {
        String text = tickets.toString();

        writeExportToFile(text, "txt");
    }

    private void writeExportToFile(String exportText, String format) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(getExportFileName() + '.' + format));
            writer.write(exportText);
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not write to file");
        }

    }

    private String getExportFileName() {
        return "Order_" + orderNr;
    }
}

