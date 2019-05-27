package mk.workshops.moneyconverter.converter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

abstract public class CommonConverter {
    private Set<String> supportedCurrencies = new HashSet<>() {{
        add("PLN"); add("USD"); add("EUR");
    }};

    public BigDecimal convert(BigDecimal money, String currencyFrom, String currencyTo) {
        if (!supportedCurrencies.contains(currencyFrom)) {
            throw new UnsupportedCurrencyException();
        }

        return getRatio(currencyFrom, currencyTo).multiply(money).setScale(2, RoundingMode.CEILING);
    }

    protected abstract BigDecimal getRatio(String currencyFrom, String currencyTo);

}
