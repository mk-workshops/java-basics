package mk.workshops.moneyconverter.converter;

import java.math.BigDecimal;
import java.util.Optional;

public interface RatioProvider {
     Optional<BigDecimal> getRatio(Converter.Currency from, Converter.Currency to);
}
