package com.example.ServiceDeskV1;

import com.example.ServiceDeskV1.entity.AppUser;
import com.example.ServiceDeskV1.entity.Ticket;
import com.example.ServiceDeskV1.repository.AppUserRepository;
import com.example.ServiceDeskV1.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
public class AppUserAndTicketTest {


    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    @Transactional
    public void createUser() {

        AppUser user1 = new AppUser("andrew@xxx.com","123");
        appUserRepository.saveAndFlush(user1);



    }
    @Test
    @Transactional
    public void createTicket() {

        AppUser user2 = new AppUser("gargamel@zzz.com","123");
        user2 = appUserRepository.saveAndFlush(user2);


        Ticket ticket1 = new Ticket("Problem z komputerem",
                "Komputer sie nie wlacza po aktualizacji",
                user2);

        ticketRepository.saveAndFlush(ticket1);

    }
    @Test
    @Transactional
    @Commit
    public void removeTestData() {

        ticketRepository.deleteById(1L);
        ticketRepository.flush();
        appUserRepository.deleteById(1L);
        appUserRepository.deleteById(2L);
    }


}
