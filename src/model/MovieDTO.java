package model;

public class MovieDTO {
    private int movieNum;
    private int movieLevel;
    private String movieName;
    private String summary;
    
    
    public int getMovieNum() {
        return movieNum;
    }
    public void setMovieNum(int movieNum) {
        this.movieNum = movieNum;
    }
    public int getMovieLevel() {
        return movieLevel;
    }
    public void setMovieLevel(int movieLevel) {
        this.movieLevel = movieLevel;
    }
    public String getMovieName() {
        return movieName;
    }
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    
    public MovieDTO() {
        
    }
    
    public MovieDTO(int movieNum) {
        
    }
    
    public MovieDTO(String movieName, String summary, int movieNum,int movieLevel) {
        this.movieName = new String();
        this.summary = new String();
        this.movieNum = movieNum;
        this.movieLevel = movieLevel;     
    }
    
    public MovieDTO(MovieDTO m) {
        this.movieName = m.movieName;
        this.summary = m.summary;
        this.movieNum = m.movieNum;
        this.movieLevel = m.movieLevel;
    }
    
    public boolean equals(Object o) {
        if(o instanceof MovieDTO) {
            MovieDTO m = (MovieDTO)o;
            return movieNum == m.movieNum;
        }
        return false;
    }
}
