package facades;

import entities.Movie;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MovieFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Movie addMovie(Movie movie) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(movie);
            em.getTransaction().commit();
            return movie;
        } finally {
            em.close();
        }
    }

    public Movie getMovie(long id) {

        return getEntityManager().find(Movie.class, id);
    }

    public List<Movie> getAllMovies() {

        TypedQuery<Movie> query = getEntityManager().createQuery("SELECT m FROM Movie m", Movie.class);
        return query.getResultList();
    }

    public Long getMovieCount() {

        Query query = getEntityManager().createQuery("SELECT COUNT(m) FROM Movie m");
        Long count = (Long) query.getSingleResult();
        return count;

    }
    
    public List<Movie> getMoviesByName(String name) {

        TypedQuery<Movie> query = getEntityManager().createQuery("SELECT m FROM Movie m WHERE m.name = :name", Movie.class);
        query.setParameter("name", name);

        return query.getResultList();
    }

}
