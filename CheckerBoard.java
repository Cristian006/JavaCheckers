import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

import java.util.ArrayList;
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
    private final int fullWidth = 100;
    private CheckersControl mainControl;
    private final int numberOfSquaresAcrossTheBoard  = 8;
    private final int numberOfSquaresDownTheBoard  = 8;
    private final int numberOfRowsToPutCheckersOn  = 3;
    private static CheckerPiece [][] checkAry;
    //private vars
    private boolean isRedTurn = false;
    private CheckerPiece selectedPiece = null;
    private ScoreBoard scoreBoard = null;

    ArrayList<Vector2> movableVectors = new ArrayList<Vector2>();
        
    public CheckerBoard()
    {    
        super(800, 800, 1); 
        
        putRedPiecesOnTheBoard();
        putBlackPiecesOnTheBoard();
    }
    
    private enum checkerType{Empty, OutOfBounds, Opponent, Player}

    public void putRedPiecesOnTheBoard()
    {
        int xLocation;
        int yLocation; 

        checkAry = new CheckerPiece[numberOfSquaresDownTheBoard][numberOfSquaresAcrossTheBoard  / 2];
        for (int i = 0; i < numberOfRowsToPutCheckersOn ; i++){
            for (int k = 0; k < numberOfSquaresAcrossTheBoard  / 2; k++){
                xLocation = ( ( ( (i + 1) % 2 ) + (k * 2) ) * (fullWidth)) + halfWidth;
                yLocation = (i * (fullWidth)) + halfWidth;
                
                checkAry[i][k] = new RedPiece(xLocation, yLocation);
                addObject(checkAry[i][k], xLocation, yLocation);  
            }
        }    
    }

    public void putBlackPiecesOnTheBoard()
    {
        // black needs to set values in the constructor l like the red does.
        int xLocation;
        int yLocation;
        int rowsForBlackCheckers  = numberOfSquaresDownTheBoard  - numberOfRowsToPutCheckersOn ;
        
        for (int i = rowsForBlackCheckers; i < numberOfSquaresDownTheBoard; i++){
            for (int k = 0; k < numberOfSquaresAcrossTheBoard  / 2; k++){
                xLocation = ( ( (i + 1) % 2 + (k * 2) ) * halfWidth * 2) + halfWidth;
                yLocation = (i * halfWidth * 2) + halfWidth;

                checkAry[i][k] = new BlackPiece(xLocation, yLocation);
                addObject(checkAry[i][k], xLocation , yLocation);
            }
        }
    }

    //is the current turn red
    public boolean getIsRedTurn(){
        return isRedTurn;
    }

    //sets is red turn to whatever the opposite is
    public void switchTurn(){
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
            unSelect();
        }
    }

    //Create Movable Path for checker piece
    public void setMovableSquares(){
        System.out.println("SETTING MOVABLESQUARES");
        movableVectors = new ArrayList<Vector2>();
        int curX = selectedPiece.getX();
        int curY = selectedPiece.getY();
        ArrayList<CheckerPiece> takenPieces = new ArrayList<CheckerPiece>();
        
        //populate array list recursively with vector2's of available spaces
        getMovableSquares(curX, curY, false, false, false, takenPieces);
        
        //System.out.println(movableVectors.size());
        
        /*
        for(int i = 0; i < movableVectors.size(); i++){
            //set yellow squares in world
            System.out.println("MOVE " + i + ": " + movableVectors.get(i).toString());
            placePathTile(movableVectors.get(i).getX(), movableVectors.get(i).getY());
        }
        */
    }
    
    // Returns true if there is a further square to move to
    public boolean getMovableSquares(int x, int y, boolean dirIgnoreX, boolean dirIgnoreY,
                                     boolean dirIg,ArrayList<CheckerPiece> remPieces){
        boolean notFinalSquare = false;
        
        // Now has the opposite moving direction check to make sure the checkers dont loop back on one jump
        if (!(dirIg && dirIgnoreY && dirIgnoreX) && (selectedPiece.isUpgraded() || isRedTurn)){
            if (getMovableSquaresInDirection(true, true, x, y,new ArrayList<CheckerPiece>(remPieces))){
                notFinalSquare = true;
            }
        }
        
        if (!(dirIg && !dirIgnoreY && dirIgnoreX) && (selectedPiece.isUpgraded() || !isRedTurn)){
            if (getMovableSquaresInDirection(true, false, x, y,new ArrayList<CheckerPiece>(remPieces))){
                notFinalSquare = true;
            }
        }
        
        if (!(dirIg && dirIgnoreY && !dirIgnoreX) && (selectedPiece.isUpgraded() || isRedTurn)){
            if (getMovableSquaresInDirection(false, true, x, y,new ArrayList<CheckerPiece>(remPieces))){
                notFinalSquare = true;
            }
        }
        
        if (!(dirIg && !dirIgnoreY && !dirIgnoreX) && (selectedPiece.isUpgraded() || !isRedTurn)){
            if (getMovableSquaresInDirection(false, false, x, y,new ArrayList<CheckerPiece>(remPieces))){
                notFinalSquare = true;
            }
        }
        // See if there is a further square to move to
        return notFinalSquare;
    }
    
    // Returns true if there is a further square to move to
    public boolean getMovableSquaresInDirection(boolean posXDir, boolean posYDir, int x, int y,
                                                ArrayList<CheckerPiece> remList){
        int xMovementSize = posXDir ? fullWidth : -fullWidth;
        int yMovementSize = posYDir ? fullWidth : -fullWidth;
  
        checkerType currentType = checkPos(x + xMovementSize, y + yMovementSize);
        
        if(currentType == checkerType.Empty && remList.size() == 0){
            placePathTile(x + xMovementSize, y + yMovementSize, remList);
            return false;
        }else if (currentType == checkerType.Opponent){
            currentType = checkPos(x + (xMovementSize * 2), y + (yMovementSize * 2));
                
            if(currentType == checkerType.Empty){
                remList.add(checkAry[(y+yMovementSize)/fullWidth][(x+xMovementSize)/(fullWidth * 2)]);
                if( ! getMovableSquares(x + (xMovementSize * 2), y + (yMovementSize * 2), !posXDir, !posYDir,
                        true,new ArrayList<CheckerPiece>(remList))){
                            
                    placePathTile(x + (xMovementSize * 2), y + (yMovementSize * 2), remList);
                }
                return true;
            }
            else{
                return false;
            }
        }
        else
        {
            return false;
        }
    }
    
    public checkerType checkPos(int posX, int posY){ // Given in mouse pos
        //Check out of bounds first
        
        if (posX < 0 || posY < 0 || (posX > (fullWidth * (numberOfSquaresAcrossTheBoard)) ) ||
            posY > (fullWidth * (numberOfSquaresAcrossTheBoard) )){
                return checkerType.OutOfBounds;
        }
            
        
        posX = posX/(fullWidth);
        posY = posY/(fullWidth);
        
        // posX + posY % 2 checks for whether it is a black square or not
        // further investigate if problems arise
        if (checkAry[posY][(posX / 2)] == null && (posX + posY)% 2 > 0) 
        {
            //the spot is open
            System.out.printf("Empty square");
            return checkerType.Empty;
            
        }
        else if(checkAry[posY][(posX / 2)] != null && (posX + posY)% 2 > 0)
        {
            if(isRedTurn != checkAry[posY][(posX/2)].isRed()){
                return checkerType.Opponent;
            }
            else{
                System.out.printf("Player piece");
                return checkerType.Player;
                
            }
        }
        else{
            return checkerType.Empty;
        }
    }

    //Remove Movable Path for checker piece
    public void removeMovableSquares(){
        removePathTiles();
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
    
    public void placePathTile(int x, int y, ArrayList<CheckerPiece> remPieces){
       addObject(new PathTile(remPieces), x, y); 
    }

    public void moveCheckerPiece(int x, int y, ArrayList<CheckerPiece> takenPieces){
        // this is making a move - not final
        removePathTiles();
        
        if (takenPieces.size() > 0){
            for (int i = 0; i < takenPieces.size(); i++){
                int remX = takenPieces.get(i).getX();
                int remY = takenPieces.get(i).getY();
                
                removeCheckers(takenPieces.get(i));
                checkAry[remY/fullWidth][remX/(fullWidth * 2)] = null;
            }
            takenPieces.clear();
        }
        
        int tempX = selectedPiece.getX();
        int tempY = selectedPiece.getY();
        
        selectedPiece.setLocation(x, y);
        //move in array pos
        System.out.printf("x new " + x/(fullWidth * 2) + " y new " + y/fullWidth);
        System.out.printf("\nx old " + tempX/(fullWidth * 2) + "y old " + tempY/fullWidth);
        
        checkAry[y/fullWidth][x/(fullWidth * 2)] = selectedPiece;
        checkAry[tempY/fullWidth][tempX/(fullWidth * 2)] = null;
        
        if (( y/fullWidth == numberOfSquaresDownTheBoard && isRedTurn ) ||
              y/fullWidth == 0 && !isRedTurn){
            selectedPiece.upgrade();
        }
        
        unSelect();
        switchTurn();
    }
    
    public void removePathTiles(){
        removeObjects(getObjects(PathTile.class));
    }

    public void addCheckers(int posX, int posY, Checkers newCheck){
        addObject(newCheck, posX, posY );
    }

    public void removeCheckers(CheckerPiece remCheck){
        removeObject(remCheck);
    }

    private void makeScoreBoard(){
        scoreBoard = new ScoreBoard(isRedTurn);
    }
}
