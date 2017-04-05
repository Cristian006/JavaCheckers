import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.awt.Color;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CheckerBoard extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    private final int halfWidth = 50;
    private CheckersControl mainControl;
    private final int blocksWide = 8;
    private final int blocksLong = 8;
    private final int playerRows = 3;
    
    //private vars
    private boolean isRedTurn = false;
    private CheckerPiece selectedPiece = null;
    private ScoreBoard scoreBoard = null;
    
    //is the current turn red
    public boolean getIsRedTurn(){
        return isRedTurn;
    }
    
    //sets is red turn to whatever the opposite is
    public void switchTurns(){
        isRedTurn = !isRedTurn;
    }
    
    //Returns whether the checker board has a currently selected piece
    public boolean hasSelectedCheckerPiece(){
        return (selectedPiece != null);
    }
    
    //Set the currently selected piece
    public void setSelected(CheckerPiece piece){
        //if the piece the player is trying to select is part of whoever's turn it is
        //then select the piece
        if(isRedTurn && piece.isRed()){
            unSelect();
            System.out.println("SELECTING RED");
            selectedPiece = piece;
            selectedPiece.selectPiece();
            setMovableSquares();
        }
        else if(!isRedTurn && !piece.isRed()){
            unSelect();
            System.out.println("SELECTING BLACK");
            selectedPiece = piece;
            selectedPiece.selectPiece();
            setMovableSquares();
        }
        else{
            System.out.println("CAN'T MOVE THAT OBJECT!");
            //don't do anything
            //the sent in piece is invalid
            //maybe unselect only????
            //UnSelect();
        }
    }
    
    //Create Movable Path for checker piece
    public void setMovableSquares(){
    
    }
    
    //Remove Movable Path for checker piece
    public void removeMovableSquares(){
        removeYellow();
    }
    
    //Un Select the currently selected piece
    public void unSelect(){
        if(hasSelectedCheckerPiece()){
            //unselect object
            System.out.println("UN SELECTING OBJECT");
            selectedPiece.resetPiece();
            removeMovableSquares();
            selectedPiece = null;
        }
        //else nothing we have to do.
    }
    
    public CheckerBoard()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 800, 1); 
        makeBoard();
        makeScoreBoard();
        List<RedSquare> redsq = getObjects(RedSquare.class);
        mainControl = new CheckersControl(blocksLong, (blocksWide / 2));
        addObject(mainControl,0,0);
        mainControl.setupBoard(playerRows);
        
    }
    
    public void addYellow(int posX, int posY){ // Given in array pos
        
        addObject(new PathTile(),
            (posX * (halfWidth * 2)) + halfWidth,
            (posY * (halfWidth * 2)) + halfWidth);
            
    }
    
    public void removeYellow(){
        removeObjects(getObjects(PathTile.class));   
    }
    
    public void addCheckers(int posX, int posY, Checkers newCheck){
        addObject(newCheck, posX, posY );
    }
    
    public void removeCheckers(Checkers remCheck){
        removeObject(remCheck);
    }
    
    private void makeScoreBoard(){
        scoreBoard = new ScoreBoard(isRedTurn);
    }
    
    private void makeBoard()
    {
        int i, j;
        // red square
        for(j = 50; j < 800; j+= 200)
        {
            for(i = 50; i < 800; i+=200)
            {
                addObject(new RedSquare(), i, j);
                
            }
        }
        
        for(j = 150; j < 800; j+= 200)
        {
            for(i = 150; i < 800; i+= 200)
            {
                addObject(new RedSquare(), i, j);
            }
        }
        
        // black square
        for(j = 50; j < 800; j+= 200)
        {
            for(i = 150; i < 800; i+= 200)
            {
                addObject(new BlackSquare(), i, j);
            }
        }
        
        for(j = 150; j < 800; j+= 200)
        {
            for(i = 50; i < 800; i+= 200)
            {
                addObject(new BlackSquare(), i, j);
            }
        }
        
    }
}
