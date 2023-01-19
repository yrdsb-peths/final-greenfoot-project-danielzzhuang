import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    public int size=300;
    public int enemyMaxHp=20;
    public int enemyMinHp=0;
    public int enemyHp=enemyMaxHp;
    public int id;
    public static String state="_Normal";
    
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    /**
     * Act - do whatever the Ele wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Enemy(int id){
        this.id=id;
    }
    public int getEnemyHp(){
        return enemyHp;
    }
    public int getEnemyMaxHp(){
        return enemyMaxHp;
    }
    public void setEnemyHp(int change){
        if(enemyHp+change > enemyMaxHp){
            enemyHp=enemyMaxHp;
        }
        else if(enemyHp+change < enemyMinHp){
            enemyHp=enemyMinHp;
        }
        else{
            if(change<0){
                states("_Hits");
            }
            enemyHp=enemyHp+change;
        }
    }
    public boolean enemyAlive(){
        if(enemyHp==0){
            return false;
        }
        return true;
    }
    public static void states(String states){
        state=states;
        //"_Hits", "_Normal"
    }
    
    public void show(){
        setLocation(770+id*40, 410);
    }
    
    public void hide(){
        setLocation(900, 1450);
    }
    
    public void moveForward(){
        setLocation(getX()-2, getY());
    }
    
    public void bossSetter(){
        if(id==4){
            size=500;
            enemyMaxHp=500;
        }
    }
    public void act(){
        GreenfootImage image = new GreenfootImage("images/enemy_images/enemy"+id+state+".png");
        image.scale(300, 300);
        setImage(image);
    }
}
