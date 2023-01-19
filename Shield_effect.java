import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Shield_effect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shield_effect extends Actor
{
    /**
     * Act - do whatever the Shield_effect wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Shield_effect(){
        
    }
    public void show(){
        setLocation(160, 430);
    }
    public void hide(){
        setLocation(900, 1450);
    }
    public void act()
    {
        GreenfootImage image = new GreenfootImage("images/shield_images/sheild_1.png");
        image.scale(100, 100);
        setImage(image);
    }
}
