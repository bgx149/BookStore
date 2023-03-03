package fi.haagahelia.course.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.course.domain.Book;
import fi.haagahelia.course.domain.BookStoreRepository;
import fi.haagahelia.course.domain.CategoryRepository;



@Controller
public class BookStoreController {
	
	@Autowired
	private BookStoreRepository repository; 
	
	@Autowired
	private CategoryRepository crepository; 
	
	
	  @RequestMapping(value="/login")
	    public String login() {	
	        return "login";
	    }
	

		// Show all Books in the booklist
    @RequestMapping(value= {"/booklist"})
    public String bookList(Model model) {	
    	
        model.addAttribute("books", repository.findAll());
        return "booklist";
      
    }
     // RESTful service to get all books
       @RequestMapping(value="/books", method = RequestMethod.GET)
        public @ResponseBody List<Book> bookListRest() {	
            return (List<Book>) repository.findAll();
        }    

    	// RESTful service to get book by id
        @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
        public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
        	return repository.findById(bookId);
        }
        
        
    
    // Add new book to the list
    @RequestMapping(value = "/addbook")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categorys", crepository.findAll());
        return "addbook";
    }
    
    
    // save new book to the list
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        repository.save(book);
        return "redirect:booklist";
    } 
    
    // Delete book From the list
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	repository.deleteById(bookId);
        return "redirect:../booklist";
    }
    

}
