package Day14RestroomRedoubt;

public class Robot {
    private Point position;
    private Velocity velocity;

    public Robot(Point startPosition, Velocity velocity) {
        this.position = startPosition;
        this.velocity = velocity;
    }


    public Point getPosition() {
        return position;
    }

    public Velocity getVelocity() {
        return velocity;
    }


    public void setPosition(Point position) {
        this.position = position;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

}
