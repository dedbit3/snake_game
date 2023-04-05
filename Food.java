/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snakegame;

import java.util.Random;

/**
 *
 * @author root
 */
public class Food {
    
    private final int Xcoordinate;
    private final int Ycoordinate;
    private final int min = 10;
    private final int max = 700;
    
    //constructor
    public Food(){
        Xcoordinate = createCoordinate();
        Ycoordinate = createCoordinate();
    }
    
    private int createCoordinate(){
        Random random = new Random();
        return random.nextInt(min, max + 1);
    }

    public int getXcoordinate() {
        return Xcoordinate;
    }

    public int getYcoordinate() {
        return Ycoordinate;
    }
    
}
