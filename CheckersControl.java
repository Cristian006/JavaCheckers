 
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CheckersControl here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CheckersControl extends Actor
{
    
    private final int  halfWidth = 50;
    
    private final int numberOfSquaresAcrossTheBoard ;
    private final int numberOfSquaresDownTheBoard;;
    //private static Checkers [][] checkAry;
    private static CheckerPiece [][] checkAry;
    
    private static int [][] yellowPos = new int[16][2];
    private static int numOfYel = 0;
    private boolean selected = false;
    private int selPosX;
    private int selPosY;
    
    public CheckersControl(int numberOfSquaresDownTheBoard, int numberOfSquaresAcrossTheBoard ){
        this.numberOfSquaresAcrossTheBoard  = numberOfSquaresAcrossTheBoard ;
        this.numberOfSquaresDownTheBoard  = numberOfSquaresDownTheBoard;
    }
  
    
    public void act() 
    {
        MouseInfo currentMouse = Greenfoot.getMouseInfo();
        // If mouse is clicked
        /*
        if (currentMouse != null){
            System.out.println("NewThing");
            if (currentMouse.getButton() == 1){
               System.out.println("Hello");
                int posX = currentMouse.getX();
                int posY = currentMouse.getY();
                if (selected == true){
                    System.out.println("true Selected");
                    if (checkYellow(posX, posY)){
                        //Moving Checkers piece
                        checkAry[posY/(halfWidth * 2)][posX/(halfWidth * 4)] = new Checkers(
                            checkAry[selPosY/(halfWidth * 2)][selPosX/(halfWidth * 4)]);//,
                            //(selPosX - (selPosX % (halfWidth * 2))) + halfWidth,
                            //(selPosY - (selPosY % (halfWidth * 2))) + halfWidth);
                        checkAry[selPosY/(halfWidth * 2)][selPosX/(halfWidth * 4)] = null;
                    }
                    
                    removeYellow();
                    selPosX = -1;
                    selPosY = -1;
                    selected = false;
                }
                else {
                    System.out.println("false Selected");
                    if (checkPos(posX, posY)){
                        System.out.println("Checkers Selected");
                        if (checkForward(posX, posY,
                            checkAry[posY/(halfWidth * 2)][posX/(halfWidth * 4)].getUpFacing(),
                            checkAry[posY/(halfWidth * 2)][posX/(halfWidth * 4)].getUpgraded())){
                            selected = true;
                            selPosX = posX;
                            selPosY = posY;
                        } 
                    } 
                }
            }
        }*/
    }
    // COmmented out by Antonio, dthis code did nothing that I c could see
    
    public boolean checkYellow(int posX, int posY){ // Given in mouse pos
        int tempPosY = (posY/ (halfWidth * 2));
        int tempPosX = (posX/ (halfWidth * 4)) + ((1 + posY) % 2);
        
        for (int i = 0; i < numOfYel; i++){
            if (yellowPos[i][0] == tempPosX && yellowPos[i][1] == tempPosY) {   return true;    }
        }
        return false;
    }
    
    public boolean checkPos(int posX, int posY){ // Given in mouse pos
        posX = posX/(halfWidth * 2);
        posY = posY/(halfWidth * 2);
        
        if ((posX + posY) % 2 > 0){
            if (checkAry[posY][(posX / 2)] != null && 
                (posX + posY)% 2 > 0) { return true; }
            
            else { return false; }
            
        } else { return false; }
            
    }

    public boolean checkForward(int posX,
        int posY, boolean facing, boolean upgraded){ // Given in mouse pos
        
        int arPosX = posX/(halfWidth * 4);
        int arPosY = posY/(halfWidth * 2);
        boolean hasDest = false;
        //boolean facing = checkAry[arPosY][arPosX].upFacing;
        //boolean upgraded = checkAry[arPosY][arPosX].upgraded;
        
        // Start going up     !!! Assuming that up is toward 0
        if ((facing || upgraded) && arPosY > 1){
            // Check left
            if (posX > 100){
                if (checkPos(posX - 100, posY - 100)){
                    if (posX > 200 && !checkPos(posX - 200, posY - 200)){
                        if (checkForward(posX - 200, posY - 200, facing, upgraded)){
                            hasDest = true;
                        }else { 
                            createYellow(arPosX, arPosY);
                            hasDest = true;
                        }
                    }
                }else {
                    createYellow(arPosX, arPosY);
                    hasDest = true;
                }
            }
            // Check right
            if (posX < (numberOfSquaresAcrossTheBoard  - 1) * 100){
                if (checkPos(posX + 100, posY - 100)){
                    if (posX < (numberOfSquaresAcrossTheBoard  - 2) * 100 && !checkPos(posX + 200, posY - 200)){
                        if (checkForward(posX + 200, posY - 200, facing, upgraded)){
                            hasDest = true;
                        }else { 
                            createYellow(arPosX, arPosY);
                            hasDest = true;
                        }
                    }
                }else {
                    createYellow(arPosX, arPosY);
                    hasDest = true;
                }
            }
        }
        if ((!facing || upgraded) && arPosY < numberOfSquaresDownTheBoard  - 2){
            // check Left
            if (posX > 100){
                if (checkPos(posX - 100, posY + 100)){
                    if (posX > 200 && !checkPos(posX - 200, posY + 200)){
                        if (checkForward(posX - 200, posY + 200, facing, upgraded)){
                            hasDest = true;
                        }else { 
                            createYellow(arPosX, arPosY);
                            hasDest = true;
                        }
                    }
                }else {
                    createYellow(arPosX, arPosY);
                    hasDest = true;
                }
            }
            // Check right
            if (posX < (numberOfSquaresAcrossTheBoard  - 1) * 100){
                if (checkPos(posX + 100, posY + 100)){
                    if (posX < (numberOfSquaresAcrossTheBoard  - 2) * 100 && !checkPos(posX + 200, posY + 200)){
                        if (checkForward(posX + 200, posY + 200, facing, upgraded)){
                            hasDest = true;
                        }else { 
                            createYellow(arPosX, arPosY);
                            hasDest = true;
                        }
                    }
                }else {
                    createYellow(arPosX, arPosY);
                    hasDest = true;
                }
            }
        }
        return hasDest;
    }
    
    public void removeYellow(){
        numOfYel = 0;
        ((CheckerBoard)getWorld()).removePathTiles();
    }
    
    public void createYellow(int posX, int posY){ // Given in array pos
        ((CheckerBoard)getWorld()).placePathTile(posX, posY); // Give array pos
        numOfYel++;
    }
}
