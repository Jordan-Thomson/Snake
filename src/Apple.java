import java.util.Random;

/**
 * Class to represent an apple/target
 */
public class Apple {

    private Plot position;

    /**
     * Constructor, co-ords are random generated
     */
    public Apple() {
        Random rnd = new Random();
        position = new Plot(rnd.nextInt(GamePanel.WIDTH/10)*10,rnd.nextInt(GamePanel.HEIGHT/10)*10);
    }


    public Plot getPosition() {
        return position;
    }
}
