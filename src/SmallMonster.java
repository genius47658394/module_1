import java.util.Scanner;

public class SmallMonster extends Monster{
    private String image = "\uD83D\uDC09";

    SmallMonster(int sizeBoard) {
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

        int x = r.nextInt(20);
        int y = r.nextInt(20);

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

    public boolean taskMonster() {
        return super.taskMonster(0);
    }
}
