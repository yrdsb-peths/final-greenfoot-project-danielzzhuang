import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class atk_c1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Skill extends Actor
{
    GreenfootImage[] deselected = new GreenfootImage[3];
    public int skillNum;
    public int id;
    
    /**
     * Act - do whatever the atk_c1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Skill(int id){
        this.id=id;
    }
    
    public void setSkillNum(int skillNum){
        this.skillNum=skillNum;
    }
    public void show(){
        setLocation(450, 150+id*110);
        //y:150, 250, 350, 450 
    }
    public void hide(){
        setLocation(450, 1000);
    }
    public void act(){
        GreenfootImage image = new GreenfootImage("images/playerSkill_deselected/skill"+id+"_deselected.png");
        image.scale(300, 100);
        setImage(image);
    }
}
