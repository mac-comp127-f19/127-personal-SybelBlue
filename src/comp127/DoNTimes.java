package comp127;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DoNTimes {

    public static void main(String[] args) {
        printStream(IntStream.range(0, 10));
        printStream(IntStream.iterate(1, i -> i < 256, i -> i * 2));
        printStream(IntStream.iterate(1, i -> i < 256, i -> i * 2).map(i -> i - 1));
        IntStream.iterate(1, i -> i < 51, i -> i + 2).mapToDouble(Math::sqrt)
                .mapToObj(d -> d + " ").forEach(System.out::print);
    }

    private static void printStream(IntStream stream) {
        stream.mapToObj(i -> i + " ").forEach(System.out::print);
        System.out.println();
    }
}
