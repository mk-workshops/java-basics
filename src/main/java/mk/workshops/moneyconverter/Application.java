package mk.workshops.moneyconverter;

import mk.workshops.moneyconverter.basket.Basket;
import mk.workshops.moneyconverter.basket.Book;
import mk.workshops.moneyconverter.converter.BackupRatioProvider;
import mk.workshops.moneyconverter.converter.Converter;
import mk.workshops.moneyconverter.converter.api.ApiRatioProvider;
import mk.workshops.moneyconverter.converter.api.ExchangeRatesApiService;
import mk.workshops.moneyconverter.converter.file.FileRatioProvider;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.math.BigDecimal;

import static mk.workshops.moneyconverter.converter.Converter.Currency.*;

public class Application {
    public static void main(String args[]) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.exchangeratesapi.io")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        var service = retrofit.create(ExchangeRatesApiService.class);
        var apiProvider = new ApiRatioProvider(service);

        var fileProvider = new FileRatioProvider("src/test/resources/ratios");

        var backupProvider = new BackupRatioProvider(apiProvider, fileProvider);

        var basket = new Basket(1, new Converter(backupProvider));

        basket.add(new Book("Java dla opornych", new BigDecimal("29.99"), EUR), 2);
        basket.add(new Book("TypeScript dla progamistów Java", new BigDecimal("19.22"), USD), 3);

        System.out.println(basket.calculateTotalPrice(PLN));
    }
}
