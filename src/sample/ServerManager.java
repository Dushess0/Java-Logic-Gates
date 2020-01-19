package sample;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPClient;
import java.io.*;
import java.net.SocketException;
public class ServerManager
{


    public  static ConnectionProperties read_config()
    {
       ConnectionProperties result=new ConnectionProperties();

       File f = new File("server_config.yml");
        try(BufferedReader br = new BufferedReader(new FileReader(f)))
        {
            for(String line; (line = br.readLine()) != null; )
            {
               String[] a=  line.split(":");

               if (a[0].equals("server"))
               {
                   result.setAdress(a[1]);
               }
               else if (a[0].equals("port"))
               {
                   result.setPort(Integer.parseInt( a[1]));
               }
               else if (a[0].equals("user"))
               {
                   result.setUser(a[1]);
               }
               else if (a[0].equals("path"))
               {
                   result.setPath(a[1]);
               }
               else if (a[0].equals("password"))
               {
                   result.setPassword(a[1]);
               }
            }

        }
        catch (IOException e)
        {
          e.printStackTrace();
        }

        return result;


    }
    public static void  uploadFiles()
    {

         ConnectionProperties a=  read_config();
        FTPClient ftpClient = new FTPClient();
        try
        {
            ftpClient.connect(a.getAdress(), a.getPort());
            ftpClient.login(a.getUser(), a.getPassword());
            ftpClient.enterLocalPassiveMode();
            File folder = new File(".") ;
            File[] listOfFiles = folder.listFiles();
            assert listOfFiles != null;

            for (File f : listOfFiles)
            {
                if (f.getName().endsWith(".op"))
                {
                    InputStream input = new FileInputStream(f);
                    ftpClient.storeFile(a.getPath()+f.getName() , input);

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
        ConnectionProperties a=  read_config();
        FTPClient ftpClient = new FTPClient();
        try
        {
            ftpClient.connect(a.getAdress(), a.getPort());
            ftpClient.login(a.getUser(), a.getPassword());
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(a.getPath());
            FTPFile[] ftpFiles = ftpClient.listFiles();

            if (ftpFiles != null && ftpFiles.length > 0) {

                for (FTPFile file : ftpFiles) {
                    if (!file.isFile())
                    {
                        continue;
                    }
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
