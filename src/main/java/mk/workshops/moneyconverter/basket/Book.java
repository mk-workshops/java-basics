package mk.workshops.moneyconverter.basket;

import lombok.Value;
import mk.workshops.moneyconverter.converter.Converter;

import java.math.BigDecimal;

@Value
public class Book {
    String title;
    BigDecimal price;
    Converter.Currency currency;
}
