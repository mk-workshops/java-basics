package mk.workshops.moneyconverter;

import mk.workshops.moneyconverter.converter.Converter;
import mk.workshops.moneyconverter.converter.FileRatioProvider;

import java.math.BigDecimal;

public class Application {
    public static void main(String args[]) {
        var converter = new Converter(new FileRatioProvider("src/test/resources/ratios"));

        System.out.println(converter.convert(BigDecimal.TEN, "PLN", "USD"));
    }
}
