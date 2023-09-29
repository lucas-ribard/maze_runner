package org.example;

public class Maze {
    public static void main(String[] args) {
        int SizeX = Integer.parseInt(args[0]);
        int SizeY = Integer.parseInt(args[1]);
        String MazeType = args[2];

        if (MazeType.equals("perfect")){
            SimplePerfectMazeGenerator maze = new SimplePerfectMazeGenerator();
            maze.MazeGenerator(SizeX, SizeY);
        }else{
            System.out.println("MazeType not found");
        }
    }
}



