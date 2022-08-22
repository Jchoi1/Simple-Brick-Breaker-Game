package gamePack;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Rectangle;



public class gamePlay extends JPanel implements KeyListener, ActionListener{

    private boolean play = false;
    private int score = 0;

    private Timer timer; 
    private int delay = 8;

    private int playerX = 310;
    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;

    private maprGen map;
    private int mapColumn = 5;
    private int mapRow = 9;
    private int totalBricks = mapRow * mapColumn;

    public gamePlay() {
        map = new maprGen(mapRow, mapColumn);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();

    }
    public void paint(Graphics g)   {
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);

        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);

        g.setColor(Color.blue);
        g.fillRect(playerX, 550, 100, 8);

        g.setColor(Color.green);
        g.fillOval(ballposX, ballposY, 20, 20);

        map.draw((Graphics2D)g);

        g.setColor(Color.red);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("" + score, 590, 30);

        if(ballposY > 570){
            play = false;
            ballXdir = 0;
            ballYdir = 0;

            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over, Score: " + score, 190, 300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart.", 230, 350);
        }
        if(totalBricks <= 0){
            play = false;
            ballXdir = 0;
            ballYdir = 0;

            g.setColor(Color.GREEN);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You Won! Score: " + score, 190, 300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart!", 230, 350);
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(play) {

            //Ball and Pedal Interaction
            if(new Rectangle(ballposX, ballposY, 20, 30).intersects(new Rectangle(playerX, 550, 100, 8))){
                ballYdir = -ballYdir;
            }
            ballposX += ballXdir;
            ballposY += ballYdir;
            if(ballposX < 0)    {
                ballXdir = -ballXdir;
            }
            if(ballposY < 0)    {
                ballYdir = -ballYdir;
            }
            if(ballposX > 670)  {
                ballXdir = -ballXdir;
            }

            for(int i = 0; i < map.map.length; i++){
                for(int j = 0; j < map.map[0].length; j++){
                    if(map.map[i][j] > 0){
                        int brickX = j*map.brickWidth + 80;
                        int brickY = i*map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballrect = new Rectangle(ballposX, ballposY, 20, 20);
                        Rectangle brickRect = rect;

                        if(ballrect.intersects(brickRect)){
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            score += 5;
                            if(ballposX +19 <= brickRect.x || ballposX +1 <= brickRect.x + brickRect.width){
                                ballXdir = -ballXdir;
                                ballYdir = -ballYdir;
                            }
                            else{
                                ballYdir = -ballYdir;
                            }
                        }
                    }
                }
            }
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)  {
            if(playerX >= 600 ) {
                playerX = 600;
            }
            else {
                moveRight();
            
            }
            
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT)  {
            if(playerX <= 10) {
                playerX = 10;
            }
            else {
                moveLeft();
            
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER)  {
            if(!play) {
                play = true;
                ballposX = 120;
                ballposY = 350;
                ballXdir = -1;
                ballYdir = -2;
                playerX = 310;
                score = 0;
                totalBricks = 21;
                map = new maprGen(3, 7);

                repaint();
            }
        }
            
    }
        public void moveRight() {
            play = true;
            playerX +=20;
            
        }
        public void moveLeft() {
            play = true;
            playerX -=20;
            
        }
    

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
