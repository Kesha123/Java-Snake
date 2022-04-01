import java.util.ArrayList;

public class Food {

    private int x;
    private int y;

    public Food(Window window) {
        this.spawn(window);
    }

    public void spawn(Window window) {
        ArrayList<Integer[]> empty = window.getEmpty();
        Integer[] cell = empty.get( (int) (Math.random() * empty.size()) );
        this.x = cell[0];
        this.y = cell[1];
        window.putFood(new Integer[]{this.x, this.y});
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
