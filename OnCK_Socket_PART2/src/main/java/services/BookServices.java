package services;

import java.util.List;
import java.util.Map;

import entity.Book;

public interface BookServices {
	public List<Book> listRatedBooks(String author, int rating);
	public List<Book> getAllBook();
	public Map<String, Long> countBooksByAuthors();
	public boolean updateReviews(String isbn, String readerId, int rating, String comment);
	
}
