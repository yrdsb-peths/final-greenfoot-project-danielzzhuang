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
    public int shield=0;
    public boolean roll=false;
    
    
    GreenfootImage[] idleRolling = new GreenfootImage[5];
    SimpleTimer animationTimer_rolling = new SimpleTimer();
    /**
     * Act - do whatever the Ele wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public PlayerCharacter(){
        for(int i=1; i<idleRolling.length; i++){
            idleRolling[i] = new GreenfootImage("images/player_images/player"+i+".png");
            setImage(idleRolling[i]);
            idleRolling[i].scale(70, 70);
        }
        animationTimer_rolling.mark();
    }
    int imageIndex_f=0;
    public void animateRolling(){
        if(animationTimer_rolling.millisElapsed() < 100){
            return;
        }
        animationTimer_rolling.mark();
        setImage(idleRolling[imageIndex_f]);
        imageIndex_f = (imageIndex_f + 1) % idleRolling.length;
    }
    public int getShield(){
        return shield;
    }
    public void setShield(int num){
        shield=shield+num;
    }
    public void setRolling(boolean nextFight){
        roll=nextFight;
    }
    public int getPlayerHp(){
        return playerHp;
    }
    public static int getPlayerMaxHp(){
        return playerMaxHp;
    }
    public void setPlayerHp(int change){
        int changeA=change;
        if(changeA<0 && shield>0){
            changeA*=Math.pow((0.8),shield);
            shield--;
        }
        if(playerHp+changeA > playerMaxHp){
            playerHp=playerMaxHp;
        }
        else if(playerHp+changeA < playerMinHp){
            playerHp=playerMinHp;
        }
        else{
            playerHp=playerHp+changeA;
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
        setLocation(900, 1450);
    }
    
    public void act()
    {
        if(roll){
            animateRolling();
        }
        else{
            GreenfootImage image = new GreenfootImage("images/player_images/player0.png");
            image.scale(70, 70);
            setImage(image);
        }
    }
}
