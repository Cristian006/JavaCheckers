import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.List;
/**
 * Write a description of class RedSquare here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RedSquare extends Actor
{
    private GreenfootImage redSquare = new GreenfootImage(100, 100);
    
    public RedSquare()
    {
        colorSquare();
    }
    
    private void colorSquare()
    {
        redSquare.setColor(Color.RED);
        redSquare.fillRect(0,0,100,100);
        setImage(redSquare);
    }
    
    private boolean checkColor()
    {
        if(redSquare.getColor() == Color.RED)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Act - do whatever the RedSquare wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
