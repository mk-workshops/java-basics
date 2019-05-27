package mk.workshops.moneyconverter.basket;

import mk.workshops.moneyconverter.converter.Converter;
import mk.workshops.moneyconverter.converter.FileRatioProvider;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

public class BasketTest {
    Basket basket;

    @Before
    public void setUp() throws Exception {
        var converter = Mockito.mock(Converter.class);
        Mockito.when(converter.convert(new BigDecimal("30.99"), "PLN", "PLN")).thenReturn(new BigDecimal("30.99"));
        Mockito.when(converter.convert(new BigDecimal("36.00"), "PLN", "PLN")).thenReturn(new BigDecimal("36.00"));
        Mockito.when(converter.convert(new BigDecimal("8.00"), "USD", "PLN")).thenReturn(new BigDecimal("32.00"));

        basket = new Basket(1, converter);
    }

    @Test
    public void should_return_quantity_for_added_book() {
        //given
        Book book1 = new Book("Java dla opornych", new BigDecimal("30.99"), "PLN");
        basket.add(book1, 2);

        //when
        var quantity = basket.getQuantity(book1);

        //then
        Assertions.assertThat(quantity).isEqualTo(2);
    }

    @Test
    public void should_return_sum_of_quantity_for_added_book_twice() {
        //given
        basket.add(new Book("Java dla opornych", new BigDecimal("30.99"), "PLN"), 2);

        //when
        basket.add(new Book("Java dla opornych", new BigDecimal("30.99"), "PLN"), 1);
        var quantity = basket.getQuantity(new Book("Java dla opornych", new BigDecimal("30.99"), "PLN"));

        //then
        Assertions.assertThat(quantity).isEqualTo(3);
    }

    @Test
    public void should_return_total_price_for_all_books() {
        //given
        basket.add(new Book("Java", new BigDecimal("12.00"), "PLN"), 3);
        basket.add(new Book("Php", new BigDecimal("8.00"), "USD"), 1);

        //when
        var totalPrice = basket.calculateTotalPrice("PLN");

        //then
        Assertions.assertThat(totalPrice).isEqualByComparingTo("68.00");

    }
}