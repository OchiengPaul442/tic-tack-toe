import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class App {
    static ArrayList<Integer> playerPositions =new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions =new ArrayList<Integer>();

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePieces(int pos, char[][] gameBoard, String user) {
        char symbol = ' ';
        // if user its user give symbol X and cpu give symbol 0
        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(pos);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(pos);
        }

        switch (pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String checkWinner() {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for (List l : winning) {          
            if (playerPositions.containsAll(l)) {
                return "Congratulations, you won!";
            } else if (cpuPositions.containsAll(l)) {
                return "Sorry, you lost! CPU Wins!";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "It's a tie!";
            }
        }
        return "";
    }

    public static void main(String[] args) throws Exception {
        // designing the gameboard
        char[][] gameBoard = {
                { ' ', '|', ' ', '|', ' ' },
                { '-', '+', '-', '+', '-' },
                { ' ', '|', ' ', '|', ' ' },
                { '-', '+', '-', '+', '-' },
                { ' ', '|', ' ', '|', ' ' } };

        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Enter your placements (1-9):");
            int playerpos = input.nextInt();
            while(playerPositions.contains(playerpos) || cpuPositions.contains(playerPositions)){
                System.out.println("position taken! Enter correct position");
                playerpos = input.nextInt();
            }
            placePieces(playerpos, gameBoard, "player");
            String result = checkWinner();
            if(result.length() > 0  ){
                System.out.println(result);
                break;
            }
            // System.out.println(playerpos);
            Random random = new Random();
            int cpuPos = random.nextInt(9) + 1;
            while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)){
                cpuPos = random.nextInt(9) + 1;
            }
            placePieces(cpuPos, gameBoard, "cpu");
            printGameBoard(gameBoard);
            result = checkWinner();
            if(result.length() > 0  ){
                System.out.println(result);
                break;
            }
            System.out.println(result);
        }

    }
}
