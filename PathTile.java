import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class YellowSquare here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PathTile extends BoardObject
{
    /**
     * Act - do whatever the YellowSquare wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private final int halfWidth = 50;
    private ArrayList<CheckerPiece> remPieces;
    
    private GreenfootImage yellowSquare = new GreenfootImage(100, 100);
    
    public PathTile(ArrayList<CheckerPiece> setRem)
    {
        remPieces = new ArrayList<CheckerPiece>(setRem);
        colorSquare();
    }
    
    private void colorSquare()
    {
        yellowSquare.setColor(new Color(208, 208, 90, 180));
        yellowSquare.fillRect(0,0,100,100);
        setImage(yellowSquare);
    }
    
    @Override
    public void OnClick(){
        //MOVE CURRENTLY SELECTED OBJECT TO THIS POSITION\
        ((CheckerBoard)getWorld()).moveCheckerPiece(getX(), getY(), remPieces);
    }
}
