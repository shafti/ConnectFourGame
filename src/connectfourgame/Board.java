package connectfourgame;

/**
 *
 * @author Claude Lalanne
 * @author claude.r.lalanne@gmail.com
 */
public class Board {
    private int numOfColumns = 7;
    private int numOfRows = 6;
    public char[][] board; 
    private int row;
    
    //used in game engine
    public Board(){ 
        board = new char[numOfRows][numOfColumns];
        for(int i = 0; i < numOfRows; i++){
            for(int j = 0; j < numOfColumns; j++){
                board[i][j] = '_';
            }            
        }
    }

    
    public int totalSquares() {
        return numOfColumns * numOfRows;
    }
    
    public void displayBoard() {
        System.out.println(" 0 1 2 3 4 5 6");
        System.out.println(" _ _ _ _ _ _ _");
        for (int i = board.length - 1; i >= 0; i--) {
            System.out.print("|");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + "|");
            }
            System.out.println("");
        }
    }
    
    public boolean isValidMove(int column) {
       return board[board.length-1][column] == '_';
   }
    

    
    public int setColumn(int colset, char charValue) {
        for(int i = 0; i < board.length; i++) {
            if(board[i][colset] == '_'){
                this.row = i;
                board[i][colset] = charValue;
                break;
            }
        }
        return row;
    }    
    
    public char[][] getBoard() {
        return board;
    }
}
