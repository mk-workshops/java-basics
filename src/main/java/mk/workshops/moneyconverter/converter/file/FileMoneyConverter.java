package mk.workshops.moneyconverter.converter.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FileMoneyConverter {
    private String path;
    private Set<String> supportedCurrencies = new HashSet<>() {{
        add("PLN"); add("USD"); add("EUR");
    }};

    public FileMoneyConverter(String path) {
        this.path = path;
    }

    public BigDecimal convert(BigDecimal money, String from, String to) {
        if (!supportedCurrencies.contains(from)) {
            throw new UnsupportedCurrencyException();
        }

        var ratio = getRatios().get(from + "-" + to);
        return money.multiply(ratio).setScale(2, RoundingMode.CEILING);
    }

    private Map<String, BigDecimal> getRatios() {
        try {
            var reader = new BufferedReader(new FileReader(path));

            String line;
            var ratios = new HashMap<String, BigDecimal>();

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(", ");
                ratios.put(
                        values[0] + "-" + values[1],
                        new BigDecimal(values[2]));
            }

            return ratios;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
