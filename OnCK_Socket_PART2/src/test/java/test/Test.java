package test;

import java.util.List;
import java.util.Map;

import entity.Book;
import jakarta.persistence.Persistence;
import services.BookServices;
import services.impl.BookImpl;

public class Test {
	public static void main(String[] args) {
//		System.out.println("Creating .......");
//		Persistence.createEntityManagerFactory("jpa-mssql");
//		System.out.println("Done");
		BookServices bookServices = new BookImpl();
//		List<Book> lst = bookServices.getAllBook();
//		List<Book> lst = bookServices.listRatedBooks("Robert C. Martin", 4);
//		lst.forEach(System.out::println);
//		Map<String, Long> lst = bookServices.countBooksByAuthors();
//		lst.forEach((k, v) -> System.out.println(k + " : " + v));
		boolean result = bookServices.updateReviews("888-0132350800", "20", 5, "Good book");
		if (result) {
			System.out.println("Updated");
		} else {
			System.out.println("Failed");
		}
	}
}
