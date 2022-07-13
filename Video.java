import java.util.Date;

public class Video {
    private String title;
    private PriceCode priceCode;
    private VideoType videoType;
    private Date registeredDate;
    private boolean rented;

    public Video(String title, VideoType videoType, PriceCode priceCode, Date registeredDate) {
        this.setTitle(title);
        this.setVideoType(videoType);
        this.setPriceCode(priceCode);
        this.registeredDate = registeredDate;
    }

    public int getLateReturnPointPenalty() {
        int pentalty = 0;
        switch (videoType) {
            case VHS:
                pentalty = 1;
                break;
            case CD:
                pentalty = 2;
                break;
            case DVD:
                pentalty = 3;
                break;
        }
        return pentalty;
    }

    public PriceCode getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(PriceCode priceCode) {
        this.priceCode = priceCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public VideoType getVideoType() {
        return videoType;
    }

    public void setVideoType(VideoType videoType) {
        this.videoType = videoType;
    }
}
