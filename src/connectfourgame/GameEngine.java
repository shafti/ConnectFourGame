package connectfourgame;

import java.util.Scanner;

/**
 *
 * @author Claude Lalanne
 * @author claude.r.lalanne
 */
public class GameEngine {
    
    private Board board;                //ok
    private int totalSquares;           //ok
    private int counter = 0;            //ok
    private Scanner input = new Scanner(System.in);
    private int columnSelected;         //ok
    private int rowPlaced;              //ok
    private boolean isPlayable = true;  //ok
    private boolean winner;
    private char[] charToPlace = {'X', 'O'};
    private boolean[] level = {false, false, false, false};
    private int mediumLevel = 5;
    private int hardLevel = 10;

    public GameEngine(int playMode) {
        level[playMode] = true;
        board = new Board();
        totalSquares = board.totalSquares();
    }

    public void playGame() {        
        showBoard();
        while (isPlayable) {            
            verifySelectColumn();
            setBoardValue(columnSelected);
            showBoard();
            winner = GameEvaluator.checkWin(board.getBoard(), rowPlaced, columnSelected);
            if (winner) {
                break;
            }
            isPlayable = isBoardFull();        
        }
    }

    public void showBoard() {
        board.displayBoard();
    }

    public void setBoardValue(int col) {        
        rowPlaced = board.setColumn(col, charToPlace[counter%2]);        
        counter++;
    }
    
    public void verifySelectColumn() {
        boolean verify = true;
        while (verify) {
            int column;
            try {
                if (counter % 2 == 0) {
                    System.out.print("select a column:  ");
                    column = input.nextInt();
                } else {
                    System.out.println("AI select a column:  ");
                    
                    //create temp board for AI test runs                   
                    char[][] temp = board.getBoard();
                    AI ai = new AI(temp, counter);
                    if (level[1]) {
                        column = ai.easyMode();
                    } else if (level[2]) {
                        column = ai.hardMode(mediumLevel);
                    } else {                       
                        column = ai.hardMode(hardLevel);
                    }
                }
                if (column < 0 || column > 6) {
                    throw new Exception();
                }
                if (!board.isValidMove(column)) {
                    throw new Exception();
                }
                this.columnSelected = column;
                verify = false;
            } catch (Exception ex) {
                System.out.println(".....Incorrect input.....");
                input.nextLine();
            }
        }
    }

    private boolean isBoardFull() {        
        return totalSquares > counter;
    }
}
