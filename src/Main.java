import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int correctAnswers = 0;
        int totalAnswers = 0;

        Random r = new Random();

        String castle = "\uD83C\uDFF0";
        int sizeBoard = 5;

        Person person = new Person(sizeBoard);

        int step = 0;

        String[][] board = new String[sizeBoard][sizeBoard];
        for (int y = 0; y < sizeBoard; y++) {
            for (int x = 0; x < sizeBoard; x++) {
                board[y][x] = "  ";
            }
        }

        int countMonster = sizeBoard * sizeBoard - sizeBoard - 13;

        Monster[] arrMonster = new Monster[countMonster + 1];
        int count = 0;
        Monster test;
        while (count <= countMonster){
            if (r.nextBoolean()) {
                test = new Monster(sizeBoard);
            }else {
                if (r.nextBoolean()){
                    test = new BigMonster(sizeBoard);
                }
                else {
                    test = new SmallMonster(sizeBoard);
                }
            }
            if (board[test.getY()][test.getX()].equals("  ")){
                board[test.getY()][test.getX()] = test.getImage();
                arrMonster[count] = test;
                count++;
            }
        }
        count = 0;

        int castleX = r.nextInt(sizeBoard);
        int castleY = 0;

        board[castleY][castleX] = castle;

        String heart = "❤";

        while (count < 3){
            int heartX = r.nextInt(sizeBoard);
            int heartY = r.nextInt(sizeBoard);

            if (board[heartY][heartX].equals("  ")){
                board[heartY][heartX] = heart;
                count++;
            }
        }

        System.out.println("Привет! Ты готов начать играть в игру? (Напиши: ДА или НЕТ)");

        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        System.out.println("Ваш ответ:\t" + answer);

        switch (answer) {
            case "ДА" -> {
                System.out.println("Выбери сложность игры(от 1 до 5):");
                int difficultGame = sc.nextInt();
                System.out.println("Выбранная сложность:\t" + difficultGame);
                while (true) {
                    board[person.getY() - 1][person.getX() - 1] = person.getImage();
                    outputBoard(board, person.getLive());
                    System.out.println("Введите куда будет ходить персонаж(ход возможен только по вертикали и горизонтали на одну клетку;" +
                            "\nКоординаты персонажа - (x: " + person.getX() + ", y: " + person.getY() + "))");
                    int x = sc.nextInt();
                    int y = sc.nextInt();

                    if (person.moveCorrect(x, y)) {
                        String next = board[y - 1][x - 1];
                        if (next.equals("  ")) {
                            board[person.getY() - 1][person.getX() - 1] = "  ";
                            person.move(x, y);
                            step++;
                            System.out.println("Ход корректный; Новые координаты: " + person.getX() + ", " + person.getY() +
                                    "\nХод номер: " + step);
                        }
                        else if (next.equals(castle)) {
                            Dragon dragon = new Dragon(sizeBoard);
                            dragon.taskMonster(difficultGame);
                            showStats(correctAnswers, totalAnswers);
                            break;

                        } else if (next.equals("❤")){
                            person.upLive();
                            board[person.getY() - 1][person.getX() - 1] = "  ";
                            person.move(x, y);
                            step++;
                        }
                        else {
                            for (Monster monster : arrMonster) {
                                if (monster.conflictPerson(x, y)) {
                                    if (monster.taskMonster(difficultGame)) {
                                        board[person.getY() - 1][person.getX() - 1] = "  ";
                                        person.move(x, y);
                                        correctAnswers++;
                                        totalAnswers++;
                                    } else {
                                        person.downLive();
                                        totalAnswers++;
                                    }
                                    break;
                                }
                            }
                        }

                        moveAllMonsters(board, arrMonster, sizeBoard, difficultGame);

                    } else {
                        System.out.println("Некорректный ход");
                    }
                }
            }
            case "НЕТ" -> System.out.println("Жаль, приходи еще!");
            default -> System.out.println("Данные введены некорректно");
        }
    }

    static void moveAllMonsters(String[][] board, Monster[] monsters, int boardSize, int difficultGame) {
        Random r = new Random();

        for (int i = 0; i < monsters.length; i++) {
            if (monsters[i] == null) continue;

            int oldX = monsters[i].getX();
            int oldY = monsters[i].getY();

            int way = r.nextInt(4);
            int newX = oldX;
            int newY = oldY;

            switch (way) {
                case 0:
                    newY = oldY - 1;
                    break;
                case 1:
                    newY = oldY + 1;
                    break;
                case 2:
                    newX = oldX - 1;
                    break;
                case 3:
                    newX = oldX + 1;
                    break;
            }

            if (monsters[i].moveCorrect(newX, newY, boardSize) &&
                    board[newY][newX].equals("  ")) {

                board[oldY][oldX] = "  ";

                monsters[i].move(newX, newY);

                board[newY][newX] = monsters[i].getImage();
            }
            else if (monsters[i].moveCorrect(newX, newY, boardSize) &&
                    board[newY][newX].equals("\uD83E\uDDD9\u200D")) {
                monsters[i].taskMonster(difficultGame);
            }
        }
    }

    static void outputBoard(String[][] board, int live) {
        String leftBlock = "| ";
        String rightBlock = "|";
        String wall = "+ —— + —— + —— + —— + —— +";

        for (String[] raw : board) {
            System.out.println(wall);
            for (String col : raw) {
                System.out.print(leftBlock + col + " ");
            }
            System.out.println(rightBlock);
        }
        System.out.println(wall);

        System.out.println("Количество жизней:\t" + live + "\n");
    }

    static void showStats(int correctAnswers, int totalAnswers) {
        double successRate = (double) correctAnswers / totalAnswers * 100;

        System.out.println("=== СТАТИСТИКА ИГРЫ ===");
        System.out.println("Побеждено монстров: " + correctAnswers);
        System.out.println("Точность ответов: " + String.format("%.1f", successRate) + "%");
//        System.out.println("Лучшее время: " + bestTime + " сек"); // maybe in future...
    }
}