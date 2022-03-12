package com.example.mock.service;

import com.example.mock.dto.TicketDTO;
import com.example.mock.entity.Ticket;
import com.example.mock.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    /**
     *
     * @return
     */
    public List<TicketDTO> getAllTicket() {
        List<TicketDTO> result = new ArrayList<>();
        try {
            List<Ticket> ticketList = ticketRepository.findAll();
            ticketList.forEach(ticket -> {
                TicketDTO ticketDTO = new TicketDTO();
                ticketDTO.setTicketId(ticket.getTicketId());
                ticketDTO.setBookingTime(ticket.getBookingTime());
                ticketDTO.setCar(ticket.getCar().getLicensePlate());
                ticketDTO.setCustomerName(ticket.getCustomerName());
                ticketDTO.setTrip(ticket.getTrip().getDestination());

                result.add(ticketDTO);
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
    public List<TicketDTO> getAllTicketWithPaging(Pageable pageable) {
        List<TicketDTO> result = new ArrayList<>();
        try {
            List<Ticket> ticketList = ticketRepository.findAll(pageable).getContent();
            ticketList.forEach(ticket -> {
                TicketDTO ticketDTO = new TicketDTO();
                ticketDTO.setTicketId(ticket.getTicketId());
                ticketDTO.setBookingTime(ticket.getBookingTime());
                ticketDTO.setCar(ticket.getCar().getLicensePlate());
                ticketDTO.setCustomerName(ticket.getCustomerName());
                ticketDTO.setTrip(ticket.getTrip().getDestination());

                result.add(ticketDTO);
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
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Data with this id: " + id + " is not found"));
    }

    /**
     *
     * @param ticket
     * @return
     */
    public Ticket saveTicket(Ticket ticket) {
        try {
            return ticketRepository.save(ticket);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param id
     */
    public void removeTicket(Long id) {
        try {
            ticketRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    public int totalTicket() {
        return (int) ticketRepository.count();
    }
}
