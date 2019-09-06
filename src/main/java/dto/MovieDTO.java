/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Movie;

/**
 *
 * @author aamandajuhl
 */
public class MovieDTO {

    private Long movie_id;
    private String name;
    private int release_year;
    private String director;

    public MovieDTO(Movie movie) {

        this.movie_id = movie.getId();
        this.name = movie.getName();
        this.release_year = movie.getRelease_year();
        this.director = movie.getDirector();
    }

    public MovieDTO() {
    }

    public Long getMovie_id() {
        return movie_id;
    }

    public String getName() {
        return name;
    }

    public int getRelease_year() {
        return release_year;
    }

    public String getDirector() {
        return director;
    }

}
