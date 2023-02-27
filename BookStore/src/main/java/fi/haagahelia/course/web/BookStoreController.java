package fi.haagahelia.course.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fi.haagahelia.course.domain.Book;
import fi.haagahelia.course.domain.BookStoreRepository;

@Controller
public class BookStoreController {
	
	@Autowired
	private BookStoreRepository repository; 


    @RequestMapping(value= {"/"})
    public String bookList(Model model) {	
        model.addAttribute("books", repository.findAll());
        return "index";
    }

}
