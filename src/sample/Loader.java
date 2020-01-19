package sample;

import java.io.*;
public class Loader
{
    public static LogicOperation[]  Load()
    {
        File folder = new File("./");
        File[] listOfFiles = folder.listFiles();
        assert listOfFiles != null;
        int len=0;
        for (int i = 0; i < listOfFiles.length; i++)
        {
            if (listOfFiles[i].getName().endsWith(".op"))
                len++;
        }
        LogicOperation[] result= new LogicOperation[len];
        int j=0;
        for (int i = 0; i < listOfFiles.length; i++)
        {
            if (listOfFiles[i].getName().endsWith(".op"))
            {
                result[j]=LoadOne(listOfFiles[i]);
                j++;
            }
        }
        return result;
    }
    public static LogicOperation LoadOne(File file)
    {
        try
        {

            BufferedReader brTest;
            brTest = new BufferedReader(new FileReader(file));
            String in = brTest .readLine();
            String out=brTest.readLine();
            int inputs=Integer.parseInt(in);
            int outputs=Integer.parseInt(out);
             boolean[][] answers=new boolean[(int)Math.pow(2,inputs)][outputs];
            String st;
            int i=0;
            while ((st = brTest.readLine()) != null)
            {
               String[] splitted= st.split(" ");
                for (int j = 0; j < outputs; j++)
                {
                    answers[i][j]=Boolean.parseBoolean(splitted[j]);
                }
                i++;
                if (i>Math.pow(2,inputs)-1)
                    break;
            }
            brTest.close();
            return new LogicOperation(file.getName(),inputs,outputs,answers);

        } catch (FileNotFoundException e)
        {
            System.out.println("No such file");
        } catch (IOException e)
        {
           System.out.println("IOException");
        }
       return null;
    }


}
