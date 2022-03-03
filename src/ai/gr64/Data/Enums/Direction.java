package ai.gr64.Data.Enums;

public enum Direction {
    UP_LEFT(0),
    UP_RIGHT(1),
    RIGHT(2),
    DOWN_RIGHT(3),
    DOWN_LEFT(4),
    LEFT(5);

    private final int value;
    private Direction(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
