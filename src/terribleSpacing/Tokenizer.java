package terribleSpacing;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tokenizer {

    private Scanner parser;

    public Tokenizer(String text) {
        this(text, "\\s+");
    }

    public Tokenizer(String text, String splitOn) {
        parser = new Scanner(text).useDelimiter(splitOn);
    }

    public String nextToken() {
        return hasNextToken()? parser.next(): null;
    }

    public boolean hasNextToken() {
        return parser.hasNext();
    }

    public List<String> getAllTokens() {
        List<String> out = new LinkedList<>();
        while (hasNextToken()) out.add(nextToken());
        return out;
    }

    public static String detokenize(List<String> tokens, String inBetween) {
        return String.join(inBetween, tokens);
    }
}
