import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.awt.Color;
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
    private CheckersControl mainControl;
    private final int numberOfSquaresAcrossTheBoard  = 8;
    private final int numberOfSquaresDownTheBoard  = 8;
    private final int numberOfRowsToPutCheckersOn  = 3;
    private static CheckerPiece [][] checkAry;
    //private vars
    private boolean isRedTurn = false;
    private CheckerPiece selectedPiece = null;
    private ScoreBoard scoreBoard = null;

    //private ArrayList<BlackSquareLocationOnBoard > listOfValidLocationsOnCheckerBoard;

    public CheckerBoard()
    {    
        super(800, 800, 1); 

         putRedPiecesOnTheBoard();
         putBlackPiecesOnTheBoard();

         // This method testAllBlackLocations
         // shows how to use and get all the valid black locations
         // black locations are all possible  valid moves
        //testAllBlackLocations();

        mainControl = new CheckersControl(numberOfSquaresDownTheBoard, 
            numberOfSquaresAcrossTheBoard );
        addObject(mainControl,0,0);
    }

    public void testAllBlackLocations()
    {
        // This should put a black piece on every single black square
        // this verifies that getAllValidLocationsOnCheckerBoard works

        ArrayList<BlackSquareLocationOnBoard > listOfValidLocationsOnCheckerBoard;
        listOfValidLocationsOnCheckerBoard  = getAllValidLocationsOhCheckerBoard();

        for(int i = 0; i < listOfValidLocationsOnCheckerBoard .size(); i++)
        {
            int xLocation = listOfValidLocationsOnCheckerBoard .get(i).getXPosition();
            int yLocation = listOfValidLocationsOnCheckerBoard .get(i).getYPosition();
            RedPiece redPiece = new RedPiece(xLocation, yLocation);
            addObject(redPiece, xLocation, yLocation); 
        }
    }

    public void putRedPiecesOnTheBoard()
    {
        int xLocation;
        int yLocation; 

        checkAry = new CheckerPiece[numberOfSquaresDownTheBoard][numberOfSquaresAcrossTheBoard  / 2];
        for (int i = 0; i < numberOfRowsToPutCheckersOn ; i++){
            for (int k = 0; k < numberOfSquaresAcrossTheBoard  / 2; k++){
                xLocation = ( ( ( (i + 1) % 2 ) + (k * 2) ) * (halfWidth * 2)) + halfWidth;
                yLocation = (i * (halfWidth * 2)) + halfWidth;

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
        checkAry = new CheckerPiece[numberOfSquaresDownTheBoard][numberOfSquaresAcrossTheBoard  / 2];

        for (int i = rowsForBlackCheckers; i < numberOfSquaresDownTheBoard; i++){
            for (int k = 0; k < numberOfSquaresAcrossTheBoard  / 2; k++){
                xLocation = ( ( (i + 1) % 2 + (k * 2) ) * halfWidth * 2) + halfWidth;
                yLocation = (i * halfWidth * 2) + halfWidth;

                checkAry[i][k] = new BlackPiece(xLocation, yLocation);
                addObject(checkAry[i][k], xLocation , yLocation);
            }
        }
    }

    private ArrayList<BlackSquareLocationOnBoard > getAllValidLocationsOhCheckerBoard()
    {
        ArrayList<BlackSquareLocationOnBoard > listOfblackSquareLocationOnBoard ;
        listOfblackSquareLocationOnBoard  = new ArrayList<BlackSquareLocationOnBoard >();
        BlackSquareLocationOnBoard   blackSquareLocationOnBoard;

        for(int x = 150; x <= 750; x += 200)
        {
            for(int y = 50; y <= 650; y += 200)
            {  
                blackSquareLocationOnBoard  = new BlackSquareLocationOnBoard (x,y);
                listOfblackSquareLocationOnBoard .add(blackSquareLocationOnBoard );
            }
        }

        for(int x = 50; x <= 650; x += 200)
        {
            for(int y = 150; y <= 750; y += 200)
            {
                blackSquareLocationOnBoard  = new BlackSquareLocationOnBoard (x,y);
                listOfblackSquareLocationOnBoard .add(blackSquareLocationOnBoard );
            }
        }

        return listOfblackSquareLocationOnBoard ;
    }

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
}
