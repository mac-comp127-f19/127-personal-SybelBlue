package mastermind.model;

import java.util.Arrays;

public class Guess {
    private final Row guess;
    public final int WHITE_PEGS, BLACK_PEGS;
    public final boolean CORRECT;

    public Guess(Row guess, Row answer) {
        if (guess.getPegs().length != answer.getPegs().length) {
            throw new IllegalArgumentException("Guess not same size as answer");
        }

        this.guess = guess;
        BLACK_PEGS = getBlackPegs(guess, answer);
        WHITE_PEGS = getWhitePegs(guess, answer);

        CORRECT = WHITE_PEGS == guess.getPegs().length;
    }

    public static int getBlackPegs(Row guess, Row answer) {
        return (int) Arrays.stream(guess.getPegs()).filter(Arrays.asList(answer.getPegs())::contains).count()
                - getWhitePegs(guess, answer);
    }

    public static int getWhitePegs(Row guess, Row answer) {
        int count = 0;

        for (int i = 0; i < guess.getPegs().length; i++)
            if (guess.getPegs()[i] == answer.getPegs()[i])
                count++;

        return count;
    }

    public String responseString() {
        return multiplyString("•  ", WHITE_PEGS) + multiplyString("◦  ", BLACK_PEGS);
    }


    /**
     * https://stackoverflow.com/questions/2255500/can-i-multiply-strings-in-java-to-repeat-sequences
     * @param s
     * @param n
     * @return
     */
    public static String multiplyString(String s, int n) {
        return new String(new char[n]).replace("\0", s);
    }
}
