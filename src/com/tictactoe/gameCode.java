package com.tictactoe;

import java.util.HashMap;
import java.util.Scanner;

public class gameCode extends HashMap {

    public static void main(String[] args) {

        gameCode myGame = new gameCode();

        String gameState = "Game not finished";
        int winCount = 0,  numberOfMoves = 0;
        StringBuilder field = new StringBuilder("         ");
        char[][] coordinatesMappingArray = new char[4][4];
        HashMap<String, Integer> coordinatesMapping = new HashMap<>();

        //print the 1st  game field
        myGame.printGameField(field);

        //Set coordinatesMappingArray to '_' to denote empty
        for (int i = 1; i < coordinatesMappingArray.length; i++) {
            for (int j = 1; j < coordinatesMappingArray.length; j++) {
                coordinatesMappingArray[i][j] = '_';
            }
        }

        //populate coordinates Field
        int xCoordinate = 0, yCoordinate = 0;
        String intToString;
        int inputCharStartPos = 6, currentCharPosition;
        for (int i = 1; i <= 3; i++) {
            currentCharPosition = inputCharStartPos;
            for (int j = 1; j <= 3; j++) {
                intToString = String.valueOf(i) + j;
                coordinatesMapping.put(intToString, currentCharPosition);
//                    coordinatesMappingArray[i][j] = field.charAt(currentCharPosition);
                currentCharPosition -= 3;
            }
            inputCharStartPos++;
        }


        while (gameState.equals("Game not finished")) {
            char winner = ' ';
            boolean success = false;
            int xCount = 0, oCount = 0, blankCount = 0;
            //prompt user input
            while (!success) {
                try {
                    System.out.print("Enter the coordinates: ");
                    Scanner userinput = new Scanner(System.in);

                    //Validate coordinate value
                    xCoordinate = userinput.nextInt();
                    if (xCoordinate < 1 || xCoordinate > 3) {
                        throw new coordinateValueException();
                    }

                    yCoordinate = userinput.nextInt();
                    if (yCoordinate < 1 || yCoordinate > 3) {
                        throw new coordinateValueException();
                    }

                    //check if coordinate location is empty
                    if (coordinatesMappingArray[xCoordinate][yCoordinate] != '_') {
                        throw new cellOccuppiedException();
                    }
                    success = true;

                } catch (cellOccuppiedException | coordinateValueException notEmpty) {
                    System.out.println(notEmpty);
                    success = false;
                } catch (Exception e) {
                    System.out.println("You should enter numbers!");
                    success = false;
                }
            }

            //Alternate user inputs 'X' and 'Y'
            if (numberOfMoves % 2 == 0) {
                coordinatesMappingArray[xCoordinate][yCoordinate] = 'X';
            } else {
                coordinatesMappingArray[xCoordinate][yCoordinate] = 'O';
            }
            numberOfMoves++;

            //update the field with the user's move
            String convertedKeyString = String.valueOf(xCoordinate) + yCoordinate;
            int charUpdatePosition = coordinatesMapping.get(convertedKeyString);
            char temp = coordinatesMappingArray[xCoordinate][yCoordinate];
            field.setCharAt(charUpdatePosition, temp);

            //print the 2nd game field
            myGame.printGameField(field);

            // count occurences of each character
            for (int i = 0; i < field.length(); i++) {
                if (field.charAt(i) == 'X') xCount += 1;
                else if (field.charAt(i) == 'O') oCount += 1;
                else blankCount += 1;
            }

            //determine the state of game field
            //check for winners in rows
            char c;
            for (int i = 0; i <= 2; i += 3) {
                c = field.charAt(i);
                if (c == 'X' || c == 'O') {
                    if (field.charAt(i + 1) == c && field.charAt(i + 2) == c) {
                        winner = field.charAt(i);
                        winCount += 1;
                    }
                }
            }

            // check for winners in columns
            for (int i = 0; i <= 2; i++) {
                c = field.charAt(i);
                if (c == 'X' || c == 'O') {
                    if (field.charAt(i + 3) == c && field.charAt(i + 6) == c) {
                        winner = field.charAt(i);
                        winCount += 1;
                    }
                }
            }


            //check left diagonal
            c = field.charAt(0);
            if (c == 'X' || c == 'O') {
                if (field.charAt(4) == c && field.charAt(8) == c) {
                    winner = field.charAt(0);
                    winCount += 1;
                }
            }

            //check right diaonal
            c = field.charAt(2);
            if (c == 'X' || c == 'O') {
                if (field.charAt(4) == c && field.charAt(6) == c) {
                    winner = field.charAt(2);
                    winCount += 1;
                }
            }


            //determine game conclusion
            if (winCount == 0) {
                if (blankCount > 0) {
                    gameState = "Game not finished";
                } else if (blankCount == 0) {
                    gameState = "Draw";
                    break;
                }
            } else if (winCount == 1) gameState = winner + " wins";
            else if (winCount > 1 || Math.abs(xCount - oCount) > 1 || Math.abs(oCount - xCount) > 1) {
                gameState = "Impossible";
            }
        }
        System.out.println(System.lineSeparator() + gameState);
    }

    public void printGameField(StringBuilder input) {
        System.out.print("---------");
        for (int i = 0; i < input.length(); i += 3) {
            System.out.print(System.lineSeparator() + "|" + " " + input.charAt(i) + " " + input.charAt(i + 1) + " "
                    + input.charAt(i + 2) + " " + "|" + System.lineSeparator());
        }
        System.out.println("---------");
    }
}

