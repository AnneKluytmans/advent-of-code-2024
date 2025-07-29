package Day13ClawContraption;

public class ClawMachine {
    private Button buttonA;
    private Button buttonB;
    private Point prizeLocation;

    public ClawMachine(Button buttonA, Button buttonB, Point prizeLocation) {
        this.buttonA = buttonA;
        this.buttonB = buttonB;
        this.prizeLocation = prizeLocation;
    }


    public Point clickA(Point point) {
        return new Point(
                point.x() + buttonA.dx(),
                point.y() + buttonA.dy()
        );
    }

    public Point clickB(Point point) {
        return new Point(
                point.x() + buttonB.dx(),
                point.y() + buttonB.dy()
        );
    }


    public Button getButtonA() {
        return buttonA;
    }

    public Button getButtonB() {
        return buttonB;
    }

    public Point getPrizeLocation() {
        return prizeLocation;
    }


    public void setButtonA(Button buttonA) {
        this.buttonA = buttonA;
    }

    public void setButtonB(Button buttonB) {
        this.buttonB = buttonB;
    }

    public void setPrizeLocation(Point prizeLocation) {
        this.prizeLocation = prizeLocation;
    }

}
