package snakebyte;

import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

public class Food {

    public static final int SIZE = Snake.SQUARE;

    private double x, y;
    private Color foodColor = Color.GREEN;
    private Snake snake;
    private Random random = new Random();

    public Food(Snake snake) {
        this.snake = snake;
        setLocation();
    }

    public void draw(Graphics g) {
        g.setColor(foodColor);
        g.fillRect((int) x, (int) y, SIZE, SIZE);
    }

    public double getX() { return x; }
    public double getY() { return y; }

    public void setLocation() {

        boolean valid = false;

        while (!valid) {

            int maxX = (GamePanel.WIDTH - SIZE) / SIZE;
            int maxY = (GamePanel.HEIGHT - SIZE) / SIZE;

            x = random.nextInt(maxX + 1) * SIZE;
            y = random.nextInt(maxY + 1) * SIZE;

            valid = true;

            for (int i = 0; i < snake.getBody().size(); i++) {

                Point p = snake.getBody().get(i);

                if (p.getX() == x && p.getY() == y) {
                    valid = false;
                }
            }
        }
    }
}