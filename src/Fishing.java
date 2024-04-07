import java.util.Scanner;
import java.util.Spliterator;

public class Fishing {
    private Space[][] board;
    private Scanner scan;
    private int userMoves = 0;
    private int fishCount = 0;


    public Fishing() {
        scan = new Scanner(System.in);
        System.out.println("Easy, Medium, or Hard? (E/M/H): ");
        String ans = scan.nextLine();
        setupBoard(ans);
        play();
    }

    private void setupBoard(String level) {
        Treasure t1 = new Treasure("\uD83D\uDC1F");
        Treasure t2 = new Treasure("\uD83D\uDC1F");
        Treasure t3 = new Treasure("\uD83D\uDC1F");
        Treasure t4 = new Treasure("\uD83D\uDC1F");
        Treasure[] treasureList = {t1, t2, t3, t4};

        if (level.equals("E") || level.equals("e")) {
            board = new Space[5][10];
        } else if (level.equals("M") || level.equals("m")) {
            board = new Space[7][14];
        } else if (level.equals("H") || level.equals("h")){
            board = new Space[9][18];
        }
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (col % 2 == 0) {
                    board[row][col] = new Space("\uD83C\uDF0A");
                } else {
                    board[row][col] = new Space(" ");
                }
            }
        }
        board[0][(board[0].length / 2) - 1] = new Space("\uD83E\uDDCD");
        for (int i = 0; i < treasureList.length; i++) {
            int randX = ((int) (Math.random() * board.length - 1)) + 1;
            int randY = ((int) (Math.random() * board[0].length - 1)) + 1;
            if (board[randX][randY].getSymbol().equals("\uD83C\uDF0A")) {
                board[randX][randY] = treasureList[i];
            } else {
                i--;
            }
        }
    }
    private void printBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                System.out.print(board[row][col].getSymbol());
            }
            System.out.println();
        }
    }
    // plays the game;
    private void play() {
        printBoard();
        Scanner scan = new Scanner(System.in);
        int xCoord = 0;
        int yCoord = (board[0].length / 2) - 1;
        String choice = "z";
        while (userMoves < 10) {
            System.out.println("\nWould you like to:\nW) move up\nA) move left\nS) move down\nD) move right");
            choice = scan.nextLine();
            if ((choice.equals("W") || choice.equals("w")) && xCoord - 1 >= 0) {
                if (board[xCoord - 1][yCoord] instanceof Treasure) {
                    System.out.println("You caught a fish!");
                    fishCount++;
                }
                board[xCoord - 1][yCoord] = new Space("\uD83E\uDDCD");
                board[xCoord][yCoord] = new Space("\uD83C\uDF0A");
                xCoord--;
                userMoves++;
            } else if ((choice.equals("A") || choice.equals("a")) && yCoord - 2 >= 0) {
                if (board[xCoord][yCoord - 2] instanceof Treasure) {
                    System.out.println("You caught a fish!");
                    fishCount++;
                }
                board[xCoord][yCoord - 2] = new Space("\uD83E\uDDCD");
                board[xCoord][yCoord] = new Space("\uD83C\uDF0A");
                yCoord-=2;
                userMoves++;
            } else if ((choice.equals("S") || choice.equals("s")) && xCoord + 1 <= board.length - 1) {
                if (board[xCoord + 1][yCoord] instanceof Treasure) {
                    System.out.println("You caught a fish!");
                    fishCount++;
                }
                board[xCoord + 1][yCoord] = new Space("\uD83E\uDDCD");
                board[xCoord][yCoord] = new Space("\uD83C\uDF0A");
                xCoord++;
                userMoves++;
            } else if ((choice.equals("D") || choice.equals("d")) && yCoord + 2 <= board[0].length - 1) {
                if (board[xCoord][yCoord + 2] instanceof Treasure) {
                    System.out.println("You caught a fish!");
                    fishCount++;
                }
                board[xCoord][yCoord  + 2] = new Space("\uD83E\uDDCD");
                board[xCoord][yCoord] = new Space("\uD83C\uDF0A");
                yCoord+=2;
                userMoves++;
            }
            int remaining = 10 - userMoves;


            System.out.println("\n\n\nUser moves remaining: " + remaining);
            System.out.println("Total Fish caught: " + fishCount + "/4");
            printBoard();
        }
    }
}