package connectfourgame;

import java.util.Random;

/**
 *
 * @author Claude Lalanne
 * @author claude.r.lalanne
 */
public class AI {

    private int counter;
    private char[][] currentBoard;
    private Random random = new Random();
    private char[] charToEnter = {'X', 'O'};
    int total;


    private int column;
    private int row;
    
    public AI(char[][] board, int counter) {
        this.counter = counter;
        currentBoard = new char[board.length][];
        for (int i = 0; i < board.length; i++) {
            currentBoard[i] = board[i].clone();
        }
        total = currentBoard.length * currentBoard[0].length;       
    }
    
    public int randomMove() {
        return random.nextInt(currentBoard[0].length);         
    }
    
    public boolean isValidMove(char[][] board, int column) {         
        return board[board.length-1][column] == '_';
    }
    
    public char[][] copyBoard() {
        char[][] testBoard = new char[currentBoard.length][];
        for (int i = 0; i < currentBoard.length; i++) {
            testBoard[i] = currentBoard[i].clone();
        }
        return testBoard;
    }
    
    public int easyMode() {        
        boolean isValid = false;
        int col = -1;
        while (!isValid) {            
            col = randomMove();
            isValid = isValidMove(currentBoard, col);
        }        
        return col;
    }
    
    public int modeVerify(char[][] board) {
        boolean isValid = false;
        int col = -1;
        while (!isValid) {            
            col = randomMove();
            //System.out.println("in modeVefiry column: " + col);
            isValid = isValidMove(board, col);
        }
        return col;
    }
    
    public int hardMode(int m) {
        int bestWin = 0;
        int bestCol = -1;        
        int maxRun = m;
        
        for(int col = 0; col < currentBoard[0].length; col++) {            
            if(bestWin == maxRun){
                break;
            }            
            
            if(isValidMove(currentBoard, col)) {                
                int aiWins = 0;
                
                for (int runs = 0; runs < maxRun; runs++) {  
                    
                    boolean testGameOver = false;
                    char[][] currentTestBoard = copyBoard();                    
                    int runCounter = this.counter; 
                    
                    int row = enterMove(col, currentTestBoard, runCounter);                    
                    runCounter++;
                    testGameOver = GameEvaluator.checkWin(currentTestBoard, row, col);
                    
                    if (testGameOver) {
                       bestWin = maxRun;
                        bestCol = col;                       
                       break;
                   }
                    int whilecounter = 0;
                    while(!testGameOver && !(runCounter >= this.total) ){ 
                        
                        whilecounter++;
                        
                        int pColum = modeVerify(currentTestBoard);
                        int pRow = enterMove(pColum, currentTestBoard, runCounter); 
                        runCounter++;
                        testGameOver = GameEvaluator.checkWin(currentTestBoard, pRow, pColum); 
                        
                        if(testGameOver || (runCounter >= this.total)){
                            break;
                        }
                        
                        int aiColum = modeVerify(currentTestBoard);
                        int aiRow = enterMove(aiColum, currentTestBoard, runCounter); 
                        runCounter++;
                        testGameOver = GameEvaluator.checkWin(currentTestBoard, aiRow, aiColum);
                        if (testGameOver) {
                           aiWins++;
                            
                           break;
                       }
                    }
                    if (aiWins > bestWin) {
                       bestWin = aiWins;
                       bestCol = col;                       
                   }
                    if (bestWin == 0) {
                       for (int c = 0; c < currentTestBoard.length; c++) {
                           if (isValidMove(currentTestBoard, c)) {
                               bestCol = c;
                               break;
                           }
                       }
                   }                    
                }
            }            
        }
        return bestCol;
    }

    public int enterMove(int column, char[][]board, int counterV) {
        int row = -1;       
        for(int i =0; i < board.length; i++) {
            if(board[i][column] == '_'){
               board[i][column] = charToEnter[counterV % 2];
               row = i;                
               break;
            }
        }
        return row;
    }  
}