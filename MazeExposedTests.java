
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

public class MazeExposedTests {
    char[][] m0,m1,m2,m3;
    Maze solver;
    @BeforeEach
    void setUp() throws IOException {
        solver = new Maze();

        m0 = solver.loadMaze("mazes/m0.txt");
        m1 = solver.loadMaze("mazes/m1.txt");
        m2 = solver.loadMaze("mazes/m2.txt");
        m3 = solver.loadMaze("mazes/m3.txt");
    }

    @Test
    @Tag("score:6")
    @DisplayName("Maze 0")
    void mazeTest0() {
        assertTrue(solver.solveMaze(m0));
    }

    @Test
    @Tag("score:7")
    @DisplayName("Maze 1")
    void mazeTest1() {
        assertTrue(solver.solveMaze(m1));
    }

    @Test
    @Tag("score:6")
    @DisplayName("Maze 2")
    void mazeTest2() {
        assertTrue(solver.solveMaze(m2));
    }

    @Test
    @Tag("score:6")
    @DisplayName("Maze 3")
    void mazeTest3() {
        assertFalse(solver.solveMaze(m3));
    }
}
