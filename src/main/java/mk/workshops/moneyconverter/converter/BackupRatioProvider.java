package mk.workshops.moneyconverter.converter;

import java.math.BigDecimal;

public class BackupRatioProvider implements RatioProvider {

    private RatioProvider first;
    private RatioProvider second;

    public BackupRatioProvider(RatioProvider first, RatioProvider second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public BigDecimal getRatio(String from, String to) {
        try {
            return first.getRatio(from, to);
        } catch (RuntimeException e) {
            return second.getRatio(from, to);
        }
    }
}
