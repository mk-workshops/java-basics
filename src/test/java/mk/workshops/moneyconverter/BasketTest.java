package mk.workshops.moneyconverter;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.math.BigDecimal;

public class BasketTest {

    @Test
    public void should_return_quantity_for_added_book() {
        //given
        Basket johnBasket = new Basket(1);
        Book book1 = new Book("Java dla opornych", new BigDecimal("30.99"));
        johnBasket.add(book1, 2);

        //when
        var quantity = johnBasket.getQuantity(book1);

        //then
        Assertions.assertThat(quantity).isEqualTo(2);
    }

    @Test
    public void should_return_sum_of_quantity_for_added_book_twice() {
        //given
        Basket johnBasket = new Basket(1);
        johnBasket.add(new Book("Java dla opornych", new BigDecimal("30.99")), 2);

        //when
        johnBasket.add(new Book("Java dla opornych", new BigDecimal("30.99")), 1);
        var quantity = johnBasket.getQuantity(new Book("Java dla opornych", new BigDecimal("30.99")));

        //then
        Assertions.assertThat(quantity).isEqualTo(3);
    }
}