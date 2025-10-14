import java.util.Scanner;

public class Dragon extends Monster{
    Dragon(int sizeBoard) {
        super(sizeBoard);
    }

    @Override
    public boolean taskMonster(int difficultGame){
        System.out.println("В замке ты встретился с драконом, реши последнюю задачу, чтобы победить его и освободить королевство");

        int x = r.nextInt(10);
        int y = r.nextInt(100);
        int z = r.nextInt(100);
        int s = r.nextInt(3);

        int trueAns = Math.powExact(x, s) + y - z;

        System.out.println("Пример: " + x + " ^ " + s + " + " + y + " - " + z);

        Scanner sc = new Scanner(System.in);
        int ans = sc.nextInt();

        if (trueAns == ans) {
            System.out.println("Молодец, ты одолел главного босса, теперь королевство снова заживёт хорошей жизнью!");
            return true;
        }
        System.out.println("Ты проиграл эту битву! Возвращайся как станешь сильнее...");
        return false;
    }
}
