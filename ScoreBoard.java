import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ScoreBoard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScoreBoard extends Actor
{
    public int RedScore = 0;
    public int BlackScore = 0;
    
    public boolean isRedTurn;
    
    public void setTurn(boolean isRed){
        System.out.print(isRed ? "RED TURN GO\n" : "BLACK TURN GO\n");
    }
    
    public ScoreBoard(boolean isRed){
        super();
        startGame(isRed);
    }
    
    public void startGame(boolean isRed){
        System.out.println("START GAME!");
        setTurn(isRed);
    }
}
