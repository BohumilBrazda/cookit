package cz.brazda.cookit.repository.service;

import cz.brazda.cookit.repository.UserEventRepository;
import cz.brazda.cookit.repository.entity.Author;
import cz.brazda.cookit.repository.entity.UserEvent;
import cz.brazda.cookit.repository.service.impl.UserEventServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class UserEventServiceTest extends AbstractRepositoryServiceTest {

    public static final String BOHUMIL = "Bohumil";
    public static final String BRAZDA = "Brazda";
    private UserEvent a;
    private Author author;
    private Date date;

    @TestConfiguration
    static class UserEventServiceImplTestContextConfiguration {

        @MockBean
        private UserEventRepository userEventuserEventRepository;


        @Bean
        public UserEventService userEventService() {
            return new UserEventServiceImpl(userEventuserEventRepository) {
            };
        }

    }

    @Autowired
    private UserEventService userEventService;

    @Before
    public void mockUserEvent(){
        author = new Author(1L, BOHUMIL, BRAZDA);
        date =  new Date();
        a = new UserEvent(1L, author, date);

        Stream<UserEvent> userEvents = Stream.of(a);
        Mockito.when(userEventService.findById(1L)).thenReturn(a);
        Mockito.when(userEventService.findAll()).thenReturn(userEvents.collect(Collectors.toList()));
        Mockito.when(userEventService.create(a)).thenReturn(a);
    }

    @Test
    public void whenValidId_thenShouldBeFound(){
        UserEvent userEvent = userEventService.findById(1L);
        assertAuthor(userEvent);
    }

    @Test
    public void whenNotValidId_thenReturnEmptyResult(){
        UserEvent UserEvent = userEventService.findById(3L);
        assertThat(UserEvent).isNull();
    }

    @Test
    public void getAllUserEventsTest(){
        List<UserEvent> UserEvents = userEventService.findAll();
        assertThat(UserEvents.size()).isEqualTo(1);
        assertThat(UserEvents).contains(a);
    }

    @Test
    public void createUserEventTest(){
        UserEvent createdUserEvent = userEventService.create(a);
        assertAuthor(createdUserEvent);
    }

    private void assertAuthor(UserEvent createdUserEvent) {
        assertThat(createdUserEvent.getAuthor()).isEqualTo(author);
        assertThat(createdUserEvent.getEventTime()).isEqualTo(date);
        assertThat(createdUserEvent.getName()).isEqualTo(createdUserEvent.getName());
    }
}
