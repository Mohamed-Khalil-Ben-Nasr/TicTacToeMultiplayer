package edu.lawrence.chatclient;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;

public class FXMLDocumentController implements Initializable {
    
    private TicTacToeGateway gateway;
    @FXML 
    private Button button1;
    @FXML 
    private Button button2;
    @FXML 
    private Button button3;
    @FXML 
    private Button button4;
    @FXML 
    private Button button5;
    @FXML 
    private Button button6;
    @FXML 
    private Button button7;
    @FXML 
    private Button button8;
    @FXML 
    private Button button9;
   
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gateway = new TicTacToeGateway();
        /** Here you need to do
         * playerNumber = gateway.joinGame();
         */
        // Start the transcript check thread
        new Thread(new GameCheck(gateway,button1, button2,button3, button4,button5, button6,button7, button8,button9)).start();
    }    
    
    @FXML
    private void ButtonOneClick(ActionEvent event) {
        /** The logic here is incorrect, in that it would allow either player to 
         *  make a move for the current player. What you need to do here instead is 
         *  store your player number when you first connect to the server and 
         *  then make a move here only if currentPlayer equals your player number.
         */
       int currentPlayer = gateway.whoseTurn();
        boolean MoveAccepted = false;
        String makeMoveResponse = gateway.makeMove(currentPlayer,1 ,0 );
        /** Remember to use .equals() to compare strings, not == **/
        if(makeMoveResponse == "true")
        {
           if (currentPlayer == 1) {
                button1.setText("X");
        }
        if (currentPlayer == 2) {
                button1.setText("O");
        } 
        }     
    }
    
     @FXML
    private void ButtonTwoClick(ActionEvent event) {
        int currentPlayer = gateway.whoseTurn();
         boolean MoveAccepted = false;
        String makeMoveResponse = gateway.makeMove(currentPlayer,1 ,1 );
        if(makeMoveResponse == "true")
        {
           if (currentPlayer == 1) {
                button1.setText("X");
        }
        if (currentPlayer == 2) {
                button1.setText("O");
        } 
        }     
    }
    
     @FXML
    private void ButtonThreeClick(ActionEvent event) {
        int currentPlayer = gateway.whoseTurn();
         boolean MoveAccepted = false;
        String makeMoveResponse = gateway.makeMove(currentPlayer,1 ,2 );
        if(makeMoveResponse == "true")
        {
           if (currentPlayer == 1) {
                button1.setText("X");
        }
        if (currentPlayer == 2) {
                button1.setText("O");
        } 
        }     
    }
    
     @FXML
    private void ButtonFourClick(ActionEvent event) {
        int currentPlayer = gateway.whoseTurn();
        boolean MoveAccepted = false;
        String makeMoveResponse = gateway.makeMove(currentPlayer,2 ,0 );
        if(makeMoveResponse == "true")
        {
           if (currentPlayer == 1) {
                button1.setText("X");
        }
        if (currentPlayer == 2) {
                button1.setText("O");
        } 
        }     
    }
    
     @FXML
    private void ButtonFiveClick(ActionEvent event) {
        int currentPlayer = gateway.whoseTurn();
        boolean MoveAccepted = false;
        String makeMoveResponse = gateway.makeMove(currentPlayer,2 ,1 );
        if(makeMoveResponse == "true")
        {
           if (currentPlayer == 1) {
                button1.setText("X");
        }
        if (currentPlayer == 2) {
                button1.setText("O");
        } 
        }     
    }
    
     @FXML
    private void ButtonSixClick(ActionEvent event) {
        int currentPlayer = gateway.whoseTurn();
        boolean MoveAccepted = false;
        String makeMoveResponse = gateway.makeMove(currentPlayer,2 ,2);
        if(makeMoveResponse == "true")
        {
           if (currentPlayer == 1) {
                button1.setText("X");
        }
        if (currentPlayer == 2) {
                button1.setText("O");
        } 
        }     
    }
    
     @FXML
    private void ButtonSevenClick(ActionEvent event) {
        int currentPlayer = gateway.whoseTurn();
        boolean MoveAccepted = false;
        String makeMoveResponse = gateway.makeMove(currentPlayer,3 ,0 );
        if(makeMoveResponse == "true")
        {
           if (currentPlayer == 1) {
                button1.setText("X");
        }
        if (currentPlayer == 2) {
                button1.setText("O");
        } 
        }     
    }
    
     @FXML
    private void ButtonEightClick(ActionEvent event) {
        int currentPlayer = gateway.whoseTurn();
        boolean MoveAccepted = false;
        String makeMoveResponse = gateway.makeMove(currentPlayer,3 ,1 );
        if(makeMoveResponse == "true")
        {
           if (currentPlayer == 1) {
                button1.setText("X");
        }
        if (currentPlayer == 2) {
                button1.setText("O");
        } 
        }     
    }
    
     @FXML
    private void ButtonNineClick(ActionEvent event) {
        int currentPlayer = gateway.whoseTurn();
         boolean MoveAccepted = false;
        String makeMoveResponse = gateway.makeMove(currentPlayer,3 ,2 );
        if(makeMoveResponse == "true")
        {
           if (currentPlayer == 1) {
                button1.setText("X");
        }
        if (currentPlayer == 2) {
                button1.setText("O");
        } 
        }     
       
    }
}

