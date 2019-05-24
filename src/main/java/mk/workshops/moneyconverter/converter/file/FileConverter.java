package mk.workshops.moneyconverter.converter.file;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FileConverter {
    private String path;
    private Set<String> supportedCurrencies = new HashSet<>() {{
        add("USD"); add("PLN"); add("EUR");
    }};

    public FileConverter(String path) {
        this.path = path;
    }

    public BigDecimal convert(BigDecimal value, String currencyFrom, String currencyTo) {
        if (!supportedCurrencies.contains(currencyFrom)) {
            throw new UnsupportedCurrencyException();
        }

        return getRatio(currencyFrom, currencyTo).multiply(value).setScale(2, RoundingMode.HALF_EVEN);
    }

    private BigDecimal getRatio(String currencyFrom, String currencyTo)  {
        try {
            List<String> linies = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);

            Map<String, BigDecimal> ratios = new HashMap<>();

            for (String line : linies) {
                String[] parts = line.split(", ");
                ratios.put(parts[0] + parts[1], new BigDecimal(parts[2]));
            }

            return ratios.get(currencyFrom + currencyTo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
