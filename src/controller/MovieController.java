package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.MovieDTO;

public class MovieController {
    ArrayList<MovieDTO> MovieList;
    Scanner scanner = new Scanner(System.in);
    int nextId;

    public MovieController() {
        MovieList = new ArrayList<>();
        nextId = 1;
    }

    public void MovieInsert(MovieDTO m) {
        m.setMovieNum(nextId++);
        MovieList.add(m);
    }

    public ArrayList<MovieDTO> selectAll() {
        ArrayList<MovieDTO> temp = new ArrayList<>();

        for (MovieDTO m : MovieList) {
            temp.add(new MovieDTO(m));
        }
        return temp;
    }

    public MovieDTO selectOne(int movieNum) {
        for (MovieDTO m : MovieList) {
            if (m.getMovieNum() == movieNum) {
                return new MovieDTO(m);
            }
        }
        return null;
    }

    public void update(MovieDTO m) {
        int index = MovieList.indexOf(m);
        MovieList.set(index, m);
    }

    public void removeMovie(int movieNum) {
        MovieList.remove(movieNum);
    }
}
