package fi.haagahelia.course;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fi.haagahelia.course.domain.Book;
import fi.haagahelia.course.domain.Category;
import fi.haagahelia.course.domain.BookStoreRepository;
import fi.haagahelia.course.domain.CategoryRepository;
import fi.haagahelia.course.web.BookStoreController;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class BookStoreApplicationTests {

	 @Autowired
	 private BookStoreRepository repository;
	 
	 @Autowired
	 private CategoryRepository crepository;
	 @Test
	 public void findByTitleShouldReturnAuthor() {
	 List<Book> books = repository.findBytitle("Harry Potter Deathy Hallows");
	 //assertThat(books).hasSize(1);
	 assertThat(books.get(0).getAuthor()).isEqualTo("J.K Rowling");
	 }
	 @Test
	 public void createNewBook() {
	 Book book = new Book("A Head Full of Ghosts", "Paul Trembley", 2015, 
	 "ISBN434345621394", 16.30, crepository.findByName("Fiction").get(0));
	 repository.save(book);
	 assertThat(book.getId()).isNotNull();
	 }
	 
	 @Test
	 public void deleteNewBook() {
		 List<Book> books = repository.findBytitle("Holy Bible: : English Standard Version");
		 Book book = books.get(0);
		 repository.delete(book);
		 List<Book> newBooks = repository.findBytitle("Holy Bible: : English Standard Version");
		 assertThat(newBooks).hasSize(0);
		 
		
		 }
	 
	 	@Test
	 	public void findByCategoryName() {
	 		List<Category> categories = crepository.findByName("Fiction");
	 		assertThat(categories).hasSize(1);
	 	 
	 }
	 	@Test
	 	public void createNewCategory() {
	 		Category category = new Category("BedTime story");
	 		crepository.save(category);
	 		assertThat(category.getCategoryid()).isNotNull();
	 	}
	 	
	 	@Test
	 	public void deleteNewCategory() {
	 		List<Category> categories = crepository.findByName("Fiction");
	 		Category category = categories.get(0);
	 		crepository.delete(category);
	 		List<Category> newCategories = crepository.findByName("Fiction");
	 		assertThat(newCategories).hasSize(0);
			 
	 		
	 	}
	 
	 }