class GameCheck implements Runnable, edu.lawrence.chat.TicTacToeConstants {
    private TicTacToeGateway gateway; // Gateway to the server
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    
    /** Construct a thread */
    public GameCheck(TicTacToeGateway gateway,Button button1,Button button2,Button button3,Button button4,Button button5,Button button6,Button button7,Button button8,Button button9) {
      this.gateway = gateway;
      this.button1 = button1;
      this.button2 = button2;
      this.button3 = button3;
      this.button4 = button4;
      this.button5 = button5;
      this.button6 = button6;
      this.button7 = button7;
      this.button8 = button8;
      this.button9 = button9;
           
    }
    
    /** Run a thread */
    public void run() {
      while(true) {
          String symbol = "";
          // Go to the gateway to ask whose turn it is. Use that result to update the label.
          // Ask the server to send you the game board, then update the text in the 
          // nine button
          int[][] gameBoard = gateway.gameBoard();
          int currentPlayer = gateway.whoseTurn();
          /** When you get the player number you should change the text of the 
           *  label that shows the current player number.
           */
          Platform.runLater(() -> {
            String text1 = "";
            if (gameBoard[0][0] == 1) {
              text1 = "X";
            } else if (gameBoard[0][0] == 2) {
              text1 = "O";
            }
            button1.setText(text1);
            
            String text2 = "";
            if (gameBoard[0][1] == 1) {
              text2 = "X";
            } else if (gameBoard[0][1] == 2) {
              text2 = "O";
            }
            button2.setText(text1);
            
            String text3 = "";
            if (gameBoard[0][2] == 1) {
              text3 = "X";
            } else if (gameBoard[0][2] == 2) {
              text3 = "O";
            }
            button3.setText(text3);
            
            String text4 = "";
            if (gameBoard[1][0] == 1) {
              text4 = "X";
            } else if (gameBoard[1][0] == 2) {
              text4 = "O";
            }
            button4.setText(text4);
            
            String text5 = "";
            if (gameBoard[1][1] == 1) {
              text5 = "X";
            } else if (gameBoard[1][1] == 2) {
              text5 = "O";
            }
            button5.setText(text5);
            
            String text6 = "";
            if (gameBoard[1][2] == 1) {
              text6 = "X";
            } else if (gameBoard[1][2] == 2) {
              text6 = "O";
            }
            button6.setText(text6);
            
            String text7 = "";
            if (gameBoard[2][0] == 1) {
              text7 = "X";
            } else if (gameBoard[2][0] == 2) {
              text7 = "O";
            }
            button7.setText(text7);
            
            String text8 = "";
            if (gameBoard[2][1] == 1) {
              text8 = "X";
            } else if (gameBoard[2][1] == 2) {
              text8 = "O";
            }
            button8.setText(text8);
            
            String text9 = "";
            if (gameBoard[2][2] == 1) {
              text9 = "X";
            } else if (gameBoard[2][2] == 2) {
              text9 = "O";
            }
            button9.setText(text9);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
     
      }
    }
  }

/** This is pretty close to being done, but you have a number of small problems
 *  in both your server and your client. See my comments for details.
 * 
 *  74/80
 */