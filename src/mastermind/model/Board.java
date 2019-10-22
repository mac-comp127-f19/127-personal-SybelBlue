package mastermind.model;

import java.util.LinkedList;

public class Board extends LinkedList<Guess> {
    private final Row answer;
    public final int MAX_GUESSES;

    public Board(Row answer, int maxGuesses) {
        this.answer = answer;
        MAX_GUESSES = maxGuesses;
    }

    public Guess add(Row guessRow) {
        if (reachedLimit()) return null;
        Guess g = new Guess(guessRow, answer);
        super.add(g);
        return g;
    }

    public boolean reachedLimit() {
        return size() >= MAX_GUESSES;
    }

    public int remainingGuesses() {
        return MAX_GUESSES - size();
    }
}
