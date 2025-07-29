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
            return new Edge(new Point(inside.row() + 1, inside.col()), new Point(outside.row() + 1, outside.col()));
        } else if (isHorizontal()) {
            return new Edge(new Point(inside.row(), inside.col() + 1), new Point(outside.row(), outside.col() + 1));
        }
        return null;
    }

    public Edge previousInSameDirection() {
        if (isVertical()) {
            return new Edge(new Point(inside.row() - 1, inside.col()), new Point(outside.row() - 1, outside.col()));
        } else if (isHorizontal()) {
            return new Edge(new Point(inside.row(), inside.col() - 1), new Point(outside.row(), outside.col() - 1));
        }
        return null;
    }
}
