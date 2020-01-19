package sample;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger
{
    private VBox box;
    private  DateTimeFormatter formatter;
    public Logger(VBox box)
    {
        this.box=box;
        formatter=DateTimeFormatter.ofPattern("HH:mm:ss");
    }
    public void Log(String message)
    {   LocalDateTime  tmp = LocalDateTime.now();
        String toLog=tmp.format(formatter)+" :"+message;
        try
        {
            box.getChildren().add(0,new Label(toLog));
            System.out.println(toLog);
        }
        catch (NullPointerException e)
        {
            System.out.println("Logger is disabled, cause of NullPointerException");
        }
    }

}
