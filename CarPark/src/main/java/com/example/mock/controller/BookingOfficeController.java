package com.example.mock.controller;

import com.example.mock.constant.Constant;
import com.example.mock.dto.BookingOfficeDTO;
import com.example.mock.entity.BookingOffice;
import com.example.mock.service.BookingOfficeService;
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
@RequestMapping("/booking-office") // => @RequestMapping("api/v1/booking-office")
public class BookingOfficeController {
    
    /**
    @Autowired
    private BookingOfficeService bookingOfficeService; => NG
     */
    
    private final BookingOfficeService bookingOfficeService;

    public BookingOfficeController(BookingOfficeService bookingOfficeService) {
        this.bookingOfficeService = bookingOfficeService;
    }

    /**
     *
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllBookingOffice() {
        Map<String, Object> response = new HashMap<>();

        try {
            List<BookingOfficeDTO> bookingOfficeList = bookingOfficeService.getAllBookingOffice();
            response.put("bookingOfficeList", bookingOfficeList);

        } catch (Exception e) { // => Da catch o service nen branch nay khong vao duoc
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // => khong can
        }
        return new ResponseEntity<>(response, HttpStatus.OK); // => always return http OK
    }

    /**
     *
     * @param raw_page
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAllBookingOffice(
            @RequestParam(name = "page", defaultValue = Constant.DEFAULT_NUM_PAGE) String raw_page) {
        Map<String, Object> response = new HashMap<>();
        int currentPage = Integer.parseInt(raw_page);
        Pageable pageable = PageRequest.of(currentPage, Constant.DEFAULT_PAGE_SIZE);
        int totalPage = (int) Math.ceil((double) (bookingOfficeService.totalBookingOffice()) / Constant.DEFAULT_PAGE_SIZE);

        try {
            List<BookingOfficeDTO> bookingOfficeList = bookingOfficeService.getAllBookingOfficeWithPaging(pageable);
            response.put("bookingOfficeList", bookingOfficeList);
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
    public ResponseEntity<BookingOffice> getBookingOfficeById(@PathVariable("id") String raw_id) {
        Long id = Long.parseLong(raw_id);

        BookingOffice bookingOffice;
        try {
            bookingOffice = bookingOfficeService.getBookingOfficeById(id);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookingOffice, HttpStatus.OK);
    }

    /**
     *
     * @param bookingOffice
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<BookingOffice> createNewBookingOffice(@RequestBody BookingOffice bookingOffice) {
        try {
            BookingOffice bookingOfficeReturn = bookingOfficeService.saveBookingOffice(bookingOffice);
            if (bookingOfficeReturn == null) {
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
     * @param bookingOffice
     * @param raw_id
     * @return
     */
    @PutMapping("{id}")
    public ResponseEntity<BookingOffice> updateBookingOffice(@RequestBody BookingOffice bookingOffice, @PathVariable("id") String raw_id) {
        Long id = Long.parseLong(raw_id);
        try {
            bookingOffice.setOfficeId(id);
            BookingOffice bookingOfficeReturn = bookingOfficeService.saveBookingOffice(bookingOffice);
            if (bookingOfficeReturn == null) {
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
    public ResponseEntity<BookingOffice> removeBookingOffice(@PathVariable("id") String raw_id) {
        Long id = Long.parseLong(raw_id);

        try{
            bookingOfficeService.removeBookingOffice(id);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
