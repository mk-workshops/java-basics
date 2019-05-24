package mk.workshops.moneyconverter.basket;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    public double calculateTotalPrice() {
        Set<Map.Entry<Book, Integer>> allBooks = books.entrySet();

        double totalPrice = 0;

        for(Map.Entry<Book, Integer> bookEntry: allBooks) {
            totalPrice += bookEntry.getKey().getPrice() * bookEntry.getValue();
        }

        return totalPrice;
    }
}
