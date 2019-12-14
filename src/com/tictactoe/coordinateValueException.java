package com.tictactoe;

public class coordinateValueException extends Exception {
    int xCoord;

    public coordinateValueException() {
    }

    @Override
    public String toString() {
        return "Coordinates should be from 1 to 3!";
    }
}

