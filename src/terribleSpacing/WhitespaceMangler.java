package terribleSpacing;

import java.util.List;
import java.util.Random;

public class WhitespaceMangler {

    private static final Random RNG = new Random();
    private static final List<String> WHITESPACE_STRINGS = List.of(" ", "\t", "\n");

    private List<String> lines;

    public WhitespaceMangler(String text) {
        this.lines = new Tokenizer(text, "\n").getAllTokens();
    }

    public void mangleALittle() {
        String line = pickRandomStringFrom(lines);
        replaceOldLine(line, mangleLine(line));
    }

    private void replaceOldLine(String oldLine, String newLine) {
        int oldPos = lines.indexOf(oldLine);
        lines.remove(oldPos);
        lines.add(oldPos, newLine);
    }

    public String getLines() {
        return Tokenizer.detokenize(lines, "\n");
    }

    public static String mangleLine(String line) {
        Tokenizer tokenizer = new Tokenizer(line);

        StringBuilder sb = new StringBuilder();
        while (tokenizer.hasNextToken()) {
            sb.append(tokenizer.nextToken());

            if (tokenizer.hasNextToken())
                sb.append(randomWhitespacePhrase());
        }

        return sb.toString();
    }

    private static String randomWhitespacePhrase() {
        StringBuilder sb = new StringBuilder();

        do
            sb.append(randomWhitespace());
        while (RNG.nextGaussian() < 0.7);

        return sb.toString();
    }

    private static String randomWhitespace() {
        return pickRandomStringFrom(WHITESPACE_STRINGS);
    }

    private static String pickRandomStringFrom(List<String> list) {
        return list.get(RNG.nextInt(list.size()));
    }

}
