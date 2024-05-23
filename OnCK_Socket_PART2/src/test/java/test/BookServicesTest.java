package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import entity.Book;

import org.junit.jupiter.api.Test;

import services.BookServices;
import services.impl.BookImpl;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookServicesTest {
	private BookServices bookServices;
	
	@BeforeAll
	public void setUp() {
		bookServices = new BookImpl();
	}
	
	@Test
	public void testListRatedBooks() {
		List<Book> lst = bookServices.listRatedBooks("Robert C. Martin", 4);
		lst.forEach(System.out::println);
		assertEquals(lst.size(), 3);
	}
	
	@Test
	public void testCountBooksByAuthors() {
		Map<String, Long> lst = bookServices.countBooksByAuthors();
		lst.forEach((k, v) -> System.out.println(k + " : " + v));
		lst.forEach((k, v) -> {
			if (k.equals("Robert C. Martin")) {
				assertEquals(v, 2);
			}
		});
	}
	
//	@Test
//	public void testUpdateReviews() {
//		boolean result = bookServices.updateReviews("888-0132350800", "20", 5, "Good book");
//		assertEquals(result, true);
//	}
	
	@AfterAll
	public void tearDown() {
		bookServices = null;
	}
}
