import java.util.*;
import java.lang.Thread;
import java.io.*;

public class  DrunkardsWalkAlgorithem{
  public static void main(String[] args){
    Chunk chunk = new Chunk(50, 30, 10);
    System.out.print("\u001b[3;0H");
    chunk.generateTerrain(300);
    chunk.render();
    /*
    for (int i=0; i<100; i++){
      System.out.print("\u001b[3;0H");
      chunk.generateTerrain(1);
      chunk.render();
      try{Thread.sleep(100);}
      catch (Exception expn){System.out.println(expn);}
    }*/
    
  }
}

class Chunk{
  int[][] grid;
  int width, height;
  List<Walker> walkers = new ArrayList<Walker>();

  public Chunk(int width, int height, int w){
    this.grid = new int[width][height];
    this.width = width;
    this.height = height;
    for (int i=0; i<w; i++){
      this.walkers.add(new Walker(width/2, height/2));
    }
  }
  public void generateTerrain(int iter){
    for (int i=0; i<iter; i++){
      for (Walker walker:walkers){
        try {
          grid[walker.x][walker.y] = 4;
          walker.move();
        } catch (Exception expn){
          walker.reset(width/2, height/2);
        }
      }
    }
  }
  public void render(){
    for (int col=0; col<grid[0].length; col++){
      for (int row=0; row<grid.length; row++){
        if (grid[row][col] == 0){
          System.out.print("\u001b[7m"+grid[row][col]+"\u001b[0m");
        }else if(grid[row][col] == 4){
          System.out.print("\u001b[0m"+grid[row][col]+"\u001b[0m");
        }
      }
      System.out.println();
    }
  }
}

class Walker {
  int x;
  int y;
  int restricted;
  Random rn;

  public Walker(int x, int y){
    this.x = x;
    this.y = y;
    this.rn = new Random();
  }
  public void move(){
    switch(rn.nextInt(4)){
      case 0:
        if (restricted == 0) {
          y++;
        } else {
          x--;
        }
        restricted = 2;
        break;
      case 1:
        if (restricted == 1) {
          x++;
        } else {
          y--;
        }
        restricted = 3;
        break;
      case 2:
        if (restricted == 2) {
          y--;
        } else {
          x++;
        }
        restricted = 0;
        break;
      case 3:
        if (restricted == 3) {
          x--;
        } else {
          y++;
        }
        restricted = 1;
        break;
      default:
        System.out.println("all case failed");
        break; 
    }
  }
  public void reset(int x_, int y_){
    this.x = x_;
    this.y = y_;
  }
  public void moveIteration(int iteration){
    for (int i=0; i<iteration; i++){
      move();
    }
  }
}

