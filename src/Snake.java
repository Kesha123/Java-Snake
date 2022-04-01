import java.awt.event.KeyEvent;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Snake {
    private String direction = "UP";
    private int length = 1;
    private ArrayList<Integer[]> body = new ArrayList<Integer[]>();
    private boolean alive = true;

    private Instant first;
    private Instant last;

    public Snake(int spawnX, int spawnY) {
        Integer[] head = {spawnX, spawnY};
        body.add(0, head);
        this.first = Instant.now();
    }

    public void move(int x, int y) {

        for (int i = 0; i < body.size(); i++) {
            if (x == body.get(i)[0] && y == body.get(i)[1]) {
                alive = false;
            }
        }

        last = Instant.now();

        if (Duration.between(first, last).toMillis() >= 500) {
            body.add(0, new Integer[] {x,y});
            body.remove(body.size()-1);
            first = Instant.now();
        }
    }

    public void eat(int length, int foodX, int foodY) {
        this.length += length;
        body.add(0, new Integer[]{foodX,foodY});
    }

    public void changeDirection(String direction) {
        this.direction = direction;
    }
    public String getDirection() { return this.direction; }
    public Integer[] getHead() { return body.get(0).clone(); }
    public ArrayList<Integer[]> getBody() { return body; }

    public int getLength() {
        return length;
    }

    public boolean isAlive() {
        return alive;
    }
}
