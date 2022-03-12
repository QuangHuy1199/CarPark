package com.example.mock.controller;

import com.example.mock.constant.Constant;
import com.example.mock.dto.CarDTO;
import com.example.mock.entity.Car;
import com.example.mock.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/car")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    /**
     *
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllCar() {
        Map<String, Object> response = new HashMap<>();

        try {
            List<CarDTO> carList = carService.getAllCar();
            response.put("carList",carList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     *
     * @param raw_page
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAllCarWithPaging(
            @RequestParam(name = "page", defaultValue = Constant.DEFAULT_NUM_PAGE) String raw_page) {
        Map<String, Object> response = new HashMap<>();
        int currentPage = Integer.parseInt(raw_page);
        Pageable pageable = PageRequest.of(currentPage, Constant.DEFAULT_PAGE_SIZE);
        int totalPage = (int) Math.ceil((double) (carService.totalCar()) / Constant.DEFAULT_PAGE_SIZE);

        try {
            List<CarDTO> carList = carService.getAllCarWithPaging(pageable);
            response.put("carList",carList);
            response.put("current_page", currentPage);
            response.put("total_page", totalPage);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     *
     * @param licensePlate
     * @return
     */
    @GetMapping("{licensePlate}")
    public ResponseEntity<Car> getCarByLicensePlate(@PathVariable("licensePlate") String licensePlate) {
        Car car;
        try {
            car = carService.getCarByLicensePlate(licensePlate);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    /**
     *
     * @param car
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<Car> createNewCar(@RequestBody Car car) {
        try {
            Car carReturn = carService.saveCar(car);
            if (carReturn == null) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param car
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity<Car> updateCar(@RequestBody Car car) {
        try {
            Car carReturn = carService.saveCar(car);
            if (carReturn == null) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param licensePlate
     * @return
     */
    @DeleteMapping("{licensePlate}")
    public ResponseEntity<Car> removeCar(@PathVariable("licensePlate") String licensePlate) {
        try{
            carService.removeCar(licensePlate);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
