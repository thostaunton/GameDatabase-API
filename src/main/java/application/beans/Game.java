package application.beans;

public class Game {

    private String title;
    private String genre;
    private String publisher;
    private String release;
    private int reviewScore = 1;

    public Game(String title, String genre, String publisher, String release, int reviewScore) {
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
        this.release = release;
        this.reviewScore = reviewScore;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public int getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(int reviewScore) {
        this.reviewScore = reviewScore;
    }

}
