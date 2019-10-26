package fairytails;

public class Papa implements Bear {
    private Porridge porridge;

    public Papa(Porridge porridge) {
        this.porridge = porridge;
    }

    @Override
    public void eat(Porridge p) {
        System.out.println("Too hot");
    }

    public Porridge losePorridge() {
        return porridge;
    }
}
