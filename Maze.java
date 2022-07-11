import java.io.*;

public class Maze {
    private final int dimension = 15;
    public int counter = 0;

    // Checks if we can move to (x,y)
    boolean canMove(char maze[][], boolean found, int x, int y) {
        if(found) {
            return (x >= 0 && x < dimension && y >= 0 && y < dimension && (maze[x][y] == '.' || maze[x][y] == '0'));
        } else {
            return (x >= 0 && x < dimension && y >= 0 && y < dimension && (maze[x][y] == '.' || maze[x][y] == 'k'));
        }
    }


    boolean solveMaze(char maze[][]) {

        if (!solveMazeUtil(maze, false, 0, 1)) {
            System.out.print("Solution doesn't exist\n");
            return false;
        }
        return true;
    }

    // A recursive function to solve Maze problem
    boolean solveMazeUtil(char maze[][], boolean found, int x, int y) {
        // please do not delete/modify the next line!
        counter++;

        // Insert your solution here and modify the return statement.
        // whether current point could go to end
        boolean flag=false;
        // store the previous data
        char pre=maze[x][y];
        // update found after reach k
        if(maze[x][y]=='k'){
            found=true;
        }else{
            if(found){
                // tag 1 when finding the second path
                maze[x][y]='1';
            }else{
                // tag 0 when finding the first path
                maze[x][y]='0';
            }
        }
        // whether get to the end
        if(x==dimension-1&&y==dimension-2&&found){
            return true;
        }
        // right
        if(!flag&&canMove(maze,found,x,y-1)){
            flag|=solveMazeUtil(maze,found,x,y-1);
        }
        // down
        if(!flag&&canMove(maze,found,x+1,y)){
            flag|=solveMazeUtil(maze,found,x+1,y);
        }
        // left
        if(!flag&&canMove(maze,found,x,y+1)){
            flag|=solveMazeUtil(maze,found,x,y+1);
        }
        // up
        if(!flag&&canMove(maze,found,x-1,y)){
            flag|=solveMazeUtil(maze,found,x-1,y);
        }
        // revert the data if it could not go to end in found path
        if(!flag){
            maze[x][y]=pre;
        }
        return flag;
    }

    //Loads maze from text file
    char[][] loadMaze(String directory) throws IOException{
        char[][] maze = new char[dimension][dimension];

        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                for (int col = 0; col < line.length(); col++){
                    maze[row][col] = line.charAt(col);
                }
                row++;
            }
        }
        return maze;

    }

    //Prints maze
    private static void printMaze(char[][] maze) {
        for (int i = 0; i < maze[0].length; i++) {
            for (int j = 0; j < maze[0].length; j++)
                System.out.print(" " + maze[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }


    public static void main(String args[]) {
        Maze m = new Maze();
        for (int i = 0; i < 4; i++) {
            try {
                char[][] myMaze = m.loadMaze("mazes/m"+i+".txt");
                System.out.println("\nMaze "+i);
                Maze.printMaze(myMaze);
                if(m.solveMaze(myMaze)){
                    Maze.printMaze(myMaze);
                }
            } catch (Exception e){
                System.out.print("File was not found.");
            }

        }
    }
}