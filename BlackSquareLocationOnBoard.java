import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class is meant to store  one valid location on the checker board.
 * Only black locations need to be stored.
 */
public class BlackSquareLocationOnBoard
{
    private int xPosition;
    private int yPosition;
    
    public BlackSquareLocationOnBoard(int xPos, int yPos)
    {
        xPosition = xPos;
        yPosition = yPos;
    }
    
    public int getYPosition()
    {
        return yPosition;
    }
    
    public int getXPosition()
    {
        return xPosition;
    }
}


