package activityStarterCode.streamActivity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;

@SuppressWarnings("WeakerAccess")
public class Library {
    private final List<Book> books;

    public Library(List<Book> books) {
        this.books = books;
    }

    /**
     * Task 1: Finds the titles of all the books by the given author
     */
    public List<String> findTitlesByAuthor(String authorName) {
        return books.stream()
                .filter(b -> b.getAuthor().equals(authorName))
                .map(Book::getTitle)
                .collect(toList());
    }

    /**
     * Task 2: Find the titles of the books with more than the given minimum number of pages.
     */
    public List<String> findTitlesOfLongBooks(int minPageCount) {
        List<String> titles = new LinkedList<>();
        for (Book b: books) {
            if (b.getPageCount() > minPageCount) {
                titles.add(b.getTitle());
            }
        }
        return titles;
    }

    /**
     * Task 3: Describes the titles and authors of all the books in the given genre.
     */
    public List<String> findTitlesAndAuthorsInGenre(String genre) {
        return books.stream()
                .filter(bookInGenre(genre))
                .map(Book::fullTitle)
                .collect(toList());
    }

    /**
     * Task 4: How many books in the given genre does the library have?
     */
    public long countBooksInGenre(String genre) {
        return books.stream().filter(bookInGenre(genre)).count();
    }

    /**
     * Task 5: What are the n shortest books in the library?
     */
    public List<Book> findShortestBooks(int n) {
        List<Book> sorted = new ArrayList<>(books);
        sorted.sort(Comparator.comparing(Book::getPageCount));
        return sorted.size() > n ? sorted.subList(0, n) : sorted;
    }

    /**
     * Task 6: Finds all books in the given genre, sorted from shortest to longest.
     */
    public List<Book> findBooksInGenreSortedByLength(String genre) {
        return books.stream()
                .filter(bookInGenre(genre))
                .sorted(Comparator.comparing(Book::getPageCount))
                .collect(toList());
    }

    /**
     * Task 7: What is the book with the shortest title?
     */
    public Book findBookWithShortestTitle() {
        Book shortestTitle = null;
        for (Book book : books) {
            if (shortestTitle == null ||
                    book.getTitle().length() < shortestTitle.getTitle().length()) {
                shortestTitle = book;
            }
        }
        return shortestTitle;
    }

    /**
     * Task 8: Print all books in the library to STDOUT.
     */
    public void printLibrary() {
        books.forEach(System.out::println);
    }


    // ––––––––––––  Special bonus challenges! ––––––––––––
    /**
     * What is the average number of pages of books in the library?
     * @return 0 if there are no books.
     */
    public double computeAverageBookLength() {
        if (books.size() == 0) return 0;
        long sum = 0L;
        for (Book book : books) {
            sum += book.getPageCount();
        }
        return sum / (double) books.size();
    }

    /**
     * Has the given author written any books in the given genre?
     *
     * Hint: Use anyMatch()
     */
    public boolean hasAuthorWrittenInGenre(String author, String genre) {
        return books.stream()
                .filter(book -> book.getAuthor().equals(author))
                .anyMatch(bookInGenre(genre));
    }

    /**
     * Gives a set containing all authors in the books.
     *
     * A Set is a bit like a List, with two differences:
     *
     * - It contains no duplicates; an element can only be in a set once.
     * - It does not preserve the order of the elements. They might not come out in the same order
     *   you put them in.
     *
     * Hint: If you want to make a new mutable Set, use new HashSet<>().
     */
    public Set<String> getAllAuthors() {
        Set<String> authors = new HashSet<>(books.size());
        for (Book book : books) {
            authors.add(book.getAuthor());
        }
        return authors;
    }

    /**
     * What are the names of all the authors who have written in the given genre?
     */
    public Set<String> allAuthorsInGenre(String genre) {
        return books.parallelStream().filter(bookInGenre(genre)).map(Book::getAuthor).collect(toSet());
    }

    /**
     * What are all the different genres in the library?
     *
     * Hint: flatMap() is like map(), except the lambda returns a stream, and all the
     *       elements in that returned stream feed into the larger stream one at a time
     *       instead of as a whole collection all at once.
     */
    public Set<String> getAllGenres() {
        return books.parallelStream().map(Book::getGenres).flatMap(Set::stream).collect(toSet());
    }

    private static Predicate<? super Book> bookInGenre(String genre) {
        return book -> book.inGenre(genre);
    }
}
