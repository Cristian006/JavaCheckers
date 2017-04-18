import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BoardObject here.
 * Any object on the board will inherit from this object class to 
 * @author (your name) 
 * @version (a version number or a date)
 */
abstract class BoardObject extends Actor
{
    public BoardObject(){
        //empty constructor
        super();
    }
    
    public void act() 
    {
        //setting the checkerboard's currently selected piece to this object
        if (Greenfoot.mouseClicked(this)) {
            //Whenever this Object is Clicked On
            OnClick();
        }
    }
    
    public abstract void OnClick();
}