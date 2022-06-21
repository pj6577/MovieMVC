package main;

import view.UserViewer;
import view.MovieViewer;
public class MovieMain {
    public static void main(String[] args) {
        UserViewer u= new UserViewer();
        MovieViewer m = new MovieViewer();
        
       u.setMovieViewer(m);
        u.showMenu();
    }
}
