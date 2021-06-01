import dao.AuthorDao;
import dao.AuthorDaoImpl;
import dao.BookDao;
import dao.BookDaoImpl;
import entities.Book;
import service.BookService;
import service.BookServiceImpl;
import util.comparators.books.AlphabetComparator;
import util.comparators.books.DateCreatedComparator;
import util.comparators.books.ReverseAlphabetComparator;
import java.util.Scanner;

public class UserController implements Runnable {
    AuthorDao authorDao = new AuthorDaoImpl();
    BookDao bookDao = new BookDaoImpl(authorDao);
    BookService bookService = new BookServiceImpl(bookDao, authorDao);
    Scanner scanner = new Scanner(System.in);
    Scanner scanner1 = new Scanner(System.in);


    @Override
    public void run() {
        int flag = 0;
        while (flag != 5) {
            System.out.println("Choose Select an action:");
            System.out.println("1. Add book");
            System.out.println("2. Edit book");
            System.out.println("3. Delete book");
            System.out.println("4. Get a list of the books");
            System.out.println("5. Stop working with the library");
            flag = scanner.nextInt();
            switch (flag) {
                case 1:
                    System.out.println("Enter book's id");
                    int id = Integer.parseInt(scanner.next());
                    System.out.println("Enter title");
                    String title = scanner1.nextLine();
                    System.out.println("Enter genre");
                    String genre = scanner1.nextLine();
                    System.out.println("Enter author");
                    String author = scanner1.nextLine();
                    if (bookService.addBook(id, title, genre, author))
                        System.out.println("The book was added to the library");
                    else
                        System.out.println("Book with this ID already exists");
                    break;
                case 2:
                    System.out.println("Enter book's id, title and genre");
                    if (bookService.editBook(scanner.nextInt(), scanner1.nextLine(), scanner1.nextLine())) {
                        System.out.println("The book was edited");
                    } else
                        System.out.println("Book with this ID doesn't exist");
                    break;
                case 3:
                    System.out.println("Enter book's id");
                    if (bookService.deleteBookFromLibrary(scanner.nextInt())) {
                        System.out.println("The book was deleted");
                    } else
                        System.out.println("Book with this ID doesn't exist");
                    break;
                case 4:
                    System.out.println("Choose sorting parameters");
                    System.out.println("1. By alphabet (Ascending)");
                    System.out.println("2. By alphabet (Descending)");
                    System.out.println("3. By date creating");
                    int index = scanner.nextInt();
                    if (index == 1) {
                        for (Book a : bookService.getListOfBooks(new AlphabetComparator())) {
                            System.out.println(a);
                        }
                    }
                    if (index == 2) {
                        for (Book a : bookService.getListOfBooks(new ReverseAlphabetComparator())) {
                            System.out.println(a);
                        }
                    }
                    if (index == 3) {
                        for (Book a : bookService.getListOfBooks(new DateCreatedComparator())) {
                            System.out.println(a);
                        }
                    }
                    break;
                case 5:
                    break;
                default:
                    System.out.println("You entered wrong number, try again");
            }
        }
    }
}
