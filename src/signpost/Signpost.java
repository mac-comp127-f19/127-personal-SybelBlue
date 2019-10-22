package signpost;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Signpost extends Node {

    public static Direction getDirection(String code) {
        switch (code) {
            case "Rt":
                return Direction.RIGHT;
            case "Lt":
                return Direction.LEFT;
            case "Up":
                return Direction.UP;
            case "Dn":
                return Direction.DOWN;
            case "UL":
                return Direction.UP_LEFT;
            case "DL":
                return Direction.DOWN_LEFT;
            case "UR":
                return Direction.UP_RIGHT;
            case "DR":
                return Direction.DOWN_RIGHT;
            case "F":
                return Direction.FINAL;
        }
        throw new IllegalArgumentException(code + " not recognized");
    }

    public static String directionNotation(Direction d) {
        switch (d) {
            case UP:
                return "Up";
            case DOWN:
                return "Dn";
            case LEFT:
                return "Lt";
            case RIGHT:
                return "Rt";
            case UP_LEFT:
                return "UL";
            case UP_RIGHT:
                return "UR";
            case DOWN_LEFT:
                return "DL";
            case DOWN_RIGHT:
                return "DR";
            case FINAL:
                return "F";
        }
        throw new IllegalArgumentException(d + " not recognized");
    }

    public static Function<Integer, Integer> getXChange(Direction d) {
        switch (d) {
            case DOWN_RIGHT:
            case DOWN_LEFT:
            case DOWN:
                return i -> i + 1;
            case UP_LEFT:
            case UP_RIGHT:
            case UP:
                return i -> i - 1;
            default:
                return Function.identity();
        }
    }

    public static Function<Integer, Integer> getYChange(Direction d) {
        switch (d) {
            case UP_RIGHT:
            case DOWN_RIGHT:
            case RIGHT:
                return i -> i + 1;
            case UP_LEFT:
            case DOWN_LEFT:
            case LEFT:
                return i -> i - 1;
            default:
                return Function.identity();
        }
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT, FINAL
    }

    private Signpost next, previous;
    public final Direction DIR;
    public final int x, y;
    private int number;


    public Signpost(int number, Direction direction, int x, int y) {
        super();
        this.number = number;
        this.DIR = direction;
        this.x = x;
        this.y = y;
    }

    public Signpost(Direction direction, int x, int y) {
        this(-1, direction, x, y);
    }

    public boolean checkForced() {
        boolean changed = pruneIn() || pruneOut();

        if (next == null && outNumber() == 1) {
            makeCertainConnection(this, (Signpost) out.get(0));
            changed = true;
        }

        if (number < 0 && next != null && next.number > 0) {
            this.number = next.number - 1;
        }

        if (number != 1 && previous == null && inNumber() == 1) {
            makeCertainConnection((Signpost) in.get(0), this);
            changed = true;
        }

        if (number < 0 && previous != null && previous.number > 0) {
            this.number = previous.number + 1;
        }

        return changed;
    }

    private void setPrevious(Signpost signpost) {
        previous = signpost;
    }

    private void setNext(Signpost signpost) {
        next = signpost;
    }

    public boolean pruneIn() {
        int previousCount = in.size();
        in = in.stream().map(n -> (Signpost) n)
                .filter(s -> s.next == null && (s.number < 0 || number < 0 || number - 1 == s.number))
                .collect(Collectors.toList());
        return in.size() != previousCount;
    }

    public boolean pruneOut() {
        int previousCount = out.size();
        out = out.stream().map(n -> (Signpost) n)
                .filter(s -> s.previous == null && (s.number < 0 || number < 0 || number + 1 == s.number))
                .collect(Collectors.toList());
        return out.size() != previousCount;
    }

    public Signpost getNext() {
        return next;
    }

    public Signpost getPrevious() {
        return previous;
    }

    public List<Signpost> getChain() {
        return generateChain(this);
    }

    @Override
    public boolean addIn(Node n) {
        if (n instanceof Signpost) {
            return super.addIn(n);
        }
        throw new IllegalArgumentException(n + " is not of type Signpost");
    }

    @Override
    public boolean addOut(Node n) {
        if (n instanceof Signpost) {
            return super.addOut(n);
        }
        throw new IllegalArgumentException(n + " is not of type Signpost");
    }

    public static List<Signpost> generateChain(Signpost s) {
        if (s == null) return List.of();

        List<Signpost> chain = new LinkedList<>();
        Signpost current = s;
        while (current != null) {
            chain.add(0, current);
            current = current.getPrevious();
        }

        Stream.iterate(s.getNext(), Objects::nonNull, Signpost::getNext).forEach(chain::add);
        return chain;
    }

    public String standardNotation() {
        String dirStr = directionNotation(DIR);
        return number > 0? number + ":" + dirStr : dirStr;
    }

    public static void makeCertainConnection(Signpost from, Signpost to) {
        from.setNext(to);
        to.setPrevious(from);
    }

    @Override
    public String toString() {
        return "Signpost{" +
                "number=" + number +
                ", nextFound=" + (next!=null) +
                ", previousFound=" + (previous!=null) +
                ", direction=" + DIR.name() +
                ", x=" + x +
                ", y=" + y +
                "}";
    }
}
