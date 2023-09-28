package uk.co.diegesis.kylefinch.gt2;

public class ClientMain {

	public static void main(String[] args) {
		// An instance of the ClientManager is made with the IP address and TCP port as arguments
		ClientManager client = new ClientManager(Constants.LOCAL_IP_ADDRESS, Constants.TCP_PORT);
		System.exit(0);
	}
}