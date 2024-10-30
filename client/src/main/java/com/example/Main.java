package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) 
    {
        Scanner scan = new Scanner(System.in);

        String operazione, username;
        int disponibili;

        try
        {
            System.out.println("Client Avviato!");
            Socket s = new Socket("localhost", 3000);
            System.out.println("Client connesso al server");

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            System.out.println("Inserisci lo username");
            username = scan.nextLine();
            out.writeBytes(username + "\n");

            do
            {
                // Menu client
                System.out.println("BUY) Compra biglietti, N). Controlla Disponibilita', QUIT). Esci");
                System.out.println("Scegli operazione: ");

                operazione = scan.nextLine();

                if(operazione.equals("QUIT"))
                {
                    System.out.println("B-Bye");
                    out.writeBytes("QUIT" + "\n");
                    break;
                }

                out.writeBytes(operazione + "\n");

                if(operazione.equals("BUY"))
                {
                    System.out.println("inserisci");
                    String acquisto = scan.nextLine();
                    out.writeBytes(acquisto + "\n");
                    String ok = in.readLine();
                    System.out.println(ok);
                }
                else if(operazione.equals("N"))
                {
                    String disp = in.readLine();
                    System.out.println("Sono disponibili: " + disp + " biglietti");
                }
                
            }while(true);
            s.close();
        }catch(IOException e){e.printStackTrace();}

        scan.close();

    }
}