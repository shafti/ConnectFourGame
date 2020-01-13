package connectfourgame;

import java.util.Scanner;

/**
 * This class allows the user to play a game of connect 4.
 * 
 * @author Claude Lalanne
 * @author claude.r.lalanne@gmail.com
 */
public class ConnectFourGame {
    
    public static void main(String[] args) {
        System.out.println("---SELECT MODE---");
        System.out.println("Easy:     Enter 1");
        System.out.println("Medium:   Enter 2");
        System.out.println("hard:     Enter 3");
        try (Scanner sc = new Scanner(System.in)) {
            while(true){
                System.out.print("Enter Mode:  ");
                try{
                    int mode = sc.nextInt();
                    if(mode < 1 || mode > 3){
                        throw new Exception();
                    }
                    GameEngine g = new GameEngine(mode);
                    g.playGame();                    
                    break;
                }catch(Exception e){
                    System.out.println("incorrect input");
                    sc.nextLine();
                }
            }            
        }
    }    
}