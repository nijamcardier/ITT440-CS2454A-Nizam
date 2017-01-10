import socket               # Import socket module
import RPi.GPIO as GPIO
import time


s = socket.socket()         # Create a socket object
host = socket.gethostname()       # Get local machine name
port = 12345                # Reserve a port for your service.
s.bind((host, port))        # Bind to the port

print"Waiting 4 connections!"

s.listen(5)                 # Now wait for client connection.
while True:
   c, addr = s.accept()     # Establish connection with client.
   print 'Got connection from', addr
   c.send('Thank you for connecting')
   print"LED ON"
   GPIO.setmode(GPIO.BCM)
   GPIO.setwarnings(False)
   GPIO.setup(18,GPIO.OUT)
  
   GPIO.output(18,GPIO.HIGH)
   time.sleep(4)
   print"\n LED OFF"
   GPIO.output(18,GPIO.LOW)
   
   c.shutdown(1)
   c.close()                # Close the connection

