import java.util.Scanner;

public class Dragon extends Monster{
    Dragon(int sizeBoard) {
        super(sizeBoard);
    }

    @Override
    public boolean taskMonster(int difficultGame){
        System.out.println("ДРАКОН МАТЕМАТИКИ: Приготовься к самой сложной битве!");
        System.out.println("Реши мое уравнение, смертный!");

        int a = r.nextInt(5) + 2;
        int b = r.nextInt(10) + 5;
        int c = r.nextInt(8) + 3;
        int d = r.nextInt(12) + 4;

        int trueAns = (a * a + b) * c - d;

        System.out.println("Реши уравнение: (" + a + "^2 + " + b + ") * " + c + " - " + d + " = ?");
        System.out.println("Подсказка: сначала возведи в квадрат, потом сложи, умножь и вычти!");

        Scanner sc = new Scanner(System.in);
        int ans = sc.nextInt();

        if (trueAns == ans) {
            System.out.println("НЕВЕРОЯТНО! Ты одолел Дракона Математики!");
            System.out.println("Королевство спасено! Тебя будут помнить как величайшего героя!");
            return true;
        }

        System.out.println("Дракон побеждает! Его огонь сжигает твои надежды...");
        System.out.println("Тренируйся больше и возвращайся, когда станешь сильнее!");
        return false;
    }
}
