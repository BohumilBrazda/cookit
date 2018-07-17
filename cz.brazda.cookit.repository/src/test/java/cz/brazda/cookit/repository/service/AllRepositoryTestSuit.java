package cz.brazda.cookit.repository.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AuthorServiceTest.class,
        IngredientServiceTest.class,
        UserEventServiceTest.class,
        MealServiceTest.class
})
public class AllRepositoryTestSuit {

}
