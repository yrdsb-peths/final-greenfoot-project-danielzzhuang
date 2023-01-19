import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class startButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class instruction_d extends Actor
{
    /**
     * Act - do whatever the startButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public String id="skill";
    public instruction_d(String id){
        this.id=id;
    }
    public void setImages(String id){
        this.id=id;
    }
    public void act(){
        GreenfootImage image = new GreenfootImage("images/instruction_images/I_"+id+".png");
        image.scale(300, 300);
        setImage(image);
    }
}
