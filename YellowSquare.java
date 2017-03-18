import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class YellowSquare here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class YellowSquare extends Actor
{
    /**
     * Act - do whatever the YellowSquare wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private final int halfWidth = 50;
    
    private GreenfootImage yellowSquare = new GreenfootImage(100, 100);
    
    public YellowSquare()
    {
        colorSquare();
    }
    
    private void colorSquare()
    {
        yellowSquare.setColor(new Color(208, 208, 90, 180));
        yellowSquare.fillRect(0,0,100,100);
        setImage(yellowSquare);
    }
    
    public void act() 
    {
        // Add your action code here.
    }    
}
