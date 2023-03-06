package fi.haagahelia.course;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.course.domain.AppUser;
import fi.haagahelia.course.domain.AppUserRepository;
import fi.haagahelia.course.domain.Book;
import fi.haagahelia.course.domain.BookStoreRepository;
import fi.haagahelia.course.domain.Category;
import fi.haagahelia.course.domain.CategoryRepository;



@SpringBootApplication
public class BookStoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookStoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookStoreRepository brepository, CategoryRepository crepository, AppUserRepository urepository) {
		return (args) -> {
			log.info("save couple books");
			
			
			crepository.save(new Category("Fiction"));
			crepository.save(new Category("Nonfiction"));
			crepository.save(new Category("Drama"));
			crepository.save(new Category("Poetry"));
			crepository.save(new Category("Folktale"));
			
			brepository.save(new Book("Harry Potter Deathy Hallows", "J.K Rowling", 2007, "9780545010221", 9.99, crepository.findByName("Fiction").get(0)));
		
			AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			AppUser user3 = new AppUser("alex", "$2y$10$LE/DXurNQY4Q7mWV6.V8o.qKMbW35p4dIz2bUaFXUnPROujIAb2cu", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
	
			
			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}
			

		};
	}

}
