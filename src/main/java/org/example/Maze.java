package org.example;

public class Maze {
    public static void main(String[] args) {
        int SizeX = Integer.parseInt(args[0]);
        int SizeY = Integer.parseInt(args[1]);

        if (SizeX < 3 || SizeY < 3){
            System.out.println("Erreur : Veuillez fournir une largeur et une hauteur valides supérieurs à 3");
            System.out.println("Utilisation : java -jar MazeRunner.jar [largeur] [hauteur] [perfect/imperfect]");
            System.exit(1);
        }

        String MazeType = args[2];

        if (MazeType.equals("perfect")){
            SimplePerfectMazeGenerator maze = new SimplePerfectMazeGenerator();
            maze.MazeGenerator(SizeX, SizeY);
        }else if (MazeType.equals("imperfect")){
            System.out.println("Erreur : La Génération de labyrinthine imparfait n'est pas encore disponible.");}
        else{
            System.out.println("Erreur : Veuillez fournir un type de labyrinthe et une méthode de génération valides.");
            System.out.println("Utilisation : java -jar MazeRunner.jar [largeur] [hauteur] [perfect/imperfect]");
        }
    }
}



