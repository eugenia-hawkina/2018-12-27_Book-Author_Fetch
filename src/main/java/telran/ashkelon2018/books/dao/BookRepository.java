package telran.ashkelon2018.books.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import telran.ashkelon2018.books.domain.Author;
import telran.ashkelon2018.books.domain.Book;

@Repository
public class BookRepository {
	
//	@PersistenceContext(type=PersistenceContextType.EXTENDED) - BAD idea
	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public void addBooks() {
		Author markTwain = new Author();
		markTwain.setName("Mark Twain");
		em.persist(markTwain);
		
		Book pandp = new Book();
		pandp.setIsbn("978-01403500173");
		pandp.setAuthors(new HashSet<>(Arrays.asList(markTwain)));
		pandp.setTitle("The Prince and the Pauper");
		em.persist(pandp);
		
		
		Author ilf = new Author();
		ilf.setName("Ilya Ilf");
		em.persist(ilf);
		
		Author petrov = new Author();
		petrov.setName("Evgeny Petrov");
		em.persist(petrov);
		
		Book chairs12 = new Book();
		chairs12.setIsbn("978-081011485");
		chairs12.setTitle("Twelve chairs");
		chairs12.setAuthors(new HashSet<>(Arrays.asList(ilf, petrov)));
		em.persist(chairs12);
	}
	
// 	@Transactional(readOnly=true) - ok variant
	public void printAuthorsOfBook(String isbn) {		
	
		TypedQuery<Book> query = em.createQuery("select b from Book b join fetch b.authors a where b.isbn=?1", Book.class);
		query.setParameter(1, isbn);
		Book book = query.getSingleResult();
		
//		Book book = em.find(Book.class, isbn); - to bad idea and ok variant
		Set<Author> authors = book.getAuthors();
		authors.forEach(a -> System.out.println(a.getName()));
	}

}
