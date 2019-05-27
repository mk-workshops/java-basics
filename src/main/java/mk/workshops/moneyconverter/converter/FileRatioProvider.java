package mk.workshops.moneyconverter.converter;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileRatioProvider implements RatioProvider {
    private String path;

    public FileRatioProvider(String path) {
        this.path = path;
    }

    public BigDecimal getRatio(String currencyFrom, String currencyTo) {
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
