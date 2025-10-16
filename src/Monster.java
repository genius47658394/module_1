import java.util.Random;
import java.util.Scanner;

public class Monster {
    private String image = "\uD83E\uDDDF\u200D";
    private int x, y;
    Random r = new Random();

    Monster(int sizeBoard){
        this.y = r.nextInt(sizeBoard - 1);
        this.x = r.nextInt(sizeBoard);
    }

    public String getImage() {
        return image;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public boolean conflictPerson(int perX, int perY){
        return perY - 1 == this.y && perX - 1 == this.x;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void move(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean moveCorrect(int newX, int newY, int boardSize) {
        return newX >= 0 && newX < boardSize &&
                newY >= 0 && newY < boardSize;
    }

    public boolean taskMonster(int difficultGame) {
        int x = 0, y = 0;
        Random r = new Random();

        switch (difficultGame) {
            case 0:
                x = r.nextInt(20);
                y = r.nextInt(20);
                break;
            case 1:
                x = r.nextInt(100);
                y = r.nextInt(100);
                break;
            case 2:
                x = r.nextInt(200);
                y = r.nextInt(200);
                break;
            case 3:
                x = r.nextInt(500);
                y = r.nextInt(500);
                break;
            case 4:
                x = r.nextInt(1000);
                y = r.nextInt(1000);
                break;
            case 5:
                x = r.nextInt(5000);
                y = r.nextInt(5000);
                break;
            default:
                System.out.println("Неизвестный ввод, попробуйте ещё раз");
                break;
        }

        int trueAnswer = x + y;
        System.out.println("Реши пример: " + x + " + " + y + " = ?");
        Scanner sc = new Scanner(System.in);
        int ans = sc.nextInt();
        if (trueAnswer == ans) {
            System.out.println("Верно! Ты победил монстра");
            return true;
        }
        System.out.println("Ты проиграл эту битву!");

        return false;
    }
}
