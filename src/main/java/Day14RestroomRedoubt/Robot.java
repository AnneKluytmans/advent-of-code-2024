package Day14RestroomRedoubt;

public class Robot {
    private Point startPosition;
    private Velocity velocity;

    public Robot(Point startPosition, Velocity velocity) {
        this.startPosition = startPosition;
        this.velocity = velocity;
    }


    public Point getStartPosition() {
        return startPosition;
    }

    public Velocity getVelocity() {
        return velocity;
    }


    public void setStartPosition(Point startPosition) {
        this.startPosition = startPosition;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

}
