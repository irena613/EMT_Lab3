//package mk.ukim.finki.emt.lab.config;
//
//import jakarta.annotation.PostConstruct;
//
//import mk.ukim.finki.emt.lab.model.domain.User;
//import mk.ukim.finki.emt.lab.repository.UserRepository;
//import mk.ukim.finki.emt.lab.service.domain.AuthorService;
//import mk.ukim.finki.emt.lab.service.domain.BookService;
//import mk.ukim.finki.emt.lab.model.enums.Category;
//import mk.ukim.finki.emt.lab.service.domain.CountryService;
//import mk.ukim.finki.emt.lab.service.domain.WishlistService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import mk.ukim.finki.emt.lab.model.enums.Role;
//
//@Component
//public class DataInitializer {
//
//    private final AuthorService authorService;
//    private final BookService bookService;
//    private final CountryService countryService;
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final WishlistService wishlistService;
//
//    public DataInitializer(AuthorService authorService, BookService bookService, CountryService countryService
//                           , UserRepository userRepository,
//                           PasswordEncoder passwordEncoder, WishlistService wishlistService
//    ) {
//        this.authorService = authorService;
//        this.bookService = bookService;
//        this.countryService = countryService;
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//
//        this.wishlistService = wishlistService;
//    }
//
//
//    private Category randomize(int i) {
//        if (i % 7 == 0) return Category.NOVEL;
//        else if (i % 7 == 1)return Category.THRILER;
//        else if (i % 7 == 2)return Category.HISTORY;
//        else if (i % 7 == 3)return Category.FANTASY;
//        else if (i % 7 == 4)return Category.BIOGRAPHY;
//        else if (i % 7 == 5)return Category.CLASSICS;
//        return Category.DRAMA;
//    }
//
//    @PostConstruct
//    public void initData() {
////        userRepository.save(new User(
////                "user",
////                passwordEncoder.encode("user"),
////                "Irena",
////                "Latinovska",
////                Role.ROLE_USER
////        ));
////
////        userRepository.save(new User(
////                "librarian",
////                passwordEncoder.encode("librarian"),
////                "BangTan",
////                "Sonyeondan",
////                Role.ROLE_LIBRARIAN
////        ));
////
////        userRepository.save(new User(
////                "admin",
////                passwordEncoder.encode("admin"),
////                "BangTan",
////                "Sonyeondan",
////                Role.ROLE_ADMIN
////        ));
//
////        for (int i = 1; i < 7; i++) {
////            this.countryService.create("Country: " + i, "Continent: " + (i/2+1));
////        }
////
////        for (int i = 1; i < 6; i++) {
////            this.authorService.create("AuthorName: " + i, "AuthorSurname: " + i, this.countryService.listAll().get((i - 1) % 6).getId());
////        }
//
//        for (int i = 11; i < 18; i++) {
//            //this.bookService.create("Book: " + i, this.randomize(i), this.authorService.listAll().get((i - 1) % 5).getId(), userRepository.findByUsername("Irena").orElseThrow(InvalidUsernameOrPasswordException::new), Long.parseLong("7"));
//            this.bookService.create("Book: " + i, this.randomize(i), this.authorService.listAll().get((i - 1) % 5).getId(), Long.parseLong("7"), false);
//        }
//
//    }
//}
//
