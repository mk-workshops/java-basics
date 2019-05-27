package mk.workshops.moneyconverter.converter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

import static mk.workshops.moneyconverter.converter.Converter.Currency.PLN;
import static mk.workshops.moneyconverter.converter.Converter.Currency.USD;
import static org.assertj.core.api.Assertions.assertThat;

public class ConverterTest {

    private Converter converter;

    @Before
    public void setUp() throws Exception {
        var provider = Mockito.mock(RatioProvider.class);
        Mockito.when(provider.getRatio(PLN, PLN)).thenReturn(Optional.of(new BigDecimal("1")));
        Mockito.when(provider.getRatio(USD, PLN)).thenReturn(Optional.of(new BigDecimal("3.8246271989")));

        converter = new Converter(provider);
    }

    @Test
    public void should_return_the_same_money_for_the_same_currencies() throws IOException {
        //when
        var convertedMoney = converter.convert(new BigDecimal("10.00"), PLN, PLN);

        //then
        assertThat(convertedMoney).hasValueSatisfying(value -> assertThat(value).isEqualByComparingTo(new BigDecimal("10.00")));
    }

    @Test
    public void should_return_converted_up_money_for_the_different_currencies() throws IOException {
        //when
        var convertedMoney = converter.convert(new BigDecimal("10.00"), USD, PLN);

        //then
        assertThat(convertedMoney).hasValueSatisfying(value -> assertThat(value).isEqualTo(new BigDecimal("38.25")));
    }
}