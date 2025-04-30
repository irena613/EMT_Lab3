package mk.ukim.finki.emt.lab.service.domain.impl;

import mk.ukim.finki.emt.lab.model.domain.Book;
import mk.ukim.finki.emt.lab.model.domain.User;
import mk.ukim.finki.emt.lab.model.domain.Wishlist;
import mk.ukim.finki.emt.lab.model.exceptions.BookNotFoundException;
import mk.ukim.finki.emt.lab.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.emt.lab.model.exceptions.WishlistNotFoundException;
import mk.ukim.finki.emt.lab.repository.BookRepository;
import mk.ukim.finki.emt.lab.repository.UserRepository;
import mk.ukim.finki.emt.lab.repository.WishlistRepository;
import mk.ukim.finki.emt.lab.service.domain.BookService;
import mk.ukim.finki.emt.lab.service.domain.UserService;
import mk.ukim.finki.emt.lab.service.domain.WishlistService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistServiceImpl implements WishlistService {
    private final WishlistRepository wishlistRepository;
    private final BookService bookService;
    private final UserService userService;
    private final UserRepository userRepository;

    public WishlistServiceImpl(WishlistRepository wishlistRepository, 
                             BookService bookService, 
                             UserService userService,
                             UserRepository userRepository) {
        this.wishlistRepository = wishlistRepository;
        this.bookService = bookService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public Wishlist viewWishlist(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
                
        return wishlistRepository.findByUserUsername(username)
                .orElseGet(() -> {
                    Wishlist newWishlist = new Wishlist(LocalDateTime.now(), new ArrayList<>(), user);
                    return wishlistRepository.save(newWishlist);
                });
    }

    @Override
    public Wishlist addBookToWishlist(String username, Long bookId) {
        if (!canAddBookToWishlist(bookId)) {
            throw new RuntimeException("Cannot add book to wishlist - no available copies");
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Wishlist wishlist = wishlistRepository.findByUserUsername(username)
                .orElseGet(() -> {
                    Wishlist newWishlist = new Wishlist(LocalDateTime.now(), new ArrayList<>(), user);
                    return wishlistRepository.save(newWishlist);
                });

        Book book = bookService.findById(bookId).orElseThrow(BookNotFoundException::new);

        if (!wishlist.getBooks().contains(book)) {
            wishlist.getBooks().add(book);
            return wishlistRepository.save(wishlist);
        }
        return wishlist;
    }

    @Override
    public void removeBookFromWishlist(String username, Long bookId) {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));

        Wishlist wishlist = wishlistRepository.findByUserUsername(username)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));

        Book book = bookService.findById(bookId).orElseThrow(BookNotFoundException::new);

        wishlist.getBooks().remove(book);
        wishlistRepository.save(wishlist);
    }

    @Override
    public void rentAllBooksFromWishlist(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Wishlist wishlist = wishlistRepository.findByUserUsername(username)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));
    
        List<Book> booksToRent = new ArrayList<>(wishlist.getBooks());
        
        for (Book book : booksToRent) {
            if (book.getAvailableCopies() > 0) {
                bookService.isRented(book);
            }
        }
        
        wishlist.getBooks().clear();
        wishlistRepository.save(wishlist);
    }

    @Override
    public boolean canAddBookToWishlist(Long bookId) {
        return bookService.findById(bookId)
                .map(book -> book.getAvailableCopies() > 0)
                .orElse(false);
    }
}