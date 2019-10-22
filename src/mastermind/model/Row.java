package mastermind.model;

import java.util.Arrays;
import java.util.Random;

public class Row {
    private PegColor[] pegs;

    public Row(PegColor... pegs) {
        this.pegs = pegs;
    }

    public static Row getRandom(int n) {
        PegColor[] pegs = new PegColor[n];
        Random rng = new Random();

        for (int i = 0; i < pegs.length; i++)
            pegs[i] = PegColor.values()[rng.nextInt(PegColor.values().length)];

        return new Row(pegs);
    }

    public PegColor[] getPegs() {
        return pegs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Row)) return false;
        Row row = (Row) o;
        return Arrays.equals(pegs, row.getPegs());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(pegs);
    }

    @Override
    public String toString() {
        return Arrays.toString(pegs);
    }
}
