package mk.workshops.moneyconverter.converter.api;

import java.math.BigDecimal;

public class ApiMoneyConverter {
    ExchangeRatesApiService exchangeRatesApiService;

    public ApiMoneyConverter(ExchangeRatesApiService exchangeRatesApiService) {
        this.exchangeRatesApiService = exchangeRatesApiService;
    }

    public BigDecimal convert(BigDecimal money, String from, String to) {
        return money;
    }

}
