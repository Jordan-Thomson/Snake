/**
 * Class Plot:
 * Defines a co-ord position (x,y)
 */

public class Plot {

    private int x;
    private int y;

    /**
     * Constructor
     * @param x x co-ordinate
     * @param y y co-ordinate
     */
    public Plot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Mutators to move the position
    public void up() {
        y-=10;
    }

    public void right() {
        x+=10;
    }

    public void left() {
        x-=10;
    }

    public void down() {
        y+=10;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Override equals method to check x and y co-ords
     * @param o Object to be checked for equality
     * @return true if the objects have the same x,y co-ords
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plot plot = (Plot) o;

        if (getX() != plot.getX()) return false;
        return getY() == plot.getY();
    }
}
