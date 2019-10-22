package signpost;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Puzzle {

    private final Signpost[][] signpostGrid;
    private final List<Signpost> signposts;
    private final int sideLength;

    public static void main(String[] args) throws IOException {
//        final String path = "C:\\Users\\lbcdn\\IdeaProjects\\127-personal-SybelBlue\\res\\puzzle.txt";
//        Puzzle p = Puzzle.createPuzzleFromTextFile(path);
        Puzzle p = new Puzzle(4,
                "1:DR Dn DL    Dn\n" +
                        "  DR Lt UR    Lt\n" +
                        "  UR UR Dn    Lt\n" +
                        "  Rt Lt UR 16:F");
        p.solve();
        System.out.println(p.formattedPuzzle());
        System.out.println(p.getSolution());
    }

    public static Puzzle createPuzzleFromTextFile(String path) throws IOException {
        // got this lovely loop and reading snippet from stack overflow.
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));

        List<String> strings = new LinkedList<>();
        String st;
        while ((st = br.readLine()) != null) strings.add(st);

        return new Puzzle(strings.size(), String.join(" ", strings));
    }

    public static Signpost[][] extractSignposts(int n, String pstring) {
        Signpost[][] out = new Signpost[n][n];
        Scanner tokenizer = new Scanner(pstring).useDelimiter(Pattern.compile("\\s+"));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int index = i * n + j;
                String match = tokenizer.next();
                String[] halves = Pattern.compile(":").split(match);
                Signpost.Direction dir = Signpost.getDirection(halves[halves.length - 1]);
                Signpost s;
                if (halves.length > 1) {
                    s = new Signpost(Integer.parseInt(halves[0]), dir, i, j);
                } else {
                    s = new Signpost(dir , i, j);
                }
                out[i][j] = s;
            }
        }
        return out;
    }

    public Puzzle(int sideLength, String puzzle) {
        this.sideLength = sideLength;
        signpostGrid = extractSignposts(sideLength, puzzle);
        signposts = new ArrayList<>(sideLength * sideLength);
        Arrays.stream(signpostGrid).map(Arrays::stream).forEach(s -> s.forEach(signposts::add));

        signposts.forEach(this::generateEdges);
    }

    public String solve() {
        while (takeStep());
        return formattedPuzzle();
    }

    private void generateEdges(Signpost s) {
        if (s.DIR == Signpost.Direction.FINAL) return;
        Function<Integer, Integer> delX = Signpost.getXChange(s.DIR), delY = Signpost.getYChange(s.DIR);
        int x = delX.apply(s.x), y = delY.apply(s.y);
        while (inBounds(x) && inBounds(y)) {
            s.canConnectTo(signpostGrid[x][y]);
            x = delX.apply(x);
            y = delY.apply(y);
        }
    }

    private boolean inBounds(int i) {
        return 0 <= i && i < sideLength;
    }

    private boolean takeStep() {
        return signposts.stream().anyMatch(Signpost::checkForced);
    }

    public List<Signpost> finalChain() {
        return signpostGrid[0][0].getChain();
    }

    public String formattedPuzzle() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < signpostGrid.length; i++) {
            if (i > 0) sb.append('\n');
            for (int j = 0; j < signpostGrid.length; j++) {
                sb.append(String.format("%6s", signpostGrid[i][j].standardNotation() + " "));
            }
        }
        return sb.toString();
    }

    public String getSolution() {
        return String.join(" -> ", finalChain().stream().map(s -> s.x + "," + s.y).collect(Collectors.toList()));
    }

    @Override
    public String toString() {
        return "Puzzle{" +
                "signpostGrid=" + Arrays.toString(signpostGrid) +
                ", signposts=" + signposts +
                '}';
    }
}
