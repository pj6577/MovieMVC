package controller;
import java.util.ArrayList;
import java.util.Scanner;

import model.TheaterDTO;
public class TheaterController {
    
    ArrayList<TheaterDTO> TheaterList;
    Scanner scanner = new Scanner(System.in);
    int nextId;
    
    public TheaterController () {
        TheaterList = new ArrayList<>();
        nextId = 1; 
    }
    public TheaterController(TheaterDTO t) {
        t.setTheaterNum(nextId++);
        TheaterList.add(t);
    }
    
    public ArrayList<TheaterDTO> selectAll(){
        ArrayList<TheaterDTO> temp = new ArrayList<>();
        
        for(TheaterDTO t : TheaterList ) {
            temp.add(t);
        }
        return temp;
    }
    
    public TheaterDTO selectOne(int theaterNum) {
        for (TheaterDTO t : TheaterList) {
            if (t.getTheaterNum() == theaterNum) {
                return new TheaterDTO(t);
            }
        }
        return null;
    }
    
}
