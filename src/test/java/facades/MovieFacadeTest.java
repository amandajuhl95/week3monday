package facades;

import utils.EMF_Creator;
import entities.Movie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MovieFacadeTest {

    private static EntityManagerFactory emf;
    private static MovieFacade facade;
    private Movie m1;
    private Movie m2;
    private Movie m3;
    private Movie m4;

    public MovieFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        facade = MovieFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        m1 = new Movie("Titanic", 1998, "James Cameron", "3 hr 15 m");
        m2 = new Movie("The Nun", 2018, "Corin Hardy", "1 hr 36 m");
        m3 = new Movie("Hercules", 1997, "Brett Ratner", "1 hr 33 m");
        m4 = new Movie("Mary Poppins", 1965, "Robert Stevenson", "2 hr 20 m");
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            em.persist(m1);
            em.persist(m2);
            em.persist(m3);
            em.persist(m4);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    /**
     * Test of getMovieByName method, of class MovieFacade.
     */
    @Test
    public void testGetMovieByName() {
        List<Movie> movies = facade.getMoviesByName("The nun");
        assertFalse(movies.isEmpty());
        assertEquals(1, movies.size());
        assertEquals(2018, movies.get(0).getRelease_year());
        assertEquals("Corin Hardy", movies.get(0).getDirector());
        assertEquals("1 hr 36 m", movies.get(0).getDuration());
    }

    /**
     * Test of addMovie method, of class MovieFacade.
     */
    @Test
    public void testAddMovie() {

        int moviesbefore = facade.getAllMovies().size();
        Movie movie = new Movie("Saving Private Ryan", 1998, "Steven Spielberg", "2 hr 50 m");
        facade.addMovie(movie);
        int moviesafter = facade.getAllMovies().size();

        assertTrue(moviesbefore < moviesafter);

    }

    /**
     * Test of getMovies method, of class MovieFacade.
     */
    @Test
    public void testGetMovies() {
        List<Movie> movies = facade.getAllMovies();
        assertFalse(movies.isEmpty());
        assertEquals(4, movies.size());
    }

    /**
     * Test of MovieCount method, of class MovieFacade.
     */
    @Test
    public void testGetMovieCount() {

        long count = facade.getMovieCount();
        assertEquals(4, count);
    }

    /**
     * Test of getMovieById method, of class MovieFacade.
     */
    @Test
    public void testGetMovie() {
        Movie movie = facade.getMovie(m1.getId());

        assertEquals("Titanic", movie.getName());
        assertEquals(1998, movie.getRelease_year());
        assertEquals("James Cameron", movie.getDirector());
        assertEquals("3 hr 15 m", movie.getDuration());
    }

}
