package com.example.mock.service;


import com.example.mock.dto.TripDTO;
import com.example.mock.entity.Trip;
import com.example.mock.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TripService {
    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    /**
     *
     * @return
     */
    public List<TripDTO> listAllTrip() {
        List<TripDTO> result = new ArrayList<>();
        try {
            List<Trip> tripList = tripRepository.findAll();
            tripList.forEach(trip -> {
                TripDTO tripDTO = new TripDTO();
                tripDTO.setTripId(trip.getTripId());
                tripDTO.setCarType(trip.getCarType());
                tripDTO.setDepartureTime(trip.getDepartureTime());
                tripDTO.setDestination(trip.getDestination());
                tripDTO.setDriver(trip.getDriver());
                tripDTO.setBookedTicketNumber(trip.getBookedTicketNumber());

                result.add(tripDTO);
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
    public List<TripDTO> listAllTripWithPaging(Pageable pageable) {
        List<TripDTO> result = new ArrayList<>();
        try {
            List<Trip> tripList = tripRepository.findAll(pageable).getContent();
            tripList.forEach(trip -> {
                TripDTO tripDTO = new TripDTO();
                tripDTO.setTripId(trip.getTripId());
                tripDTO.setCarType(trip.getCarType());
                tripDTO.setDepartureTime(trip.getDepartureTime());
                tripDTO.setDestination(trip.getDestination());
                tripDTO.setDriver(trip.getDriver());
                tripDTO.setBookedTicketNumber(trip.getBookedTicketNumber());

                result.add(tripDTO);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *
     * @param id
     * @return
     */
    public Trip getTripById(Long id) {
        return tripRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Data with this id: " + id + " is not found"));
    }

    /**
     *
     * @param trip
     * @return
     */
    public Trip saveTrip(Trip trip) {
        try {
            return tripRepository.save(trip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param id
     */
    public void removeTrip(Long id) {
        try {
            tripRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    public int totalTrip() {
        return (int) tripRepository.count();
    }
}
