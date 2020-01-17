package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.*;
import javafx.scene.Scene;
public class Controller
{
    private Logger logger;
    private String mode="Testing";
    private VariableRow inputs;
    private VariableRow outputs;
    private Scene scene;
    public  void init_scene(Scene scene)
    {   this.scene=scene;
        logger=new Logger((VBox) scene.lookup("#loggerbox"));
        inputs= new VariableRow((HBox) scene.lookup("#input_box"));
        outputs= new VariableRow((HBox) scene.lookup("#output_box"));

    }

    public void pressDebug(ActionEvent e)
    {
       logger.Log("Debugging");
    }
    public void changeMode(ActionEvent e)
    {
      Button mode_btn=  (Button) scene.lookup("#mode_button");
      Button action_btn=(Button) scene.lookup("#action_button");
      if (mode=="Variants")
        {
            logger.Log("You cant change modes while defining new operation");
        }
       else if (mode=="Testing")
      {
          mode = "New Operation";

          inputs.clear();
          outputs.clear();
          inputs.add_New_Button();
          outputs.add_New_Button();
          action_btn.setText("Save inputs and outputs");

          action_btn.setOnAction((ActionEvent a)-> Saver.CreateNewOperation(inputs.get_size(),outputs.get_size()));


          mode_btn.setText(mode);
      }
    }


}
