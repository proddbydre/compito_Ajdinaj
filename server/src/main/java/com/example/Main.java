package com.example;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException
    {
        System.out.println("Server avviato!");

        try
        {
            ServerSocket ss = new ServerSocket(3000);
            {
                do
                {
                    Socket s = ss.accept();
                    System.out.println("Un client ha effettuato il collegamento!");
                    MioThread t = new MioThread(s);
                    t.start();
                }while(true);
            }
        }catch (Exception e){}
    }
}