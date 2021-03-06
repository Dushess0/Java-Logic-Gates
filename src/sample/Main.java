package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("sample.fxml").openStream());


        Controller controller =  fxmlLoader.getController();
        Scene scene =new Scene(root);
        root.applyCss();
        root.layout();
        controller.init_scene(scene);
        primaryStage.setTitle("Logic Gates");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
