package client;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import entity.Book;

public class Client {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        try (Socket socket = new Socket("AzureVDT", 7878); Scanner sc = new Scanner(System.in)) {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            int choice = 0;
            while (true) {
                System.out.println("1. listRatedBooks");
                System.out.println("2. getAllBook");
                System.out.println("3. countBooksByAuthors");
                System.out.println("4. updateReviews");
                System.out.println("5. Exit");

                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                out.writeInt(choice);
                out.flush();

                switch (choice) {
                    case 1:
                        sc.nextLine();
                        System.out.println("Enter author: ");
                        String author = sc.nextLine();
                        out.writeUTF(author);
                        out.flush();
                        System.out.println("Enter rating: ");
                        int rating = sc.nextInt();
                        out.writeInt(rating);
                        out.flush();
                        List<Book> books = (List<Book>) in.readObject();
                        for (Book book : books) {
                            System.out.println(book);
                        }
                        break;
                    case 2:
                        List<Book> books1 = (List<Book>) in.readObject();
                        for (Book book : books1) {
                            System.out.println(book);
                        }
                        break;
                    case 3:
                        Map<String, Long> lst = (Map<String, Long>) in.readObject();
                        lst.forEach((k, v) -> {
                            System.out.println(k + " : " + v);
                        });
                        break;
                    case 4:
                        sc.nextLine();
                        System.out.println("Enter ISBN: ");
                        String isbn = sc.nextLine();
                        System.out.println("Enter readerId: ");
                        String readerId = sc.nextLine();
                        System.out.println("Enter rating: ");
                        int rating1 = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Enter comment: ");
                        String comment = sc.nextLine();
                        out.writeUTF(isbn);
                        out.writeUTF(readerId);
                        out.writeInt(rating1);
                        out.writeUTF(comment);
                        out.flush();
                        boolean result = in.readBoolean();
                        if (result) {
                            System.out.println("Review updated successfully");
                        } else {
                            System.out.println("Review not updated");
                        }
                        break;
                    case 5:
                        socket.close();
                        System.exit(0);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
