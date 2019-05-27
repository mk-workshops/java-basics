package mk.workshops.moneyconverter.converter;

import mk.workshops.moneyconverter.converter.UnsupportedCurrencyException;
import mk.workshops.moneyconverter.converter.file.FileMoneyConverter;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

public class ConverterTest {

    private Converter converter;

    @Before
    public void setUp() throws Exception {
        var provider = Mockito.mock(RatioProvider.class);
        Mockito.when(provider.getRatio("PLN", "PLN")).thenReturn(new BigDecimal("1"));
        Mockito.when(provider.getRatio("USD", "PLN")).thenReturn(new BigDecimal("3.8246271989"));

        converter = new Converter(provider);
    }

    @Test
    public void should_return_the_same_money_for_the_same_currencies() throws IOException {
        //when
        var convertedMoney = converter.convert(new BigDecimal("10.00"), "PLN", "PLN");

        //then
        Assertions.assertThat(convertedMoney).isEqualTo(new BigDecimal("10.00"));
    }

    @Test
    public void should_return_converted_up_money_for_the_different_currencies() throws IOException {
        //when
        var convertedMoney = converter.convert(new BigDecimal("10.00"), "USD", "PLN");

        //then
        Assertions.assertThat(convertedMoney).isEqualTo(new BigDecimal("38.25"));
    }

    @Test
    public void should_throw_exception_if_converting_from_not_supported_currency() throws IOException {
        //when
        var exception = Assertions.catchThrowable(
                () -> converter.convert(new BigDecimal("10.00"), "GBP", "PLN"));

        //then
        Assertions.assertThat(exception).isInstanceOf(UnsupportedCurrencyException.class);
    }
}