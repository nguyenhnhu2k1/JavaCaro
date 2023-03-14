
package server.dao;

import server.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao extends Dao{
    public UserDao() {
        super();
    }
    public User verifyUser(User user) {
        try {
            PreparedStatement preparedStatement;
            preparedStatement = con.prepareStatement("SELECT *\n"
                    + "FROM ACCOUNT\n"
                    + "WHERE UserName = ?\n"
                    + "AND pass = ?");
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPass());
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt(1),
                                rs.getString(2), 
                                rs.getString(3), 
                                rs.getString(4),
                                rs.getInt(5),
                                rs.getInt(6),
                                rs.getInt(7),
                                (rs.getInt(8)!=0),
                                (rs.getInt(9)!=0));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
   public User getUserByID(int ID) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("SELECT *\n"
                    + "FROM ACCOUNT\n"
                    + "WHERE ID=?");
            preparedStatement.setInt(1, ID);
            System.out.println(preparedStatement);
            ResultSet rs =  preparedStatement.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt(1),
                                rs.getString(2), 
                                rs.getString(3), 
                                rs.getString(4),
                                rs.getInt(5),
                                rs.getInt(6),
                                rs.getInt(7),
                                (rs.getInt(8)!=0),
                                (rs.getInt(9)!=0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void addUser(User user){
        try {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT\n"
                    + "INTO ACCOUNT(UserName, pass, avatar)\n"
                    + "VALUES(?,?,?)");
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPass());
            preparedStatement.setString(3, user.getAvatar());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean checkDuplicated(String username){
        try {
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM ACCOUNT WHERE UserName = ?");
            preparedStatement.setString(1, username);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
        public int getNumberOfWin(int ID) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("SELECT ACCOUNT.numberOfWin\n"
                    + "FROM ACCOUNT\n"
                    + "WHERE ACCOUNT.ID = ?");
            preparedStatement.setInt(1, ID);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return -1;
    }
    
    public int getNumberOfDraw(int ID) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("SELECT ACCOUNT.numberOfDraw\n"
                    + "FROM ACCOUNT\n"
                    + "WHERE ACCOUNT.ID = ?");
            preparedStatement.setInt(1, ID);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return -1;
    }
    
    public int getNumberOfGame(int ID) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("SELECT ACCOUNT.numberOfGame\n"
                    + "FROM ACCOUNT\n"
                    + "WHERE ACCOUNT.ID = ?");
            preparedStatement.setInt(1, ID);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return -1;
    }
    
    public void addDrawGame(int ID){
        try {
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE ACCOUNT\n"
                    + "SET ACCOUNT.numberOfDraw = ?\n"
                    + "WHERE ACCOUNT.ID = ?");
            preparedStatement.setInt(1, new UserDao().getNumberOfDraw(ID)+1);
            preparedStatement.setInt(2, ID);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void addWinGame(int ID){
        try {
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE ACCOUNT\n"
                    + "SET ACCOUNT.numberOfWin = ?\n"
                    + "WHERE ACCOUNT.ID = ?");
            preparedStatement.setInt(1, new UserDao().getNumberOfWin(ID)+1);
            preparedStatement.setInt(2, ID);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public void addGame(int ID) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE ACCOUNT\n"
                    + "SET ACCOUNT.numberOfGame = ?\n"
                    + "WHERE ACCOUNT.ID = ?");
            preparedStatement.setInt(1, new UserDao().getNumberOfGame(ID)+1);
            preparedStatement.setInt(2, ID);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void decreaseGame(int ID){
        try {
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE ACCOUNT\n"
                    + "SET ACCOUNT.numberOfGame = ?\n"
                    + "WHERE ACCOUNT.ID = ?");
            preparedStatement.setInt(1, new UserDao().getNumberOfGame(ID) - 1);
            preparedStatement.setInt(2, ID);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public String getUserNameByID(int ID){
        try {
            PreparedStatement preparedStatement = con.prepareStatement("SELECT ACCOUNT.UserName\n"
                    + "FROM ACCOUNT\n"
                    + "WHERE ACCOUNT.ID=?");
            preparedStatement.setInt(1, ID);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void updateToOnline(int ID) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE ACCOUNT\n"
                    + "SET IsOnline = 1\n"
                    + "WHERE ID = ?");
            preparedStatement.setInt(1, ID);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void updateToOffline(int ID) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE ACCOUNT\n"
                    + "SET IsOnline = 0\n"
                    + "WHERE ID = ?");
            preparedStatement.setInt(1, ID);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void updateToPlaying(int ID){
        try {
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE ACCOUNT\n"
                    + "SET IsPlaying = 1\n"
                    + "WHERE ID = ?");
            preparedStatement.setInt(1, ID);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void updateToNotPlaying(int ID){
        try {
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE ACCOUNT\n"
                    + "SET IsPlaying = 1\n"
                    + "WHERE ID = ?");
            preparedStatement.setInt(1, ID);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
