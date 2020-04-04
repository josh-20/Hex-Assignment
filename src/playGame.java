import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class playGame {
    public static String ANSI_Red = "\u001B[31m";
    public static String ANSI_RESET = "\u001B[30m";
    public static String ANSI_BLUE = "\u001B[34m";
    static final int boardSize = 126;

    public void newGame(String file1) throws FileNotFoundException {
        HexGame hg = new HexGame();
        DisJointSet dj = new DisJointSet(boardSize);
        String [] colorGame = new String[boardSize];
        Arrays.fill(colorGame, "0");
        File file = new File(file1);
        int redMoves = 0; int blueMoves = 0; int count = 1;
        Scanner moves = new Scanner(file);
        while (moves.hasNextInt()) {

            if (dj.find(124) == dj.find(125)) break;
            if (dj.find(122) == dj.find(123)) break;

            if (count % 2 == 0) {
                redMoves++;
                hg.playHexGame(moves.nextInt(), dj, colorGame, ANSI_Red + "R" + ANSI_RESET, redMoves);
                count++;
            } else {
                blueMoves++;
                hg.playHexGame(moves.nextInt(), dj, colorGame, ANSI_BLUE + "B" + ANSI_RESET, blueMoves);
                count++;
            }
        }
        System.out.println(hg.toString());

    }
    // To run
    public static void main(String[] args) throws FileNotFoundException {
        playGame p = new playGame();
        p.newGame("moves.txt");
        System.out.println();
        p.newGame("moves2.txt");
    }

}
