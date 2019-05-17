package mk.workshops.moneyconverter.converter.api;

import mk.workshops.moneyconverter.converter.file.UnsupportedCurrencyException;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

public class ApiMoneyConverterTest {
    private ApiMoneyConverter converter;

    @Before
    public void setUp() throws Exception {
        var service = Mockito.mock(ExchangeRatesApiService.class);
        var callUsdPln = Mockito.mock(Call.class);

        var exchangeUsdPln = new Exchange();
        exchangeUsdPln.setBase("USD");
        exchangeUsdPln.setRates(new HashMap<>() {{
            put("PLN", 3.8246271989f);
        }});

        var callPlnPln = Mockito.mock(Call.class);

        var exchangePlnPln = new Exchange();
        exchangePlnPln.setBase("PLN");
        exchangePlnPln.setRates(new HashMap<>() {{
            put("PLN", 1.0f);
        }});

        Mockito.when(callUsdPln.execute()).thenReturn(Response.success(exchangeUsdPln));
        Mockito.when(callPlnPln.execute()).thenReturn(Response.success(exchangePlnPln));
        Mockito.when(service.getRatios("USD", "PLN")).thenReturn(callUsdPln);
        Mockito.when(service.getRatios("PLN", "PLN")).thenReturn(callPlnPln);

        converter = new ApiMoneyConverter(service);
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