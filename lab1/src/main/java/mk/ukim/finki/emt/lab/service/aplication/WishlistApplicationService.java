package mk.ukim.finki.emt.lab.service.aplication;

import mk.ukim.finki.emt.lab.model.dto.previousDTOs.WishlistDto;

public interface WishlistApplicationService {
    WishlistDto viewWishlist(String username);
    
    WishlistDto addBookToWishlist(String username, Long bookId);
    
    void removeBookFromWishlist(String username, Long bookId);
    
    void rentAllBooksFromWishlist(String username);
    
    // boolean canAddBookToWishlist(Long bookId);
}
