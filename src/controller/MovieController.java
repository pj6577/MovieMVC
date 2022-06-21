package controller;
import java.util.ArrayList;
import java.util.Scanner;

import model.MovieDTO;

public class MovieController {
    ArrayList<MovieDTO> MovieList;
    Scanner scanner = new Scanner(System.in);
    int nextId;
    
    public MovieController(){
        MovieList = new ArrayList<>();
        nextId =1;
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
    
    

    public MovieDTO selectOne(int id) {
        for (MovieDTO m : MovieList) {
            if (m.getMovieNum() == id) {
                return new MovieDTO(m);
            }
        }
        return null;
    }
}
