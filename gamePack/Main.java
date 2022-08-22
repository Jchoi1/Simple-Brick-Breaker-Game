package gamePack;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame obj = new JFrame();
        gamePlay  GamePlay = new gamePlay();
        obj.setBounds(10, 10, 700, 700);
        obj.setTitle("Brick Breaker");
        obj.setResizable(true);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(GamePlay);

    }
}
