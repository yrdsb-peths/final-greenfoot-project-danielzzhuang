import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaveWorld extends World
{
    public int dicePlayerNeed=3;
    public int EnemyAtkCoolDown=2;
    public int dicePlayerHave=dicePlayerNeed;
    public int round=1;
    public int maxFight=3;
    public int fight=0;
    public int enemyId=0;
    public int flagFight=0;
    
    public PlayerCharacter player;
    public Enemy[] enemyArr=new Enemy[4];
    public HpIcon[] iconArr=new HpIcon[2];
    public HpBar[] barArr=new HpBar[2];
    public GameDice[] diceArr=new GameDice[dicePlayerNeed];
    public Skill[] skillArr= new Skill[3];
    
    public CaveBackGround cBG;
    public Label completedLabel;
    public Label endLabel;
    
    public boolean gameStart=true;
    public boolean nextRound=false;
    public boolean nextFight=false;
    public boolean[] fixedD=new boolean[]{false, false, false};
    public boolean[] fixedS=new boolean[]{false, false, false};

    MouseInfo mouse = Greenfoot.getMouseInfo();
    SimpleTimer fixTimer = new SimpleTimer();
    SimpleTimer cBGTimer = new SimpleTimer();
    SimpleTimer nextRoundTimer = new SimpleTimer();
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public CaveWorld()
    {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 600, 1, false);
        cBG = new CaveBackGround();
        addObject(cBG, 900, getHeight()/2);
        
        for(int i=0; i<skillArr.length; i++){
            skillArr[i] = new Skill(i);
            addObject(skillArr[i], getWidth()/2, 1000);
        }
        
        
        for(int i=0; i<dicePlayerNeed; i++){
            diceArr[i]= new GameDice(i+1, 1);
            addObject(diceArr[i], 100, 900);
        }
        
        for(int i=0; i<2; i++){
            enemyArr[i] = new Enemy(i);
            addObject(enemyArr[i], 770+i*250, 410);
        }
        
        enemyArr[2] = new Enemy(2);
        addObject(enemyArr[2], 770+2*250+50, 400);
        
        enemyArr[3] = new Enemy(3);
        addObject(enemyArr[3], 770+3*250+170, 350);
        
        player = new PlayerCharacter();        
        addObject(player, 170, 900);
        
        completedLabel= new Label("Stage Completed!", 100);
        addObject(completedLabel, 450, 1300);
        
        endLabel= new Label("Game Over", 100);
        addObject(endLabel, 450, 1300);
        
        for(int i=0; i<barArr.length; i++){
            barArr[i]=new HpBar(i);
            addObject(barArr[i], 1000, 27);
        }
        for(int i=0; i<iconArr.length; i++){
            iconArr[i]=new HpIcon(i);
            addObject(iconArr[i], 1000, 27);
        }
        
    }
    //get random point for all dice
    public void setUp_diceAndPoint(int diceNeed){
        for(int i=0; i<dicePlayerNeed; i++){
            diceArr[i].setDicePoint(Greenfoot.getRandomNumber(5));
            diceArr[i].show();
        }
    }
    public void setUp_skill(){
        for(int i=0; i<skillArr.length; i++){
            skillArr[i].show();
        }
    }
    public void hideEverything(){
        for(int i=0; i<skillArr.length; i++){
            skillArr[i].hide();
        }
        for(int i=0; i<barArr.length; i++){
            barArr[i].hide();
            iconArr[i].hide();
        }
        for(int i=0; i<dicePlayerHave; i++){
            diceArr[i].hide();
        }
    }
    public void showEverything(){
        setUp_HpAndHpBar();
        setUp_skill();
        for(int i=0; i<dicePlayerHave; i++){
            diceArr[i].show();
        }
    }
    public void setUp_HpAndHpBar(){
        //155,27
        for(int i=0; i<2; i++){
            iconArr[i].show();
            barArr[i].show();
        }
    }
    public void dynamicHpAndHpBar(){
        if(nextFight){
            showText("",40, 70);
        }
        else if(!nextFight){
            barArr[0].setLocation(player.getPlayerHp()-45, 27);
            showText("HP: "+String.valueOf(player.getPlayerHp()),40, 70);
        }
        if(nextFight){
            showText("",860, 70);
        }
        else if(!nextFight){
            barArr[1].setLocation(945-(int)(enemyArr[enemyId].getEnemyHp()/3.2258), 27);
            showText("HP: "+String.valueOf(enemyArr[enemyId].getEnemyHp()),860, 70);
        }
    }
    
    //backgorund move
    public void moveToNextFight(){
        if(cBG.getX()>=(900-fight*200)){
            cBG.moveForward();
            for(int i=0; i<enemyArr.length; i++){
                enemyArr[i].moveForward();
            }
            player.setRolling(true);
        }
        else if(cBG.getX()<(900-fight*200)){
            nextFight=false;
            player.setRolling(false);
        }
    }
    public void EnemyAtk(int enemyId){
        if(enemyId<3){
            player.setPlayerHp(-(enemyArr[enemyId].getEnemyMaxHp()/14+(int)(Math.random()*10)));
        }
        else if(enemyArr[3].getEnemyHp()<(int)(500/4*2) && EnemyAtkCoolDown<=0)
        {
            EnemyAtkCoolDown=3;
            enemyArr[3].setEnemyHp(+100);
            player.setPlayerHp(-(int)((enemyArr[3].getEnemyMaxHp()/14)*(player.getPlayerMaxHp()/100)*1.5));
        }
        else if(enemyArr[3].getEnemyHp()>(int)(500/4*2)||EnemyAtkCoolDown>0){
            player.setPlayerHp(-(player.getPlayerMaxHp()/17+(int)(Math.random()*10)));
            EnemyAtkCoolDown--;
        } 
    }
    public void usedSkill(int point, int skillNum){
        if(skillNum==0){
            enemyArr[enemyId].setEnemyHp(-(point*13+player.getShield()*15));
            if(player.getShield()>0){
                player.setShield(-1);
            }
        }
        else if(skillNum==1){
            player.setPlayerHp((int)(point*player.getPlayerMaxHp()*0.02));
        }
        else if(skillNum==2){
            enemyArr[enemyId].setEnemyHp(-30);
            if(player.getShield()<5){
                player.setShield(1);
            }
        }
    }
    public void fixedD(){
        for(int i=0; i<dicePlayerNeed; i++){
            for(int j=0; j<skillArr.length; j++){
                if( ((int)Math.sqrt(Math.pow(diceArr[i].getX()-(skillArr[j].getX()+100), 2)+Math.pow(diceArr[i].getY()-(skillArr[j].getY()+5), 2)))<15 && ((int)Math.sqrt(Math.pow(diceArr[i].getX()-(skillArr[j].getX()+100), 2)+Math.pow(diceArr[i].getY()-(skillArr[j].getY()+5), 2)))> -15){
                    diceArr[i].hide();
                    fixedS[j]=true;
                    fixedD[i]=true;
                }
            }
        }
    }
    
    public void act(){
        
        if(nextFight){
            hideEverything();
            if(flagFight==1){
                fight++;
                enemyId=fight;
                flagFight=0;
            }
            if(fight>=4){
                completedLabel.setLocation(450, 300);
                Greenfoot.stop();
            }
            moveToNextFight();
            if(!nextFight){
                showEverything();
                round=0;
                /*
                if(fight%2==0){
                    player.setPlayerHp(player.getPlayerMaxHp());
                }
                */
            }
        }
        else if(!nextFight){
            if(nextRoundTimer.millisElapsed() > 1500 && nextRound){
                EnemyAtk(enemyId);
                dicePlayerHave=dicePlayerNeed;
                setUp_diceAndPoint(dicePlayerNeed);
                round+=1;
                nextRound=false;
            }
            else if(!nextRound){
                dynamicHpAndHpBar();
                fixedD();
                if(dicePlayerHave<=0){
                    nextRound=true;
                }
                if(!player.playerAlive()){
                    endLabel.setLocation(450, 300);
                    hideEverything();
                    Greenfoot.stop();
                }
                else if(!enemyArr[enemyId].enemyAlive()){
                    nextFight=true;
                    nextRound=false;
                    flagFight=1;
                    enemyArr[enemyId].hide();
                }
                if(!nextFight){
                    showText("Round: "+String.valueOf(round), 450, 50);
                }
                else{
                    showText("", 450, 50);
                }
                if(gameStart){
                    setUp_diceAndPoint(dicePlayerNeed);
                    player.show();
                    setUp_HpAndHpBar();
                    setUp_skill();
                    gameStart=false;
                }
            }
        }
        //test user dice and skill used
        if(fixTimer.millisElapsed() > 4000){
            for(int i=0; i<dicePlayerNeed; i++){
                for(int j=0; j<skillArr.length; j++){
                    if(fixedD[i] && fixedS[j]){
                        usedSkill(diceArr[i].getDicePoint(), j);
                        fixedS[j]=false;
                        fixedD[i]=false;
                        dicePlayerHave--;
                        fixTimer.mark();
                    }
                }
            }
        }
    }
}
