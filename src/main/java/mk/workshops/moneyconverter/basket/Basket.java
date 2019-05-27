package mk.workshops.moneyconverter.basket;

import mk.workshops.moneyconverter.converter.Converter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Basket{
    private int id;
    private Converter converter;
    private HashMap<Book, Integer> books = new HashMap<>();

    public Basket(int id, Converter converter) {
        this.id = id;
        this.converter = converter;
    }

    public void add(Book book, Integer quantity) {
        if (books.containsKey(book)) {
            var currentQuantity = books.get(book);
            books.put(book, currentQuantity + quantity);
        } else {
            books.put(book, quantity);
        }
    }

    public Integer getQuantity(Book book) {
        return books.get(book);
    }

    public BigDecimal calculateTotalPrice(String currencyTo) {
        return this.books.entrySet().stream()
            .filter(this::atLeastTwoBooks)
            .map((bookEntry) -> convert(currencyTo, bookEntry))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal convert(String currencyTo, Map.Entry<Book, Integer> bookEntry) {
        var quantity = bookEntry.getValue();
        var price = bookEntry.getKey().getPrice().multiply(BigDecimal.valueOf(quantity));
        var currencyFrom = bookEntry.getKey().getCurrency();
        return converter.convert(price, currencyFrom ,currencyTo);
    }

    private Boolean atLeastTwoBooks(Map.Entry<Book, Integer> bookEntry) {
        return bookEntry.getValue() > 1;
    }
}
