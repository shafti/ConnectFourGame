package connectfourgame;

import java.util.regex.Pattern;

/**
 * Evaluates connect four game play to see if selection provides a winner.  If 
 * move wins game play is stopped.
 * 
 * @author Claude Lalanne
 * @author claude.r.lalanne@gmail.com
 */
public class GameEvaluator { 
    
    public static boolean checkWin(char[][] boardValues, int row, int col) {
        boolean win = false;
        StringBuilder str = new StringBuilder();  
        
        evaluateColumn(str, boardValues, col);
        evaluateRow(str, boardValues, row);
        evaluateDiagUp(str, boardValues, row, col);
        evaluateDiagDown(str, boardValues, row, col);
        
        if (Pattern.matches(".*OOOO.*", str) || Pattern.matches(".*XXXX.*", str)) {
            win = true;            
        }
        return win;
    }
    
    private static void evaluateColumn(StringBuilder str, char[][] board, int column){
       str.append(" C ");
       for(int r = 0; r < board.length; r++){
           str.append(board[r][column]);
        }       
   }
    
    private static void evaluateRow(StringBuilder str, char[][] board, int row){
       str.append(" R ");
       for(int c = 0; c < board[row].length; c++){
           str.append(board[row][c]);
       }       
   }
    
   private static void evaluateDiagUp(StringBuilder str, char[][] board, int r, int c){
        int row = 0;
        int column = c - r;
        
        if(column < 0) {
            row = Math.abs(column);
            column = 0;
        }
        str.append(" U ");
        for(; column < board[row].length; column++) {
            str.append(board[row][column]);
            if(row <board.length -1) {
                row++;
            }else{
                break;
            }
        }        
    }
   
   private static void evaluateDiagDown(StringBuilder str, char[][] board, int r, int c){
       int row = r + c;
       int column = 0;
       
       if(row > board.length - 1) {
           column = row - (board.length -1);
           row = board.length - 1;           
       }
       str.append(" D ");
	for(; row >= 0; row--) {
            str.append(board[row][column]);
            if(column < board[row].length-1) {
                column++;
            }else {
                break;
            }            
        }       
   }    
}
