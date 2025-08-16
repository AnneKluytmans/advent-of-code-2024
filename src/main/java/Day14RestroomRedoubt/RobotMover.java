package Day14RestroomRedoubt;

public class RobotMover {
    public static Point moveRobot(Robot robot, Size spaceSize, int seconds) {
        Point position = robot.getStart();
        Velocity velocity = robot.getVelocity();


        int newX = mod(position.x() + velocity.dx() * seconds, spaceSize.width());
        int newY = mod(position.y() + velocity.dy() * seconds, spaceSize.height());
        return new Point(newX, newY);
    }

    public static int mod(int value, int max) {
        return ((value % max) + max) % max;
    }
}
