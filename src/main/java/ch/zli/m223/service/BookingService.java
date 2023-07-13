package ch.zli.m223.service;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import ch.zli.m223.model.Booking;

@ApplicationScoped
public class BookingService {

  @Inject
  EntityManager entityManager;

  @Transactional
  public Booking createBooking(Booking booking) {
    entityManager.persist(booking);
    return booking;
  }

  @Transactional
  public void deleteBooking(Long id) {
    var booking = entityManager.find(Booking.class, id);
    if (booking == null) {
      throw new NotFoundException();
    }
    entityManager.remove(booking);
  }

  @Transactional
  public Booking updateBooking(Booking newbooking) {
    entityManager.merge(newbooking);
    return newbooking;
  }

  public List<Booking> findAll() {
    var query = entityManager.createQuery("FROM Booking", Booking.class);
    return query.getResultList();
  }

}