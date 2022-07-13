import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;

    private List<Rental> rentals = new ArrayList<Rental>();

    public Customer(String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);

    }

    public String getReport() {
        String result = "Customer Report for " + getName() + "\n";

        List<Rental> rentals = getRentals();

        double totalCharge = 0;
        int totalPoint = 0;

        for (Rental each : rentals) {
            int daysRented = each.getDaysRented();
            double eachCharge = each.getCharge();
            int eachPoint = each.getPoint();

            result += "\t" + each.getVideo().getTitle() + "\tDays rented: " + daysRented + "\tCharge: " + eachCharge
                    + "\tPoint: " + eachPoint + "\n";

            totalCharge += eachCharge;

            totalPoint += eachPoint;
        }

        result += "Total charge: " + totalCharge + "\tTotal Point:" + totalPoint + "\n";


        if (totalPoint >= 10) {
            System.out.println("Congrat! You earned one free coupon");
        }
        if (totalPoint >= 30) {
            System.out.println("Congrat! You earned two free coupon");
        }
        return result;
    }

}
