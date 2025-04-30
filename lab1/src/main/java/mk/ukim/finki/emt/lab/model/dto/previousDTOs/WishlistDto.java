package mk.ukim.finki.emt.lab.model.dto.previousDTOs;

import mk.ukim.finki.emt.lab.model.domain.Wishlist;
import mk.ukim.finki.emt.lab.model.dto.display.DisplayBookDto;

import java.time.LocalDateTime;
import java.util.List;

public record WishlistDto(
    Long id,
    LocalDateTime dateCreated,
    List<DisplayBookDto> books,
    String username
) {
    public static WishlistDto from(Wishlist wishlist) {
        return new WishlistDto(
            wishlist.getId(),
            wishlist.getDateCreated(),
            DisplayBookDto.from(wishlist.getBooks()),
            wishlist.getUser().getUsername()
        );
    }
}
