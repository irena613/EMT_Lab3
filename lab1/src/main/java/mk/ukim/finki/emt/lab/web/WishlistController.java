package mk.ukim.finki.emt.lab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.lab.model.dto.previousDTOs.WishlistDto;
import mk.ukim.finki.emt.lab.service.aplication.WishlistApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlist")
@Tag(name = "Wishlist Controller", description = "Operations related to wishlist management")
public class WishlistController {
    private final WishlistApplicationService wishlistApplicationService;

    public WishlistController(WishlistApplicationService wishlistApplicationService) {
        this.wishlistApplicationService = wishlistApplicationService;
    }

    @GetMapping("/{username}")
    @Operation(
        summary = "Get user's wishlist",
        description = "Retrieves the wishlist for a specific user, including all books in the wishlist."
    )
    public ResponseEntity<WishlistDto> getWishlist(
            @Parameter(description = "Username of the user whose wishlist to retrieve") 
            @PathVariable String username) {
        return ResponseEntity.ok(wishlistApplicationService.viewWishlist(username));
    }

    @PostMapping("/{username}/add/{bookId}")
    @Operation(
        summary = "Add book to wishlist",
        description = "Adds a book to a user's wishlist. The book must have available copies to be added."
    )
    public ResponseEntity<WishlistDto> addBookToWishlist(
            @Parameter(description = "Username of the user whose wishlist to modify") 
            @PathVariable String username,
            @Parameter(description = "ID of the book to add to the wishlist") 
            @PathVariable Long bookId) {
        try {
            return ResponseEntity.ok(wishlistApplicationService.addBookToWishlist(username, bookId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{username}/remove/{bookId}")
    @Operation(
        summary = "Remove book from wishlist",
        description = "Removes a book from a user's wishlist."
    )
    public ResponseEntity<Void> removeBookFromWishlist(
            @Parameter(description = "Username of the user whose wishlist to modify") 
            @PathVariable String username,
            @Parameter(description = "ID of the book to remove from the wishlist") 
            @PathVariable Long bookId) {
        try {
            wishlistApplicationService.removeBookFromWishlist(username, bookId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{username}/rent-all")
    @Operation(
        summary = "Rent all books from wishlist",
        description = "Attempts to rent all books from a user's wishlist. Books with available copies will be rented, and the wishlist will be cleared afterward."
    )
    public ResponseEntity<Void> rentAllBooksFromWishlist(
            @Parameter(description = "Username of the user whose wishlist books to rent") 
            @PathVariable String username) {
        ResponseEntity.ok(wishlistApplicationService.viewWishlist(username));
        try {
            wishlistApplicationService.rentAllBooksFromWishlist(username);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // @GetMapping("/can-add/{bookId}")
    // public ResponseEntity<Boolean> canAddBookToWishlist(@PathVariable Long bookId) {
    //     return ResponseEntity.ok(wishlistApplicationService.canAddBookToWishlist(bookId));
    // }
}
