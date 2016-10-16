import java.io.*;
import java.net.*;

public class TestClient 
{
   public static void main(String[] args)
   {
      String host = "sciris.shu.edu";
      try 
      {
         Socket           client    = new Socket(host, 4321);
         DataOutputStream socketOut = new DataOutputStream(client.getOutputStream());
         DataInputStream  socketIn  = new DataInputStream(client.getInputStream());
         DataInputStream  console   = new DataInputStream(System.in);
         System.out.println("Connected to " + host + ". Enter text:");

         boolean done = false;
         String line;
         while (!done) 
         {
            line = console.readLine();
            if (line.equalsIgnoreCase(".bye"))
               done = true;
            socketOut.writeBytes(line + '\n');
         }

         socketOut.close(); socketIn.close(); client.close();
      } 
      catch (UnknownHostException e) 
      { System.err.println(host + ": unknown host."); } 
      catch (IOException e) 
      { System.err.println("I/O error with " + host); }
   }
}