package controller;
import model.ReplyDTO;

import java.util.ArrayList;
import java.util.Scanner;
public class ReplyController {
    ArrayList<ReplyDTO> replyList;
    Scanner scanner = new Scanner(System.in);
    int nextId;
    
    public ReplyController() {
        replyList = new ArrayList<>();
        nextId =1;  
    }
    
    public void ReplyInsert(ReplyDTO r) {
        r.setReplyId(nextId++);
        replyList.add(r);
    }
    
    public ArrayList<ReplyDTO> selectAll(){
        ArrayList<ReplyDTO> temp = new ArrayList<>();

        for (ReplyDTO r : replyList) {
            temp.add(new ReplyDTO(r));
        }
        return temp;
    }
    
    public ReplyDTO selectOne(int replyId) {
        for (ReplyDTO r : replyList) {
            if (r.getReplyId() == replyId) {
                return new ReplyDTO(r);
            }
        }
        return null;
    }
    
    
    public void upDate(ReplyDTO r) {
        int index = replyList.indexOf(r);
        replyList.set(index, r);
    }
    public void remove(int replyId) {
        replyList.remove(replyId);
    }
    public ArrayList<ReplyDTO> selectReplyAll(){
        ArrayList<ReplyDTO> temp = new ArrayList<>();
        for(ReplyDTO r : replyList) {
            temp.add(new ReplyDTO(r));
        }
        return temp;
    }
}
