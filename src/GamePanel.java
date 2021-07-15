import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Draws the current game state and uses ActionListener to get directional input
 */
public class GamePanel extends JPanel implements ActionListener {

    public static final int WIDTH = 300; // keep to multiples of 10
    public static final int HEIGHT = 300; // keep to multiples of 10
    private Snake snake;
    private Apple apple;
    private boolean gameOver = false;
    private int pressed;

    /**
     * Constructor
     * Creates and defines the panel
     */
    public GamePanel() {
        addKeyListener(new MyAdapter());
        setBackground(Color.BLACK);
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        pressed = 0;
        snake = new Snake();
        apple = new Apple();
        Timer timer = new Timer(140,this);
        timer.start();
    }

    /**
     * Custom paintComponent, draw the location of all elements in play
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!gameOver) {
            //draw the apple
            g.setColor(Color.GREEN);
            g.drawOval(apple.getPosition().getX(), apple.getPosition().getY(), 10, 10);

            //draw the snake
            g.setColor(Color.BLUE);
            for (SnakeBody sBody : snake.snakeBodyList()) {
                g.drawRect(sBody.getPosition().getX(), sBody.getPosition().getY(), 10, 10);
            }

            Toolkit.getDefaultToolkit().sync();
        }
        else {
            g.setColor(Color.white);
            g.drawString("Game Over",WIDTH/2,HEIGHT/2);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            checkApple();
            checkFail();
            act();
        }
        repaint();
    }

    /**
     * Using last key pressed, updates the co-ords of the snake using the last direction pressed
     */
    private void act() {
        switch (pressed) {
            case KeyEvent.VK_UP:
                snake.up();
                break;
            case KeyEvent.VK_DOWN:
                snake.down();
                break;
            case KeyEvent.VK_RIGHT:
                snake.right();
                break;
            case KeyEvent.VK_LEFT:
                snake.left();
                break;
        }
    }

    /**
     * Identifies any failure conditions, snake hitting walls or self
     */
    private void checkFail() {
        if (snake.hitSelf()) {
            gameOver = true;
        }
        if (snake.getHeadPosition().getX() < 0 || snake.getHeadPosition().getX() > WIDTH) {
            gameOver = true;
        }
        if (snake.getHeadPosition().getY() < 0 || snake.getHeadPosition().getY() > HEIGHT) {
            gameOver = true;
        }
    }

    /**
     * Has the snake head reached the apple?
     */
    private void checkApple() {
        if (snake.getHeadPosition().equals(apple.getPosition())) {
            snake.addToTail();
            apple = new Apple();
        }
    }

    /**
     * Inner class to get the last key pressed
     */
    private class MyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            pressed = e.getKeyCode();
        }

    }
}
