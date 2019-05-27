package mk.workshops.moneyconverter.converter.api;

import mk.workshops.moneyconverter.converter.UnsupportedCurrencyException;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

public class ApiMoneyConverter {
    ExchangeRatesApiService exchangeRatesApiService;
    private Set<String> supportedCurrencies = new HashSet<>() {{
        add("PLN"); add("USD"); add("EUR");
    }};

    public ApiMoneyConverter(ExchangeRatesApiService exchangeRatesApiService) {
        this.exchangeRatesApiService = exchangeRatesApiService;
    }

    public BigDecimal convert(BigDecimal money, String from, String to) {
        if (!supportedCurrencies.contains(from)) {
            throw new UnsupportedCurrencyException();
        }

        var ratio = getRatio(from, to);
        return money.multiply(ratio).setScale(2, RoundingMode.CEILING);
    }

    private BigDecimal getRatio(String from, String to) {
        try {
           var request = exchangeRatesApiService.getRatios(from, to);
           var response = request.execute();
           var ratio = response.body().getRates().get(to);

           return BigDecimal.valueOf(ratio);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
