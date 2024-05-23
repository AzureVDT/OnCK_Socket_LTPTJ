package server;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try(ServerSocket server = new ServerSocket(7878)) {
			System.out.println("Server is running on port 7878");
			while(true) {
				Socket client = server.accept();
				System.out.println("Client Connected");
				System.out.println("Client IP: " + client.getInetAddress().getHostAddress());
				System.out.println("Client Port: " + client.getPort());
				
				Thread t = new Thread(new ClientHandler(client));
				t.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
