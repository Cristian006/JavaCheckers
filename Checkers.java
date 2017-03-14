import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Checkers here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Checkers extends Actor
{
/**
     * Act - do whatever the Checkers wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private boolean exists = true;
    private boolean upgraded = false;
    private final boolean upFacing;
    private final int halfWidth = 50;

    
    private GreenfootImage checkerPiece = new GreenfootImage(100, 100);
    
    public Checkers( boolean white){
        if (white){
            checkerPiece.setColor(Color.WHITE);
            upFacing = false;
        }else {
            checkerPiece.setColor(Color.RED);
            upFacing = true;
        }
        
        checkerPiece.fillOval(0, 0, halfWidth, halfWidth);
        
        setImage(checkerPiece);
    }
    public Checkers(Checkers prevCheck){
        upgraded = prevCheck.getUpgraded();
        upFacing = prevCheck.getUpFacing();
    }
    
    public boolean getUpgraded(){   return upgraded;    }
    public boolean getUpFacing(){   return upFacing;    }
    public void upgrade(){   upgraded = true;  }
    
    public void act() 
    {
        
        

    }
    
    
}
