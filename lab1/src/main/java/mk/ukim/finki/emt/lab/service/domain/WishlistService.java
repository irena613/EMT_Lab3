package mk.ukim.finki.emt.lab.service.domain;

import mk.ukim.finki.emt.lab.model.domain.Wishlist;


public interface WishlistService {
    Wishlist viewWishlist(String username);
    
    Wishlist addBookToWishlist(String username, Long bookId);
    
    void removeBookFromWishlist(String username, Long bookId);
    
    void rentAllBooksFromWishlist(String username);
    
    boolean canAddBookToWishlist(Long bookId);
}
