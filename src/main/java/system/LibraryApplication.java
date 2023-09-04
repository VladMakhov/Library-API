package system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import system.model.Author;
import system.model.Book;
import system.model.Review;
import system.repository.AuthorRepository;
import system.repository.BookRepository;
import system.repository.ReviewRepository;

@SpringBootApplication
public class LibraryApplication {

    private static AuthorRepository authorRepository;
    private static BookRepository bookRepository;
    private static ReviewRepository reviewRepository;

    public LibraryApplication(AuthorRepository authorRepository, BookRepository bookRepository, ReviewRepository reviewRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.reviewRepository = reviewRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);

//        Author Pushkin = new Author("Alexander", "Pushkin", "Russian", 1799, "Moscow", "Poet");
//        Author Gogol = new Author("Nikolai", "Gogol", "Ukrainian", 1809, "Velyki Sorochyntsi", "Novelist");
//        Author Dostoevsky = new Author("Fyodor", "Dostoevsky", "Russian", 1821, "Moscow", "Novelist");
//        Author Tolstoy = new Author("Leo", "Tolstoy", "Russian", 1828, "Yasnaya Polyana", "Writer");
//        Author Lermontov = new Author("Mikhail", "Lermontov", "Russian", 1814, "Moscow", "Poet");
//
//        Book WarAndPeace = new Book("War and Peace", 1867, Tolstoy);
//        Book AnnaKarenina = new Book("Anna Karenina", 1877, Tolstoy);
//        Book DeadSouls = new Book("Dead Souls", 1842, Gogol);
//        Book TarasBulba = new Book("Taras Bulba", 1835, Gogol);
//        Book TheCaptainsDaughter = new Book("The Captain's Daughter", 1836, Pushkin);
//        Book Dubrovsky = new Book("Dubrovsky", 1831, Pushkin);
//        Book CrimeAndPunishment = new Book("Crime and Punishment", 1866, Dostoevsky);
//        Book Demons = new Book("Demons", 1872, Dostoevsky);
//        Book AHeroOfOurTime = new Book("A Hero of Our Time", 1840, Lermontov);
//        Book Borodino = new Book("Borodino", 1837, Lermontov);
//
//        Review reviewFor_WarAndPeace_1 = new Review("Best book ever", 5, WarAndPeace);
//        Review reviewFor_WarAndPeace_2 = new Review("Recommend for every one", 5, WarAndPeace);
//        Review reviewFor_AnnaKarenina_1 = new Review("Not bad", 4, AnnaKarenina);
//        Review reviewFor_AnnaKarenina_2 = new Review("I have red something better", 4, AnnaKarenina);
//        Review reviewFor_DeadSouls_1 = new Review("Interesting book", 4, DeadSouls);
//        Review reviewFor_DeadSouls_2 = new Review("Quite good", 5, DeadSouls);
//        Review reviewFor_TarasBulba_1 = new Review("Not enough", 4, TarasBulba);
//        Review reviewFor_TarasBulba_2 = new Review("Love it", 5, TarasBulba);
//        Review reviewFor_TheCaptainsDaughter_1 = new Review("Got to army because of this book", 5, TheCaptainsDaughter);
//        Review reviewFor_TheCaptainsDaughter_2 = new Review("Good", 5, TheCaptainsDaughter);
//        Review reviewFor_Dubrovsky_1 = new Review("Not bad", 4, Dubrovsky);
//        Review reviewFor_Dubrovsky_2 = new Review("Recommend for every one", 5, Dubrovsky);
//        Review reviewFor_CrimeAndPunishment_1 = new Review("Made me think", 5, CrimeAndPunishment);
//        Review reviewFor_CrimeAndPunishment_2 = new Review("So deep", 5, CrimeAndPunishment);
//        Review reviewFor_Demons_1 = new Review("Did not get it", 4, Demons);
//        Review reviewFor_Demons_2 = new Review("Cool one", 5, Demons);
//        Review reviewFor_AHeroOfOurTime_1 = new Review("Big book, but interesting", 4, AHeroOfOurTime);
//        Review reviewFor_AHeroOfOurTime_2 = new Review("Best book", 5, AHeroOfOurTime);
//        Review reviewFor_Borodino_1 = new Review("Quite good", 5, Borodino);
//        Review reviewFor_Borodino_2 = new Review("Best book", 4, Borodino);
//
//
//        authorRepository.save(Pushkin);
//        authorRepository.save(Gogol);
//        authorRepository.save(Dostoevsky);
//        authorRepository.save(Tolstoy);
//        authorRepository.save(Lermontov);
//
//        bookRepository.save(WarAndPeace);
//        bookRepository.save(AnnaKarenina);
//        bookRepository.save(DeadSouls);
//        bookRepository.save(TarasBulba);
//        bookRepository.save(TheCaptainsDaughter);
//        bookRepository.save(Dubrovsky);
//        bookRepository.save(CrimeAndPunishment);
//        bookRepository.save(Demons);
//        bookRepository.save(AHeroOfOurTime);
//        bookRepository.save(Borodino);
//
//        reviewRepository.save(reviewFor_WarAndPeace_1);
//        reviewRepository.save(reviewFor_WarAndPeace_2);
//        reviewRepository.save(reviewFor_AnnaKarenina_1);
//        reviewRepository.save(reviewFor_AnnaKarenina_2);
//        reviewRepository.save(reviewFor_DeadSouls_1);
//        reviewRepository.save(reviewFor_DeadSouls_2);
//        reviewRepository.save(reviewFor_TarasBulba_1);
//        reviewRepository.save(reviewFor_TarasBulba_2);
//        reviewRepository.save(reviewFor_TheCaptainsDaughter_1);
//        reviewRepository.save(reviewFor_TheCaptainsDaughter_2);
//        reviewRepository.save(reviewFor_Dubrovsky_1);
//        reviewRepository.save(reviewFor_Dubrovsky_2);
//        reviewRepository.save(reviewFor_CrimeAndPunishment_1);
//        reviewRepository.save(reviewFor_CrimeAndPunishment_2);
//        reviewRepository.save(reviewFor_Demons_1);
//        reviewRepository.save(reviewFor_Demons_2);
//        reviewRepository.save(reviewFor_AHeroOfOurTime_1);
//        reviewRepository.save(reviewFor_AHeroOfOurTime_2);
//        reviewRepository.save(reviewFor_Borodino_1);
//        reviewRepository.save(reviewFor_Borodino_2);
    }
}
