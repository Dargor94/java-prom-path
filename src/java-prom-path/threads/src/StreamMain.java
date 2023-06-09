import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamMain {

    public static void main(String[] args) {
        var main = new StreamMain();
        main.filtering();
        main.mapping();
    }

    void mapping() {
        var numbersListA = loadNumbersList(1, 2, 3);
        var numbersListB = loadNumbersList(4, 5);
        var mappedList = mapList(numbersListA, numbersListB);
        printMappedList(mappedList);
    }

    void printMappedList(String mappedList) {
        System.out.println("mappedList = " + mappedList);
    }

    String mapList(List<Integer> numbersListA, List<Integer> numbersListB) {

        return Arrays.toString(numbersListA
                .stream()
                .flatMap(integerA ->
                        numbersListB
                                .stream()
                                .map(integerB -> Arrays.asList(integerA, integerB)))
                .toArray());

    }

    List<Integer> loadNumbersList(Integer... ints) {
        return Arrays.stream(ints).collect(Collectors.toList());
    }

    void filtering() {
        var books = loadBooks();
        var filteredBooks = filterBooks(books);
        printBooks(filteredBooks);
    }

    void printBooks(List<Book> filteredBooks) {
        filteredBooks.forEach(System.out::println);
    }

    List<Book> filterBooks(List<Book> books) {
        return books
                .stream()
                .filter(book -> book.getTitle().split(" ").length == 2)
                .collect(Collectors.toList());
    }

    List<Book> loadBooks() {
        return List.of(
                new Book("The Trials of Mount Fanor", "Franz Kafka", 240, BookGenre.NOVEL),
                new Book("The Maltese Falcon", "Dashiell Hammett", 634, BookGenre.THRILLER),
                new Book("The Silmarillion", "J.R.R. Tolkien", 1024, BookGenre.NOVEL),
                new Book("Ancient Greece", "Robert F.", 500, BookGenre.HISTORY),
                new Book("Journey to the Center of the Earth", "Jules Verne", 428, BookGenre.NOVEL),
                new Book("Allice in Chains", "Jhon Doe", 310, BookGenre.THRILLER)
        );
    }

    enum BookGenre {
        NOVEL, THRILLER, HISTORY
    }

    static class Book {
        private final String title;
        private final String author;
        private final int pages;
        private final BookGenre genre;

        public Book(String title, String author, int pages, BookGenre genre) {
            this.title = title;
            this.author = author;
            this.pages = pages;
            this.genre = genre;
        }

        public String getTitle() {
            return title;
        }


        @Override
        public String toString() {
            return "Book{" +
                    "title='" + title + '\'' +
                    ", author='" + author + '\'' +
                    ", pages=" + pages +
                    ", genre=" + genre +
                    '}';
        }
    }
}
