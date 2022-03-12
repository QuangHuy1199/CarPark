package com.example.mock.service;

import com.example.mock.dto.CarDTO;
import com.example.mock.entity.Car;
import com.example.mock.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /**
     *
     * @return
     */
    public List<CarDTO> getAllCar() {
        List<CarDTO> result = new ArrayList<>();
        try {
            List<Car> carList = carRepository.findAll();
            carList.forEach(car -> {
                CarDTO carDTO = new CarDTO();
                carDTO.setLicensePlate(car.getLicensePlate());
                carDTO.setCarColor(car.getCarColor());
                carDTO.setCarType(car.getCarType());
                carDTO.setCompany(car.getCompany());
                carDTO.setParkingLot(car.getParkingLot().getParkName());

                result.add(carDTO);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *
     * @param pageable
     * @return
     */
    public List<CarDTO> getAllCarWithPaging(Pageable pageable) {
        List<CarDTO> result = new ArrayList<>();
        try {
            List<Car> carList = carRepository.findAll(pageable).getContent();
            carList.forEach(car -> {
                CarDTO carDTO = new CarDTO();
                carDTO.setLicensePlate(car.getLicensePlate());
                carDTO.setCarColor(car.getCarColor());
                carDTO.setCarType(car.getCarType());
                carDTO.setCompany(car.getCompany());
                carDTO.setParkingLot(car.getParkingLot().getParkName());

                result.add(carDTO);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *
     * @param licensePlate
     * @return
     */
    public Car getCarByLicensePlate(String licensePlate) {
        return carRepository.findById(licensePlate).orElseThrow(() -> new EntityNotFoundException("Data with this id: " + licensePlate + " is not found"));
    }

    /**
     *
     * @param car
     * @return
     */
    public Car saveCar(Car car) {
        try {
            return carRepository.save(car);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param licensePlate
     */
    public void removeCar(String licensePlate) {
        try {
            carRepository.deleteById(licensePlate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    public int totalCar() {
        return (int) carRepository.count();
    }
}
