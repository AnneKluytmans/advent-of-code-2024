package Day16ReindeerMaze;

import Helpers.Point;

public record State(Point position, Direction direction, int steps, int rotations) implements Comparable<State> {

    public long getTotalScore() { return (long) steps + (long) rotations * 1000; }

    @Override
    public int compareTo(State other) {
        return Long.compare(this.getTotalScore(), other.getTotalScore());
    }

    public String key() {
        return position.x() + "," + position.y() + "," + direction.name();
    }
}
