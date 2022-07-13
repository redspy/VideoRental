import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VideoRental {
    private List<Customer> customers = new ArrayList<>();
    private List<Video> videos = new ArrayList<>();

    public Video registerVideo(String title, VideoType videoType, PriceCode priceCode) {
        Date today = new Date();

        Video video = new Video(title, videoType, priceCode, today);
        videos.add(video);
        return video;
    }

    public Customer registerCustomer(String name) {
        Customer customer = new Customer(name);
        customers.add(customer);
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

    public Rental rentVideo(Customer foundCustomer, Video foundVideo) {
        Rental rental = new Rental(foundVideo);
        foundVideo.setRented(true);

        List<Rental> customerRentals = foundCustomer.getRentals();
        customerRentals.add(rental);
        foundCustomer.setRentals(customerRentals);

        return rental;
    }

    public String getCustomerReport(Customer foundCustomer) {
        return foundCustomer.getReport();
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

    Rental findRentalByVideoTitle(Customer foundCustomer, String videoTitle) {
        List<Rental> customerRentals = foundCustomer.getRentals();
        for (Rental rental : customerRentals) {
            if (rental.getVideo().getTitle().equals(videoTitle) && rental.getVideo().isRented()) {
                return rental;
            }
        }
        return null;
    }

    void clearRentals(Customer foundCustomer) {
        List<Rental> rentals = new ArrayList<>();
        foundCustomer.setRentals(rentals);
    }
}
