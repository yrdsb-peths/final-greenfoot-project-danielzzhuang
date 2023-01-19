import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class startButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class instruction_d extends Actor
{
    public int size=300;
    /**
     * Act - do whatever the startButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public String id="skill";
    public instruction_d(String id, int size){
        this.id=id;
        this.size=size;
    }
    public void setImages(String id){
        this.id=id;
    }
    public void act(){
        GreenfootImage image = new GreenfootImage("images/instruction_images/I_"+id+".png");
        image.scale(size, size);
        setImage(image);
    }
}
