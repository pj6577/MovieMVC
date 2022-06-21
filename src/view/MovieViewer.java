package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.MovieDTO;
import model.UserDTO;
import controller.MovieController;
import util.ScannerUtil;


public class MovieViewer {
    private MovieController MovieController;
    private Scanner scanner;
    
    public MovieViewer(){
        MovieController = new MovieController();
        scanner = new Scanner(System.in); 
    }
    public void showMovie() {
        while(true) {
            System.out.println("영화 페이지 입니다");
            
            String message = "1. 영화 목록 보기 2. 영화별 전체 평점 보기";
           int userChoice = ScannerUtil.nextInt(scanner, message);
           if(userChoice == 1) {
               //영화 목록 보기 
               showMoive();
           } else if (userChoice == 2) {
               //전체 평점 보기 
               showStar();
           }
        }
    }
 
    public void showMoive() {
        ArrayList<MovieDTO> MovieList = MovieController.selectAll();
        
        if(MovieList.isEmpty()) {
            System.out.println("상영중인 영화가 없습니다");
        } else {
            for(MovieDTO m : MovieList) {
                System.out.printf("%s. %s\n", m.getMovieName(), m.getSummary());
            }
        }
    }
   private void showStar() {
        ArrayList<MovieDTO> movieStar = MovieController.selectAll();
    }
}
