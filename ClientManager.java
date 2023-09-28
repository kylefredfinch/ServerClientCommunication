package uk.co.diegesis.kylefinch.gt2;

import java.net.*;
import java.io.*;

public class ClientManager {
	private Socket socket;
	private BufferedReader inputStream;
	private BufferedReader userInput;
	private PrintWriter outputStream;
	
	// Create a ClientManager constructor which takes a specific TCP port, and IP address
	public ClientManager(String serverAddress, int serverPort) {
		try {
			socket = new Socket(serverAddress, serverPort);
			System.out.println(Constants.SOCKET_OPEN);
			
			inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			userInput = new BufferedReader(new InputStreamReader(System.in));
			outputStream = new PrintWriter(socket.getOutputStream(), true);
			
			// Display the menu to the user
			System.out.println(Constants.MENU);
			
			String fromUser;
			
			// Read and send the first user input to the server
			fromUser = userInput.readLine();
			outputStream.println(fromUser);
			
			// Start a new thread to read and display server responses
			Thread serverResponsethread = new Thread(() -> {
				try {
					String fromServer;
					while((fromServer = inputStream.readLine()) != null) {
						System.out.println(Constants.SERVER + fromServer);
					}
				} catch (IOException e) {
					System.err.println(Constants.ERROR_SERVER_RESPONSE + e);
				}
			});
			serverResponsethread.start();
			
			// Read user input and send it to the server
			while (true) {
				fromUser = userInput.readLine();
				outputStream.println(fromUser);
				if (fromUser.toLowerCase().contains(Constants.STOP)) {
					break;
				}
			}	
		} catch(IOException e) {
			System.err.println(Constants.IOEXCEPTION_ERROR + e.getMessage());
		}
		try {
			System.out.println(Constants.CLOSING_CONNECTION);
			// Close the resources
			inputStream.close();
			outputStream.close();
			userInput.close();
			socket.close();
		} catch(IOException e) {
			System.err.println(Constants.RESOURCE_CLOSE_ERROR + e.getMessage());
		}
	}
}
