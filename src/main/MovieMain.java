package main;

import view.UserViewer;
import view.MovieViewer;
import view.ReplyViewer;

public class MovieMain {
    public static void main(String[] args) {
        UserViewer u = new UserViewer();
        MovieViewer m = new MovieViewer();
        ReplyViewer r = new ReplyViewer();

        u.setReplyViewer(r);
        u.setMovieViewer(m);

        m.setReplyViewer(r);
        m.setUserViewer(u);
        
        r.setMovieViewer(m);
        r.setUserViewer(u);
        u.showMenu();
    }
}
