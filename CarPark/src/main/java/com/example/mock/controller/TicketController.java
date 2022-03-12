package com.example.mock.controller;

import com.example.mock.constant.Constant;
import com.example.mock.dto.TicketDTO;
import com.example.mock.entity.Ticket;
import com.example.mock.service.TicketService;
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
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    /**
     *
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllTicket() {
        Map<String, Object> response = new HashMap<>();

        try {
            List<TicketDTO> ticketList = ticketService.getAllTicket();
            response.put("ticketList",ticketList);
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
    public ResponseEntity<Map<String, Object>> getAllTicketWithPaging(
            @RequestParam(name = "page", defaultValue = Constant.DEFAULT_NUM_PAGE) String raw_page) {
        Map<String, Object> response = new HashMap<>();
        int currentPage = Integer.parseInt(raw_page);
        Pageable pageable = PageRequest.of(currentPage, Constant.DEFAULT_PAGE_SIZE);
        int totalPage = (int) Math.ceil((double) (ticketService.totalTicket()) / Constant.DEFAULT_PAGE_SIZE);

        try {
            List<TicketDTO> ticketList = ticketService.getAllTicketWithPaging(pageable);
            response.put("ticketList",ticketList);
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
    public ResponseEntity<Ticket> getTicketById(@PathVariable("id") String raw_id) {
        Long id = Long.parseLong(raw_id);

        Ticket ticket;
        try {
            ticket = ticketService.getTicketById(id);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    /**
     *
     * @param ticket
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<Ticket> createNewTicket(@RequestBody Ticket ticket) {
        try {
            Ticket ticketReturn = ticketService.saveTicket(ticket);
            if (ticketReturn == null) {
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
     * @param ticket
     * @param raw_id
     * @return
     */
    @PutMapping("{id}")
    public ResponseEntity<Ticket> updateTicket(@RequestBody Ticket ticket, @PathVariable("id") String raw_id) {
        Long id = Long.parseLong(raw_id);
        try {
            ticket.setTicketId(id);
            Ticket ticketReturn = ticketService.saveTicket(ticket);
            if (ticketReturn == null) {
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
    public ResponseEntity<Ticket> removeTicket(@PathVariable("id") String raw_id) {
        Long id = Long.parseLong(raw_id);

        try{
            ticketService.removeTicket(id);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
