import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class BlackSquare here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BlackSquare extends Actor
{
    private GreenfootImage blackSquare = new GreenfootImage(100, 100);
    
    public BlackSquare()
    {
        colorSquare();
    }
    
    private void colorSquare()
    {
        blackSquare.setColor(Color.BLACK);
        blackSquare.fillRect(0,0,100,100);
        setImage(blackSquare);
    }
    
    private boolean checkColor()
    {
        if(blackSquare.getColor() == Color.BLACK)
        {
            return true;
        }
        else
        {
            return false;
        }
    } 
}
