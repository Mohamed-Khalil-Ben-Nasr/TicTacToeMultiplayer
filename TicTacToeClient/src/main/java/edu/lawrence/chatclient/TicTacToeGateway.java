package edu.lawrence.chatclient;

import edu.lawrence.chat.TicTacToeConstants;
import static edu.lawrence.chat.TicTacToeConstants.GAME_BOARD;
import static edu.lawrence.chat.TicTacToeConstants.JOIN_GAME;
import static edu.lawrence.chat.TicTacToeConstants.MAKE_MOVE;
import static edu.lawrence.chat.TicTacToeConstants.WHOSE_TURN;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class TicTacToeGateway implements TicTacToeConstants {

    private PrintWriter outputToServer;
    private BufferedReader inputFromServer;
    private TextArea textArea;
    
    // Establish the connection to the server.
    public TicTacToeGateway() {
        try {
            // Create a socket to connect to the server
            Socket socket = new Socket("localhost", 8000);

            // Create an output stream to send data to the server
            outputToServer = new PrintWriter(socket.getOutputStream());

            // Create an input stream to read data from the server
            inputFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (IOException ex) {
            Platform.runLater(() -> textArea.appendText("Exception in gateway constructor: " + ex.toString() + "\n"));
        }
    }

    public synchronized int joinGame() {
        outputToServer.println(JOIN_GAME);
        outputToServer.flush();
        String response = "";
        try {
            response = inputFromServer.readLine();
        } catch (IOException ex) {
            Platform.runLater(() -> textArea.appendText("Exception in joinGame: " + ex.toString() + "\n"));
        }
        return Integer.parseInt(response);
        
    }

    public synchronized int whoseTurn() {
        outputToServer.println(WHOSE_TURN);
        outputToServer.flush();
        String response = "";
        try {
            response = inputFromServer.readLine();
        } catch (IOException ex) {
            Platform.runLater(() -> textArea.appendText("Exception in whoseTurn: " + ex.toString() + "\n"));
        }
        return Integer.parseInt(response);
    }

    public synchronized int[][] gameBoard() {
        outputToServer.println(GAME_BOARD);
        outputToServer.flush();
        try {
            String boardString = inputFromServer.readLine();
            String[] rows = boardString.split(",");
            int[][] board = new int[3][3];
            for (int i = 0; i < 3; i++) {
                String[] cols = rows[i].split(" ");
                for (int j = 0; j < 3; j++) {
                    board[i][j] = Integer.parseInt(cols[j]);
                }
            }
            return board;
        } catch (IOException ex) {
            Platform.runLater(() -> textArea.appendText("Exception in gameBoard: " + ex.toString() + "\n"));
        }
        return null;
    }

    public synchronized String makeMove(int player, int row, int col) {
        outputToServer.println(MAKE_MOVE);
        outputToServer.println(player);
        outputToServer.println(row);
        outputToServer.println(col);
        outputToServer.flush();
        String response = "";
        String actualResponse = "";
        try {
           response = inputFromServer.readLine();
           /** Remember to use .equals() to compare strings, not == **/
           if (response == "true")
               actualResponse = "true";
           if (response == "false")
               actualResponse = "false";
               
        } catch (IOException ex) {
            Platform.runLater(() -> textArea.appendText("Exception in makeMove: " + ex.toString() + "\n"));
        }
        return actualResponse;
    }
}
