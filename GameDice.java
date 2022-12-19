import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class GameDice extends Actor
{
    /**
     * Act - do whatever the gameDice_IV wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage[] gameDice_images = new GreenfootImage[6];
    boolean pickUp=false;
    public int diceNum;
    public int dicePoint;
    /**
     * Act - do whatever the gameDice wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public GameDice(int diceNum, int dicePoint){
        this.diceNum=diceNum;
        this.dicePoint=dicePoint;
    }
    public void setDicePoint(int point){
        dicePoint=point+1;
    }
    public int getDicePoint(){
        return dicePoint;
    }
    public void show()
    {
        setLocation(diceNum*60, 560);
    }
    public void hide()
    {
        setLocation(-50, 560);
    }
    public void act()
    {
        if(Greenfoot.mousePressed(this)) pickUp = true;
        if(Greenfoot.mouseClicked(null)) pickUp = false;
        if(pickUp){
            MouseInfo info = Greenfoot.getMouseInfo();
            setLocation(info.getX(), info.getY());
        }
        
        gameDice_images[dicePoint] = new GreenfootImage("images/gameDice_images/GameUsingDice"+dicePoint+".png");
        setImage(gameDice_images[dicePoint]);
        gameDice_images[dicePoint].scale(100, 100);
    }
}
