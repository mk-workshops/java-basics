package mk.workshops.moneyconverter.converter;

import mk.workshops.moneyconverter.converter.api.ApiRatioProvider;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

public class Converter {
    private Set<String> supportedCurrencies = new HashSet<>() {{
        add("PLN"); add("USD"); add("EUR");
    }};
    private RatioProvider provider;

    public Converter(RatioProvider provider) {
        this.provider = provider;
    }

    public BigDecimal convert(BigDecimal money, String currencyFrom, String currencyTo) {
        if (!supportedCurrencies.contains(currencyFrom)) {
            throw new UnsupportedCurrencyException();
        }

        return provider.getRatio(currencyFrom, currencyTo).multiply(money).setScale(2, RoundingMode.CEILING);
    }
}
