package mk.workshops.moneyconverter.basket;

import lombok.RequiredArgsConstructor;
import mk.workshops.moneyconverter.converter.Converter;
import mk.workshops.moneyconverter.converter.Converter.Currency;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class Basket{
    private final int id;
    private final Converter converter;
    private Map<Book, Integer> books = new HashMap<>();

    public void add(Book book, Integer quantity) {
        books.merge(book, quantity, Integer::sum);
    }

    public Integer getQuantity(Book book) {
        return books.get(book);
    }

    public BigDecimal calculateTotalPrice(Currency currencyTo) {
        return this.books.entrySet()
            .stream()
            .map(bookEntry -> convert(currencyTo, bookEntry.getKey(), bookEntry.getValue()))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal convert(Currency to, Book book, Integer quantity) {
        var price = book.getPrice().multiply(BigDecimal.valueOf(quantity));
        var from = book.getCurrency();

        return converter.convert(price, from ,to).orElseThrow(RuntimeException::new);
    }
}
