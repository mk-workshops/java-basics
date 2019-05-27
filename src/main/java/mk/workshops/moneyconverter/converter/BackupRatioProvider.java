package mk.workshops.moneyconverter.converter;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Optional;

@RequiredArgsConstructor
public class BackupRatioProvider implements RatioProvider {
    private final RatioProvider main;
    private final RatioProvider backup;

    @Override
    public Optional<BigDecimal> getRatio(Converter.Currency from, Converter.Currency to) {
        try {
            var ratio = main.getRatio(from, to);
            return ratio.isPresent() ? ratio : backup.getRatio(from, to);
        } catch (RuntimeException e) {
            return backup.getRatio(from, to);
        }
    }
}
