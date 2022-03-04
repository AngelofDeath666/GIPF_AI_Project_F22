package ai.gr64.Data.Enums;

public enum Direction {
    UP_LEFT(0),
    UP_RIGHT(1),
    RIGHT(2),
    DOWN_RIGHT(3),
    DOWN_LEFT(4),
    LEFT(5);

    private final int value;

    private Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Direction fromValue(int value) {
        switch (value) {
            case 0:
                return Direction.UP_LEFT;
            case 1:
                return Direction.UP_RIGHT;
            case 2:
                return Direction.RIGHT;
            case 3:
                return Direction.DOWN_RIGHT;
            case 4:
                return Direction.DOWN_LEFT;
            case 5:
                return Direction.LEFT;
            default:
                throw new IndexOutOfBoundsException();
        }

    }

    public static Direction opposite(Direction dir) {
        return fromValue((dir.getValue() + 3) % 6);
    }

}
