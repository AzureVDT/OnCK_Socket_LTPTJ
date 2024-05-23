package entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="book_translations")
public class BookTranslation extends Book implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 481029095912392811L;
	@Column(name="translate_name")
	private String translateName;
	private String language;
	
	public BookTranslation() {
		super();
	}

	public BookTranslation(String translateName, String language) {
		super();
		this.translateName = translateName;
		this.language = language;
	}

	public BookTranslation(String ISBN, String name, int publishYear, int numOfPages, double price,
			String translateName, String language) {
		super(ISBN, name, publishYear, numOfPages, price);
		this.translateName = translateName;
		this.language = language;
	}

	public String getTranslateName() {
		return translateName;
	}

	public void setTranslateName(String translateName) {
		this.translateName = translateName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return "BookTranslation [translateName=" + translateName + ", language=" + language + ", ISBN=" + ISBN
				+ ", name=" + name + ", publishYear=" + publishYear + ", numOfPages=" + numOfPages + ", price=" + price
				+ "]";
	}
	
	
	
	
	
	
	
}
