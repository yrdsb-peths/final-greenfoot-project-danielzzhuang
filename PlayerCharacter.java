import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ele here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class PlayerCharacter extends Actor
{
    public static int playerMaxHp=200;
    public static int playerMinHp=0;
    public int playerHp=playerMaxHp;
    /**
     * Act - do whatever the Ele wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public int getPlayerHp(){
        return playerHp;
    }
    public static int getPlayerMaxHp(){
        return playerMaxHp;
    }
    public void setPlayerHp(int change){
        if(playerHp+change > playerMaxHp){
            playerHp=playerMaxHp;
        }
        else if(playerHp+change < playerMinHp){
            playerHp=playerMinHp;
        }
        else{
            playerHp=playerHp+change;
        }
    }
    
    public boolean playerAlive(){
        if(playerHp==0){
            return false;
        }
        return true;
    }
    
    public void show(){
        setLocation(160, 430);
    }
    
    public void hide(){
        setLocation(900, 450);
    }
    
    public void act()
    {
        GreenfootImage image = new GreenfootImage("images/player.png");
        image.scale(150, 150);
        setImage(image);
    }
}
