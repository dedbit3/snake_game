/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;


/**
 *
 * @author root
 */
public class Graphics extends JPanel implements ActionListener{
    
    static final int WIDTH = 800;
    static final int LENGTH = 800;
    static final int TICK_SIZE = 50;
    static final int BOARD_SIZE = (WIDTH * HEIGHT) / TICK_SIZE * TICK_SIZE;
    
    final Font font = new Font("TimesRonan", Font.BOLD, 30);
    
    int[] snakePosX = new int[BOARD_SIZE];
    int[] snakePosY = new int[BOARD_SIZE];
    int snakeLength;
    
    Food food;
    int foodConsumed;
    
    char direction = 'R';
    boolean isMoving = false;
    
    final Timer timer = new Timer(150, this);
    
    public Graphics(){
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        
        this.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent ae) {
    
                if(isMoving){
                    switch(ae.getKeyCode()){
                        case java.awt.event.KeyEvent.VK_LEFT:
                            if(direction != 'R'){
                                direction = 'L';
                            }
                            break; 
                        case java.awt.event.KeyEvent.VK_RIGHT:
                              if(direction != 'L'){
                                direction = 'R';
                            }
                            break; 
                        case java.awt.event.KeyEvent.VK_UP:
                              if(direction != 'D'){
                                direction = 'U';
                            }
                            break; 
                        case java.awt.event.KeyEvent.VK_DOWN:
                              if(direction != 'U'){
                                direction = 'D';
                            }
                            break; 
                    }
                }else{
                    start();
                }     
            }
        });
    
        start();
    }
    
    protected void start(){
        snakePosX = new int[BOARD_SIZE];
        snakePosY = new int[BOARD_SIZE];
        snakeLength = 5;
        foodConsumed = 0;
        direction = 'R';
        isMoving = true;
        spawnFood();
        timer.start(); 
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g); 
        
        if(isMoving){
            g.setColor(Color.RED);
            g.fillRect(food.getXcoordinate(), food.getYcoordinate(), TICK_SIZE, TICK_SIZE);
            
            
            g.setColor(Color.PINK);
            
            for(int i = 0; i < snakeLength; i++){
                g.fillRect(snakePosX[i], snakePosY[i], TICK_SIZE, TICK_SIZE);
                
            }    
        }else{
            String scoreText = String.format("You LOST :(   SCORE: %d ---- press a key to restart", foodConsumed);
            g.setColor(Color.CYAN);
            g.setFont(font);
            
            //drawing the score screen not centered right
            //g.drawString(scoreText, (WIDTH - getFontMetrics(g.getFont()).stringWidth(scoreText)) / 2, HEIGHT / 2);
            
            //this is my actual score screen
            g.drawString(scoreText, ((230+WIDTH) - getFontMetrics(g.getFont()).stringWidth(scoreText)) , HEIGHT+350 );
        }
    }

    
    protected void move(){
        for(int i = snakeLength; i>0; i--){
            snakePosX[i] =snakePosX[i-1];
            snakePosY[i] =snakePosY[i-1];
        }
        
        switch(direction){
            case 'U' -> snakePosY[0] -= TICK_SIZE;
            case 'D' -> snakePosY[0] += TICK_SIZE;
            case 'L' -> snakePosX[0] -= TICK_SIZE;
            case 'R' -> snakePosX[0] += TICK_SIZE;
        }
    }
    
    protected void spawnFood(){
        food = new Food();
    }
    
    protected void consumeFood(){
        if((snakePosX[0] == food.getXcoordinate()) && (snakePosY[0] == food.getYcoordinate())){
            snakeLength++;
            foodConsumed++;
            spawnFood();
            ///System.out.println("the food was eaten");
        }
    }
    
    protected void collisionTest(){
         for(int i = snakeLength; i>0; i--){
             if((snakePosX[0] == snakePosX[i]) && (snakePosY[0] == snakePosY[i])){
                 isMoving = false;
                 break;
             }
         }
         
         //snake collision to right and bottom corner (not working)
         /**if(snakePosX[0] < 0 || snakePosX[0] > WIDTH - TICK_SIZE || snakePosY[0] < 0 || snakePosY[0] > HEIGHT - TICK_SIZE){
        isMoving = false;
        }**/
         
         //snake collision with the bottom and right corner
          if(snakePosX[0] < 0 || snakePosY[0] < 0 || snakePosX[0] > WIDTH*2 - 250|| snakePosY[0] > HEIGHT+800 ){
        isMoving = false;
        }
          
         
         if(!isMoving){
             timer.stop();
         }
    }
   
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(isMoving){
            move();
            collisionTest();
            consumeFood();
            
            //testPrint statements 
            System.out.println("food X: " + food.getXcoordinate());
            System.out.println("food Y: " + food.getYcoordinate());
            System.out.println("Snake X: " + snakePosX[0]);
            System.out.println("Snake X: " + snakePosY[0]);
        }
        
        repaint();
    }
    
}
