package cz.brazda.cookit.repository.service;

import cz.brazda.cookit.repository.entity.Ingredient;
import cz.brazda.cookit.repository.service.impl.IngredientServiceImpl;
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
public class IngredientServiceTest extends AbstractRepositoryServiceTest {

    public static final String NAME = "Sul";
    public static final String DESCRIPTION = "Koreni";
    private Ingredient item;

    @TestConfiguration
    static class IngredientServiceImplTestContextConfiguration {

        @MockBean
        private IngredientRepository ingredientRepository;


        @Bean
        public IngredientService userEventService() {
            return new IngredientServiceImpl(ingredientRepository) {
            };
        }

    }

    @Autowired
    private IngredientService userEventService;

    @Before
    public void mockIngredient(){
        item = new Ingredient(1L, NAME, DESCRIPTION);

        Stream<Ingredient> userEvents = Stream.of(item);
        Mockito.when(userEventService.findById(1L)).thenReturn(item);
        Mockito.when(userEventService.findAll()).thenReturn(userEvents.collect(Collectors.toList()));
        Mockito.when(userEventService.create(item)).thenReturn(item);
    }

    @Test
    public void whenValidId_thenShouldBeFound(){
        Ingredient userEvent = userEventService.findById(1L);
        assertAuthor(userEvent);
    }

    @Test
    public void whenNotValidId_thenReturnEmptyResult(){
        Ingredient Ingredient = userEventService.findById(3L);
        assertThat(Ingredient).isNull();
    }

    @Test
    public void getAllIngredientsTest(){
        List<Ingredient> Ingredients = userEventService.findAll();
        assertThat(Ingredients.size()).isEqualTo(1);
        assertThat(Ingredients).contains(item);
    }

    @Test
    public void createIngredientTest(){
        Ingredient createdIngredient = userEventService.create(item);
        assertAuthor(createdIngredient);
    }

    private void assertAuthor(Ingredient createdIngredient) {
        assertThat(createdIngredient.getName()).isEqualTo(NAME);
        assertThat(createdIngredient.getDescription()).isEqualTo(DESCRIPTION);
    }
}
