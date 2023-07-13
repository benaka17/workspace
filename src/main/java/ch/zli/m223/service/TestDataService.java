package ch.zli.m223.service;

import java.time.LocalDate;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.ApplicationUser;
import ch.zli.m223.model.Booking;
import ch.zli.m223.model.RoleEnum;
import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.runtime.StartupEvent;

@IfBuildProfile("dev")
@ApplicationScoped
public class TestDataService {

  @Inject
  EntityManager entityManager;

  @Transactional
  public void generateTestData(@Observes StartupEvent event) {
    
    var admin = new ApplicationUser();
    admin.setFirstname("Jonathan");
    admin.setLastname("Jones");
    admin.setEmail("JJ123@gmail.com");
    admin.setPassword("zli123");
    admin.setRole(RoleEnum.ADMIN);
    entityManager.persist(admin);

    var member = new ApplicationUser();
    member.setFirstname("Khabib");
    member.setLastname("Nurmagomedov");
    member.setEmail("KN123@gmail.com");
    member.setPassword("zli123");
    member.setRole(RoleEnum.MEMBER);
    entityManager.persist(member);

    var booking = new Booking();
    booking.setDate(LocalDate.now());
    booking.setDuration(1);
    booking.setStatus("Pending");
    booking.setSmoker(true);
    entityManager.persist(booking);
    

  }

}