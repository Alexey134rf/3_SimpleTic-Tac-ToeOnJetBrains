package com.company;

import java.util.Scanner;

public class SimpleTicTacToe {
    private static final char ZERO = 'O';
    private static final char CROSS = 'X';
    private static final int SIZE_GAME_FIELD = 3;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        startGame();
    }

    public static void startGame() {
        char[][] createGemaField = createGameField();
        printGameField(createGemaField);
        while (true) {
            userInput(createGemaField , CROSS);
            printGameField(createGemaField);
            if (checkPlayerX(createGemaField)) {
                System.out.println(CROSS + " wins");
                break;
            }
            if (!checkDraw(createGemaField)) {
                System.out.println("Draw");
                break;
            }
            userInput(createGemaField , ZERO);
            printGameField(createGemaField);
            if (checkPlayerO(createGemaField)) {
                System.out.println(ZERO + " wins");
                break;
            }
        }
    }

    private static char[][] createGameField() {
        char[][] arrayPlayField = new char[SIZE_GAME_FIELD][SIZE_GAME_FIELD];
        int counter = 0;
        for (int i = 0; i < SIZE_GAME_FIELD; i++) {
            for (int j = 0; j < SIZE_GAME_FIELD; j++) {
                arrayPlayField[i][j] = ' ';
                counter++;
            }
        }
        return arrayPlayField;
    }


    private static void userInput(char[][] arrayPlayField, char playerFlag) {
        while (true) {
            System.out.print("Enter the coordinates: ");
            String coordinate1 = sc.next();
            String coordinate2 = sc.next();
            if (!checkOnIntField(coordinate1) && !checkOnIntField(coordinate2)) {
                System.out.println("You should enter numbers!");
                continue;
            }

            int coordinate1Int = Integer.parseInt(coordinate1);
            int coordinate2Int = Integer.parseInt(coordinate2);

            if (!checkRangeCoordinate(coordinate1Int) || !checkRangeCoordinate(coordinate2Int)) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;

            }

            if (checkOccupiedField(arrayPlayField, coordinate1Int, coordinate2Int)) {
                if (playerFlag == CROSS) {
                    arrayPlayField[coordinate1Int - 1][coordinate2Int - 1] = CROSS;
                    break;
                } else {
                    arrayPlayField[coordinate1Int - 1][coordinate2Int - 1] = ZERO;
                    break;
                }
            } else
                System.out.println("This cell is occupied! Choose another one!");
        }
    }

    private static boolean checkOccupiedField(char[][] arrayPlayField, int coordinate1, int coordinate2) {
        return arrayPlayField[coordinate1 - 1][coordinate2 - 1] == ' ';
    }

    private static boolean checkOnIntField(String coordinate) {
        return coordinate.matches("\\d+");
    }

    private static boolean checkRangeCoordinate(int coordinate) {
        return coordinate >= 0 && coordinate < 4;
    }

    private static void printGameField(char[][] arrayPlayField) {
        System.out.println("---------");
        for (int i = 0; i < SIZE_GAME_FIELD; i++) {
            for (int j = 0; j < SIZE_GAME_FIELD; j++) {
                if (j == 0) {
                    System.out.print("| " + arrayPlayField[i][j] + " ");
                } else if (j == SIZE_GAME_FIELD - 1) {
                    System.out.print(arrayPlayField[i][j] + " |");
                } else {
                    System.out.print(arrayPlayField[i][j] + " ");
                }

            }
            System.out.println("");
            if (i == SIZE_GAME_FIELD - 1) {
                System.out.print("---------\n");
            }
        }

    }

    private static boolean checkPlayerX(char[][] arrayPlayField) {
        return checkWin(arrayPlayField, CROSS) && !checkWin(arrayPlayField, ZERO);
    }

    private static boolean checkPlayerO(char[][] arrayPlayField) {
        return (checkWin(arrayPlayField, ZERO) && !checkWin(arrayPlayField, CROSS));
    }
    private static boolean checkDraw(char[][] arrayPlayField) {
        boolean flag = false;
        for (int i = 0; i < SIZE_GAME_FIELD && !flag; i++) {
            for (int j = 0; j < SIZE_GAME_FIELD; j++) {
                if (arrayPlayField[i][j] == ' ') {
                    flag = true;

                }
            }
        }
        return flag;
    }

    private static boolean checkWin(char[][] arrayPlayField, char symbol) {
        boolean diagonal1 = true;
        boolean diagonal2 = true;
        boolean cols = false;
        boolean rows = false;

        for (int i = 0; i < SIZE_GAME_FIELD; i++) {
            diagonal1 = diagonal1 && arrayPlayField[i][i] == symbol;
            diagonal2 = diagonal2 && arrayPlayField[SIZE_GAME_FIELD - 1 - i][i] == symbol;
        }

        for (int i = 0; i < SIZE_GAME_FIELD; i++) {
            cols = true;
            rows = true;
            for (int j = 0; j < SIZE_GAME_FIELD; j++) {
                cols = cols && arrayPlayField[i][j] == symbol;
                rows = rows && arrayPlayField[j][i] == symbol;
            }
            if (cols || rows) {
                break;
            }
        }

        if (diagonal1 || diagonal2 || rows || cols) {
            return true;
        }
        return false;
    }
}