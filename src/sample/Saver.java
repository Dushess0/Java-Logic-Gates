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

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

public class Saver
{
    public static  void CreateNewOperation(int inputs,int outputs,boolean[][] answers) throws IOException
    {
        TextInputDialog td = new TextInputDialog("name your operation: e.g AvB ");

        // setHeaderText
        td.setHeaderText("Saving new operation");
        td.showAndWait();
        String filename=td.getEditor().getText()+".op";

        FileWriter fileWriter = new FileWriter(filename);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(inputs);
        printWriter.println(outputs);
        for (int i = 0; i < Math.pow(2,inputs); i++)
        {
            for (int j = 0; j < outputs; j++)
            {
                printWriter.print(answers[i][j]+" ");

            }
            printWriter.println();
        }
        printWriter.close();



    }

}
