
package server.model;

public class User {
    private int ID;
    private String UserName;
    private String pass;
    private String avatar;
    private int numberOfGame;
    private int numberOfwin;
    private int numberOfDraw;
    private boolean isOnline;
    private boolean isPlaying;
    
    public User(int ID, String username, String password, String avatar, int numberOfGame, int numberOfwin, int numberOfDraw) {
        this.ID = ID;
        this.UserName = username;
        this.pass = password;
        this.avatar = avatar;
        this.numberOfGame = numberOfGame;
        this.numberOfwin = numberOfwin;
        this.numberOfDraw = numberOfDraw;
    }
    
    public User(int ID, String username, String password, String avatar, int numberOfGame, int numberOfwin, int numberOfDraw, boolean isOnline, boolean isPlaying) {
        this.ID = ID;
        this.UserName = username;
        this.pass = password;
        this.avatar = avatar;
        this.numberOfGame = numberOfGame;
        this.numberOfwin = numberOfwin;
        this.numberOfDraw = numberOfDraw;
        this.isOnline = isOnline;
        this.isPlaying = isPlaying;
    }
    
    public User(int ID, String username) {
        this.ID = ID;
        this.UserName = username;
    }
    
    public User(int ID, boolean isOnline, boolean isPlaying) {
        this.ID = ID;
        this.isOnline = isOnline;
        this.isPlaying = isPlaying;
    }
    
    public User(String username, String password) {
        this.UserName = username;
        this.pass= password;
    }
    
    public User(int ID, String username, String password, String avatar) {
        this.ID = ID;
        this.UserName = username;
        this.pass = password;
        this.avatar = avatar;
    }
    
    public User(String username, String password, String avatar) {
        this.UserName = username;
        this.pass = password;
        this.avatar = avatar;
    }
    
    public int getID() {
        return ID;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPass() {
        return pass;
    }

    public String getAvatar() {
        return avatar;
    }

    public int getNumberOfGame() {
        return numberOfGame;
    }

    public int getNumberOfwin() {
        return numberOfwin;
    }

    public boolean getIsOnline() {
        return isOnline;
    }

    public boolean getIsPlaying() {
        return isPlaying;
    }


    public void setUsername(String username) {
        this.UserName = username;
    }

    public void setPassword(String password) {
        this.pass = password;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setNumberOfGame(int numberOfGame) {
        this.numberOfGame = numberOfGame;
    }

    public void setNumberOfwin(int numberOfwin) {
        this.numberOfwin = numberOfwin;
    }

    public void setIsOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public void setIsPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }
    
    public User(int ID, int numberOfGame, int numberOfDraw) {
        this.ID = ID;
        this.numberOfGame = numberOfGame;
        this.numberOfDraw = numberOfDraw;
    }
    
    public int getNumberOfDraw() {
        return numberOfDraw;
    }

    public void setNumberOfDraw(int numberOfDraw) {
        this.numberOfDraw = numberOfDraw;
    }
    
}
