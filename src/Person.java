import java.util.Random;

public class Person {
    int x, y;
    String image = "\uD83E\uDDD9\u200D";
    int live = 3;
    Random r = new Random();

    Person(int sizeBoard) {
        y = sizeBoard;
        int n = r.nextInt(sizeBoard);
        x = n == 0 ? 1 : n;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setLive(int live) {
        this.live = live;
    }

    public int getX(){
        return x;
    }
    public int getY() {
        return y;
    }
    public int getLive() {
        return live;
    }
    public String getImage(){
        return image;
    }

    public void move(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean moveCorrect(int x, int y){
        return this.x == x && Math.abs(this.y - y) == 1 || this.y == y && Math.abs(this.x - x) == 1;
    }

    public void upLive() {
        this.live++;
    }

    public void downLive() {
        if (live < 0)
            live = 0;
        else
            live--;
    }
}
