package mk.ukim.finki.emt.lab.service.aplication.impl;

import mk.ukim.finki.emt.lab.model.domain.Wishlist;
import mk.ukim.finki.emt.lab.model.dto.previousDTOs.WishlistDto;
import mk.ukim.finki.emt.lab.service.aplication.WishlistApplicationService;
import mk.ukim.finki.emt.lab.service.domain.WishlistService;
import org.springframework.stereotype.Service;

@Service
public class WishlistApplicationServiceImpl implements WishlistApplicationService {
    private final WishlistService wishlistService;

    public WishlistApplicationServiceImpl(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @Override
    public WishlistDto viewWishlist(String username) {
        Wishlist wishlist = wishlistService.viewWishlist(username);
        return WishlistDto.from(wishlist);
    }

    @Override
    public WishlistDto addBookToWishlist(String username, Long bookId) {
        Wishlist wishlist = wishlistService.addBookToWishlist(username, bookId);
        return WishlistDto.from(wishlist);
    }

    @Override
    public void removeBookFromWishlist(String username, Long bookId) {
        wishlistService.removeBookFromWishlist(username, bookId);
    }

    @Override
    public void rentAllBooksFromWishlist(String username) {
        wishlistService.rentAllBooksFromWishlist(username);
    }

    // @Override
    // public boolean canAddBookToWishlist(Long bookId) {
    //     return wishlistService.canAddBookToWishlist(bookId);
    // }
}
