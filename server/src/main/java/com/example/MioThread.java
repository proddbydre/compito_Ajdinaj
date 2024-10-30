package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class MioThread extends Thread
{
    Socket s;

    public MioThread(Socket s)
    {
        this.s = s;
        
    }

    public void run()
    {
        try
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            String operazione, username;
            String acquisto;

            int disp = 14;
            
            username = in.readLine();

            do
            {   
            
                operazione=in.readLine();

                switch(operazione)
                {
                    case "BUY":

                        acquisto = in.readLine();
                        int num = Integer.parseInt(acquisto);

                        System.out.println(num);

                        if(disp < num)
                        {
                            out.writeBytes("KO" + "\n");
                        }
                        else if(disp >= num)
                        {
                            disp = disp - num;
                            System.out.println(disp);
                            out.writeBytes("OK" + "\n");
                            break;
                        }

                    case "N":

                        System.out.println(disp);
                        out.writeBytes(disp + "\n");
                
                    break;

                    case "QUIT":

                        System.out.println("Il client: " + username + " non vuole più acquistare biglietti");
                        s.close();

                    break;
                }

            }while(true);
            
        }catch(Exception e){}
    }
}
