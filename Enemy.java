import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    public int enemyMaxHp=500;
    public int enemyMinHp=0;
    public int enemyHp=enemyMaxHp;
    public int id;
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    /**
     * Act - do whatever the Ele wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Enemy(int id){
        this.id=id;
    }
    public int getEnemyHp(){
        return enemyHp;
    }
    public int getEnemyMaxHp(){
        return enemyMaxHp;
    }
    public void setEnemyHp(int change){
        if(enemyHp+change > enemyMaxHp){
            enemyHp=enemyMaxHp;
        }
        else if(enemyHp+change < enemyMinHp){
            enemyHp=enemyMinHp;
        }
        else{
            enemyHp=enemyHp+change;
        }
    }
    
    public boolean enemyAlive(){
        if(enemyHp==0){
            return false;
        }
        return true;
    }
    
    public void show(){
        setLocation(770, 450);
    }
    
    public void hide(){
        setLocation(900, 450);
    }
    
    public void act(){
        GreenfootImage image = getImage();
        image.scale(150,150);
        setImage(image);
    }
}
