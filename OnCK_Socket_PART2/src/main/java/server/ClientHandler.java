package server;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import entity.Book;
import services.BookServices;
import services.impl.BookImpl;

public class ClientHandler implements Runnable {
    private Socket socket;
    private BookServices bookServices;

    public ClientHandler(Socket client) {
        this.socket = client;
        this.bookServices = new BookImpl();
    }

    @Override
    public void run() {
        try (DataInputStream in = new DataInputStream(socket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

            while (true) {
                int choice = in.readInt();
                switch (choice) {
                    case 1:
                        String author = in.readUTF();
                        int rating = in.readInt();
                        System.out.println("Author: " + author + " Rating: " + rating);
                        List<Book> books = bookServices.listRatedBooks(author, rating);
                        books.forEach(System.out::println);
                        out.writeObject(books);
                        out.flush();
                        break;
                    case 2:
                        out.writeObject(bookServices.getAllBook());
                        out.flush();
                        break;
                    case 3:
                        out.writeObject(bookServices.countBooksByAuthors());
                        out.flush();
                        break;
                    case 4:
                        String isbn = in.readUTF();
                        String readerId = in.readUTF();
                        int rating1 = in.readInt();
                        String comment = in.readUTF();
                        out.writeBoolean(bookServices.updateReviews(isbn, readerId, rating1, comment));
                        out.flush();
                        break;
                    case 5:
                        socket.close();
                        return;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Client Disconnected");
            e.printStackTrace();
        }
    }
}
