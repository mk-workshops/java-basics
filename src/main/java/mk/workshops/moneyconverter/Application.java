package mk.workshops.moneyconverter;

import mk.workshops.moneyconverter.basket.Book;
import mk.workshops.moneyconverter.converter.api.ExchangeRatiosRestApiService;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Application {
    public static void main(String[] arg) throws IOException {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.exchangeratesapi.io")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        ExchangeRatiosRestApiService service = retrofit.create(ExchangeRatiosRestApiService.class);

        Double ratio = service.getRatios("PLN", "EUR").execute().body().rates.get("EUR");

        System.out.println(ratio);
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
