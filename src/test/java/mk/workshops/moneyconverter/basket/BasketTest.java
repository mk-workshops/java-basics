package mk.workshops.moneyconverter.basket;

import mk.workshops.moneyconverter.converter.Converter;
import mk.workshops.moneyconverter.converter.RatioProvider;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static mk.workshops.moneyconverter.converter.Converter.Currency.PLN;
import static mk.workshops.moneyconverter.converter.Converter.Currency.USD;

public class BasketTest {
    Basket basket;

    @Before
    public void setUp() throws Exception {
        var provider = Mockito.mock(RatioProvider.class);
        Mockito.when(provider.getRatio(PLN, PLN)).thenReturn(Optional.of(new BigDecimal("1")));
        Mockito.when(provider.getRatio(USD, PLN)).thenReturn(Optional.of(new BigDecimal("4")));

        basket = new Basket(1, new Converter(provider));
    }

    @Test
    public void should_return_quantity_for_added_book() {
        //given
        Book book1 = new Book("Java dla opornych", new BigDecimal("30.99"), PLN);
        basket.add(book1, 2);

        //when
        var quantity = basket.getQuantity(book1);

        //then
        Assertions.assertThat(quantity).isEqualTo(2);
    }

    @Test
    public void should_return_sum_of_quantity_for_added_book_twice() {
        //given
        basket.add(new Book("Java dla opornych", new BigDecimal("30.99"), PLN), 2);

        //when
        basket.add(new Book("Java dla opornych", new BigDecimal("30.99"), PLN), 1);
        var quantity = basket.getQuantity(new Book("Java dla opornych", new BigDecimal("30.99"), PLN));

        //then
        Assertions.assertThat(quantity).isEqualTo(3);
    }

    @Test
    public void should_return_total_price_for_all_books() {
        //given
        basket.add(new Book("Java", new BigDecimal("12.00"), PLN), 3);
        basket.add(new Book("Php", new BigDecimal("8.00"), USD), 1);

        //when
        var totalPrice = basket.calculateTotalPrice(PLN);

        //then
        Assertions.assertThat(totalPrice).isEqualByComparingTo("68.00");

    }
}