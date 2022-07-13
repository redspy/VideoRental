import java.util.List;

public class CustomerReport {
    private final Customer customer;
    private final List<Rental> rentals;
    private final double totalCharge;
    private final int totalPoint;

    public CustomerReport(Customer customer, List<Rental> rentals, double totalCharge, int totalPoint) {
        this.customer = customer;
        this.rentals = rentals;
        this.totalCharge = totalCharge;
        this.totalPoint = totalPoint;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public double getTotalCharge() {
        return totalCharge;
    }

    public int getTotalPoint() {
        return totalPoint;
    }

}
