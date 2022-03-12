package com.example.mock.controller;

import com.example.mock.constant.Constant;
import com.example.mock.dto.TripDTO;
import com.example.mock.entity.Trip;
import com.example.mock.service.TripService;
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
@RequestMapping("/trip")
public class TripController {
    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    /**
     *
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllTrip() {
        Map<String, Object> response = new HashMap<>();

        try {
            List<TripDTO> tripList = tripService.listAllTrip();
            response.put("tripList", tripList);
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
    public ResponseEntity<Map<String, Object>> getAllTripWithPaging(
            @RequestParam(name = "page", defaultValue = Constant.DEFAULT_NUM_PAGE) String raw_page) {
        Map<String, Object> response = new HashMap<>();
        int currentPage = Integer.parseInt(raw_page);
        Pageable pageable = PageRequest.of(currentPage, Constant.DEFAULT_PAGE_SIZE);
        int totalPage = (int) Math.ceil((double) (tripService.totalTrip()) / Constant.DEFAULT_PAGE_SIZE);

        try {
            List<TripDTO> tripList = tripService.listAllTripWithPaging(pageable);
            response.put("tripList", tripList);
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
    public ResponseEntity<Trip> getTripById(@PathVariable("id") String raw_id) {
        Long id = Long.parseLong(raw_id);

        Trip trip;
        try {
            trip = tripService.getTripById(id);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trip, HttpStatus.OK);
    }

    /**
     *
     * @param trip
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<Trip> createTrip(@RequestBody Trip trip) {
        try {
            Trip tripReturn = tripService.saveTrip(trip);
            if (tripReturn == null) {
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
     * @param trip
     * @param raw_id
     * @return
     */
    @PutMapping("{id}")
    public ResponseEntity<Trip> updateTrip(@RequestBody Trip trip, @PathVariable("id") String raw_id) {
        Long id = Long.parseLong(raw_id);
        try {
            trip.setTripId(id);
            Trip tripReturn = tripService.saveTrip(trip);
            if (tripReturn == null) {
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
    public ResponseEntity<Trip> removeTrip(@PathVariable("id") String raw_id) {
        Long id = Long.parseLong(raw_id);

        try{
            tripService.removeTrip(id);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
