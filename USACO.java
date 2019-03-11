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
      int[] moves = new int[] {1, 0, 0, 1, -1, 0, 0, -1};
      File file = new File(filename);
      Scanner scanner = new Scanner(file);
      int rows = scanner.nextInt();
      int cols = scanner.nextInt();
      int time = scanner.nextInt();
      //System.out.println(""+ rows + cols + time);
      scanner.nextLine();
      int[][] map = new int[rows][cols];
      for (int i = 0; i < rows; i++) {
        String line = scanner.nextLine();
        //System.out.println(line);
        for (int j = 0; j < cols; j++) {
          if (line.charAt(j) == '*') {
            map[i][j] = -1;
          }
          if (line.charAt(j) == '.') {
            map[i][j] = 0;
          }
          //System.out.print(""+map[i][j]);
        }
        //System.out.println("");
      }
      int startR = scanner.nextInt()-1;
      int startC = scanner.nextInt()-1;
      int endR = scanner.nextInt()-1;
      int endC = scanner.nextInt()-1;
      //System.out.println("" + startR + startC + endR + endC);
      for (int i = 0; i < moves.length; i+=2) {
        try {
          if (map[startR + moves[i]][startC + moves[i+1]] != -1) {
            map[startR + moves[i]][startC + moves[i+1]] += 1;
          }
        }
        catch(Exception e) {}
      }
      time--;
      /*for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
          System.out.print(map[i][j] + ",");
        }
        System.out.println("");
      }*/
      boolean go;
      if (startR + startC % 2 == 0) {
        go = false;
      }
      else {
        go = true;
      }
      while (time > 0) {
        for (int r = 0; r < rows; r++) {
          for (int c = 0; c < cols; c++) {
            if (go) {
              if (map[r][c] > 0) {
                for (int i = 0; i < moves.length; i+=2) {
                  try{
                    if (map[r + moves[i]][c + moves[i+1]] != -1) {
                      map[r + moves[i]][c + moves[i+1]] += map[r][c];
                    }
                  }
                  catch(Exception e) {}
                }
                map[r][c] = 0;
              }
              go = false;
            }
            else {
              go = true;
            }
          }
          if (cols % 2 == 0) {
            if (go) {
              go = false;
            }
            else{
              go = true;
            }
          }
        }
        time--;
        if (!(rows % 2 == 1 && cols % 2 == 0)) {
          if (go) {
            go = false;
          }
          else{
            go = true;
          }
        }
        /*System.out.println(" ");
        for (int i = 0; i < rows; i++) {
          for (int j = 0; j < cols; j++) {
            System.out.print(map[i][j] + ",");
          }
          System.out.println("");
        }*/
      }
      return map[endR][endC];
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
