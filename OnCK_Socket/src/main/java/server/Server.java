package server;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try(ServerSocket serverSocket = new ServerSocket(7878)) {
			System.out.println("Server is listening on port 7878");
			while(true) {
				Socket client = serverSocket.accept();
				System.out.println("Client connected");
				System.out.println("Client IP: " + client.getInetAddress().getHostAddress());
				System.out.println("Client Port: " + client.getPort());
				
				Thread t = new Thread(new ClientHandler(client));
				t.start();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}