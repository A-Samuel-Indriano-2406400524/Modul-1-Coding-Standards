package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarReadRepository;
import id.ac.ui.cs.advprog.eshop.repository.CarWriteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarReadRepository carReadRepository;

    @Mock
    private CarWriteRepository carWriteRepository;

    @InjectMocks
    private CarServiceImpl carService;

    @Test
    void testCreate() {
        Car car = new Car();
        car.setCarId("car-1");
        when(carWriteRepository.create(car)).thenReturn(car);

        Car createdCar = carService.create(car);
        assertSame(car, createdCar);
        verify(carWriteRepository).create(car);
    }

    @Test
    void testCreateAssignsIdWhenCarIdIsNull() {
        Car car = new Car();

        Car createdCar = carService.create(car);
        assertNotNull(createdCar.getCarId());
        assertFalse(createdCar.getCarId().isBlank());
        verify(carWriteRepository).create(car);
    }

    @Test
    void testFindAll() {
        Car firstCar = new Car();
        firstCar.setCarId("car-1");
        Car secondCar = new Car();
        secondCar.setCarId("car-2");
        Iterator<Car> iterator = List.of(firstCar, secondCar).iterator();
        when(carReadRepository.findAll()).thenReturn(iterator);

        List<Car> cars = carService.findAll();
        assertEquals(2, cars.size());
        assertSame(firstCar, cars.get(0));
        assertSame(secondCar, cars.get(1));
    }

    @Test
    void testFindById() {
        Car car = new Car();
        car.setCarId("car-1");
        when(carReadRepository.findById("car-1")).thenReturn(car);

        Car result = carService.findById("car-1");
        assertSame(car, result);
        verify(carReadRepository).findById("car-1");
    }

    @Test
    void testUpdate() {
        Car car = new Car();
        car.setCarId("car-1");

        carService.update("car-1", car);
        verify(carWriteRepository).update("car-1", car);
    }

    @Test
    void testDeleteCarById() {
        carService.deleteCarById("car-1");
        verify(carWriteRepository).delete("car-1");
    }
}
