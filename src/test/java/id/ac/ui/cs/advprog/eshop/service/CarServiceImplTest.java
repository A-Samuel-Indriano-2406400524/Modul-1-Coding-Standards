package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    @Test
    void testCreate() {
        Car car = new Car();
        car.setCarId("car-1");
        when(carRepository.create(car)).thenReturn(car);

        Car createdCar = carService.create(car);
        assertSame(car, createdCar);
        verify(carRepository).create(car);
    }

    @Test
    void testFindAll() {
        Car firstCar = new Car();
        firstCar.setCarId("car-1");
        Car secondCar = new Car();
        secondCar.setCarId("car-2");
        Iterator<Car> iterator = List.of(firstCar, secondCar).iterator();
        when(carRepository.findAll()).thenReturn(iterator);

        List<Car> cars = carService.findAll();
        assertEquals(2, cars.size());
        assertSame(firstCar, cars.get(0));
        assertSame(secondCar, cars.get(1));
    }

    @Test
    void testFindById() {
        Car car = new Car();
        car.setCarId("car-1");
        when(carRepository.findById("car-1")).thenReturn(car);

        Car result = carService.findById("car-1");
        assertSame(car, result);
        verify(carRepository).findById("car-1");
    }

    @Test
    void testUpdate() {
        Car car = new Car();
        car.setCarId("car-1");

        carService.update("car-1", car);
        verify(carRepository).update("car-1", car);
    }

    @Test
    void testDeleteCarById() {
        carService.deleteCarById("car-1");
        verify(carRepository).delete("car-1");
    }
}
