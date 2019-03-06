import java.util.*;
import java.io.*;

public class USACO {
  public static int bronze(String filename) throws FileNotFoundException{
    int[][] map;
    int[][] moves;
    int elevation;

    File file = new File(filename);
    Scanner scanner = new Scanner(file);

    int row = scanner.nextInt();
    int col = scanner.nextInt();

    map = new int[row][col];
    //System.out.println(row);
    //System.out.println(col);

    elevation = scanner.nextInt();
    int move = scanner.nextInt();
    moves = new int[move][3];

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        map[i][j] = scanner.nextInt();
        //System.out.print(map[i][j] + " ");
      }
      //System.out.print("\n");
    }

    for (int i = 0; i < move; i++) {
      for (int j = 0; j < 3; j++) {
        moves[i][j] = scanner.nextInt();
        //System.out.print(moves[i][j] + " ");
      }
      //System.out.print("\n");
    }

    int count = 0;
    while (count < moves.length) {
      poundDirt(map, moves[count]);
    }

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        System.out.print(map[i][j] + " ");
      }
      System.out.print("\n");
    }
    return 0;
  }


  public static void poundDirt(int[][] map, int[] moves) {
    int largest = 0;
    for (int i = moves[0]; i < moves[0]+3; i++) {
      for (int j = moves[1]; j < moves[1]+3; j++) {
        if (map[i][j] > largest) {
          largest = map[i][j];
        }
      }
    }
    largest -= moves[2];
    for (int i = moves[0]; i < moves[0]+3; i++) {
      for (int j = moves[1]; j < moves[1]+3; j++) {
        if (map[i][j] > largest) {
          map[i][j] = largest;
        }
      }
    }
  }
  public static void main(String[] args) {
    try {
      bronze("test.txt");
    }
    catch (FileNotFoundException e) {
      System.out.println("haha");
    }
  }
}
