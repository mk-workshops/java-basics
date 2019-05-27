package mk.workshops.moneyconverter.converter;

import java.math.BigDecimal;

public interface RatioProvider {
     BigDecimal getRatio(String from, String to);
}
