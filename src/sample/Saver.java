package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import java.time.LocalDate;

public class Saver
{
    public static  void CreateNewOperation(int inputs,int outputs)
    {
        TextInputDialog td = new TextInputDialog("name your operation: e.g AvB ");

        // setHeaderText
        td.setHeaderText("Saving new operation");
        td.showAndWait();
        String filename=td.getEditor().getText();


    }

}
