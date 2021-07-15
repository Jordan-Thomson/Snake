import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class to represent the snake in the game
 */
public class Snake {

    private ArrayList<SnakeBody> body;
    private String lastDirection = "";

    /**
     * Constructor
     * Creates the position of the head, co-ords are random generated
     */
    public Snake() {
        body = new ArrayList<>();
        Random rnd = new Random();
        Plot plot = new Plot(rnd.nextInt(GamePanel.WIDTH/10)*10,rnd.nextInt(GamePanel.HEIGHT/10)*10);
        body.add(new SnakeBody(plot));
    }

    /**
     * Update the position of the head and call recursively on the rest of the body if the movement
     * direction is upwards
     */
    public void up() {
        Plot pos = body.get(0).copyPosition();
        body.get(0).getPosition().up();
        //shuffle(pos, 1);
        swap(pos);
        lastDirection = "up";
    }

    /**
     * Update the position of the head and call recursively on the rest of the body if the movement
     * direction is rightwards
     */
    public void right() {
        Plot pos = body.get(0).copyPosition();
        body.get(0).getPosition().right();
        //shuffle(pos, 1);
        swap(pos);
        lastDirection = "right";
    }

    /**
     * Update the position of the head and call recursively on the rest of the body if the movement
     * direction is leftwards
     */
    public void left() {
        Plot pos = body.get(0).copyPosition();
        body.get(0).getPosition().left();
        //shuffle(pos, 1);
        swap(pos);
        lastDirection = "left";
    }

    /**
     * Update the position of the head and call recursively on the rest of the body if the movement
     * direction is downwards
     */
    public void down() {
        Plot pos = body.get(0).copyPosition();
        body.get(0).getPosition().down();
        //shuffle(pos, 1);
        swap(pos);
        lastDirection = "down";
    }

    /**
     * Recursive method to update all body parts of the snake ** O(n) efficiency
     * Copies the current position, updates it recursively calls itself with the copied current
     * position to the next body part
     * @param newPos the new position to be set to the current body part
     * @param bodyPlace which part of the body we are updating
     */
    private void shuffle(Plot newPos, int bodyPlace) {
        if (bodyPlace >= body.size()) {
            return;
        }
        Plot pos = body.get(bodyPlace).copyPosition();
        body.get(bodyPlace).updatePosition(newPos);
        shuffle(pos,bodyPlace+1);
    }

    /**
     * Moves the tail element of the body to after the head ** O(1) efficiency
     * @param newPos the position to move the tail element to
     */
    private void swap(Plot newPos) {
        if (body.size() > 1) {
            SnakeBody last = body.remove(body.size() - 1);
            last.updatePosition(newPos);
            body.add(1, last);
        }
    }

    /**
     * Adds another bodypart to the snake
     */
    public void addToTail() {
        // If the snake is only a head use current movement direction to determine where to place
        if (body.size() == 1) {
            switch (lastDirection) {
                case "up":
                    body.add(new SnakeBody(new Plot(body.get(0).getPosition().getX(),body.get(0).getPosition().getY()+10)));
                    break;
                case "down":
                    body.add(new SnakeBody(new Plot(body.get(0).getPosition().getX(),body.get(0).getPosition().getY()-10)));
                    break;
                case "right":
                    body.add(new SnakeBody(new Plot(body.get(0).getPosition().getX()-10,body.get(0).getPosition().getY())));
                    break;
                case "left":
                    body.add(new SnakeBody(new Plot(body.get(0).getPosition().getX()+10,body.get(0).getPosition().getY())));
                    break;
            }
        }
        // otherwise use the relative positions of the last two bodyparts to determine position of new bodypart
        else {
            Plot last = body.get(body.size()-1).getPosition();
            Plot secondLast = body.get(body.size()-2).getPosition();
            if (last.getX() < secondLast.getX()) {
                body.add(new SnakeBody(new Plot(body.get(body.size()-1).getPosition().getX()-10,body.get(body.size()-1).getPosition().getY())));
            } else if (last.getX() > secondLast.getX()) {
                body.add(new SnakeBody(new Plot(body.get(body.size()-1).getPosition().getX()+10,body.get(body.size()-1).getPosition().getY())));
            } else if (last.getY() < secondLast.getY()) {
                body.add(new SnakeBody(new Plot(body.get(body.size()-1).getPosition().getX(),body.get(body.size()-1).getPosition().getY()-10)));
            } else if (last.getY() > secondLast.getY()) {
                body.add(new SnakeBody(new Plot(body.get(body.size()-1).getPosition().getX(),body.get(body.size()-1).getPosition().getY()+10)));
            }
        }
    }

    public Plot getHeadPosition() {
        return body.get(0).getPosition();
    }

    /**
     * Method to determine if the head has came into contact with another part of the body
     * @return true if the head has the same co-ords as any other bodypart
     */
    public boolean hitSelf() {
        Plot head = getHeadPosition();
        for(int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i).getPosition())) {
                return true;
            }
        }
        return false;
    }

    public List<SnakeBody> snakeBodyList() {
        return body;
    }
}
