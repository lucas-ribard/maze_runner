package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SimplePerfectMazeGenerator {
    public static class Cell {
        public boolean visited = false;
        //walls = true , false = pas de mur
        public boolean[] status = {true, true, true, true}; // 0 - top, 1 - right, 2 - bottom, 3 - left
    }

    void MazeBuilder(int SizeX, int SizeY, List<Cell> board){
        for (int i = 0; i < SizeX; i++) {

            StringBuilder line1 = new StringBuilder();
            StringBuilder line2 = new StringBuilder();
            StringBuilder line3 = new StringBuilder();

            //to get the cell number in the list (board) we use this formula : (i * SizeX) + j
            for (int j = 0; j < SizeY; j++) {
                int Coord = (i * SizeX) + j;
                // if top path is open
                if (!board.get(Coord).status[0]){
                    line1.append("#.#");
                } else if (Coord == 0) {
                    line1.append("#.#");
                } else{
                    line1.append("###");
                }

                if (!board.get(Coord).status[1] && board.get(Coord).status[3]){
                    // if right path is open and left is closed
                    line2.append("#..");
                } else if (board.get(Coord).status[1] && !board.get(Coord).status[3]) {
                    // if left path is open and right is closed
                    line2.append("..#");
                } else if (!board.get(Coord).status[1] && !board.get(Coord).status[3]){
                    // if right both open
                    line2.append("...");
                } else{
                    // if both closed
                    line2.append("#.#");
                }
                if (!board.get(Coord).status[2]){
                    line3.append("#.#");
                }else{
                    line3.append("###");
                }
            }
            System.out.println(line1);
            System.out.println(line2);
            System.out.println(line3);
        }

    }


    void MazeGenerator(int SizeX, int SizeY) {
        //List<Cell> board = null;
        ArrayList<Cell> board = new ArrayList<Cell>();

        // Create board ( add cells for every row and column)
        for (int i = 0; i < SizeX; i++) {
            for (int j = 0; j < SizeY; j++) {
                board.add(new Cell());
            }
        }
        // Starting cell ( top left corner )
        int currentCell = 0;
        // keep track of the path
        Stack<Integer> path = new Stack<>();
        // keep track of the loop
        int k = 0;

        while (k < 1000) {
            k++;
            // mark current cell as visited
            board.get(currentCell).visited = true;

            //check cell neighbours
            List<Integer> Neighbours = CheckNeighbours(currentCell, SizeX, board);

            // if no neighbours
            if (Neighbours.isEmpty()) {
                // if no path
                if (path.isEmpty()) {
                    break;
                } else {
                    currentCell = path.pop();
                }

                // if there are neighbours
            } else {
                path.push(currentCell);
                //select random neighbour
                int newCell = Neighbours.get((int) (Math.random() * Neighbours.size()));

                if (newCell > currentCell) {
                    //down or right
                    if (newCell - 1 == currentCell) {
                        // if selected neighbour is  right
                        // on current cell the right path is open
                        board.get(currentCell).status[1] = false;
                        //change cell
                        currentCell = newCell;
                        //on new cell the left path is open
                        board.get(currentCell).status[3] = false;

                    }else {
                        // if selected neighbour is down
                        // on current cell the down path is open
                        board.get(currentCell).status[2] = false;
                        //change cell
                        currentCell = newCell;
                        //on new cell the top path is open
                        board.get(currentCell).status[0] = false;
                    }
                }else{
                    //up or left
                    if (newCell + 1 == currentCell) {
                        // if selected neighbour is  left
                        // on current cell the left path is open
                        board.get(currentCell).status[3] = false;
                        //change cell
                        currentCell = newCell;
                        //on new cell the right path is open
                        board.get(currentCell).status[1] = false;

                    }else {
                        // if selected neighbour is up
                        // on current cell the up path is open
                        board.get(currentCell).status[0] = false;
                        //change cell
                        currentCell = newCell;
                        //on new cell the bottom path is open
                        board.get(currentCell).status[2] = false;
                    }
                }
            }
        }
        MazeBuilder(SizeX, SizeY, board);
    }
    List<Integer> CheckNeighbours(Integer Cell, Integer SizeX, List<Cell> board){

        ArrayList<Integer> Neighbours = new ArrayList<>();

        // check top (if not at the top of the board && is available)
        if (Cell - SizeX >= 0 && !board.get(Cell - SizeX).visited){
            Neighbours.add(Cell - SizeX);
        }
        //check bottom (if not at the bottom of the board && is available)
        if (Cell + SizeX < board.size() && !board.get(Cell + SizeX).visited){
            Neighbours.add(Cell + SizeX);

        }
        // check right (if not at the right of the board && is available)
        if ((Cell+1) % SizeX != 0 && !board.get(Cell + 1).visited){
            Neighbours.add(Cell + 1);
        }

        //check left (if not at the left of the board && is available)
        if (Cell % SizeX != 0 && !board.get(Cell - 1).visited){
            Neighbours.add(Cell - 1);
        }
        
        return Neighbours;
    }

}
