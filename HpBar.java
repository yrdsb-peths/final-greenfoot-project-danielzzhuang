import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class playerHpBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HpBar extends Actor
{
    public int identificationNum;
    /**
     * Act - do whatever the playerHpBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public HpBar(int identificationNum){
        this.identificationNum=identificationNum;
    }
    public void show(){
        setLocation(77+identificationNum*568, 27);
    }
    public void hide(){
        setLocation(1050, 50);
    }
    public void act()
    {
        GreenfootImage image = new GreenfootImage("images/HPbar.png");
        image.scale(200, 200);
        setImage(image);
    }
}
