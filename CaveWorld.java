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
    public int maxFight=4;
    public int fight=0;
    public int enemyId=0;
    
    public PlayerCharacter player;
    public Enemy[] enemyArr=new Enemy[maxFight];
    public HpIcon[] iconArr=new HpIcon[2];
    public HpBar[] barArr=new HpBar[2];
    public GameDice[] diceArr=new GameDice[dicePlayerNeed];
    public Skill[] skillArr= new Skill[3];
    
    public CaveBackGround cBG;
    
    
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
            addObject(enemyArr[i], 770+i*250, 410);
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
        barArr[0].setLocation(player.getPlayerHp()-45, 27);
        if(!nextFight){
            showText("HP: "+String.valueOf(player.getPlayerHp()),40, 70);
        }
        else{
            showText("",40, 70);
        }
        
        if(enemyId<4){
            barArr[1].setLocation(945-(int)(enemyArr[enemyId].getEnemyHp()/3.2258), 27);
            if(!nextFight){
                showText("HP: "+String.valueOf(enemyArr[enemyId].getEnemyHp()),860, 70);
            }
            else{
                showText("",860, 70);
            }
            
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
    //backgorund move
    public void moveToNextFight(){
        if(cBG.getX()>900-round*200){
            cBG.moveForward();
            for(int i=0; i<enemyArr.length; i++){
                enemyArr[i].moveForward();
            }
            player.animateRolling();
        }
        else if(cBG.getX()<=900-round*200){
            nextFight=false;
        }
    }
    public void usedSkill(int point, int skillNum){
        
        if(skillNum==0){
            showText("skill1", 350, 360);
            enemyArr[enemyId].setEnemyHp(-(point*13+player.getShield()*15));
            if(player.getShield()>0){
                player.setShield(-1);
            }
        }
        else if(skillNum==1){
            player.setPlayerHp((int)(point*player.getPlayerMaxHp()*0.02));
        }
        else if(skillNum==2){
            enemyArr[enemyId].setEnemyHp(-10);
            player.setShield(1);
        }
    }
    public void fixedD(){
        for(int i=0; i<dicePlayerNeed; i++){
            for(int j=0; j<skillArr.length; j++){
                if( ((int)Math.sqrt(Math.pow(diceArr[i].getX()-(skillArr[j].getX()+100), 2)+Math.pow(diceArr[i].getY()-(skillArr[j].getY()+5), 2)))<15 ){
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
            moveToNextFight();
            if(!nextFight){
                showEverything();
                fight++;
                enemyId=fight;
                round=0;
                if(fight%2==0){
                player.setPlayerHp(player.getPlayerMaxHp());
                }
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
                    showText("next round", 350, 330);
                }
                if(!player.playerAlive()){
                    showText("Game Over", 450, 300);
                    Greenfoot.stop();
                }
                else if(!enemyArr[enemyId].enemyAlive()){
                    nextFight=true;
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
                        showText("fixed", 350, 300);
                        usedSkill(diceArr[i].getDicePoint(), j);
                        fixTimer.mark();
                        fixedS[j]=false;
                        fixedD[i]=false;
                        enemyArr[enemyId].states("_Normal");
                        dicePlayerHave--;
                    }
                }
            }
        }
        
        
        
        
        
    }
}
