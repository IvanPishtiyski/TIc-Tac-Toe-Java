package TicTacToe;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Tic-Tac-Toe.fxml"));
        Scene scene = new Scene(root);
        stage.sizeToScene();
        stage.setTitle("Welcome to Tic-Tac-Toe!");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();    }


}
