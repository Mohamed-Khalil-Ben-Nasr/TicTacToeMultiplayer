package edu.lawrence.chatserver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private int[][] board;
    private int currentPlayer;
    private boolean gameRunning;
    private int numberOfPlayers;
    private int winnerNumber;
    
        
    public Game() {
        board = new int[][] {{0,0,0},{0,0,0},{0,0,0}};
        gameRunning = false;
        currentPlayer = 1;
        numberOfPlayers = 0;
    }
    
    public synchronized boolean joinGame(){
       boolean gameJoined = false;
       if (numberOfPlayers < 2){
           numberOfPlayers++;
           gameJoined = true;   
       }
       return gameJoined;
    }
    
    public synchronized int whoseTurn(){
        return currentPlayer;
    }
    
    public synchronized int[][] gameBoard(){
       return board;
    }
    public synchronized boolean makeMove(int player, int row, int col){
        boolean moveAllowed = false;
        int currentMove;
        boolean boardFull = true;
        for (int i = 0; i<3; i++){
            for (int j = 0; j<3; j++){
                if (board[i][j] != 0)
                    {
                        boardFull = false;
                        break;
                    }
            }
        }
        if (boardFull == false){
            gameRunning = true;
        }
        else gameRunning = false;
        
        if (gameRunning == true)
        {
            if (player == currentPlayer)
            {
                if (board[row][col] == 0)
                {
                    gameRunning = true;
                    moveAllowed = true;
                    board[row][col]=currentPlayer;
                    if(currentPlayer == 1)
                        currentPlayer = 2;
                    else if (currentPlayer == 2)
                        currentPlayer = 1;
                    //verify for win condition and stop the game
                    // start by checking all rows
                    for (int i = 0; i<3; i++){
                        if ((board[i][0] == board[i][1]) & (board[i][1] == board[i][2]))
                        {
                            gameRunning = false;
                            winnerNumber = board[i][0];
                        }
                    }
                    // now check all the columns
                     for (int i = 0; i<3; i++){
                        if ((board[0][i] == board[1][i]) & (board[1][i] == board[2][i]))
                        {
                            gameRunning = false;
                            winnerNumber = board[0][i];
                        }
                    }
                    // now finally check all the diagonals
                    if ((board[0][0] == board[1][1]) & (board[1][1] == board[2][2]))
                    {
                        gameRunning = false;
                        winnerNumber = board[0][0];
                    }
                    if ((board[0][2] == board[1][1]) & (board[1][1] == board[2][0]))
                    {
                        gameRunning = false;
                        winnerNumber = board[0][0];
                    }
                    
                }
                else moveAllowed = false;
            }         
        }
        else
            moveAllowed = false;
 
        return moveAllowed;
    }
    
    public synchronized boolean isGameRunning(){
        return gameRunning;
    }
    
}
