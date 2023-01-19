import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Sheild here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shield_Icon extends Actor
{
    GreenfootImage[] gameDice_images = new GreenfootImage[5];
    /**
     * Act - do whatever the Sheild wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public int layer;
    public Shield_Icon(int l){
        layer=l;
    }
    public void show(){
        setLocation(75+(layer)*35, 45);
    }
    public void hide(){
        setLocation(1050, 50);
    }
    public void act()
    {
        GreenfootImage image = new GreenfootImage("images/shield_images/sheild_icon0.png");
        image.scale(50, 50);
        setImage(image);
    }
}
