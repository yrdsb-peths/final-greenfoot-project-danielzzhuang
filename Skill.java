import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class atk_c1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Skill extends Actor
{
    GreenfootImage[] selected = new GreenfootImage[6];
    GreenfootImage[] deselected = new GreenfootImage[6];
    public int skillNum;
    public int id;
    /**
     * Act - do whatever the atk_c1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Skill(int id){
        this.id=id;
    }
    public int usedSkill(int point){
        if(id<4){
            return -point*13;
        }
        else if(id>4){
            return (int)(0.02*PlayerCharacter.getPlayerMaxHp());
        }
        return -1;
    }
    public void setSkillNum(int skillNum){
        this.skillNum=skillNum;
    }
    public void show(){
        setLocation(450, 50+skillNum*100);
        //y:150, 250, 350, 450 
    }
    public void hide(){
        setLocation(450, 1000);
    }
    public void act(){
        //
        
    }
}
