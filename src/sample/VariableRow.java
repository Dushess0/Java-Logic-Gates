package sample;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.scene.Node;
public class VariableRow
{
    private  HBox box;
    private char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();

  public VariableRow(HBox box)
  {
      this.box=box;
  }
  public void clear()
  {
      box.getChildren().clear();
  }
    public void load(int size,boolean[] checked)
    {
        for (int i = 0; i < size; i++)
        {
            CheckBox tmp=new CheckBox();
            tmp.setText(String.valueOf(alphabet[i]));
            tmp.setSelected(checked[i]);
            box.getChildren().add(tmp);
        }

    }
    public void setDisable(boolean disable)
    {
        for (Node child : box.getChildren()) {
               child.setDisable(disable);

        }
    }
    public boolean[] get_States()
    {   boolean[] result= new boolean[get_size()];
        int i =0;
        for (Node child : box.getChildren()) {
            CheckBox check = (CheckBox) child;
            result[i]=check.isSelected();
            i++;
        }
        return result;
    }
    public int get_size()
    {
        return box.getChildren().size();
    }

    public void add_New_Button()
    {   Button btn=new Button("Add Variable");
        btn.setOnAction((ActionEvent e) ->
            {
                CheckBox tmp=new CheckBox();
                tmp.setText(String.valueOf(alphabet[get_size()-1]));
                tmp.setSelected(false);
                box.getChildren().add(tmp);
            });

        box.getChildren().add(0,btn);
    }

    public void load(int size)
    {

        for (int i = 0; i < size; i++)
        {
            CheckBox tmp=new CheckBox();
            tmp.setText(String.valueOf(alphabet[i]));
            tmp.setSelected(false);
            box.getChildren().add(tmp);
        }
    }


}
