package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;

public interface CarWriteRepository {
    public Car create(Car car);
    public Car update(String id, Car updatedCar);
    public void delete(String id);
}
