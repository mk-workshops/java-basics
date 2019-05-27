package mk.workshops.moneyconverter.basket;

import java.math.BigDecimal;
import java.util.Objects;

public class Book {
    private String title;
    private BigDecimal price;
    private String currency;

    public Book(String title, BigDecimal price, String currency) {
        this.title = title;
        this.price = price;
        this.currency = currency;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) &&
                Objects.equals(price, book.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price);
    }
}
