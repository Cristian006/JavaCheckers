import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CheckerPiece here.
 * This will handle all base functionality for each checker piece on the board
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CheckerPiece extends BoardObject
{
    //is this checker piece currently selected
    protected boolean isSelected = false;
    private Color defaultColor;
    private Color highlightColor = Color.WHITE;
    private GreenfootImage defaultImage;
    private GreenfootImage myImage;
    protected int xPosition;
    protected int yPosition;
    
    public CheckerPiece(){
        //empty constructor
        super();
        myImage = getImage();
        defaultImage = new GreenfootImage(myImage);
        defaultColor = myImage.getColor();
    }
    
    public boolean getIsSelected(){
        return isSelected;
    }
    
    public boolean isRed(){
        return true;
    }
    
    @Override
    public void OnClick(){
        super.OnClick();
        selectThisObject();
    }
    
    protected void selectThisObject(){
        if(isSelected){
            //unselect this piece
            ((CheckerBoard)getWorld()).unSelect();
        }
        else{
            //select this piece
            ((CheckerBoard)getWorld()).setSelected(this);
        }
    }
    
    public void resetPiece(){
        isSelected = false;
        myImage.setColor(defaultColor);
        myImage.fill();
        myImage.drawImage(defaultImage, 0,0);
    }
    
    public void selectPiece(){
        isSelected = true;
        myImage.setColor(highlightColor);
        myImage.fill();
    }
}
