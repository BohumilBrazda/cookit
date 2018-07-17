package cz.brazda.cookit.repository.service;

import cz.brazda.cookit.repository.AuthorRepository;
import cz.brazda.cookit.repository.entity.Author;
import cz.brazda.cookit.repository.service.impl.AuthorServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class AuthorServiceTest extends AbstractRepositoryServiceTest {

    public static final String BOHUMIL = "Bohumil";
    public static final String BRAZDA = "Brazda";
    private Author a;
    private Author b;

    @TestConfiguration
    static class AuthorServiceImplTestContextConfiguration {

        @MockBean
        private AuthorRepository authorRepository;


        @Bean
        public AuthorService authorService() {
            return new AuthorServiceImpl(authorRepository) {
            };
        }

    }

    @Autowired
    private AuthorService authorService;

    @Before
    public void mockAuthor(){
        a = new Author(1L, BOHUMIL, BRAZDA);
        b = new Author(2L, "Franta", "Vokurka");
        Stream<Author> authors = Stream.of(a,b);
        Mockito.when(authorService.findById(1L)).thenReturn(a);
        Mockito.when(authorService.findById(2L)).thenReturn(b);
        Mockito.when(authorService.findAll()).thenReturn(authors.collect(Collectors.toList()));
        Mockito.when(authorService.create(a)).thenReturn(a);
    }

    @Test
    public void whenValidId_thenShouldBeFound(){
        Author author = authorService.findById(1L);
        assertThat(author.getFirstName()).isEqualTo(BOHUMIL);
        assertThat(author.getSecondName()).isEqualTo(BRAZDA);
    }

    @Test
    public void whenNotValidId_thenReturnEmptyResult(){
        Author author = authorService.findById(3L);
        assertThat(author).isNull();
    }

    @Test
    public void getAllAuthorsTest(){
        List<Author> authors = authorService.findAll();
        assertThat(authors.size()).isEqualTo(2);
        assertThat(authors).contains(a);
        assertThat(authors).contains(b);
    }

    @Test
    public void createAuthorTest(){
        Author createdAuthor = authorService.create(a);
        assertThat(createdAuthor.getId()).isEqualTo(1L);
        assertThat(createdAuthor.getFirstName()).isEqualTo(BOHUMIL);
        assertThat(createdAuthor.getSecondName()).isEqualTo(BRAZDA);
    }
}
