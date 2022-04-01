import java.io.IOException;
import java.util.ArrayList;

public class Window {
    private final int YAXIS = 24;
    private final int XAXIS = (int) (YAXIS*2.5);
    private final char BORDERH = '═';
    private final char BORDERV = '║';
    private final char TRC = '╔';
    private final char TLC = '╗';
    private final char BRC = '╝';
    private final char BLC = '╚';
    private int foodX;
    private int foodY;

    private char[][] field = new char[YAXIS][XAXIS];

    public Window() {
        for (int y = 1; y < YAXIS-1; y++) {
            for (int x = 1; x < XAXIS-1; x++) {
                field[y][x] = ' ';
            }
        }
    }

    public void putFood(Integer[] food) {
        this.foodX = food[0];
        this.foodY = food[1];
    }

    public void food() {
        this.field[this.foodY][this.foodX] = '0';
    }

    public void drawWindow(Snake snake) {
        field[0][0] = TRC;
        field[0][XAXIS-1] = TLC;
        field[YAXIS-1][0] = BLC;
        field[YAXIS-1][XAXIS-1] = BRC;

        for (int x = 1; x < XAXIS-1; x++) {
            field[0][x] = BORDERH;
            field[YAXIS-1][x] = BORDERH;
        }
        for (int y = 1; y < YAXIS-1; y++) {
            field[y][0] = BORDERV;
            field[y][XAXIS-1] = BORDERV;
        }
        for (int y = 1; y < YAXIS-1; y++) {
            for (int x = 1; x < XAXIS-1; x++) {
                field[y][x] = ' ';
            }
        }

        for (int i = 0; i < snake.getBody().size(); i++) {
            field[snake.getBody().get(i)[1]][snake.getBody().get(i)[0]] = 'o';
        }

        this.food();

        for (int y = 0; y < YAXIS; y++) {
            for (int x = 0; x < XAXIS; x++) {
                System.out.print(field[y][x]);
            }
            System.out.println();
        }
    }

    public void cleanWindow() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public int getYAXIS() {
        return YAXIS;
    }

    public int getXAXIS() {
        return XAXIS;
    }

    public ArrayList<Integer[]> getEmpty() {

        ArrayList<Integer[]> empty = new ArrayList<>();

        for (int y = 0; y < YAXIS; y++) {
            for (int x = 0; x < XAXIS; x++) {
                if (field[y][x] == ' ') {
                    empty.add(new Integer[] {x,y});
                }
            }
        }
        return empty;
    }
}
