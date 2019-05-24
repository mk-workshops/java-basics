package mk.workshops.moneyconverter;

import mk.workshops.moneyconverter.basket.Basket;
import mk.workshops.moneyconverter.basket.Book;

import java.util.LinkedList;
import java.util.List;

public class Application {
    public static void main(String[] arg) {
        Basket basketRomana = new Basket(1);

        basketRomana.addBook(new Book("Java dla opornych", 19.99), 3);
        basketRomana.addBook(new Book("Java dla zaawansowanych", 99.99), 2);

        LinkedList<Book> bookStorage = new LinkedList<>();
        bookStorage.add(new Book("Java dla opornych", 19.99));
        bookStorage.add(new Book("Java dla testerów", 9.99));
        bookStorage.add(new Book("Java dla zaawansowanych", 99.99));


    }

    class BookFilter {
        public List<Book> filterByPrice(List<Book> books, boolean price) {
            var filteredBooks =  new LinkedList<Book>();

            for (Book book: books) {
                if (book.getPrice() >= 10.00) {
                    filteredBooks.add(book);
                }
            }

            return filteredBooks;
        }
    }
}
