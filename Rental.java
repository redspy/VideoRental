import java.util.Date;

public class Rental {
    private Video video;
    private RentalStatus status; // 0 for Rented, 1 for Returned
    private Date rentDate;
    private Date returnDate;

    public Rental(Video video) {
        this.video = video;
        status = RentalStatus.RETURNED;
        rentDate = new Date();
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public RentalStatus getStatus() {
        return status;
    }

    public void returnVideo() {
        if (getStatus() == RentalStatus.RENTED) {
            this.status = RentalStatus.RETURNED;
            returnDate = new Date();
        }
    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getDaysRentedLimit() {
        int limit = 0;
        int daysRented = getDaysRented();
        if (daysRented <= 2) return limit;

        switch (video.getVideoType()) {
            case VHS:
                return 5;
            case CD:
                return 3;
            case DVD:
                return 2;
            default:
                throw new IllegalStateException();
        }
    }

    public int getDaysRented() {
        long diff;
        if (getStatus() == RentalStatus.RETURNED) { // returned Video
            diff = this.getReturnDate().getTime() - this.getRentDate().getTime();
        } else { // not yet returned
            diff = new Date().getTime() - this.getRentDate().getTime();
        }
        return (int) (diff / (1000 * 60 * 60 * 24)) + 1;
    }

    public double getCharge() {
        double eachCharge = 0;
        int daysRented = getDaysRented();

        switch (getVideo().getPriceCode()) {
            case REGULAR:
                eachCharge += 2;
                if (daysRented > 2)
                    eachCharge += (daysRented - 2) * 1.5;
                break;
            case NEW_RELEASE:
                eachCharge = daysRented * 3;
                break;
        }
        return eachCharge;
    }

    public int getPoint() {
        int bonus = (getVideo().getPriceCode() == PriceCode.NEW_RELEASE) ? 1 : 0;
        int penalty = (getDaysRented() > getDaysRentedLimit()) ? getVideo().getLateReturnPointPenalty() : 0;
        return Math.max(1 + bonus - penalty, 0);
    }
}
