package com.daotaku;

import java.util.Random;

public class Game {

    private Board board;
    private Random random;

    public static void main(String[] args) {
        new Game();
    }

    public Game() {
        initializeGame();
        displayBoard();
        makeFirstMove();
        playGame();
        checkStatus();
    }

    private void initializeGame() {
        this.board = new Board();
        this.board.setupBoard();
        this.random = new Random();
    }

    private void displayBoard() {
        this.board.displayBoard();
    }

    private void makeFirstMove() {
        System.out.println("When inputing block please use A1 ~ C3");
        System.out.println("Who starts first? 1 - Computer ; 2 - User");
        int choice = board.getScanner().nextInt();

        if (choice == 1) {
            Cell cell = new Cell(random.nextInt(Constants.BOARD_SIZE), random.nextInt(Constants.BOARD_SIZE));
            this.board.move(cell, Player.COMPUTER);
            displayBoard();
        }
    }

    private void playGame() {
        while (this.board.isRunning()) {
            System.out.println("User Move: ");
            String inputUser = board.getScanner().next();
            Cell userCell = new Cell(board.getInputUserX(inputUser), board.getInputUserY(inputUser));

            if (this.board.isFilledCell(userCell)) {
                System.out.println(inputUser+" is already filled or doesn't exist, please choose another!");
                continue;
            }

            this.board.move(userCell, Player.USER);
            displayBoard();

            if (!this.board.isRunning()) {
                break;
            }

            this.board.callMinimax(0, Player.COMPUTER);

            for (Cell cell : this.board.getRootValues())
            {
                System.out.println("Cell value: "+cell+" minimax value: "+cell.getMinimaxValue());
            }

            this.board.move(board.getBestMove(), Player.COMPUTER);
            displayBoard();
        }
    }

    private void checkStatus() {
        if (board.isWinning(Player.COMPUTER)) {
            System.out.println("Computer has won!");
        } else if (board.isWinning(Player.USER)) {
            System.out.println("User has won!");
        } else {
            System.out.println("It's a draw!");
        }
    }
}
