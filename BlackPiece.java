import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BlackPiece here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BlackPiece extends CheckerPiece
{
    public BlackPiece(int xPos, int yPos){
        //empty constructor   
        super();
        
        setXPosition(xPos);
        setYPosition(yPos);
    }
    
    public void setXPosition(int xPos)
    {
        xPosition = xPos;
    }
    
    public int getXPosition(){
        return xPosition;
    }
    
    public void setYPosition(int yPos)
    {
        yPosition = yPos;
    }
    
    public int getYPosition(){
        return yPosition;
    }
    
    @Override
    public boolean isRed(){
        return false;
    }
    
    
}
