package ru.solomanin.testcinema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.solomanin.testcinema.dao.TicketDao;
import ru.solomanin.testcinema.entity.Ticket;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService{

    private TicketDao ticketDao;

    @Autowired
    public TicketServiceImpl(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override
    @Transactional
    public Ticket createTicket(Ticket ticket) {
        return ticketDao.createTicket(ticket);
    }

    @Override
    @Transactional
    public List<Ticket> findAllTickets() {
        return ticketDao.findAllTickets();
    }

    @Override
    @Transactional
    public void deleteTicket(int ticketNum) {
        ticketDao.deleteTicket(ticketNum);
    }

    @Override
    @Transactional
    public Ticket findTicketByNum(int ticketNum) {
        return ticketDao.findTicketByNum(ticketNum);
    }
}
