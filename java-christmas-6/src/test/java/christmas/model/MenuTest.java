package christmas.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class MenuTest {

    @Test
    void fromName_returnsNullForUnknownName() {
        assertNull(Menu.fromName("이상한이름"));
        assertNull(Menu.fromName(""));
        assertNull(Menu.fromName(null));
    }

    @Test
    void fromName_returnsCorrectMenuEnum() {
        assertEquals(Menu.양송이수프, Menu.fromName("양송이수프"));
        assertEquals(Menu.티본스테이크, Menu.fromName("티본스테이크"));
        assertEquals(Menu.초코케이크, Menu.fromName("초코케이크"));
        assertEquals(Menu.제로콜라, Menu.fromName("제로콜라"));
        assertEquals(Menu.샴페인, Menu.fromName("샴페인"));
    }

    @Test
    void getName_returnsCorrectName() {
        assertEquals("양송이수프", Menu.양송이수프.getName());
        assertEquals("티본스테이크", Menu.티본스테이크.getName());
        assertEquals("초코케이크", Menu.초코케이크.getName());
        assertEquals("제로콜라", Menu.제로콜라.getName());
        assertEquals("샴페인", Menu.샴페인.getName());
    }

}