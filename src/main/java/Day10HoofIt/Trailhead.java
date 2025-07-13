package Day10HoofIt;

public class Trailhead {
    private long score;
    private long rating;

    public Trailhead(long trailheadScore, long trailheadRating) {
        this.score = trailheadScore;
        this.rating = trailheadRating;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }
}
