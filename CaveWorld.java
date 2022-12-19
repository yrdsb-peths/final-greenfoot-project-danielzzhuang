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
    public int enemyId;
    
    public PlayerCharacter player;
    public Enemy[] enemyArr=new Enemy[2];
    public HpIcon[] iconArr=new HpIcon[2];
    public HpBar[] barArr=new HpBar[2];
    public GameDice[] diceArr=new GameDice[dicePlayerNeed];
    public Skill[] skillArr= new Skill[2];
    
    
    public CaveBackGround cBG;
    
    
    public boolean gameStart=true;
    public boolean nextRound=false;
    
    public boolean[] fixedD=new boolean[]{false, false, false};
    /*
    public boolean fixedD1=false;
    public boolean fixedD2=false;
    public boolean fixedD3=false;
    */
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
        
        
        
        /*
        atkC1_text = new Label("atk1: cause point*30 damage", 30);
        addObject (atkC1_text, getWidth()/2, 1000);
        */
        for(int i=0; i<dicePlayerNeed; i++){
            diceArr[i]= new GameDice(i+1, 1);
            addObject(diceArr[i], 100, 900);
        }
        
        for(int i=0; i<enemyArr.length; i++){
            enemyArr[i] = new Enemy(i);
            addObject(enemyArr[i], 770, 900);
        }
        
        
        player = new PlayerCharacter();        
        addObject(player, 170, 900);
        
        for(int i=0; i<barArr.length; i++){
            barArr[i]=new HpBar(i);
            addObject(barArr[i], 1000, 27);
        }
        for(int i=0; i<iconArr.length; i++){
            iconArr[i]=new HpIcon(i);
            addObject(iconArr[i], 1000, 27);
        }
        
        
        
    }
    public void hideAllDice(){
        for(int i=0; i<dicePlayerNeed; i++){
            diceArr[i].hide();
        }
    }
    //get random point for all dice
    public void setUp_diceAndPoint(int diceNeed){
        for(int i=0; i<dicePlayerNeed; i++){
            diceArr[i].setDicePoint(Greenfoot.getRandomNumber(5));
            diceArr[i].show();
        }
    }
    public void setUp_playerAndEnemy(int fightWithEnemy){
        player.show();
        if(fightWithEnemy==0){
            enemyArr[0].show();
        }
    }
    public void setUp_HpAndHpBar(int fightWithEnemy){
        //155,27
        for(int i=0; i<2; i++){
            iconArr[i].show();
            barArr[i].show();
        }
        
        /*
        if(fightWithEnemy){
            EnemyHpBar.setHpBarLenght(Enemy.getEnemyMaxHp()/2);
        }
        else if(!fightWithEnemy){
            EnemyHpBar.setHpBarLenght(200);
        }
        */
        
    }
    public void setUp_skillOrder(){
        
        skillArr[0].setSkillNum(1);
        skillArr[0].show();
    }
    public void dynamicHpAndHpBar(int fightWithEnemy){
        barArr[0].setLocation(player.getPlayerHp()-45, 27);
        showText("HP: "+String.valueOf(player.getPlayerHp()),40, 70);
        
        if(fightWithEnemy==0){
            barArr[1].setLocation(945-(int)(enemyArr[fightWithEnemy].getEnemyHp()/3.2258), 27);
            showText("HP: "+String.valueOf(enemyArr[fightWithEnemy].getEnemyHp()),860, 70);
        }
        
    }
    public void heal(int point){
        player.setPlayerHp((int)(player.getPlayerMaxHp()*0.02*point));
    }
    public void EnemyAtk(int enemyId){
        if(enemyArr[0].getEnemyHp()<(int)(500/4*2) && EnemyAtkCoolDown<=0)
        {
            EnemyAtkCoolDown=3;
            enemyArr[0].setEnemyHp(+100);
            player.setPlayerHp(-(int)((enemyArr[0].getEnemyMaxHp()/14)*(player.getPlayerMaxHp()/100)*1.5));
        }
        else if(enemyArr[0].getEnemyHp()>(int)(500/4*2)||EnemyAtkCoolDown>0){
            player.setPlayerHp(-(player.getPlayerMaxHp()/17+(int)(Math.random()*10)));
            EnemyAtkCoolDown--;
        }
        showText(String.valueOf(EnemyAtkCoolDown), 450, 300);
    }
    public void fixedD(){
        for(int i=0; i<dicePlayerNeed; i++){
            if( ((int)Math.sqrt(Math.pow(diceArr[i].getX()-545, 2)+Math.pow(diceArr[i].getY()-145, 2)))<15 ){
                //dice_1.setLocation(545, 145);//545, 145
                diceArr[i].hide();
                fixedD[i]=true;
            }
        }
    }
    public void act(){
        enemyId=0;
        if(cBG.getX()>900-round*20){
            cBG.moveForward();
        }
        if(nextRoundTimer.millisElapsed() > 1500 && nextRound){
            EnemyAtk(enemyId);
            dicePlayerHave=dicePlayerNeed;
            setUp_diceAndPoint(dicePlayerNeed);
            round+=1;
            nextRound=false;
        }
        else if(!nextRound){
            dynamicHpAndHpBar(enemyId);
            fixedD();
            
            skillArr[0].show();
            if(dicePlayerHave<=0){
                nextRound=true;
            }
            if(!player.playerAlive() || !enemyArr[enemyId].enemyAlive()){
                showText("Game Over", 450, 300);
                Greenfoot.stop();
            }
            showText("Round: "+String.valueOf(round), 450, 50);
            if(gameStart){
                setUp_diceAndPoint(dicePlayerNeed);
                setUp_playerAndEnemy(enemyId);
                setUp_HpAndHpBar(enemyId);
                setUp_skillOrder();
                gameStart=false;
            }
        }
        if(fixTimer.millisElapsed() > 5000){
            for(int i=0; i<dicePlayerNeed; i++){
                if(fixedD[i]){
                    enemyArr[enemyId].setEnemyHp(skillArr[0].usedSkill(diceArr[i].getDicePoint()));
                    fixTimer.mark();
                    fixedD[i]=false;
                    dicePlayerHave--;
                }
            }
        }
        
        
        
        
        
        
        
        
    }
}
