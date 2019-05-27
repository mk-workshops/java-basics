package mk.workshops.moneyconverter.converter.api;

import mk.workshops.moneyconverter.converter.FileRatioProvider;
import mk.workshops.moneyconverter.converter.RatioProvider;

import java.io.IOException;
import java.math.BigDecimal;

public class ApiRatioProvider implements RatioProvider {
    ExchangeRatesApiService exchangeRatesApiService;
    private FileRatioProvider provider;

    public ApiRatioProvider(ExchangeRatesApiService exchangeRatesApiService, FileRatioProvider provider) {
        this.exchangeRatesApiService = exchangeRatesApiService;
        this.provider = provider;
    }

    public BigDecimal getRatio(String from, String to) {
        try {
            var request = exchangeRatesApiService.getRatios(from, to);
            var response = request.execute();
            var ratio = response.body().getRates().get(to);

            return BigDecimal.valueOf(ratio);
        } catch (IOException e) {
            return provider.getRatio(from, to);
        }
    }
}
