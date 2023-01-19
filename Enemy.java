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
    public int enemyMaxHp=200;
    public int enemyMinHp=0;
    public int enemyHp;
    public int id;
    public int e;
    public String state="_Normal";
    SimpleTimer normalTimer= new SimpleTimer();
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
        if(id==3){
            size=500;
            enemyMaxHp=500;
        }
        else if(id==2){
            size=400;
            enemyMaxHp=300;
        }
        enemyHp=enemyMaxHp;
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
    public void states(String states){
        state=states;
        //"_Hits", "_Normal"
    }
    
    public void show(){
        if(id==3){
            e=100;
        }
        setLocation(770+id*40+e, 410);
    }
    
    public void hide(){
        setLocation(900, 1450);
    }
    
    public void moveForward(){
        if(id<2){
            setLocation(getX()-2, getY());
        }
        else if(id==2){
            setLocation(getX()-3, getY());
        }
        else if(id==3){
            setLocation(getX()-3, getY());
        }
    }
    
    public void act(){
        if(normalTimer.millisElapsed() > 400){
            state="_Normal";
            normalTimer.mark();
        }
        
        GreenfootImage image = new GreenfootImage("images/enemy_images/enemy"+id+state+".png");
        image.scale(size, size);
        setImage(image);
        
    }
}
