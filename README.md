# ServerClientCommunication
This is a Java application that uses socket programming for a user to talk to a Server with various prompted replies.

The functionality of this program is for a server and a client to connect using socket programming through an IP address (using the local host IP address as the example), and a common TCP port.

Once the server is live (by instantiating a new ServerSocket instance), it will wait to accept a client. In the ClientManager class, a socket is created using the common TCP port which creates an environment where the Server and Client are live.

By making use of the in-built PrintWriter and BufferedReader classes (by importing java.net.*), I was able to include logic whereby the user can type messages in the Client console, it will be read by the ClientManager and communicate with the ServerManager.

At this point, the ServerManager is 'ready' to read a few different words - joke, riddle, fact and stop.

If the ServerManager reads the userInput that includes one of these words, it will then communicate back to the ClientManager and write a message back in the Client Console the corresponding use type. There are 5 random jokes, riddles and facts.

It is important to note that I have also set up threads for the riddle and joke functionality so I can have a 2 second delay between question and answer for user smoothness.

When the user types 'stop', then the socket closes and connection is terminated.

