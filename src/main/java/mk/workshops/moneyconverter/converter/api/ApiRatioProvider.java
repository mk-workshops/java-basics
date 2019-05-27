package mk.workshops.moneyconverter.converter.api;

import lombok.RequiredArgsConstructor;
import mk.workshops.moneyconverter.converter.Converter;
import mk.workshops.moneyconverter.converter.RatioProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

@RequiredArgsConstructor
public class ApiRatioProvider implements RatioProvider {
    private final ExchangeRatesApiService exchangeRatesApiService;

    public Optional<BigDecimal> getRatio(Converter.Currency from, Converter.Currency to) {
        try {
            var request = exchangeRatesApiService.getRatios(from.toString(), to.toString());
            var response = request.execute();

            if (!response.isSuccessful()) {
                return Optional.empty();
            }

            return Optional.ofNullable(response.body())
                    .map(Exchange::getRates)
                    .flatMap(rates ->
                            Optional.ofNullable(rates.get(to.toString()))
                    )
                    .map(BigDecimal::new);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
