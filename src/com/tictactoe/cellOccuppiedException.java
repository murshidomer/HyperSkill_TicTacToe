package com.tictactoe;

public class cellOccuppiedException extends Exception {
    @Override
    public String toString() {
        return "This cell is occupied! Choose another one! ";
    }

}
