package Day8EasterBunnyAntinodes;

import java.util.Objects;

public class Location {
    public int row;
    public int col;

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Location other)) return false;
        return this.row == other.row && this.col == other.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }



}
