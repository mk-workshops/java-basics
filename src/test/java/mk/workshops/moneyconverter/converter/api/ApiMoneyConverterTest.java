package mk.workshops.moneyconverter.converter.api;

import mk.workshops.moneyconverter.converter.file.FileMoneyConverter;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.Assert.*;

public class ApiMoneyConverterTest {
    private ApiMoneyConverter converter;

    @Before
    public void setUp() throws Exception {
        var service = Mockito.mock(ExchangeRatesApiService.class);
        var call = Mockito.mock(Call.class);

        var exchange = new Exchange();
        exchange.setBase("PLN");
        exchange.setRates(new HashMap<>() {{
            put("USD", 0.25f);
        }});

        Mockito.when(call.execute()).thenReturn(Response.success(exchange));
        Mockito.when(service.getRatios("PLN", "USD")).thenReturn(call);

        converter = new ApiMoneyConverter(service);
    }

    @Test
    public void should_return_the_same_money_for_the_same_currencies() throws IOException {
        //when
        var convertedMoney = converter.convert(new BigDecimal("10.00"), "PLN", "PLN");

        //then
        Assertions.assertThat(convertedMoney).isEqualTo(new BigDecimal("10.00"));
    }
}