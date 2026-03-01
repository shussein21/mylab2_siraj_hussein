package snakebyte;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    private Snake snake;
    private Food food;
    private KeyController controller;

    public GamePanel() {
        snake = new Snake();
        food = new Food(snake);
        controller = new KeyController(snake, food, this);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);

        // Must add KeyListener before requesting focus
        addKeyListener(controller);
        requestFocusInWindow(); // ensures arrow keys work immediately
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw snake and food
        snake.draw(g);
        food.draw(g);

        // Draw score
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + controller.getScore(), 10, 20);

        // Draw Game Over
        if(controller.isGameOver()){
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("GAME OVER", WIDTH / 2 - 120, HEIGHT / 2);

            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Final Score: " + controller.getScore(), WIDTH / 2 - 60, HEIGHT / 2 + 40);
        }
    }
}