package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    @Mock
    private CarServiceImpl carservice;

    @Mock
    private ProductService service;

    @Mock
    private Model model;

    @InjectMocks
    private CarController controller;

    @Test
    void testCreateCarPage() {
        String viewName = controller.createCarPage(model);
        assertEquals("createCar", viewName);
        verify(model).addAttribute(org.mockito.ArgumentMatchers.eq("car"), org.mockito.ArgumentMatchers.any(Car.class));
    }

    @Test
    void testCreateCarPost() {
        Car car = new Car();
        String viewName = controller.createCarPost(car, model);
        assertEquals("redirect:listCar", viewName);
        verify(carservice).create(car);
    }

    @Test
    void testCarListPage() {
        Car car = new Car();
        List<Car> cars = List.of(car);
        when(carservice.findAll()).thenReturn(cars);
        String viewName = controller.carListPage(model);
        assertEquals("carList", viewName);
        verify(model).addAttribute("cars", cars);
    }

    @Test
    void testEditCarPageWhenCarFound() {
        Car car = new Car();
        car.setCarId("car-1");
        when(carservice.findById("car-1")).thenReturn(car);
        String viewName = controller.editCarPage("car-1", model);
        assertEquals("editCar", viewName);
        verify(model).addAttribute("car", car);
    }

    @Test
    void testEditCarPageWhenCarNotFound() {
        when(carservice.findById("missing")).thenReturn(null);
        String viewName = controller.editCarPage("missing", model);
        assertEquals("editCar", viewName);
        verify(model).addAttribute("car", null);
    }

    @Test
    void testEditCarPost() {
        Car car = new Car();
        car.setCarId("car-1");
        String viewName = controller.editCarPost(car, model);
        assertEquals("redirect:listCar", viewName);
        verify(carservice).update("car-1", car);
    }

    @Test
    void testDeleteCar() {
        String viewName = controller.deleteCar("car-1");
        assertEquals("redirect:listCar", viewName);
        verify(carservice).deleteCarById("car-1");
    }
}