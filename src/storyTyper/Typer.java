package storyTyper;

public class Typer {

    public static void main(String[] args) throws InterruptedException {
        new Typer(80, 100, 150).write("Hello world!\nLet's try this again. How shall we do it? I don't know...");
    }

    private final int FAST, MEDIUM, SHORT;

    public Typer(int slow, int medium, int aLong) {
        FAST = slow;
        MEDIUM = medium;
        SHORT = aLong;
    }

    public void write(String s) throws InterruptedException {
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);

            System.out.print(c);

            Thread.sleep(getTimeout(c));
        }
    }

    private int getTimeout(Character c) {
        switch(c){
            case '\n':
            case '?':
            case ':':
            case '.':
            case '!':
                return SHORT;
            case ',':
            case ';':
                return MEDIUM;
            default:
                return FAST;
        }
    }
}
