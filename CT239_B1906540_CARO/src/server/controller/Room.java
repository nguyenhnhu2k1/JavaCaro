
package server.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.dao.UserDao;


public class Room {
    private int ID;
    private ServerThread user1;
    private ServerThread user2;
    private String password;
    private UserDao userDao;

    public int getID() {
        return ID;
    }

    public ServerThread getUser1() {
        return user1;
    }

    public ServerThread getUser2() {
        return user2;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Room(ServerThread user1){
        System.out.println("Tạo phòng thành công, ID là: "+Server.ID_room);
        this.password = "";
        this.ID = Server.ID_room++;
        userDao = new UserDao();
        this.user1 = user1;
        this.user2 = null;
    }
    
    public int getNumberOfUser(){
        return user2==null?1:2;
    }
    
    public void setUser2(ServerThread user2){
        this.user2 = user2;
    }
    
    public void boardCast(String message){
        try {
            user1.write(message);
            user2.write(message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public int getCompetitorID(int ID_ClientNumber){
        if(user1.getClientNumber() == ID_ClientNumber)
            return user2.getUser().getID();
        return user1.getUser().getID();
    }
    
    public ServerThread getCompetitor(int ID_ClientNumber){
        if(user1.getClientNumber()==ID_ClientNumber)
            return user2;
        return user1;
    }
    
    public void setUsersToPlaying(){
        userDao.updateToPlaying(user1.getUser().getID());
        if(user2!=null){
            userDao.updateToPlaying(user2.getUser().getID());
        }
    }
    public void setUsersToNotPlaying(){
        userDao.updateToNotPlaying(user1.getUser().getID());
        if(user2!=null){
            userDao.updateToNotPlaying(user2.getUser().getID());
        }
    }

    
    public void increaseNumberOfGame(){
        userDao.addGame(user1.getUser().getID());
        userDao.addGame(user2.getUser().getID());
    }
    
    public void increaseNumberOfDraw(){
        userDao.addDrawGame(user1.getUser().getID());
        userDao.addDrawGame(user2.getUser().getID());
    }
    
    public void decreaseNumberOfGame(){
        userDao.decreaseGame(user1.getUser().getID());
        userDao.decreaseGame(user2.getUser().getID());
    }
    
}
