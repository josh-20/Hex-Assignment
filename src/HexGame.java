import java.util.ArrayList;
public class HexGame {
    public static String ANSI_Red = "\u001B[31m";
    public static String ANSI_RESET = "\u001B[30m";
    public static String ANSI_BLUE = "\u001B[34m";
     String[] game;
    public String[] playHexGame(int numToCheck, DisJointSet dj, String [] colorGame, String color, int moves){
        game = colorGame;
        //System.out.println("Legal Neighbors : " + numToCheck+ " " + findNeighbors(numToCheck,dj,colorGame,color) + "\n");
        if (color.equals(ANSI_Red + "R" + ANSI_RESET)){
            colorGame[124] = ANSI_Red + "R" + ANSI_RESET;
            colorGame[125] = ANSI_Red + "R" + ANSI_RESET;
        }
        else {
            colorGame[122] = ANSI_BLUE + "B" + ANSI_RESET;
            colorGame[123] = ANSI_BLUE + "B" + ANSI_RESET;
        }
        // Union neighbors with same color as current move then check if the game is Won
       for(int i : findNeighbors(numToCheck,dj,colorGame,color)) {
           if(colorGame[i].equals(color)){
               union(numToCheck,i,dj);
               if(dj.find(122) == dj.find(123)){
                   System.out.println("|------------Blue has Won!! With " + moves + " Moves!-------------|");
                   break;
               }
               else if(dj.find(124) == dj.find(125)){
                   System.out.println("|------------Red has Won!! With "  + moves + " Moves!-------------|");
                   return colorGame;
               }
           }
       }
        return colorGame;
    }
    private void union(int firstNum, int secondNum, DisJointSet dj){
        dj.Union(firstNum,secondNum);
    }
    private ArrayList<Integer> findNeighbors(int numToCheck, DisJointSet dj, String [] colorGame, String color) {
        // Array for each possible move to check for any type of neighbors
        int[] inc = {-11, -10, -1, 1, 10, 11};
        int[] leftE = {-11,-10, 1, 11};
        int[] rightE = {-11, -1, 10, 11};
        int[] lTop = {1,11};
        int[] rTop = {-1,10,11};
        int[] top = {-1,1,10,11};
        int[] lBot = {-11,-10,1};
        int[] rBot = {-11,-1};
        int[] bot = {-1,1,-10,-11};

        ArrayList<Integer> legalNeighbors = new ArrayList<>();
        colorGame[numToCheck] = color;

        // if checks for the corners of the board
        if (numToCheck <= 11 || numToCheck >= 111){
            if (numToCheck == 1){
                if(color.equals(ANSI_BLUE + "B" + ANSI_RESET)){
                    for(int i:lTop){
                        legalNeighbors.add(numToCheck + i);
                    }
                    legalNeighbors.add(123);
                }else{
                    for (int i: lTop) {
                        legalNeighbors.add(numToCheck + i);
                    }
                    legalNeighbors.add(124);
                }
            }
            else if (numToCheck == 11){
                if(color.equals(ANSI_BLUE + "B" + ANSI_RESET)){
                    for (int i:rTop) {
                        legalNeighbors.add(numToCheck + i);
                    }
                    legalNeighbors.add(122);
                }else{
                    for (int i:rTop) {
                        legalNeighbors.add(numToCheck + i);
                    }
                    legalNeighbors.add(124);
                }
            }
            else if (numToCheck == 111){
                for (int i : lBot) {
                    legalNeighbors.add(numToCheck + i);
                }
                if (color.equals(ANSI_BLUE + "B" + ANSI_RESET)){
                    legalNeighbors.add(123);
                } else{
                    legalNeighbors.add(125);
                }

            }
            else if (numToCheck == 121){
                for (int i : rBot) {
                    legalNeighbors.add(numToCheck + i);
                }
                if(color.equals(ANSI_BLUE + "B" + ANSI_RESET)){
                    legalNeighbors.add(122);
                }else{
                    legalNeighbors.add(125);
                }
            }
            // middle of top or bottom rows
            else{
                if(color.equals(ANSI_Red + "R" + ANSI_RESET)) {
                    if (numToCheck <= 11){
                        legalNeighbors.add(124);
                    for (int i : top) {
                            legalNeighbors.add(i + numToCheck);
                        }
                    }else {
                        legalNeighbors.add(125);
                        for (int i : bot) {
                            legalNeighbors.add(numToCheck + i);
                        }
                    }
                }else{
                    if (numToCheck <= 11){
                        for (int i : top) {
                            legalNeighbors.add(numToCheck + i);
                        }
                    }else{
                        for (int i : bot) {
                            legalNeighbors.add(numToCheck + i);
                        }
                    }
                }
            }
        }
        // Checks for left edge
        else if (numToCheck % 11 == 1) {
            for (int i : leftE) {
                legalNeighbors.add(numToCheck + i);
            }
            if(color.equals(ANSI_BLUE + "B" + ANSI_RESET)) {
                legalNeighbors.add(123);
            }
        }
        // were on the right side of the array
        else if (numToCheck % 11 == 0) {
            if (color.equals(ANSI_BLUE + "B" + ANSI_RESET)) {
                legalNeighbors.add(122);
            }
            for (int i:rightE) {
                legalNeighbors.add(numToCheck + i);
            }
        }else{
            for (int i: inc) {
                legalNeighbors.add(numToCheck + i);
            }
        }
        return legalNeighbors;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 1; i < game.length - 4 ;i++){
            if (i % 11 == 1){
                sb.append("\n");
                for (int j = 0; j <= count;j++){
                    sb.append(" ");
                }
                count++;
            }
            sb.append( game[i] + " ");
        }
        return sb.toString();
    }
}
