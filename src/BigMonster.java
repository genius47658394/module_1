import java.util.Scanner;

public class BigMonster extends Monster{

    private String image = "\uD83D\uDC79";

    BigMonster(int sizeBoard) {
        super(sizeBoard);
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean taskMonster(int difficultGame){
        System.out.println("Решите задачу:");
        if (difficultGame == 1){
            return taskMonster();
        }else {
            int x = r.nextInt(10 * (difficultGame - 1), 10 * difficultGame);
            int y = r.nextInt(10 * (difficultGame - 1), 10 * difficultGame);
            int z = r.nextInt(100 * (difficultGame - 1), 100 * difficultGame);
            int trueAnswer = x * y - z;
            System.out.println("Реши пример: " + x + " * " + y + " - " + z + " = ?");
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

    public boolean taskMonster() {
        return super.taskMonster(1);
    }
}