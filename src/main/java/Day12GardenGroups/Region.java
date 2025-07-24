package Day12GardenGroups;

public class Region {
    private int area = 0;
    private int perimeter = 0;

    public Region() {}

    public Region(int area, int perimeter) {
        this.area = area;
        this.perimeter = perimeter;
    }

    public int getArea() {
        return area;
    }

    public int getPerimeter() {
        return perimeter;
    }

    public long getFencingPrice() {
        return (long) area * perimeter;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public void setPerimeter(int perimeter) {
        this.perimeter = perimeter;
    }

    public void increaseArea() {
        this.area++;
    }

    public void increasePerimeter() {
        this.perimeter++;
    }
}
