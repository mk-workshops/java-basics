package mk.workshops.moneyconverter.basket;

import mk.workshops.moneyconverter.basket.Basket;
import mk.workshops.moneyconverter.basket.Book;
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

    @Test
    public void should_return_total_price_for_all_books() {
        //given
        var basket = new Basket(1);
        basket.add(new Book("Java", new BigDecimal("12.00")), 3);
        basket.add(new Book("Php", new BigDecimal("8.00")), 1);

        //when
        var totalPrice = basket.calculateTotalPrice();

        //then
        Assertions.assertThat(totalPrice).isEqualByComparingTo("44.00");

    }
}