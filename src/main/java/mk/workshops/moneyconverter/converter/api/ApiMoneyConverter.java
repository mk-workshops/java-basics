package mk.workshops.moneyconverter.converter.api;

import mk.workshops.moneyconverter.converter.CommonConverter;

import java.io.IOException;
import java.math.BigDecimal;

public class ApiMoneyConverter extends CommonConverter {
    ExchangeRatesApiService exchangeRatesApiService;

    public ApiMoneyConverter(ExchangeRatesApiService exchangeRatesApiService) {
        this.exchangeRatesApiService = exchangeRatesApiService;
    }

    @Override
    protected BigDecimal getRatio(String from, String to) {
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
