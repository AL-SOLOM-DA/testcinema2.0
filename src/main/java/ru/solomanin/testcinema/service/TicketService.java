package ru.solomanin.testcinema.service;

import ru.solomanin.testcinema.entity.Ticket;

import java.util.List;

public interface TicketService {
    Ticket createTicket(Ticket ticket);

    List<Ticket> findAllTickets();

    void deleteTicket(int ticketNum);

    Ticket findTicketByNum(int ticketNum);
}
