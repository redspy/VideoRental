import java.util.List;
import java.util.Scanner;

public class VRUI {
    private static Scanner scanner = new Scanner(System.in);

    private VideoRental vr = new VideoRental();

    public static void main(String[] args) {
        VRUI ui = new VRUI();

        boolean quit = false;
        while (!quit) {
            int command = ui.showCommand();
            switch (command) {
                case 0:
                    quit = true;
                    break;
                case 1:
                    ui.listCustomers();
                    break;
                case 2:
                    ui.listVideos();
                    break;
                case 3:
                    ui.registerCustomer();
                    break;
                case 4:
                    ui.registerVideo();
                    break;
                case 5:
                    ui.rentVideo();
                    break;
                case 6:
                    ui.returnVideo();
                    break;
                case 7:
                    ui.getCustomerReport();
                    break;
                case 8:
                    ui.clearRentals();
                    break;
                case -1:
                    ui.init();
                    break;
                default:
                    break;
            }
        }
        System.out.println("Bye");
    }

    public void clearRentals() {
        Customer foundCustomer = inputCustomer();

        if (foundCustomer == null) {
            System.out.println("No customer found");
        } else {
            List<Rental> rentals = vr.getRentalsOfCustomer(foundCustomer);
            System.out.println("Name: " + foundCustomer.getName() +
                    "\tRentals: " + rentals.size());
            for (Rental rental : rentals) {
                System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ");
                System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode());
            }

            vr.clearRentals(foundCustomer);
        }
    }

    public void returnVideo() {
        Customer foundCustomer = inputCustomer();
        if (foundCustomer == null) return;

        String videoTitle = inputString("Enter video title to return: ");

        Rental foundRental = vr.findRentalByVideoTitle(foundCustomer, videoTitle);
        if (foundRental == null) return;

        vr.returnVideo(foundRental);
    }

    private void init() {
        Customer james = vr.registerCustomer("James");
        Customer brown = vr.registerCustomer("Brown");

        Video v1 = vr.registerVideo("v1", VideoType.CD, PriceCode.REGULAR);
        Video v2 = vr.registerVideo("v2", VideoType.DVD, PriceCode.NEW_RELEASE);

        vr.rentVideo(james, v1);
        vr.rentVideo(james, v2);
    }

    public void listVideos() {
        System.out.println("List of videos");

        for (Video video : vr.getVideos()) {
            System.out.println("Price code: " + video.getPriceCode() + "\tTitle: " + video.getTitle());
        }
        System.out.println("End of list");
    }

    public void listCustomers() {
        System.out.println("List of customers");
        for (Customer customer : vr.getCustomers()) {
            List<Rental> rentals = vr.getRentalsOfCustomer(customer);
            System.out.println("Name: " + customer.getName() +
                    "\tRentals: " + rentals.size());
            for (Rental rental : rentals) {
                System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ");
                System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode());
                System.out.print("\n");
            }
        }
        System.out.println("End of list");
    }

    public void getCustomerReport() {
        Customer foundCustomer = inputCustomer();

        if (foundCustomer == null) {
            System.out.println("No customer found");
        } else {
            String result = vr.getCustomerReport(foundCustomer);
            System.out.println(result);
        }
    }

    public void rentVideo() {
        Customer foundCustomer = inputCustomer();
        if (foundCustomer == null) return;

        String videoTitle = inputString("Enter video title to rent: ");
        Video foundVideo = vr.findVideo(videoTitle);
        if (foundVideo == null) return;

        vr.rentVideo(foundCustomer, foundVideo);
    }

    private Customer inputCustomer() {
        String customerName = inputString("Enter customer name: ");
        return vr.findCustomer(customerName);
    }

    public void registerCustomer() {
        String name = inputString("Enter customer name: ");

        vr.registerCustomer(name);
    }

    public void registerVideo() {
        String title = inputString("Enter video title to register: ");
        int videoTypeId = inputInt("Enter video type( 1 for VHD, 2 for CD, 3 for DVD ):");
        int priceCodeId = inputInt("Enter price code( 1 for Regular, 2 for New Release ):");

        VideoType videoType = VideoType.get(videoTypeId);
        PriceCode priceCode = PriceCode.get(priceCodeId);

        vr.registerVideo(title, videoType, priceCode);
    }

    private int inputInt(String message) {
        System.out.println(message);
        return scanner.nextInt();
    }

    private String inputString(String message) {
        System.out.println(message);
        return scanner.next();
    }

    public int showCommand() {
        System.out.println("\nSelect a command !");
        System.out.println("\t 0. Quit");
        System.out.println("\t 1. List customers");
        System.out.println("\t 2. List videos");
        System.out.println("\t 3. Register customer");
        System.out.println("\t 4. Register video");
        System.out.println("\t 5. Rent video");
        System.out.println("\t 6. Return video");
        System.out.println("\t 7. Show customer report");
        System.out.println("\t 8. Show customer and clear rentals");

        int command = scanner.nextInt();

        return command;

    }
}
