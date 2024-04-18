package ru.solomanin.testcinema.dao;

import org.apache.catalina.LifecycleState;
import ru.solomanin.testcinema.entity.Ticket;

import java.util.List;

public interface TicketDao {
    Ticket createTicket(Ticket ticket);

    List<Ticket> findAllTickets();

    void deleteTicket(int ticketNum);

    Ticket findTicketByNum(int ticketNum);
}
