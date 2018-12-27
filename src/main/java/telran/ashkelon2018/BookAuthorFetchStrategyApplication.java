package telran.ashkelon2018;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import telran.ashkelon2018.books.dao.BookRepository;

@SpringBootApplication
public class BookAuthorFetchStrategyApplication implements CommandLineRunner{
	@Autowired
	BookRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(BookAuthorFetchStrategyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repository.addBooks();
		repository.printAuthorsOfBook("978-081011485");
	}
}

