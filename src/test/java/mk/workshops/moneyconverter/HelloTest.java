package mk.workshops.moneyconverter;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class HelloTest {
    @Test
    public void alwaysTrue() {
        var value = true;
        Assertions.assertThat(value).isTrue();
    }
}
