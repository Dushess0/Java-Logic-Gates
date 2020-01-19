package sample;

import java.util.Arrays;

public class LogicOperation
{
    public String name;
    public int inputs;
    public int outputs;
    public boolean[][] answers;
    public static boolean[][] generateAllBinary(int size)
    {

        int numRows = (int)Math.pow(2, size);
        boolean[][] bools = new boolean[numRows][size];
        for(int i = 0;i<bools.length;i++)
        {
            for(int j = 0; j < bools[i].length; j++)
            {
                int val = bools.length * j + i;
                int ret = (1 & (val >>> j));
                bools[i][j] = (ret != 0);
            }
        }
        return bools;
    }
    public  LogicOperation(String name,int inputs,int outputs, boolean[][] answers)
    {   this.name=name;
        this.inputs=inputs;
        this.outputs=outputs;
        this.answers=answers;
    }
    public boolean[] Run(boolean[] params)
    {
        boolean[][] tmp=generateAllBinary(inputs);
        for (int i = 0; i < tmp.length; i++)
        {
           if (Arrays.equals(tmp[i],params))
           {
               return answers[i];
           }
        }
        return tmp[0];
    }
}

