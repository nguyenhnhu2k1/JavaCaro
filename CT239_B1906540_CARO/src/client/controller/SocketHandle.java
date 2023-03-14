
package client.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import server.controller.Server;
import server.model.User;

public class SocketHandle implements Runnable{

    private BufferedWriter os;
    private BufferedReader is;
    private Socket socketOfClient;
    private int ID_Server;
    

    public User getUserFromString(int start, String[] message){
        return new User(Integer.parseInt(message[start]),
                message[start+1],
                message[start+2],
                message[start+3],
                Integer.parseInt(message[start+4]),
                Integer.parseInt(message[start+5]),
                Integer.parseInt(message[start+6]));
    }
    
    @Override
    public void run() {
        try {
            socketOfClient = new Socket("127.0.0.1", 7777);
            System.out.println("Kết nối thành công!");
            os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
            is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
            String message;
            
            while (true) {
                message = is.readLine();
                if (message == null) {
                    break;
                }
                String[] messageSplit = message.split(",");
                if(messageSplit[0].equals("server-send-id")){
                    ID_Server = Integer.parseInt(messageSplit[1]);
                }
                //Đăng nhập thành công
                if(messageSplit[0].equals("login-success")){
                    System.out.println("Đăng nhập thành công");
                    Client.closeAllViews();
                    User user= getUserFromString(1, messageSplit);
                    Client.user = user;
                    Client.openView(Client.View.USERINTERFACE);
                }
                
                //Thông tin tài khoản sai
                if(messageSplit[0].equals("wrong-user")){
                    System.out.println("Thông tin sai");
                    Client.closeView(Client.View.GAMENOTICE);
                    Client.openView(Client.View.LOGIN,messageSplit[1],messageSplit[2]);
                    Client.loginFrame.showError("Tài khoản hoặc mật khẩu không chính xác");
                }
                //Tài khoản đã đăng nhập ở nơi khác
                if(messageSplit[0].equals("dupplicate-login")){
                    System.out.println("Đã đăng nhập");
                    Client.closeView(Client.View.GAMENOTICE);
                    Client.openView(Client.View.LOGIN,messageSplit[1],messageSplit[2]);
                    Client.loginFrame.showError("Tài khoản đã đăng nhập ở nơi khác");
                }
                
                //Xử lý register trùng tên
                if(messageSplit[0].equals("duplicate-username")){
                    Client.closeAllViews();
                    Client.openView(Client.View.SIGNUP);
                    JOptionPane.showMessageDialog(Client.signUpJFrame, "Tên tài khoản đã được người khác sử dụng");
                }
                
                //Xử lý kết quả tìm phòng từ server
                if(messageSplit[0].equals("room-fully")){
                    Client.closeAllViews();
                    Client.openView(Client.View.USERINTERFACE);
                    JOptionPane.showMessageDialog(Client.userInterfaceFrm, "Phòng chơi đã đủ 2 người chơi");
                }
                // Xử lý không tìm thấy phòng trong chức năng vào phòng
                if(messageSplit[0].equals("room-not-found")){
                    Client.closeAllViews();
                    Client.openView(Client.View.USERINTERFACE);
                    JOptionPane.showMessageDialog(Client.userInterfaceFrm, "Không tìm thấy phòng");
                }
                // Xử lý phòng có mật khẩu sai
                if(messageSplit[0].equals("room-wrong-password")){
                    Client.closeAllViews();
                    Client.openView(Client.View.USERINTERFACE);
                    JOptionPane.showMessageDialog(Client.userInterfaceFrm, "Mật khẩu phòng sai");
                }
                //Xử lý lấy danh sách phòng
                if(messageSplit[0].equals("room-list")){
                    Vector<String> rooms = new Vector<>();
                    Vector<String> passwords = new Vector<>();
                    for(int i=1; i<messageSplit.length; i=i+2){
                        rooms.add("Phòng "+messageSplit[i]);
                        passwords.add(messageSplit[i+1]);
                    }
                    Client.roomListFrm.updateRoomList(rooms,passwords);
                }
                
                if(messageSplit[0].equals("go-to-room")){
                    System.out.println("Vào phòng");
                    int roomID = Integer.parseInt(messageSplit[1]);
                    String competitorIP = messageSplit[2];
                    int isStart = Integer.parseInt(messageSplit[3]);
                    
                    User competitor = getUserFromString(4, messageSplit);
                    if(Client.findRoomFast!=null){
                        Client.findRoomFast.showFindedRoom();
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException ex) {
                            JOptionPane.showMessageDialog(Client.findRoomFast, "Lỗi khi sleep thread");
                        }
                    } else if(Client.waitRoomFrm!=null){
                        Client.waitRoomFrm.showFindedCompetitor();
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException ex) {
                            JOptionPane.showMessageDialog(Client.waitRoomFrm, "Lỗi khi sleep thread");
                        }
                    }
                    Client.closeAllViews();
                    System.out.println("Đã vào phòng: "+roomID);
                    //Xử lý vào phòng
                    Client.openView(Client.View.GAMECLIENT
                            , competitor
                            , roomID
                            ,isStart
                            ,competitorIP);
                    Client.gameClientFrm.newgame();
                }
                //Tạo phòng và server trả về tên phòng
                if(messageSplit[0].equals("your-created-room")){
                    Client.closeAllViews();
                    Client.openView(Client.View.WAITROOM);
                    Client.waitRoomFrm.setRoomName(messageSplit[1]);
                    if(messageSplit.length==3)
                        Client.waitRoomFrm.setRoomPassword("Mật khẩu phòng: "+messageSplit[2]);
                }
                //Xử lý đánh một nước trong ván chơi
                if(messageSplit[0].equals("caro")){
                    Client.gameClientFrm.addCompetitorMove(messageSplit[1], messageSplit[2]);
                }
                if(messageSplit[0].equals("chat")){
                    Client.gameClientFrm.addMessage(messageSplit[1]);
                }
                if(messageSplit[0].equals("draw-request")){
                    Client.gameClientFrm.showDrawRequest();
                }
                
                if(messageSplit[0].equals("draw-refuse")){
                    if(Client.gameNoticeFrm!=null) Client.closeView(Client.View.GAMENOTICE);
                    Client.gameClientFrm.displayDrawRefuse();
                }
                
                if(messageSplit[0].equals("new-game")){
                    System.out.println("New game");
                    Thread.sleep(4000);
                    Client.gameClientFrm.updateNumberOfGame();
                    Client.closeView(Client.View.GAMENOTICE);
                    Client.gameClientFrm.newgame();
                }
                if(messageSplit[0].equals("draw-game")){
                    System.out.println("Draw game");
                    Client.closeView(Client.View.GAMENOTICE);
                    Client.openView(Client.View.GAMENOTICE, "Ván chơi hòa", "Ván chơi mới dang được thiết lập");
                    Client.gameClientFrm.displayDrawGame();
                    Client.gameClientFrm.increaseDrawMatchToUser();
                    Thread.sleep(4000);
                    Client.gameClientFrm.updateNumberOfGame();
                    Client.closeView(Client.View.GAMENOTICE);
                    Client.gameClientFrm.newgame();
                }
                if(messageSplit[0].equals("competitor-time-out")){
                    Client.gameClientFrm.increaseWinMatchToUser();
                    Client.openView(Client.View.GAMENOTICE,"Bạn đã thắng do đối thủ quá thới gian","Đang thiết laapju ván chơi mới");
                    Thread.sleep(4000);
                    Client.closeView(Client.View.GAMENOTICE);
                    Client.gameClientFrm.updateNumberOfGame();
                    Client.gameClientFrm.newgame();
                }
                if(messageSplit[0].equals("left-room")){
                    Client.gameClientFrm.stopTimer();
                    Client.closeAllViews();
                    Client.openView(Client.View.GAMENOTICE,"Đối thủ đã thoát khỏi phòng","Đang trở về trang chủ");
                    Thread.sleep(3000);       
                    Client.closeAllViews();
                    Client.openView(Client.View.USERINTERFACE);
                }
            }
            
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void write(String message) throws IOException{
        os.write(message);
        os.newLine();
        os.flush();
    }
    
    public Socket getSocketOfClient() {
        return socketOfClient;
    }
    
}
