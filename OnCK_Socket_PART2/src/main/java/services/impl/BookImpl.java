package services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Book;
import entity.Person;
import entity.Reviews;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import services.BookServices;

public class BookImpl implements BookServices {
	private static final String PERSISTENCE_UNIT_NAME = "jpa-mssql";
	private EntityManager em;

	public BookImpl() {
		try {
			em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	@Override
	public List<Book> listRatedBooks(String author, int rating) {
		String jpql = "SELECT b FROM Book b JOIN b.authors a JOIN b.reviews r WHERE a = :author AND r.rating >= :rating";
		TypedQuery<Book> query = em.createQuery(jpql, Book.class);
		query.setParameter("author", author);
		query.setParameter("rating", rating);
		List<Book> books = query.getResultList();
		return books;	
	}

//	Native query
	@Override
	public Map<String, Long> countBooksByAuthors() {
	    Map<String, Long> result = new HashMap<>();
	    String query = "select ba.author, CAST(COUNT(ba.ISBN) AS BIGINT) as quantity from book_translations bt join books_authors ba "
	        + "on bt.ISBN = ba.ISBN "
	        + "group by ba.author "
	        + "order by ba.author";
	    @SuppressWarnings("unchecked")
	    List<Object[]> list = em.createNativeQuery(query).getResultList();
	    for (Object[] obj : list) {
	        result.put((String) obj[0], (Long) obj[1]);
	    }
	    return result;
	}
	
//	JPQL
//	@Override
//	public Map<String, Long> countBooksByAuthors() {
//	    Map<String, Long> result = new HashMap<>();
//	    String jpql = "SELECT a, COUNT(b) from BookTranslation b JOIN b.authors a GROUP BY a, ORDER BY a";
//	    TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
//	    List<Object[]> list = query.getResultList();
//		for (Object[] obj : list) {
//			result.put((String) obj[0], (Long) obj[1]);
//		}
//		return result;
//	}

	@Override
	public boolean updateReviews(String isbn, String readerId, int rating, String comment) {
		Book book = em.find(Book.class, isbn);
		Person person = em.find(Person.class, readerId);
		if (book == null || rating < 1 || rating > 5 || comment == null || comment.isEmpty() || person == null) {
			return false;
		}
		Reviews reviews = new Reviews(rating, comment);
		reviews.setBook(book);
		reviews.setPerson(person);
		try {
			em.getTransaction().begin();
			em.persist(reviews);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}


	@Override
	public List<Book> getAllBook() {
		TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b", Book.class);
		List<Book> books = query.getResultList();
		return books;
	}

}
