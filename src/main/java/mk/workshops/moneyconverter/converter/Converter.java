package mk.workshops.moneyconverter.converter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class Converter {

    private RatioProvider provider;

    public Converter(RatioProvider provider) {
        this.provider = provider;
    }

    public Optional<BigDecimal> convert(BigDecimal money, Currency from, Currency to) {
        return provider.getRatio(from, to)
                .map(ratio -> ratio.multiply(money))
                .map(ratio -> ratio.setScale(2, RoundingMode.CEILING));
    }

    public enum Currency {
        PLN,
        EUR,
        USD
    }

}
