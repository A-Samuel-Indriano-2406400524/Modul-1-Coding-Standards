package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HomeControllerTest {

    @Test
    void testHome() {
        HomeController controller = new HomeController();
        String viewName = controller.home();
        assertEquals("index", viewName);
    }
}