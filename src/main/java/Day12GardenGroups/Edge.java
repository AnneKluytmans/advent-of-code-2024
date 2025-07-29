package Day12GardenGroups;

public record Edge(Point inside, Point outside) {

    public boolean isHorizontal() {
        return inside.col() == outside.col() && inside.row() != outside.row();
    }

    public boolean isVertical() {
        return inside.row() == outside.row() && inside.col() != outside.col();
    }

    public Edge nextInSameDirection() {
        if (isVertical()) {
            return new Edge(new Point(inside.col(), inside.row() + 1), new Point(outside.col(), outside.row() + 1));
        } else if (isHorizontal()) {
            return new Edge(new Point(inside.col() + 1, inside.row()), new Point(outside.col() + 1, outside.row()));
        }
        return null;
    }

    public Edge previousInSameDirection() {
        if (isVertical()) {
            return new Edge(new Point(inside.col(), inside.row() - 1), new Point(outside.col(), outside.row() - 1));
        } else if (isHorizontal()) {
            return new Edge(new Point(inside.col() - 1, inside.row()), new Point(outside.col() - 1, outside.row()));
        }
        return null;
    }
}
