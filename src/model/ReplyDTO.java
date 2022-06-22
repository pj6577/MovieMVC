package model;

public class ReplyDTO {
    private int replyId;
    private int writerId;
    private int boardId;
    private String replyContent;
    
    public int getReplyId() {
        return replyId;
    }
    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }
    public int getWriterId() {
        return writerId;
    }
    public void setWriterId(int writerId) {
        this.writerId = writerId;
    }
    public int getBoardId() {
        return boardId;
    }
    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }
    public String getReplyContent(int movieNum) {
        return replyContent;
    }
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }
    
    public ReplyDTO() {
        
    }
    public ReplyDTO(int replyId, int writerId, int boardId, String replyContent) {
        this.boardId = boardId;
        this.replyId = replyId;
        this.writerId = writerId;
        this.replyContent = new String();
    }
    public ReplyDTO(ReplyDTO r) {
        this.boardId = r.boardId;
        this.replyId = r.replyId;
        this.writerId = r.writerId;
        this.replyContent = r.replyContent;
    }
    
    public boolean equals(Object o) {
        if (o instanceof ReplyDTO) {
            ReplyDTO r= (ReplyDTO) o;
            return replyId == r.replyId;
        }
        return false;
    }
    
}
