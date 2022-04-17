package BiranArtem_group6_lab6_var2a;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Field  extends JPanel {

    private static final int limit = 8;

    private ArrayList<BouncingBall> balls = new ArrayList<BouncingBall>(10);

    private Timer repaintTimer = new Timer(10, new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
            Field.this.repaint();
        }
    });

    public Field(){
        setBackground(Color.BLACK);
        repaintTimer.start();
    }

    public void addBall(){
        balls.add(new BouncingBall(this));
    }

    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D canvas = (Graphics2D) gr;
        for (BouncingBall ball: balls) {
            ball.paint(canvas);
        }
    }

    public synchronized void pause() {
        for (BouncingBall ball: balls){
            ball.setPaused();
        }
    }
    public synchronized void resume() {
        for (BouncingBall ball: balls){
            ball.resumePaused();
            notify();
        }
    }
    public synchronized void pauseSlow() {
        for (BouncingBall ball: balls) {
            if(ball.getSleep() >= limit) {
                ball.setPaused();
            }
        }
    }
    public synchronized void canMove(BouncingBall ball)
            throws InterruptedException{
        if (ball.isPaused()) {
            wait();
        }
    }

}