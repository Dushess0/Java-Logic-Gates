package sample;
public class ConnectionProperties
{


    private String adress;
    private int port;
    private String user;
    private String password;
    private String path;

    public String getAdress()
    {
        return adress;
    }

    public int getPort()
    {
        return port;
    }

    public String getUser()
    {
        return user;
    }

    public String getPassword()
    {
        return password;
    }

    public String getPath()
    {
        return path;
    }

    public void setAdress(String adress)
    {
        this.adress = adress;
    }

    public void setPort(int port)
    {
        this.port = port;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setPath(String path)
    {
        this.path = path;
    }



}
