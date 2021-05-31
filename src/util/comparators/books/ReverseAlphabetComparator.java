package util.comparators.books;

import entities.Book;

import java.util.Comparator;

public class ReverseAlphabetComparator implements Comparator <Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return o2.getTitle().compareToIgnoreCase(o1.getTitle());
    }
}
