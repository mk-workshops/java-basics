package mk.workshops.moneyconverter.converter.file;

import lombok.RequiredArgsConstructor;
import mk.workshops.moneyconverter.converter.Converter;
import mk.workshops.moneyconverter.converter.RatioProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class FileRatioProvider implements RatioProvider {
    private final String path;

    public Optional<BigDecimal> getRatio(Converter.Currency from, Converter.Currency to) {
        try {
            List<String> linies = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);

            Map<String, BigDecimal> ratios = new HashMap<>();

            for (String line : linies) {
                String[] parts = line.split(", ");
                ratios.put(String.format("%s%s", parts[0], parts[1]), new BigDecimal(parts[2]));
            }

            return Optional.ofNullable(
                    ratios.get(String.format("%s%s", from.toString(), to.toString()))
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
