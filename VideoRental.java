import java.util.*;

public class VideoRental {
    private List<Customer> customers = new ArrayList<>();
    private List<Video> videos = new ArrayList<>();
    private Map<Customer, List<Rental>> rentalsOfCustomer = new HashMap<>();

    public Video registerVideo(String title, VideoType videoType, PriceCode priceCode) {
        Date today = new Date();

        Video video = new Video(title, videoType, priceCode, today);
        videos.add(video);
        return video;
    }

    public Customer registerCustomer(String name) {
        Customer customer = new Customer(name);
        customers.add(customer);
        rentalsOfCustomer.put(customer, new ArrayList<>());
        return customer;
    }

    public Customer findCustomer(String customerName) {
        Customer foundCustomer = null;
        for (Customer customer : customers) {
            if (customer.getName().equals(customerName)) {
                foundCustomer = customer;
                break;
            }
        }
        return foundCustomer;
    }

    public Video findVideo(String videoTitle) {
        Video foundVideo = null;
        for (Video video : videos) {
            if (video.getTitle().equals(videoTitle) && video.isRented() == false) {
                foundVideo = video;
                break;
            }
        }
        return foundVideo;
    }

    public Rental rentVideo(Customer customer, Video video) {
        Rental rental = new Rental(customer, video);
        video.setRented(true);

        rentalsOfCustomer.get(customer).add(rental);
        return rental;
    }

    public String getCustomerReport(Customer customer) {
        String result = "Customer Report for " + customer.getName() + "\n";

        List<Rental> rentals = getRentalsOfCustomer(customer);

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

    public List<Rental> getRentalsOfCustomer(Customer customer) {
        return rentalsOfCustomer.get(customer);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Video> getVideos() {
        return videos;
    }

    void returnVideo(Rental foundRental) {
        foundRental.returnVideo();
        foundRental.getVideo().setRented(false);
    }

    Rental findRentalByVideoTitle(Customer customer, String videoTitle) {
        List<Rental> customerRentals = getRentalsOfCustomer(customer);
        for (Rental rental : customerRentals) {
            if (rental.getVideo().getTitle().equals(videoTitle) && rental.getVideo().isRented()) {
                return rental;
            }
        }
        return null;
    }

    void clearRentals(Customer customer) {
        getRentalsOfCustomer(customer).clear();
    }
}
