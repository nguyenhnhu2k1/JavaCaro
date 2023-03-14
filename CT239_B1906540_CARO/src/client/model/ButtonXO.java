
package client.model;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import org.ietf.jgss.Oid;

public class ButtonXO extends JButton{
    private ImageIcon X;
    private ImageIcon O;
    public Point point;
    public static boolean isXMove = true;
    public int value = 0;
    
    public ButtonXO(int x, int y) {
        X = new ImageIcon("assets/image/x3.jpg");
        O = new ImageIcon("assets/image/o3.jpg");
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        this.setIcon(new ImageIcon("assets/image/blank.jpg"));
        this.point = new Point(x, y);
        ButtonXO _this = this;
        this.addMouseListener(new MouseListener() {
            
            @Override
            public void mouseClicked(MouseEvent arg0) {
                
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(_this.isEnabled()) {
                    _this.setBackground(Color.GREEN);
                    _this.setIcon(new ImageIcon("assets/image/x3.jpg"));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(_this.isEnabled()) {
                    _this.setBackground(null);
                    _this.setIcon(new ImageIcon("assets/image/blank.jpg"));
                }
            }
        });
    }
    
    public void setState(Boolean isXMove) {
        if(isXMove) {
            setIcon(X);
            value = 2;
            ButtonXO.isXMove = false;
            this.setDisabledIcon(X);
        } else {
            setIcon(O);
            value = 1;
            this.setDisabledIcon(O);
            ButtonXO.isXMove = true;
        }
    }
    
    public void resetState() {
        value = 0;
        this.setEnabled(true);
        this.setIcon(new ImageIcon("assets/image/blank.jpg"));
        this.setDisabledIcon(new ImageIcon("assets/image/blank.jpg"));
    }
    
}
