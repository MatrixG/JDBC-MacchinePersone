package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ GestioneMacchineTest.class, MacchinaDAOTest.class, MacchinaPersonaDAOTest.class, PersonaDAOTest.class })
public class AllTests {

}
