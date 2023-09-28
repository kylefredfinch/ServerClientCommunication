package uk.co.diegesis.kylefinch.gt2;

import java.net.*;
import java.io.*;
import java.util.*;

public class ServerManager {
	private Socket socket;
	private ServerSocket server;
	private BufferedReader inputStream;
	private PrintWriter outputStream;
	
	String[] jokeQuestion = {
			Constants.JOKE_QUESTION_1,
			Constants.JOKE_QUESTION_2,
			Constants.JOKE_QUESTION_3,
			Constants.JOKE_QUESTION_4,
			Constants.JOKE_QUESTION_5,
	};
	
	String[] jokeAnswer = {
			Constants.JOKE_ANSWER_1,
			Constants.JOKE_ANSWER_2,
			Constants.JOKE_ANSWER_3,
			Constants.JOKE_ANSWER_4,
			Constants.JOKE_ANSWER_5
	};
	
	String[] riddleQuestion = {
			Constants.RIDDLE_QUESTION_1,
			Constants.RIDDLE_QUESTION_2,
			Constants.RIDDLE_QUESTION_3,
			Constants.RIDDLE_QUESTION_4,
			Constants.RIDDLE_QUESTION_5
	};
	
	String[] riddleAnswer = {
			Constants.RIDDLE_ANSWER_1,
			Constants.RIDDLE_ANSWER_2,
			Constants.RIDDLE_ANSWER_3,
			Constants.RIDDLE_ANSWER_4,
			Constants.RIDDLE_ANSWER_5
			
	};
	
	String[] funFact = {
			Constants.FUN_FACT_1,
			Constants.FUN_FACT_2,
			Constants.FUN_FACT_3,
			Constants.FUN_FACT_4,
			Constants.FUN_FACT_5,
	};

	// Create a ServerManager constructor which takes a specific TCP port
	public ServerManager(int serverPort) {
		try {
			// ServerSocket listening to 'serverPort'
			server = new ServerSocket(serverPort);
			System.out.println(Constants.SERVER_STARTED);
			System.out.println(Constants.WAITING_FOR_CLIENT);
			
			// Instantiate Socket, wait for attempted client connection, and accept when it comes
			socket = server.accept();
			System.out.println(Constants.CLIENT_FOUND);
			
			// Instantiate input and output streams from Server
			inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			outputStream = new PrintWriter(socket.getOutputStream(), true);
			
			outputStream.println(Constants.MENU);
			
			String inputLine = inputStream.readLine();
			
			while((inputLine = inputStream.readLine()) != null) {
				try {
					if (inputLine.toLowerCase().contains(Constants.JOKE)) {
						// Respond with a random joke question
						int randomIndex = new Random().nextInt(jokeQuestion.length);
						outputStream.println(jokeQuestion[randomIndex]);
						
						// Pause for 2 seconds
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							System.err.println(Constants.INTERRUPTION + e);
						}
						
						// Respond with the corresponding answer
						outputStream.println(jokeAnswer[randomIndex]);
					} else if (inputLine.toLowerCase().contains(Constants.RIDDLE)){
						// Respond with a random riddle question
						int randomIndex = new Random().nextInt(riddleQuestion.length);
							outputStream.println(riddleQuestion[randomIndex]);
							
							// Pause for 3 seconds
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								System.err.println("Interrupted Exception: " + e);
							}
							
							outputStream.println(Constants.RIDDLE_READY);
							String userResponse = inputStream.readLine();
							
							if (userResponse.toLowerCase().contains(Constants.YES)) {
								// Provide the corresponding answer
								outputStream.println(riddleAnswer[randomIndex]);
							} else if (userResponse.toLowerCase().contains(Constants.NO)) {
								// Continuously prompt the user until they type "ready" to reveal the answer
								while (true) {
									outputStream.println(Constants.HAVE_A_THINK);
									// Prepare to read the next line by the user
									userResponse = inputStream.readLine();
									if (userResponse.toLowerCase().contains(Constants.READY)) {
										// Provide the corresponding answer
										outputStream.println(riddleAnswer[randomIndex]);
										break;
									}
								}
							}
						} else if (inputLine.toLowerCase().contains(Constants.FACT)) {
							// Respond with a random fact
							int randomIndex = new Random().nextInt(funFact.length);
								outputStream.println(funFact[randomIndex]);
						} else if (inputLine.toLowerCase().contains(Constants.STOP)){
						// Exit the loop if "Stop" is received
							break;
					} else {
						outputStream.println(Constants.MENU);
					}
				} catch (IOException e) {
					System.err.println(e);
				}
			}
			System.out.println(Constants.CLOSING_CONNECTION);
			inputStream.close();
			outputStream.close();
			socket.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}