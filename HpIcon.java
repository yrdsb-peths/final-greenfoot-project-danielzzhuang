import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayerHp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HpIcon extends Actor
{
    public int identificationNum;
    public int size=60;
    /**
     * Act - do whatever the PlayerHp wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public HpIcon(int identificationNum){
        this.identificationNum=identificationNum;
    }
    public void show(){
        setLocation(27+identificationNum*846, 27);
    }
    public void hide(){
        setLocation(1050, 50);
    }
    public void act()
    {
        GreenfootImage image = new GreenfootImage("images/HP.png");
        image.scale(60, 60);
        setImage(image);
    }
}
