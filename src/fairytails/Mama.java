package fairytails;

public class Mama implements Bear {
    @Override
    public void eat(Porridge p) {
        System.out.println("To cold");
    }

    public void steal(Papa papa){
        eat(papa.losePorridge());
    }
}
