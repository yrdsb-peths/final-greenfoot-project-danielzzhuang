import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InstructionPage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InstructionPage extends World
{
    public boolean[] fixedD=new boolean[]{false, false};
    public instruction_d[] iArr= new instruction_d[4];
    SimpleTimer fixTimer = new SimpleTimer();
    public GameDice[] diceArr=new GameDice[2];
    public CaveBackGround cBG;
    public String[] iLoopArr={"start", "skill"};
    /**
     * Constructor for objects of class InstructionPage.
     * 
     */
    public InstructionPage()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 600, 1, false); 
        cBG = new CaveBackGround();
        addObject(cBG, 100, getHeight()/2);
        
        PlayerCharacter player = new PlayerCharacter();        
        addObject(player, 160, 430);
        
        
        iArr[0]= new instruction_d("start", 300);
        addObject(iArr[0], 700, 500);
        
        iArr[1]= new instruction_d("skill", 300);
        addObject(iArr[1], 700, 200);
        
        iArr[2]= new instruction_d("0", 300);
        addObject(iArr[2], 400, 500);
        
        iArr[3]= new instruction_d("1", 300);
        addObject(iArr[3], 400, 200);
        
        for(int i=0; i<diceArr.length; i++){
            diceArr[i]= new GameDice(i+1, 1);
        }
        addObject(diceArr[0], 550, 550);
        addObject(diceArr[1], 550, 150);
    }
    public void fixedD(){
        for(int i=0; i<diceArr.length; i++){
            if( ((int)Math.sqrt(Math.pow(diceArr[i].getX()-(iArr[i].getX()+80), 2)+Math.pow(diceArr[i].getY()-(iArr[i].getY()), 2)))<10 ){
                //dice_1.setLocation(545, 145);//545, 145
                diceArr[i].setLocation(100, 1200);
                fixedD[i]=true;
                
            }
        }
    }
    public void act(){
        fixedD();
        if(fixTimer.millisElapsed() > 2000){
            for(int i=0; i<diceArr.length; i++){
                if(fixedD[0]){
                    fixTimer.mark();
                    CaveWorld cw = new CaveWorld();
                    Greenfoot.setWorld(cw);
                }
                else if(fixedD[1]){
                    fixedD[1]=false;
                }
            }
        }
    }
}
