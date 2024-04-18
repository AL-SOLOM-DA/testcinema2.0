package ru.solomanin.testcinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.solomanin.testcinema.dao.TicketDao;
import ru.solomanin.testcinema.entity.Ticket;
import ru.solomanin.testcinema.service.TicketService;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class TicketController {

    private Logger logger = Logger.getLogger(TicketController.class.getName());

    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/ticket")
    public List<Ticket> ticketList(){
        return ticketService.findAllTickets();
    }


    @PostMapping("/ticket")
    public Ticket bookingTicket (@RequestBody Ticket ticket){
            Ticket ticket1 = ticketService.createTicket(ticket);
        return ticket1;
    }

    @GetMapping("ticket/{ticketNum}")
    public Ticket findTicketByNum(@PathVariable int ticketNum){
        return ticketService.findTicketByNum(ticketNum);
    }

    @DeleteMapping("/ticket/{id}")
    public String deleteTicket(@PathVariable int id){
        ticketService.deleteTicket(id);
        return "Бронирование номер: " + id + " отменено!";
    }

}
