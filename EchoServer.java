import java.net.*;
import java.io.IOException;
import java.io.*;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;


public class EchoServer
{
 public static void main(String[] args) throws IOException,InterruptedException 
   {
    ServerSocket serverSocket = null;

    try {
         serverSocket = new ServerSocket(10007);
        }
    catch (IOException e)
        {
         System.err.println("Could not listen on port: 10007.");
         System.exit(1);
        }

    Socket clientSocket = null;
System.out.println ("Waiting for connection.....");

    try {
         clientSocket = serverSocket.accept();
        }
    catch (IOException e)
        {
         System.err.println("Accept failed.");
         System.exit(1);
        }

    System.out.println ("Connection successful");

// create gpio controller
        final GpioController gpio = GpioFactory.getInstance();

        // provision gpio pin #01 as an output pin and turn on
        final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.HIGH);

        // set shutdown state for this pin
        pin.setShutdownOptions(true, PinState.LOW);

        System.out.println("--> GPIO state should be: ON");

      // Thread.sleep(5000);  

        // turn off gpio pin #01
       // pin.low();



    System.out.println ("Waiting for input.....");

    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),
                                      true);
    BufferedReader in = new BufferedReader(
            new InputStreamReader( clientSocket.getInputStream()));
			
			
			
			String inputLine;

    while ((inputLine = in.readLine()) != null)
        {


         if( inputLine != null) {
         System.out.println ("Message From Client: " + inputLine);
         out.println(inputLine); }


         if (inputLine.equals("Exit."))
             break;
        }

    out.close();
    in.close();
    clientSocket.close();
    pin.low();
   System.out.println("THE LED SHOULD BE OFF");
    serverSocket.close();
   }
   }

