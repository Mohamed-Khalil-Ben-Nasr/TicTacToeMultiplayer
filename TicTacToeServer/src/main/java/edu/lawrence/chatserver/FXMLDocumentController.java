package edu.lawrence.chatserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import edu.lawrence.chat.TicTacToeConstants;
import java.util.Arrays;
import java.util.Date;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class FXMLDocumentController implements Initializable {
    @FXML
    private TextArea textArea;
    
    private int playerNumber = 0;
    private Game game;
    private boolean isGameRunning;
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    game = new Game();
        
    new Thread( () -> {
      try {
        // Create a server socket
        ServerSocket serverSocket = new ServerSocket(8000);
        while (true) {
          // Listen for a new connection request
          Socket playerSocket = serverSocket.accept();    
          // Increment clientNo
          playerNumber++; 
         Platform.runLater( () -> {
            // Display the player number
            textArea.appendText("Starting thread for client " + playerNumber +
              " at " + new Date() + '\n');
            });
          // Create and start a new thread for the connection
          new Thread(new HandleAPlayer(playerSocket,game,playerNumber,textArea)).start();
        }
      }
      catch(IOException ex) {
        System.err.println(ex);
      }
    }).start();
    }        
}

class HandleAPlayer implements Runnable, TicTacToeConstants {
    private Socket playerSocket; // A connected socket
    private Game game; // Reference to shared transcript
    private String handle;
    private int playerNumber;
    private TextArea textArea;

    public HandleAPlayer(Socket socket,Game game,int playerNumber,TextArea textArea) {
      this.playerSocket = socket;
      this.game = game;
      this.playerNumber = playerNumber;
      this.textArea = textArea;
    }

    public void run() {
      try {
        // Create reading and writing streams
        BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));
        PrintWriter outputToClient = new PrintWriter(playerSocket.getOutputStream());

        // Continuously serve the client
        while (game.isGameRunning()) {
          // Receive request code from the client
          int request = Integer.parseInt(inputFromClient.readLine());
          // Process request
          switch(request) {
              case JOIN_GAME: {  
                  /** When a player joins the game you should return one of 
                   * PLAYER_ONE, PLAYER_TWO, or GAME_FULL to them, not a boolean.
                   */
                boolean gameJoined = game.joinGame();
                outputToClient.println(gameJoined);
                outputToClient.flush();
                break;
              }
              case GAME_BOARD: {
                int[][] board = game.gameBoard();
                outputToClient.println(Arrays.deepToString(board));
                outputToClient.flush();
                break;       
              }
              case WHOSE_TURN: {
                int currentPlayer = game.whoseTurn();
                outputToClient.println(currentPlayer);
                outputToClient.flush();
                break;
              }
              case MAKE_MOVE: {
                int row = Integer.parseInt(inputFromClient.readLine());
                int col = Integer.parseInt(inputFromClient.readLine());
                boolean validMove = game.makeMove(playerNumber,row, col);
                outputToClient.println(validMove);
                outputToClient.flush();
                break;
              }
          }
        }
        playerSocket.close();
      }
      catch(IOException ex) {
         Platform.runLater(()->textArea.appendText("Exception in client thread: "+ex.toString()+"\n"));
      }
    }
  }