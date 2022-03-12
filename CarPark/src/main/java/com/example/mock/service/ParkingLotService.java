package com.example.mock.service;

import com.example.mock.entity.ParkingLot;
import com.example.mock.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ParkingLotService {
    private final ParkingLotRepository parkingLotRepository;

    public ParkingLotService(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    /**
     *
     * @return
     */
    public List<ParkingLot> getAllParkingLot() {
        try {
            return parkingLotRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param pageable
     * @return
     */
    public List<ParkingLot> getAllParkingLotWithPaging(Pageable pageable) {
        try {
            return parkingLotRepository.findAll(pageable).getContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    public ParkingLot getParkingLotById(Long id) {
        return parkingLotRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Data with this id: " + id + " is not found"));
    }

    /**
     *
     * @param parkingLot
     * @return
     */
    public ParkingLot saveParkingLot(ParkingLot parkingLot) {
        try {
            return parkingLotRepository.save(parkingLot);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 
     * @param id
     */
    public void removeParkingLot(Long id) {
        try {
            parkingLotRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    public int totalParkingLot() {
        return (int) parkingLotRepository.count();
    }
}
