package mk.workshops.moneyconverter.basket;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BasketTest {
    @Test
    public void should_return_quantity_when_book_is_added() {
        //given
        Basket basket = new Basket(1);
        Book book = new Book("Java dla opornych", 19.99);
        basket.addBook(book, 3);

        //when
        int quantity = basket.getBookQuantity(book);

        //then
        assertThat(quantity).isEqualTo(3);
    }

    @Test
    public void should_return_sum_of_quantity_when_book_is_added_again() {
        //given
        Basket basket = new Basket(1);

        basket.addBook(new Book("Java dla opornych", 19.99), 3);
        basket.addBook(new Book("Java dla opornych", 19.99), 2);

        //when
        int quantity = basket.getBookQuantity(new Book("Java dla opornych", 19.99));

        //then
        assertThat(quantity).isEqualTo(5);
    }

    @Test
    public void should_calculate_total_price_for_added_books() {
        //given
        Basket basket = new Basket(1);

        basket.addBook(new Book("Java dla opornych", 20.00), 3);
        basket.addBook(new Book("Java dla testerów", 30.00), 2);

        //when
        double price = basket.calculateTotalPrice();

        //then
        assertThat(price).isEqualTo(120.00);
    }
}