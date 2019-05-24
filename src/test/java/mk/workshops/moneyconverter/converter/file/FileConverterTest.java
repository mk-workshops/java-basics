package mk.workshops.moneyconverter.converter.file;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

public class FileConverterTest {
    @Test
    public void should_return_the_same_value_id_converting_for_the_same_currencies() throws IOException {
        //given
        FileConverter converter = new FileConverter("src/test/resources/ratios");
        BigDecimal value = new BigDecimal("10.00");

        //when
        BigDecimal convertedValue = converter.convert(value, "PLN", "PLN");

        //then
        Assertions.assertThat(convertedValue).isEqualByComparingTo(value);
    }

    @Test
    public void return_converted_value_down_if_convert_from_different_currencies() throws IOException {
        //given
        FileConverter converter = new FileConverter("src/test/resources/ratios");
        BigDecimal value = new BigDecimal("10.00");

        //when
        BigDecimal convertedValue = converter.convert(value, "EUR", "PLN");

        //then
        Assertions.assertThat(convertedValue).isEqualByComparingTo("42.83");
    }

    @Test
    public void return_converted_value_up_if_convert_from_different_currencies() throws IOException {
        //given
        FileConverter converter = new FileConverter("src/test/resources/ratios");
        BigDecimal value = new BigDecimal("30.00");

        //when
        BigDecimal convertedValue = converter.convert(value, "EUR", "PLN");

        //then
        Assertions.assertThat(convertedValue).isEqualByComparingTo("128.50");
    }

    @Test(expected = UnsupportedCurrencyException.class)
    public void throw_exception_if_convert_from_not_supported_currency() throws IOException {
        //given
        FileConverter converter = new FileConverter("src/test/resources/ratios");
        BigDecimal value = new BigDecimal("30.00");

        //when
        converter.convert(value, "GBP", "PLN");
    }
}