package cz.brazda.cookit.repository.service;

import cz.brazda.cookit.repository.MealRepository;
import cz.brazda.cookit.repository.entity.Meal;
import cz.brazda.cookit.repository.entity.exceptions.MealNotFound;
import cz.brazda.cookit.repository.service.impl.MealServiceImpl;
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
import static org.assertj.core.api.Assertions.fail;

@RunWith(SpringRunner.class)
public class MealServiceTest extends AbstractRepositoryServiceTest {

    public static final String NAME = "Sul";
    public static final String DESCRIPTION = "Koreni";
    public static final String UPDATE = "update";
    private Meal item;

    @TestConfiguration
    static class MealServiceImplTestContextConfiguration {

        @MockBean
        private MealRepository mealRepository;


        @Bean
        public MealService userEventService() {
            return new MealServiceImpl(mealRepository) {
            };
        }
    }

    @Autowired
    private MealServiceImpl mealService;

    @Before
    public void createMocks() throws MealNotFound {
        mockMealAndServiceCalls();
    }

    private void mockMealAndServiceCalls() {
        item = new Meal(1L, NAME, DESCRIPTION);


        Stream<Meal> userEvents = Stream.of(item);
        Mockito.when(mealService.findById(1L)).thenReturn(item);
        Mockito.when(mealService.findAll()).thenReturn(userEvents.collect(Collectors.toList()));
        Mockito.when(mealService.create(item)).thenReturn(item);
    }


    @Test
    public void whenValidId_thenShouldBeFound() {
        Meal userEvent = mealService.findById(1L);
        assertAuthor(userEvent);
    }

    @Test
    public void whenNotValidId_thenReturnEmptyResult() {
        Meal Meal = mealService.findById(3L);
        assertThat(Meal).isNull();
    }

    @Test
    public void getAllMealsTest() {
        List<Meal> Meals = mealService.findAll();
        assertThat(Meals.size()).isEqualTo(1);
        assertThat(Meals).contains(item);
    }

    @Test
    public void createMealTest() {
        Meal createdMeal = mealService.create(item);
        assertAuthor(createdMeal);
    }

    @Test(expected = MealNotFound.class)
    public void updateMealWithExceptionTest() throws MealNotFound {

        Mockito.when(mealService.update(new Meal())).thenThrow(MealNotFound.class);
        fail("Test of meal failed");
    }

    @Test
    public void updateMealTest() {
        try {
            item.setName(UPDATE);
            Mockito.when(mealService.update(item)).thenReturn(item);

            Meal updatedTest = mealService.update(item);
            assertThat(updatedTest.getName()).isEqualTo(UPDATE);
        } catch (MealNotFound mealNotFound) {
            fail("Test of meal failed");
        }
    }

    @Test
    public void deleteMealTest() {
        try {
            mealService.delete(item.getId());
        } catch (MealNotFound mealNotFound) {
            fail("Test of meal failed");
        }
    }

    @Test(expected = MealNotFound.class)
    public void deleteMealTestWithException() throws MealNotFound{
            mealService.delete(10L);
            fail("Test of meal failed, throws exceptions was expected.");

    }

    private void assertAuthor(Meal createdMeal) {
        assertThat(createdMeal.getName()).isEqualTo(NAME);
        assertThat(createdMeal.getDescription()).isEqualTo(DESCRIPTION);
    }
}
