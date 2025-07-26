package Day12GardenGroups;

public class Region {
    private int area = 0;
    private int perimeter = 0;
    private int sides = 0;

    public Region() {}

    public Region(int area, int perimeter, int sides) {
        this.area = area;
        this.perimeter = perimeter;
        this.sides = sides;
    }

    public int getArea() {
        return area;
    }

    public int getPerimeter() {
        return perimeter;
    }

    public int getSides() {
        return sides;
    }

    public long getFencingPrice() {
        return (long) area * perimeter;
    }

    public long getDiscountFencingPrice() {
        return (long) area * sides;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public void setPerimeter(int perimeter) {
        this.perimeter = perimeter;
    }

    public void setSides(int sides) {
        this.sides = sides;
    }

    public void increaseArea() {
        this.area++;
    }

    public void increasePerimeter() {
        this.perimeter++;
    }

    public void increaseSides() {
        this.sides++;
    }
}
