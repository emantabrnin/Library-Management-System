package com.example.librarymanagment.service;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.librarymanagment.ValidationException;
import com.example.librarymanagment.entity.Book;
import com.example.librarymanagment.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookService bookService;

    @Mock
    private BookRepository bookRepo;

    @Test
    public void createBook_thenValidate() throws Exception {
        bookService = new BookService(bookRepo);
        UUID id = UUID.randomUUID();
        Book book = createBookWithId(id, "Book1", "Author1", 2022, "1234567890",4);
    
        Mockito.when(bookRepo.save(book)).thenReturn(book);
    
        Book bookSaved = bookService.save(book);
        
        Assertions.assertEquals(id, bookSaved.getId());
        Assertions.assertEquals("Book1", bookSaved.getTitle());
        Assertions.assertEquals("Author1", bookSaved.getAuthor());
        Assertions.assertEquals(2022, bookSaved.getPublicationYear());
        Assertions.assertEquals("1234567890", bookSaved.getIsbn());
        Assertions.assertEquals(4, bookSaved.getAvailableCopies());
    }

    private Book createBookWithId(UUID id,String title,String auther,int publicationYear, String isbn,int availableCopies){
        return new Book(id, title, auther, publicationYear, isbn,availableCopies);
    }

 

    @Test
    public void editBook_thenValidate() throws ValidationException{
        bookService = new BookService(bookRepo);
        UUID id = UUID.randomUUID();
        Book book = createBookWithId(id, "Book1", "Author1", 2022, "1234567890",4);
    
        Mockito.when(bookRepo.save(book)).thenReturn(createBookWithId(id, "Book1-edit", "Author1", 2022, "1234567890",4));

        Book bookEdit = bookService.save(book);
        Assertions.assertEquals(id, bookEdit.getId());
        Assertions.assertEquals("Book1-edit", bookEdit.getTitle());
        Assertions.assertEquals("Author1", bookEdit.getAuthor());
        Assertions.assertEquals(2022, bookEdit.getPublicationYear());
        Assertions.assertEquals("1234567890", bookEdit.getIsbn());
        Assertions.assertEquals(4, bookEdit.getAvailableCopies());
    }

    @Test
    public void getBookById_thenValidate(){
        bookService = new BookService(bookRepo);
        UUID id = UUID.randomUUID();
        Book book = createBookWithId(id, "Book1", "Author1", 2022, "1234567890",4);
        Mockito.when(bookRepo.findById(id)).thenReturn(Optional.of(book));
        Book book2 = bookRepo.findById(id).get();
        Assertions.assertEquals(id, book2.getId());
        Assertions.assertEquals("Book1", book2.getTitle());
        Assertions.assertEquals("Author1", book2.getAuthor());
        Assertions.assertEquals(2022, book2.getPublicationYear());
        Assertions.assertEquals("1234567890", book2.getIsbn());
        Assertions.assertEquals(4, book2.getAvailableCopies());
    }

    @Test
    public void deleteBook_thenValidate(){
        bookService = new BookService(bookRepo);
        UUID id = UUID.randomUUID();
        bookService.delete(id);
        Mockito.verify(bookRepo,Mockito.times(1)).deleteById(id);
    }
   
   
}
