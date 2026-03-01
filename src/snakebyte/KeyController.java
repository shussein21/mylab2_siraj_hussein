package snakebyte;

import javax.swing.*;
import java.awt.event.*;

public class KeyController implements KeyListener {

    private Snake snake;
    private Food food;
    private GamePanel panel;
    private Timer timer;

    private boolean gamePaused = false;
    private boolean gameOver = false;

    private int score = 0;
    private static final int DELAY = 100;

    public KeyController(Snake snake, Food food, GamePanel panel){
        this.snake = snake;
        this.food = food;
        this.panel = panel;

        // Timer calls animate every DELAY milliseconds
        timer = new Timer(DELAY, this::animate);
        timer.start();
        snake.setMoving(true);
    }

    // Animates the snake
    private void animate(ActionEvent e){
        if(!gamePaused && !gameOver){
            snake.move();
            checkCollisions();
            panel.repaint();
        }
    }

    // Check all collisions
    private void checkCollisions(){
        checkSnakeFoodCollision();
        checkBoundaryCollision();
        checkSnakeSelfCollision();
    }

    // Snake eats food
    private void checkSnakeFoodCollision(){
        if(Math.abs(snake.getX() - food.getX()) < Snake.SQUARE &&
                Math.abs(snake.getY() - food.getY()) < Snake.SQUARE){
            snake.grow();
            food.setLocation();
            score++;
        }
    }

    // Snake hits walls
    private void checkBoundaryCollision(){
        Point head = snake.getHeadLocation();
        if(head.getX() < 0 || head.getX() >= GamePanel.WIDTH ||
                head.getY() < 0 || head.getY() >= GamePanel.HEIGHT){
            endGame();
        }
    }

    // Snake hits itself
    private void checkSnakeSelfCollision(){
        Point head = snake.getHeadLocation();
        for(int i=1; i<snake.getBody().size(); i++){
            Point p = snake.getBody().get(i);
            if(head.getX() == p.getX() && head.getY() == p.getY()){
                endGame();
            }
        }
    }

    private void endGame(){
        gameOver = true;
        timer.stop();
        snake.setMoving(false);
    }

    public int getScore(){ return score; }
    public boolean isGameOver(){ return gameOver; }

    @Override
    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP -> turnUp();
            case KeyEvent.VK_DOWN -> turnDown();
            case KeyEvent.VK_LEFT -> turnLeft();
            case KeyEvent.VK_RIGHT -> turnRight();
            case KeyEvent.VK_SPACE -> toggleGamePause();
            default -> {}
        }
    }

    // Turn snake methods
    private void turnUp(){ if(snake.getDirection()!=Direction.DOWN) snake.setDirection(Direction.UP); }
    private void turnDown(){ if(snake.getDirection()!=Direction.UP) snake.setDirection(Direction.DOWN); }
    private void turnLeft(){ if(snake.getDirection()!=Direction.RIGHT) snake.setDirection(Direction.LEFT); }
    private void turnRight(){ if(snake.getDirection()!=Direction.LEFT) snake.setDirection(Direction.RIGHT); }

    // Pause/resume game
    private void toggleGamePause(){
        if(gamePaused){
            timer.start();
            gamePaused = false;
            snake.setMoving(true);
        } else {
            timer.stop();
            gamePaused = true;
            snake.setMoving(false);
        }
    }

    @Override
    public void keyReleased(KeyEvent e){}
    @Override
    public void keyTyped(KeyEvent e){}
}