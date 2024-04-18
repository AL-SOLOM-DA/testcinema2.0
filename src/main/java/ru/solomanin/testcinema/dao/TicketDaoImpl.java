package ru.solomanin.testcinema.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.solomanin.testcinema.entity.MovieSession;
import ru.solomanin.testcinema.entity.Ticket;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class TicketDaoImpl implements TicketDao {

    private MovieSessionDao movieSessionDao;

    private MovieDao movieDao;

    private EntityManager entityManager;

    private Logger logger = Logger.getLogger(TicketDaoImpl.class.getName());

    @Autowired
    public TicketDaoImpl(MovieSessionDao movieSessionDao, EntityManager entityManager, MovieDao movieDao) {
        this.movieSessionDao = movieSessionDao;
        this.entityManager = entityManager;
        this.movieDao = movieDao;
    }

    @Override
    public List<Ticket> findAllTickets() {

        Session session = entityManager.unwrap(Session.class);
        Query<Ticket> query = session.createQuery("from Ticket", Ticket.class);
        List<Ticket> ticketList = query.getResultList();

        return ticketList;
    }

    @Override
    public Ticket findTicketByNum(int ticketNum) {
        Session currentSession = entityManager.unwrap(Session.class);
        Ticket ticket = currentSession.get(Ticket.class, ticketNum);

        return ticket;
    }

    @Override
    @Transactional
    public Ticket createTicket(Ticket ticket) {
        logger.info(ticket.toString());
        Session currentSession = entityManager.unwrap(Session.class);

        //Получаем сеанс на который куплен билет

        MovieSession movieSession = movieSessionDao.findMovieSessionById(ticket.getMovieSession().getId());

        //Заменяем купленные номера мест нулями
        int indexSeat = ticket.getSeat()-1;

        if (movieSession.getSeats()[indexSeat] != 0) {
            movieSession.getSeats()[indexSeat] = 0;
        } else {
                throw new RuntimeException("К сожалению место " + ticket.getSeat() + " только что заронировали, выберете другое место");
        }
        //добавляем билет в БД
        currentSession.saveOrUpdate(ticket);
        movieSession.addTicket(ticket);

        return ticket;
    }
    @Override
    public void deleteTicket(int ticketNum) {
        Session currentSession = entityManager.unwrap(Session.class);
        Ticket ticket = findTicketByNum(ticketNum);
        MovieSession movieSession = ticket.getMovieSession();
        //Получаем место на которе куплен билет
        int ticketSeat = ticket.getSeat();
        int [] movieSessionSeats = movieSession.getSeats();

        //Заменяем номера c нулями на реальный номер

            if (movieSessionSeats[ticketSeat - 1] == 0) {
                movieSessionSeats[ticketSeat - 1] = ticketSeat;
            }
            else {
                throw new RuntimeException("К сожалению, невозможно вернуть билет. Свяжитесь со службой поддержки");
            }
        currentSession.delete(ticket);
    }


}
