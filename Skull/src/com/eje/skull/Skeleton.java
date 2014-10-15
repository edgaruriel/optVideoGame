package com.eje.skull;

public class Skeleton {

    public static final int LEFT = -1;
    public static final int RIGHT = 1;
    public int x,y;
    
    public int direction;  
    
    public Skeleton(){
    	direction = RIGHT;
    	x = 3;
    	y = 2;
    }
    
    public void turn(){
    	direction *= -1;
    }
    
    public void move(){
    	x += direction;
    }
    
    public boolean ckeckCollision(){
    	if(x < 0 || x > 8)
    		return true;
    	return false;
    }
}
