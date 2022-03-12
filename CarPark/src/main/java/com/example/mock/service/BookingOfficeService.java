package com.example.mock.service;

import com.example.mock.dto.BookingOfficeDTO;
import com.example.mock.entity.BookingOffice;
import com.example.mock.repository.BookingOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingOfficeService {
    private final BookingOfficeRepository bookingOfficeRepository;

    public BookingOfficeService(BookingOfficeRepository bookingOfficeRepository) {
        this.bookingOfficeRepository = bookingOfficeRepository;
    }

    /**
     *
     * @return
     */
    public List<BookingOfficeDTO> getAllBookingOffice() {
        List<BookingOfficeDTO> result = new ArrayList<>();
        try {
            List<BookingOffice> bookingOfficeList = bookingOfficeRepository.findAll();
            bookingOfficeList.forEach(bookingOffice -> {
                BookingOfficeDTO bookingOfficeDTO = new BookingOfficeDTO();
                bookingOfficeDTO.setOfficeId(bookingOffice.getOfficeId());
                bookingOfficeDTO.setOfficeName(bookingOffice.getOfficeName());
                bookingOfficeDTO.setTrip(bookingOffice.getTrip().getDestination());

                result.add(bookingOfficeDTO);
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
    public List<BookingOfficeDTO> getAllBookingOfficeWithPaging(Pageable pageable) {
        List<BookingOfficeDTO> result = new ArrayList<>();
        try {
            List<BookingOffice> bookingOfficeList = bookingOfficeRepository.findAll(pageable).getContent();
            bookingOfficeList.forEach(bookingOffice -> {
                BookingOfficeDTO bookingOfficeDTO = new BookingOfficeDTO();
                bookingOfficeDTO.setOfficeId(bookingOffice.getOfficeId());
                bookingOfficeDTO.setOfficeName(bookingOffice.getOfficeName());
                bookingOfficeDTO.setTrip(bookingOffice.getTrip().getDestination());

                result.add(bookingOfficeDTO);
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
    public BookingOffice getBookingOfficeById(Long id) {
        return bookingOfficeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Data with this id: " + id + " is not found"));
    }

    /**
     *
     * @param bookingOffice
     * @return
     */
    public BookingOffice saveBookingOffice(BookingOffice bookingOffice) {
        try {
            return bookingOfficeRepository.save(bookingOffice);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param id
     */
    public void removeBookingOffice(Long id) {
        try {
            bookingOfficeRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    public int totalBookingOffice() {
        return (int) bookingOfficeRepository.count();
    }
}
