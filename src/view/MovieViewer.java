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
            System.out.println("��ȭ ������ �Դϴ�");
            
            String message = "1. ��ȭ ��� ���� 2. ��ȭ�� ��ü ���� ����";
           int userChoice = ScannerUtil.nextInt(scanner, message);
           if(userChoice == 1) {
               //��ȭ ��� ���� 
               showMoive();
           } else if (userChoice == 2) {
               //��ü ���� ���� 
               showStar();
           }
        }
    }
 
    public void showMoive() {
        ArrayList<MovieDTO> MovieList = MovieController.selectAll();
        
        if(MovieList.isEmpty()) {
            System.out.println("������ ��ȭ�� �����ϴ�");
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
