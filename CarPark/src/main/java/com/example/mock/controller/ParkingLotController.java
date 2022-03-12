package com.example.mock.controller;

import com.example.mock.constant.Constant;
import com.example.mock.entity.ParkingLot;
import com.example.mock.service.ParkingLotService;
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
@RequestMapping("/parking-lot")
public class ParkingLotController {
    private final ParkingLotService parkingLotService;

    public ParkingLotController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    /**
     *
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllParkingLot() {
        Map<String, Object> response = new HashMap<>();

        try {
            List<ParkingLot> parkingLotList = parkingLotService.getAllParkingLot();
            response.put("parkingLotList",parkingLotList);
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
    public ResponseEntity<Map<String, Object>> getAllParkingLotWithPaging(
            @RequestParam(name = "page", defaultValue = Constant.DEFAULT_NUM_PAGE) String raw_page) {
        Map<String, Object> response = new HashMap<>();
        int currentPage = Integer.parseInt(raw_page);
        Pageable pageable = PageRequest.of(currentPage, Constant.DEFAULT_PAGE_SIZE);
        int totalPage = (int) Math.ceil((double) (parkingLotService.totalParkingLot()) / Constant.DEFAULT_PAGE_SIZE);

        try {
            List<ParkingLot> parkingLotList = parkingLotService.getAllParkingLotWithPaging(pageable);
            response.put("parkingLotList",parkingLotList);
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
     * @param raw_id
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<ParkingLot> getParkingLotById(@PathVariable("id") String raw_id) {
        Long id = Long.parseLong(raw_id);

        ParkingLot parkingLot;
        try {
            parkingLot = parkingLotService.getParkingLotById(id);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(parkingLot, HttpStatus.OK);
    }

    /**
     *
     * @param parkingLot
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<ParkingLot> createNewParkingLot(@RequestBody ParkingLot parkingLot) {
        try {
            ParkingLot parkingLotReturn = parkingLotService.saveParkingLot(parkingLot);
            if (parkingLotReturn == null) {
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
     * @param parkingLot
     * @param raw_id
     * @return
     */
    @PutMapping("{id}")
    public ResponseEntity<ParkingLot> updateParkingLot(@RequestBody ParkingLot parkingLot, @PathVariable("id") String raw_id) {
        Long id = Long.parseLong(raw_id);
        try {
            parkingLot.setParkId(id);
            ParkingLot parkingLotReturn = parkingLotService.saveParkingLot(parkingLot);
            if (parkingLotReturn == null) {
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
     * @param raw_id
     * @return
     */
    @DeleteMapping("{id}")
    public ResponseEntity<ParkingLot> removeParkingLot(@PathVariable("id") String raw_id) {
        Long id = Long.parseLong(raw_id);
        try {
            parkingLotService.removeParkingLot(id);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
