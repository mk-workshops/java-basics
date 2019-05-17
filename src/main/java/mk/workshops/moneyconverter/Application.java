package mk.workshops.moneyconverter;

import mk.workshops.moneyconverter.converter.api.ExchangeRatesApiService;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class Application {
    public static void main(String args[]) {
        Basket johnBasket = new Basket(1);

        Book book1 = new Book("Java dla opornych", new BigDecimal("30.99"));
        Book book2 = new Book("Java dla zaawansowanych", new BigDecimal("11.99"));
        Book book3 = new Book("Java Ninja", new BigDecimal("20.99"));

        List<Book> storage = new LinkedList<>();

        storage.add(book1);
        storage.add(book2);
        storage.add(book3);

        for (Book book: storage) {
            var zloty20 = new BigDecimal("20.00");

            if (book.getPrice().compareTo(zloty20) > 0) {
                System.out.println(book.getTitle());
            }
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.exchangeratesapi.io")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        var service = retrofit.create(ExchangeRatesApiService.class);

    }
}
