import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class caveBackGround here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaveBackGround extends Actor
{
    /**
     * Act - do whatever the caveBackGround wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public void moveForward(){
        setLocation(getX()-2, getY());
    }
    public void act()
    {
        GreenfootImage image = new GreenfootImage("images/caveBackGround.png");
        setImage(image);
    }
}
