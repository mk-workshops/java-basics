package mk.workshops.moneyconverter.basket;

import java.util.HashMap;

public class Basket {
    private int id;
    private HashMap<Book, Integer> books = new HashMap<>();

    public Basket(int id) {
      this.id = id;
    }

    public int getId() {
        return id;
    }

    public void addBook(Book book, int quantity) {
        if (books.containsKey(book)) {
            int currentQuantity = books.get(book);
            books.put(book, currentQuantity + quantity);
        } else {
            books.put(book, quantity);
        }
    }

    public int getBookQuantity(Book book) {
        return books.get(book);
    }
}
