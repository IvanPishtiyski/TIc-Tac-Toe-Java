module com.example.tictactoeapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens TicTacToe to javafx.fxml;
    exports TicTacToe;



}