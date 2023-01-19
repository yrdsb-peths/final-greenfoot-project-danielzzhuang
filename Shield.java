import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Sheild here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shield extends Actor
{
    GreenfootImage[] gameDice_images = new GreenfootImage[2];
    /**
     * Act - do whatever the Sheild wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public int id;
    public Shield(int id){
        this.id=id;
    }
    
    public void act()
    {
        GreenfootImage image = new GreenfootImage("images/shield_images/shield"+id+".png");
        image.scale(100, 100);
        setImage(image);
    }
}
