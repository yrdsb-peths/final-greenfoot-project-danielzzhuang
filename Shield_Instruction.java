import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Shield_Icon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shield_Instruction extends Actor
{
    /**
     * Act - do whatever the Shield_Icon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        GreenfootImage image = new GreenfootImage("images/shield_images/shield_Instruction.png");
        setImage(image);
    }
    public void show(){
        setLocation(450, 300);
    }
    public void hide(){
        setLocation(450, 1300);
    }
}
