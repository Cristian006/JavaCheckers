import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RedPiece here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RedPiece extends CheckerPiece
{
    public RedPiece(int xPos, int yPos){
        //empty constructor
        super();
        this.xPosition = xPos;
        this.yPosition = yPos;
    }
    
    public int getXposition()
    {
        return xPosition;
    }
    
     public int getYposition()
    {
        return yPosition;
    }
    
    @Override
    public boolean isRed(){
        return true;
    }
}
