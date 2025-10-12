package TicTacToe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;



import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller  implements Initializable {
    @FXML
    BorderPane borderPane;
    @FXML
    Label informationLabel;
    @FXML
    Button restartButton;

    @FXML
    Button button1;
    @FXML
    Button button2;
    @FXML
    Button button3;
    @FXML
    Button button4;
    @FXML
    Button button5;
    @FXML
    Button button6;
    @FXML
    Button button7;
    @FXML
    Button button8;
    @FXML
    Button button9;
    @FXML
    StackPane stackPane;
    @FXML
    GridPane grid;



    Random random = new Random();
    private int playerTurn = random.nextInt(2);
    ArrayList<Button> buttons;

    String line;
    String playerSymbol;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons = new ArrayList<>(Arrays.asList(button1,button2,button3,
                                                button4,button5,button6,
                                                button7,button8,button9));

        determineWhoToMove();
        for (Button button : buttons) {
            setupButton(button);
            button.setFocusTraversable(false);
        }
    }


    public void setupButton (Button button){
            button.setOnMouseClicked(mouseEvent ->{
                setPlayerSymbol(button);
                button.setDisable(true);
                checkIfGameEnds();
            });
        }
// here is the game logic where we check if a move is a winning one
    private void checkIfGameEnds() {
        boolean thereIsAWinner = false;
        for (int i = 0; i < 8 ; i++) {
             line = switch (i) {
                case 0 -> button1.getText() + button2.getText() + button3.getText();
                case 1 -> button4.getText() + button5.getText() + button6.getText();
                case 2 -> button7.getText() + button8.getText() + button9.getText();
                case 3 -> button1.getText() + button5.getText() + button9.getText();
                case 4 -> button3.getText() + button5.getText() + button7.getText();
                case 5 -> button1.getText() + button4.getText() + button7.getText();
                case 6 -> button2.getText() + button5.getText() + button8.getText();
                case 7 -> button3.getText() + button6.getText() + button9.getText();
                default -> null;
            };
            if (line.equals("XXX") || line.equals("OOO")) {
                String winner = line.charAt(0) + " wins!";
                Color color = line.equals("XXX") ? Constants.xBlueColor : Constants.oRedColor;
                informationLabel.setText(winner);
                informationLabel.setTextFill(color);
                thereIsAWinner = true;
                disableRemainingButtons();
            }
        }
        if (!thereIsAWinner){
            checkIfGameIsDrawn();
        }

    }

    private void disableRemainingButtons() {
        buttons.forEach(button -> {
            if (button.getText().isEmpty()){
                button.setDisable(true);
            }
        });
    }

    private void checkIfGameIsDrawn() {

        boolean allButtonsHaveText = buttons.stream()
                .allMatch(button -> !button.getText().isEmpty());

        if (allButtonsHaveText){
            informationLabel.setText("Game is drawn!");
            informationLabel.setTextFill(Color.BLACK);
            restartButton.setVisible(true);
        }
    }

    private void setPlayerSymbol(Button button) {
        if (playerTurn == 0){
            button.setTextFill(Constants.xBlueColor);
            button.setText("X");
            playerTurn = 1;
            informationLabel.setText("O's turn");
            informationLabel.setTextFill(Constants.oRedColor);
        }else{
            button.setTextFill(Color.RED);
            button.setText("O");
            playerTurn = 0;
            informationLabel.setText("X's turn");
            informationLabel.setTextFill(Constants.xBlueColor);
        }
    }
        private void determineWhoToMove() {

            if (playerTurn == 0) {
                informationLabel.setText("X's turn");
                informationLabel.setTextFill(Constants.xBlueColor);
            } else {
                informationLabel.setText("O's turn");
                informationLabel.setTextFill(Constants.oRedColor);
            }
        }

        //here is the logic for restarting the game
    public void restartGame (ActionEvent e){
        determineWhoToMove();
       for (Button button : buttons) {
           resetButton(button);
       }

   }
    private void resetButton(Button button) {
        button.setText("");
        button.setDisable(false);
    }

}
