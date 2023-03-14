package client.controller;

import javax.swing.JFrame;
import client.view.ChoiceRoomFrm;
import client.view.CreatRoomFrm;
import client.view.FindRoomFast;
import client.view.GameClientFrm;
import client.view.JoinRoomPassFrm;
import client.view.LoginFrame;
import client.view.MenuJFrame;
import client.view.RoomListFrm;
import client.view.SignUpJFrame;
import client.view.UserInterfaceFrm;
import client.view.WaitRoomFrm;
import client.view.GameNoticeFrm;
import client.view.GameAIFrame;
import server.model.User;

public class Client {
    public enum View{
        CHOICEROOM,
        CREATEROOM,
        FINDROOMFAST,
        GAMECLIENT,
        JOINROOMPASS,
        LOGIN,
        MENU,
        ROOMLIST,
        SIGNUP,
        USERINTERFACE,
        WAITROOM,
        GAMENOTICE,
        GAMEAI
    }

    public static User user;
    //Danh sach giao dien
    public static ChoiceRoomFrm choiceRoomFrm;
    public static CreatRoomFrm creatRoomFrm;
    public static FindRoomFast findRoomFast;
    public static GameClientFrm gameClientFrm;
    public static JoinRoomPassFrm joinRoomPassFrm;
    public static LoginFrame loginFrame;
    public static MenuJFrame menuJFrame;
    public static RoomListFrm roomListFrm;
    public static SignUpJFrame signUpJFrame;
    public static UserInterfaceFrm userInterfaceFrm;
    public static WaitRoomFrm waitRoomFrm;
    public static GameNoticeFrm gameNoticeFrm;
    public static GameAIFrame gameAIFrame;
    
    public static SocketHandle socketHandle;
    
    public Client(){
        
    }
    
    public static JFrame getVisibleJFrame(){
        if(roomListFrm!=null&&roomListFrm.isVisible())
            return roomListFrm;
        
        if(creatRoomFrm!=null&&creatRoomFrm.isVisible()){
            return creatRoomFrm;
        }
        if(choiceRoomFrm!=null&&choiceRoomFrm.isVisible()){
            return choiceRoomFrm;
        }
        
        return userInterfaceFrm;
    }
    
    public void initView(){
        
        loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
        socketHandle = new SocketHandle();
        socketHandle.run();
    }
    
    public static void openView(View viewName){
        if(viewName != null){
            switch(viewName){
                case LOGIN:
                    loginFrame = new LoginFrame();
                    loginFrame.setVisible(true);
                    break;
                case SIGNUP:
                    signUpJFrame = new SignUpJFrame();
                    signUpJFrame.setVisible(true);
                    break;
                case USERINTERFACE:
                    userInterfaceFrm = new UserInterfaceFrm();
                    userInterfaceFrm.setVisible(true);
                    break;
                case ROOMLIST:
                    roomListFrm = new RoomListFrm();
                    roomListFrm.setVisible(true);
                    break;
                case FINDROOMFAST:
                    findRoomFast = new FindRoomFast();
                    findRoomFast.setVisible(true);
                    break;
                case WAITROOM:
                    waitRoomFrm= new WaitRoomFrm();
                    waitRoomFrm.setVisible(true);
                    break;
                case CREATEROOM:
                    creatRoomFrm = new CreatRoomFrm();
                    creatRoomFrm.setVisible(true);
                    break;
                case JOINROOMPASS:
                    joinRoomPassFrm = new JoinRoomPassFrm();
                    joinRoomPassFrm.setVisible(true);
                    break;
                case MENU:
                    menuJFrame = new MenuJFrame();
                    menuJFrame.setVisible(true);
                    break;
                case GAMEAI:
                    gameAIFrame = new GameAIFrame();
                    gameAIFrame.setVisible(true);
                    break;
            }
        }
    }
    
    public static void openView(View viewName, int arg1, String arg2){
        if(viewName != null){
            switch(viewName){
                case CHOICEROOM:
                    choiceRoomFrm = new ChoiceRoomFrm(arg1, arg2);
                    choiceRoomFrm.setVisible(true);
                    break;
            }
        }
    }
    
    public static void openView(View viewName, User competitor, int room_ID, int isStart, String competitorIP){
        if(viewName != null){
            switch(viewName){
                case GAMECLIENT:
                    gameClientFrm = new GameClientFrm(competitor, room_ID, isStart, competitorIP);
                    gameClientFrm.setVisible(true);
                    break;
            }
        }
    }
    
    public static void closeView(View viewName){
        if(viewName != null){
            switch(viewName){
                case MENU:
                    menuJFrame.dispose();
                    break;
                case LOGIN:
                    loginFrame.dispose();
                    break;
                case SIGNUP:
                    signUpJFrame.dispose();
                    break;
                case USERINTERFACE:
                    userInterfaceFrm.dispose();
                    break;
                case ROOMLIST:
                    roomListFrm.dispose();
                    break;
                case FINDROOMFAST:
                    findRoomFast.stopAllThread();
                    findRoomFast.dispose();
                    break;
                case WAITROOM:
                    waitRoomFrm.dispose();
                    break;
                case GAMECLIENT:
                    gameClientFrm.stopAllThread();
                    gameClientFrm.dispose();
                    break;
                case CREATEROOM:
                    creatRoomFrm.dispose();
                    break;
                case JOINROOMPASS:
                    joinRoomPassFrm.dispose();
                    break;
                case CHOICEROOM:
                    choiceRoomFrm.dispose();
                    break;
                case GAMENOTICE:
                    gameNoticeFrm.dispose();
                    break;
                case GAMEAI:
                    gameAIFrame.dispose();
                    break;
            }
            
        }
    }
    
    public static void openView(View viewName, String arg1, String arg2){
        if(viewName != null){
            switch(viewName){
                case GAMENOTICE:
                    gameNoticeFrm = new GameNoticeFrm(arg1, arg2);
                    gameNoticeFrm.setVisible(true);
                    break;
                case LOGIN:
                    loginFrame = new LoginFrame(arg1, arg2);
                    loginFrame.setVisible(true);
            }
        }
    }
    
    public static void closeAllViews(){
        if(loginFrame!=null) loginFrame.dispose();
        if(signUpJFrame!=null) signUpJFrame.dispose();
        if(userInterfaceFrm!=null) userInterfaceFrm.dispose();
        if(roomListFrm!=null) roomListFrm.dispose();
        
        if(findRoomFast!=null){
            findRoomFast.stopAllThread();
            findRoomFast.dispose();
        } 
        if(waitRoomFrm!=null) waitRoomFrm.dispose();
        if(gameClientFrm!=null){
            gameClientFrm.stopAllThread();
            gameClientFrm.dispose();
        } 
        if(creatRoomFrm!=null) creatRoomFrm.dispose();
        if(joinRoomPassFrm!=null) joinRoomPassFrm.dispose();
        if(gameNoticeFrm!=null) gameNoticeFrm.dispose();
        if(joinRoomPassFrm!=null) joinRoomPassFrm.dispose();
        if(gameAIFrame!=null) gameAIFrame.dispose();
        if(choiceRoomFrm!=null) choiceRoomFrm.dispose();
    }
    public static void main(String[] args) {
        new Client().initView();
    }
    
}