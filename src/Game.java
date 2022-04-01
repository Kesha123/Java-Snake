import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Game {

    public static void gameOver(Window window, Snake snake) {
        window.cleanWindow();
        System.out.println("\tGame over!");
        System.out.println("\tTotal score: " + snake.getLength());
        System.exit(0);
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        Window window = new Window();
        Snake snake = new Snake(window.getXAXIS()/2, window.getYAXIS()/2);
        Food food = new Food(window);
        boolean run = true;

        JFrame jFrame = new JFrame();
        jFrame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_UP) { snake.changeDirection("UP");}
                else if (event.getKeyCode() == KeyEvent.VK_DOWN) { snake.changeDirection("DOWN");}
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT) { snake.changeDirection("RIGHT");}
                else if (event.getKeyCode() == KeyEvent.VK_LEFT) { snake.changeDirection("LEFT");}
            }
        });
        jFrame.setVisible(true);

        while (run) {

            if (!snake.isAlive()){
                gameOver(window, snake);
                break;
            }

            if (snake.getHead()[0] != 0 && snake.getHead()[0] != window.getXAXIS() && snake.getHead()[1] != 0 && window.getYAXIS() != snake.getHead()[1]) {
                window.drawWindow(snake);
            }
            else {
                gameOver(window, snake);
                break;
            }

            if (snake.getHead()[0] == food.getX() && snake.getHead()[1] == food.getY()) {
                snake.eat(1,food.getX(), food.getY());
                food.spawn(window);
            }

            switch (snake.getDirection()) {
                case "UP" -> snake.move(snake.getHead()[0], snake.getHead()[1] - 1);
                case "DOWN" -> snake.move(snake.getHead()[0], snake.getHead()[1] + 1);
                case "RIGHT" -> snake.move(snake.getHead()[0] + 1, snake.getHead()[1]);
                case "LEFT" -> snake.move(snake.getHead()[0] - 1, snake.getHead()[1]);
            }

            TimeUnit.MILLISECONDS.sleep(55);
            window.cleanWindow();
        }
    }
}
