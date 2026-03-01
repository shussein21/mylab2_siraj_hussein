package snakebyte;

import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Snake {

    public static final int SQUARE = 8;
    public static final int STARTING_LENGTH = 10;
    public static final int START_X_LOCATION = 300;
    public static final int START_Y_LOCATION = 300;

    private Direction direction;
    private boolean isMoving = false;
    private List<Point> snakeBody;

    // Constructor
    public Snake() {
        direction = Direction.RIGHT;
        snakeBody = make();
    }

    // Accessors
    public List<Point> getBody() { return snakeBody; }
    public Point getHeadLocation() { return snakeBody.get(0); }
    public Point getTailLocation() { return snakeBody.get(snakeBody.size() - 1); }
    public double getX() { return getHeadLocation().getX(); }
    public double getY() { return getHeadLocation().getY(); }
    public Direction getDirection() { return direction; }

    // Mutators
    public void setMoving(boolean moving) { isMoving = moving; }
    public void setDirection(Direction dir) { this.direction = dir; }

    // Draw snake
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        for (Point p : snakeBody) {
            g.fillRect((int)p.getX(), (int)p.getY(), SQUARE, SQUARE);
            g.setColor(Color.BLACK);
            g.drawRect((int)p.getX(), (int)p.getY(), SQUARE, SQUARE); // gives "stripes"/divisions
            g.setColor(Color.WHITE);
        }
    }

    // Grow snake by adding one block at tail
    public void grow() {
        Point tail = getTailLocation();
        snakeBody.add(new Point(tail.getX(), tail.getY()));
    }

    // Initialize snake body
    public List<Point> make() {
        List<Point> body = new ArrayList<>();
        for (int i = 0; i < STARTING_LENGTH; i++) {
            body.add(new Point(START_X_LOCATION - (i * SQUARE), START_Y_LOCATION));
        }
        return body;
    }

    // Move snake
    public void move() {
        if (!isMoving) return;

        Point head = getHeadLocation();
        Point newHead = new Point(
                head.getX() + direction.getX() * SQUARE,
                head.getY() + direction.getY() * SQUARE
        );

        // Add new head to start of list
        snakeBody.add(0, newHead);

        // Remove last tail segment
        snakeBody.remove(snakeBody.size() - 1);
    }
}