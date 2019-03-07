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
        if (j != 2) {
          moves[i][j] -= 1;
        }
        //System.out.print(moves[i][j] + " ");
      }
      //System.out.print("\n");
    }

    int count = 0;
    while (count < moves.length) {
      //System.out.println(""+ moves[count][0] + moves[count][1] + moves[count][2]);
      poundDirt(map, moves[count]);
      count++;
    }
    int f = 0;
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (map[i][j] < elevation) {
          f+= elevation - map[i][j];
        }
      }
    }
    return f * 72 * 72;

  }

  public static void poundDirt(int[][] map, int[] moves) {
    int largest = 0;
    for (int i = moves[0]; i < moves[0]+3 && i < map.length ; i++) {
      for (int j = moves[1]; j < moves[1]+3 && j < map[0].length; j++) {
        if (map[i][j] > largest) {
          largest = map[i][j];
        }
      }
    }
    largest -= moves[2];
    for (int i = moves[0]; i < moves[0]+3 && i < map.length; i++) {
      for (int j = moves[1]; j < moves[1]+3 && j < map[0].length; j++) {
        if (map[i][j] > largest) {
          map[i][j] = largest;
        }
      }
    }
  }

  public static int silver(String filename) throws FileNotFoundException{
      File file = new File(filename);
      Scanner scanner = new Scanner(file);
      int rows = scanner.nextInt();
      int cols = scanner.nextInt();
      int time = scanner.nextInt();
      System.out.println(""+ rows + cols + time);
      scanner.nextLine();
      int[][] map = new int[rows][cols];
      for (int i = 0; i < rows; i++) {
        String line = scanner.nextLine();
        System.out.println(line);
        for (int j = 0; j < cols; j++) {
          if (line.charAt(j) == '*') {
            map[i][j] = -1;
          }
          if (line.charAt(j) == '.') {
            map[i][j] = 0;
          }
          System.out.print(""+map[i][j]);
        }
        System.out.println("");
      }
      int startX = scanner.nextInt();
      int startY = scanner.nextInt();
      int endX = scanner.nextInt();
      int endY = scanner.nextInt();
      System.out.println("" + startX + startY + endX + endY);
      return 0;
  }

  public static void main(String[] args) {
    try {
      System.out.println(silver("test.txt"));
    }
    catch (FileNotFoundException e) {
      System.out.println("haha");
    }
  }
}
