package util.comparators.books;

import entities.Book;

import java.util.Comparator;

public class DateCreatedComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return o1.getDateCreated().compareTo(o2.getDateCreated());
    }
}
