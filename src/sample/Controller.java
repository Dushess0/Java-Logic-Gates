package sample;
import javafx.event.ActionEvent;

import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.*;
import javafx.scene.Scene;


import java.io.IOException;

public class Controller
{
    private Logger logger;
    private String mode="Testing";
    private VariableRow inputs;
    private VariableRow outputs;
    private VBox operations_list;
    private Scene scene;
    private int iteration=0;
    private int input_len=0;
    private int output_len=0;
    private boolean[][] obtained_outputs;
    private LogicOperation current_operation;
    public  void init_scene(Scene scene)
    {   this.scene=scene;
        logger=new Logger((VBox) scene.lookup("#loggerbox"));
        inputs= new VariableRow((HBox) scene.lookup("#input_box"));
        outputs= new VariableRow((HBox) scene.lookup("#output_box"));
        operations_list = (VBox) scene.lookup("#operations_list");
        load_operations_list();
        Button action_btn=(Button) scene.lookup("#action_button");
        action_btn.setOnAction((ActionEvent c)->RunTest());



    }
    public void synchonize_with_db(ActionEvent e)
    {

        ServerManager.load();
        ServerManager.uploadFiles();
        load_operations_list();
    }
    public void pressDebug(ActionEvent e)
    {
       logger.Log("Debugging");
    }

    private void start()
    {
        iteration=0;
        Button mode_btn=  (Button) scene.lookup("#mode_button");
        Button action_btn=(Button) scene.lookup("#action_button");
        change_mode("Variants");
        input_len=inputs.get_size()-1;
        output_len=outputs.get_size()-1;
        if ((input_len==0)||(output_len==0))
        {
            change_mode("Testing");
            logger.Log("Error, inputs and outputs cant be zero");
            return;
        }
        inputs.clear();
        outputs.clear();

        boolean[][] tmp = LogicOperation.generateAllBinary(input_len);
        inputs.load(input_len,tmp[0]);
        inputs.setDisable(true);
        outputs.load(output_len);
        obtained_outputs=new boolean[tmp.length][output_len];
        action_btn.setOnAction((ActionEvent a)->
        {

            inputs.clear();
            obtained_outputs[iteration]=outputs.get_States();
            iteration++;
            outputs.clear();
            if (iteration>tmp.length-1)
            {   saving_new_operation();
                 mode_btn.setText("Testing");
                action_btn.setOnAction((ActionEvent c)->RunTest());
                return;
            }
            inputs.load(input_len,tmp[iteration]);
            outputs.load(output_len);
            inputs.setDisable(true);

        });

    }

    void load_operations_list()
    {
        change_mode("Testing");
        operations_list.getChildren().clear();
        LogicOperation[] tmp= Loader.Load();
        for (int i = 0; i < tmp.length; i++)
        {
            Button btn= new Button(tmp[i].name);
            LogicOperation operation = tmp[i];
            btn.setOnAction( (ActionEvent e) ->
            {
                inputs.clear();
                outputs.clear();
                current_operation=operation;
                inputs.load(current_operation.inputs);
                outputs.load(current_operation.outputs);
            }
            );
            operations_list.getChildren().add(0,btn);

        }
    }
    void RunTest()
    {
        if (current_operation!=null)
        {
            outputs.clear();
            boolean[] answers=  current_operation.Run(inputs.get_States());
            outputs.load(current_operation.outputs,answers);
            logger.Log("Solution found");
        }
        else
        {
            logger.Log("current operation is null");
        }

    }
    void change_mode(String newMode)
    {
        mode=newMode;
        Button mode_btn=  (Button) scene.lookup("#mode_button");
        mode_btn.setText(mode);
    }
    private void  saving_new_operation()
    {
        try
        {
            Saver.CreateNewOperation(input_len,output_len,obtained_outputs);
            load_operations_list();

        }
        catch (IOException e)
        {
            logger.Log("Something went wrong when saving to file");
        }
    }
    public void changeMode(ActionEvent e)
    {
      Button action_btn=(Button) scene.lookup("#action_button");

      if (mode=="Variants")
        {
            logger.Log("You cant change modes while defining new operation");
        }
      else if (mode=="New operation")
      {
          change_mode("Testing");
          inputs.clear();
          outputs.clear();
      }
       else if (mode=="Testing")
      {
           change_mode("New operation");
          inputs.clear();
          outputs.clear();
          inputs.add_New_Button();
          outputs.add_New_Button();
          action_btn.setText("Start");
          logger.Log("Specify number of inputs and outputs, after that press [Start] button");
          action_btn.setOnAction((ActionEvent a)-> start());
      }
    }
}
