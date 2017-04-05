import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BlackPiece here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BlackPiece extends CheckerPiece
{
    public BlackPiece(){
        //empty constructor
        super();
    }
    
    @Override
    public boolean isRed(){
        return false;
    }
}
