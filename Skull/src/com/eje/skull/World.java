package com.eje.skull;

import java.util.Random;

public class World {

	static final int WORLD_WIDTH = 10;
    static final int WORLD_HEIGHT = 13;
    static final int SCORE_INCREMENT = 1;
    static final float TICK_INITIAL = 0.2f;
    
    public Skeleton skeleton;
    public Enemy enemy;
    public boolean gameOver = false;;
    public int score = 0;

    boolean fields[][] = new boolean[WORLD_WIDTH][WORLD_HEIGHT];
    Random random = new Random();
    float tickTime = 0;
    float tick = TICK_INITIAL;
    
    public World(){
    	skeleton = new Skeleton();
    	placeEnemy();
    }
    
    private void placeEnemy() {
    	int x = random.nextInt(WORLD_WIDTH-1);
        enemy = new Enemy(x, 10, random.nextInt(3));
    }
    
    public void update(float deltaTime) {
        if (gameOver)
            return;
        
        tickTime += deltaTime;

        while (tickTime > tick) {
            tickTime -= tick;
            skeleton.move();
            
            if (skeleton.ckeckCollision()) {
                gameOver = true;
                return;
            }
            
            if (skeleton.x == enemy.x && skeleton.y == enemy.y) {
            	gameOver = true;
                return;
            }
            
            if(enemy.y > 0){
            	enemy.move();
            }else{
            	placeEnemy();
            	score += SCORE_INCREMENT;
            }
        }
        
    }
}
