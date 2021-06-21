package course.start.demo.bootstrap;

import course.start.demo.domain.Author;
import course.start.demo.domain.Book;
import course.start.demo.domain.Publisher;
import course.start.demo.repositories.AuthorRepository;
import course.start.demo.repositories.BookRepository;
import course.start.demo.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher = new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setCity("St Petersburg");
        publisher.setState("FL");

        publisherRepository.save(publisher);

        System.out.println("Publisher Count: " + publisherRepository.count());

        Author eric = new Author("Eric","Evans");
        Book ddd = new Book("Domain Driven Design","123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);
        System.out.println("Publisher Count: " + publisherRepository.count());

        Author rod = new Author("Rod","Johnson");
        Book noEJB = new Book("J2EE Development without EJB","3243243231");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);
        System.out.println("Publisher Count: " + publisherRepository.count());

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books assigned to publisher:"+publisher.getBooks().size());
    }
}
