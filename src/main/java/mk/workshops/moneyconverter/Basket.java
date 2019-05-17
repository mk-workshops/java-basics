package mk.workshops.moneyconverter;

import java.math.BigDecimal;
import java.util.HashMap;

public class Basket{
    private int id;
    private HashMap<Book, Integer> books = new HashMap<>();

    public Basket(int id) {
        this.id = id;
    }

    public void add(Book book, Integer quantity) {
        if (books.containsKey(book)) {
            var current = books.get(book);
            books.put(book, current + quantity);
        } else {
            books.put(book, quantity);
        }
    }

    public Integer getQuantity(Book book) {
        return books.get(book);
    }

    public BigDecimal calculateTotalPrice() {
        var books = this.books.entrySet();
        var totalPrice = BigDecimal.ZERO;

        for (var bookEntry: books) {
            var quantity = bookEntry.getValue();
            var price = bookEntry.getKey().getPrice().multiply(BigDecimal.valueOf(quantity));
            totalPrice = totalPrice.add(price);
        }

        return totalPrice;

    }

}
