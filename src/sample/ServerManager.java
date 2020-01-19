package sample;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;
import java.net.SocketException;

public class ServerManager

{

    private static String server = "yourserver.com";
    private static   int port = 21;
    private static String  user = "user";
    private static String pass = "password";
    private static String path="path/to/your/files/on/server/";

    public static void  uploadFiles()
    {


        FTPClient ftpClient = new FTPClient();
        try
        {
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            File folder = new File(".") ;
            File[] listOfFiles = folder.listFiles();
            assert listOfFiles != null;

            for (File f : listOfFiles)
            {
                if (f.getName().endsWith(".op"))
                {
                    InputStream input = new FileInputStream(f);
                    ftpClient.storeFile(path+f.getName() , input);

                 }
            }
            ftpClient.logout();

        }
        catch ( SocketException s )
        {
            System.out.println("There was a problem(SocketException) connecting to server");
        }
        catch (IOException i)
        {
            System.out.println("Error writing to file (IOException)");
        }

    }
    public  static  void load()
    {
        FTPClient ftpClient = new FTPClient();
        try
        {
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(path);
            FTPFile[] ftpFiles = ftpClient.listFiles();

            if (ftpFiles != null && ftpFiles.length > 0) {

                for (FTPFile file : ftpFiles) {
                    if (!file.isFile())
                    {
                        continue;
                    }
                    System.out.println("Downloaded  " + file.getName());

                    OutputStream output;
                    output = new FileOutputStream(file.getName());

                    ftpClient.retrieveFile(file.getName(), output);

                    output.close();


                }
            }


            ftpClient.logout();

        }
        catch ( SocketException s )
        {
            System.out.println("There was a problem(SocketException) connecting to server");
        }
        catch (IOException i)
        {
            System.out.println("Error loading files (IOException)");
        }
    }


}
