package server_side;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MySerialServer implements Server
{
    private ServerSocket listener = null;
    private boolean _run = true;

    public void start(int port, ClientHandler c)
    {
        try
        {
            this.listener = new ServerSocket(port);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return;
        }
        System.out.println("Serial-Server started listening...");

        Runnable runnable = () ->
        {
            while(this._run)
            {
                try
                {
                    this.listener.setSoTimeout(100000);
                    Socket s = this.listener.accept();
                    System.out.println("Connection established.");

                    c.handleClient(s.getInputStream(), s.getOutputStream());
                }

                catch (IOException | NullPointerException e)
                {
                    e.printStackTrace();
                }

            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void stop()
    {
        this._run = false;

        try
        {
            this.listener.close();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
