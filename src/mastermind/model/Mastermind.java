package mastermind.model;

import java.util.Arrays;
import java.util.Scanner;

public class Mastermind {
    private final Row ANSWER;
    private final Board BOARD;
    private final int LENGTH;

    public Mastermind(Row answer, int maxGuesses) {
        BOARD = new Board(answer, maxGuesses);
        ANSWER = answer;
        LENGTH = ANSWER.getPegs().length;
    }

    public Mastermind() {
        this(Row.getRandom(4), 12);
    }

    public static void main(String[] args) {
        new Mastermind().run();
    }

    private void run() {
        Scanner input = new Scanner(System.in);
        Guess guess;
        do {
            if (BOARD.reachedLimit()) {
                System.out.println("YOU LOST!");
                System.out.println("Answer: " + Arrays.toString(ANSWER.getPegs()));
                return;
            }
            guess = BOARD.add(getInputUserRow(input));
            System.out.println(guess.responseString());
        } while (!guess.CORRECT);
        System.out.println("YOU WON! (With " + BOARD.remainingGuesses() + " guess(es) to spare.)");
    }

    private Row getInputUserRow(Scanner input) {
        System.out.println("Enter " + LENGTH + " (R/G/B/Y/P) separated by enters below to guess:");
        PegColor[] pegs = new PegColor[LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            pegs[i] = getPegColor(input.next("[RGBYPrgbyp]").toUpperCase());
        }
        return new Row(pegs);
    }

    private static PegColor getPegColor(String in) {
        switch (in) {
            case "R":
                return PegColor.RED;
            case "B":
                return PegColor.BLUE;
            case "Y":
                return PegColor.YELLOW;
            case "P":
                return PegColor.PURPLE;
            case "G":
                return PegColor.GREEN;
        }
        throw new IllegalArgumentException("Unrecognized peg-code");
    }
}
