import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleWorld extends World
{

    /**
     * Constructor for objects of class TitleWorld.
     * 
     */
    public TitleWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 600, 1); 
        
        Label titleLabel = new Label("Crystal Cave", 100);
        addObject(titleLabel, getWidth()/2, 100);
        
        Label instructionLabel_0 = new Label("Press <Space> to read instruction", 30);
        addObject(instructionLabel_0, getWidth()/2, 500);
    }
    public void act(){
        if(Greenfoot.isKeyDown("space")){
            InstructionPage ip = new InstructionPage();
            Greenfoot.setWorld(ip);
        }
    }
}
