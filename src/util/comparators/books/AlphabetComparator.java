package util.comparators.books;

import entities.Book;

import java.util.Comparator;

public class AlphabetComparator implements Comparator <Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return o1.getTitle().compareToIgnoreCase(o2.getTitle());
    }
}
