/**
 * Class to represent a part of the snakes body
 */
public class SnakeBody {

    private Plot position;

    /**
     * Constructor for the bodypart
     * @param plot Position of the body part
     */
    public SnakeBody(Plot plot) {
        position = plot;
    }

    public Plot getPosition() {
        return position;
    }

    /**
     * Method to get a copy of the position rather than a reference
     * @return new Position with the same co-ords as this position.
     */
    public Plot copyPosition() {
        return new Plot(this.getPosition().getX(),this.getPosition().getY());
    }

    public void updatePosition(Plot newPosition) {
        position = newPosition;
    }

}
