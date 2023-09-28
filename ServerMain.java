package uk.co.diegesis.kylefinch.gt2;

public class ServerMain {

	public static void main(String[] args) {
		// An instance of the ServerManager is made with the TCP port as the argument
		ServerManager server = new ServerManager(Constants.TCP_PORT);
		System.exit(0);
	}
}