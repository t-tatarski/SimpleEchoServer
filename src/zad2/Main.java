package zad2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;


/*
You can use netcat as client: nc 127.0.0.1 12345
 */
public class Main {

    public static void main(String[] args)  {
        ServerSocket  serverSocket = null;
        try {
            serverSocket = new ServerSocket(12345);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("connecting error...");
        }
        Boolean serverOn = true;

        while (serverOn){

            Socket socket = null;
            try {
                socket = serverSocket.accept();

            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream, true);

            printWriter.println("--------------------------");
            printWriter.println("Connecting server at port : "+socket.getPort()+ "\n >  "+socket.getInetAddress()+" \t"
                    + new Date().toString());
            printWriter.println("Type  Exit. to stop the server... ");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader( socket.getInputStream()));
            // echo
            String textLine;
            while ((textLine = in.readLine()) != null)
            {
                printWriter.println(" > "+textLine);

                if (textLine.equals("Exit.")){printWriter.println("server stopped... Bye!!");
                serverOn=false;
                break;}
            }} catch (IOException e) {
            e.printStackTrace();
        }
        }
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
