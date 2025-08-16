package Day14RestroomRedoubt;

public class Robot {
    private Point start;
    private Velocity velocity;

    public Robot(Point start, Velocity velocity) {
        this.start = start;
        this.velocity = velocity;
    }


    public Point getStart() {
        return start;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }
}
