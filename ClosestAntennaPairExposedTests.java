
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClosestAntennaPairExposedTests {

    // base case: empty pointsA
    @Test
    @Tag("score:2")
    @DisplayName("Base case test 1")
    void baseCaseTest1() {
        Point2D p1 = new Point2D(-0.2, 0.4);
        Point2D p2 = new Point2D(0.15, -0.1);
        Point2D p3 = new Point2D(-0.06, 0.15);
        Point2D[] pointsA = {p1, p2, p3};
        Point2D[] pointsB = {};
        ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(pointsA, pointsB);
        assertEquals(Double.POSITIVE_INFINITY, closestAntennaPair.distance());
        assertEquals(0, closestAntennaPair.getCounter());
    }

    // base case: empty pointsB
    @Test
    @Tag("score:2")
    @DisplayName("Base case test 2")
    void baseCaseTest2() {
        Point2D p1 = new Point2D(-0.2, 0.4);
        Point2D p2 = new Point2D(0.15, -0.1);
        Point2D p3 = new Point2D(-0.06, 0.15);
        Point2D[] pointsA = {};
        Point2D[] pointsB = {p1, p2, p3};
        ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(pointsA, pointsB);
        assertEquals(Double.POSITIVE_INFINITY, closestAntennaPair.distance());
        assertEquals(0, closestAntennaPair.getCounter());
    }

    // base case: one point in pointsA
    @Test
    @Tag("score:3")
    @DisplayName("Base case test 3")
    void baseCaseTest3() {
        Point2D p1 = new Point2D(-0.2, 0.4);
        Point2D p2 = new Point2D(0.15, -0.1);
        Point2D p3 = new Point2D(-0.06, 0.15);
        Point2D[] pointsA = {p2};
        Point2D[] pointsB = {p1,p3};
        ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(pointsA, pointsB);
        assertEquals(p2.distanceTo(p3), closestAntennaPair.distance());
        assertEquals(1, closestAntennaPair.getCounter());
    }

    // base case: one point in pointsB
    @Test
    @Tag("score:3")
    @DisplayName("Base case test 4")
    void baseCaseTest4() {
        Point2D p1 = new Point2D(-0.2, 0.4);
        Point2D p2 = new Point2D(0.15, -0.1);
        Point2D p3 = new Point2D(-0.06, 0.15);
        Point2D[] pointsA = {p1,p3};
        Point2D[] pointsB = {p2};
        ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(pointsA, pointsB);
        assertEquals(p2.distanceTo(p3), closestAntennaPair.distance());
        assertEquals(1, closestAntennaPair.getCounter());
    }

    // recursive case: aPoints on the right of bPoints
    @Test
    @Tag("score:3")
    @DisplayName("Recursive case 1")
    void recursiveTest1() {
        Point2D p1 = new Point2D(0.1,1);
        Point2D p2 = new Point2D(-0.1,1);
        Point2D p3 = new Point2D(0.2,2);
        Point2D p4 = new Point2D(-0.2,2);
        Point2D p5 = new Point2D(0.3,3);
        Point2D p6 = new Point2D(-0.3,3);
        Point2D[] aPoints = {p1, p3, p5};
        Point2D[] bPoints = {p2, p4, p6};

        ClosestAntennaPair pair = new ClosestAntennaPair(aPoints, bPoints);
        assertEquals(0.2, pair.distance());
        assertTrue(pair.getCounter() > 1);
    }

    // recursive case: aPoints on the left of bPoints
    @Test
    @Tag("score:3")
    @DisplayName("Recursive case 2")
    void recursiveTest2() {
        Point2D p1 = new Point2D(0.1,1);
        Point2D p2 = new Point2D(-0.1,1);
        Point2D p3 = new Point2D(0.2,2);
        Point2D p4 = new Point2D(-0.2,2);
        Point2D p5 = new Point2D(0.3,3);
        Point2D p6 = new Point2D(-0.3,3);
        Point2D[] aPoints = {p2, p4, p6};
        Point2D[] bPoints = {p1, p3, p5};

        ClosestAntennaPair pair = new ClosestAntennaPair(aPoints, bPoints);
        assertEquals(0.2, pair.distance());
        assertTrue(pair.getCounter() > 1);
    }

    // recursive case: aPoints below bPoints
    @Test
    @Tag("score:2")
    @DisplayName("Recursive case 3")
    void recursiveTest3() {
        Point2D p1 = new Point2D(1,0.1);
        Point2D p2 = new Point2D(1,-0.1);
        Point2D p3 = new Point2D(2,0.2);
        Point2D p4 = new Point2D(2,-0.2);
        Point2D p5 = new Point2D(3,0.3);
        Point2D p6 = new Point2D(3,-0.3);
        Point2D[] aPoints = {p2, p4, p6};
        Point2D[] bPoints = {p1, p3, p5};


        ClosestAntennaPair pair = new ClosestAntennaPair(aPoints, bPoints);
        assertEquals(0.2, pair.distance());
    }

    // recursive case: aPoints above bPoints
    @Test
    @Tag("score:2")
    @DisplayName("Recursive case 4")
    void recursiveTest4() {
        Point2D p1 = new Point2D(1,0.1);
        Point2D p2 = new Point2D(1,-0.1);
        Point2D p3 = new Point2D(2,0.2);
        Point2D p4 = new Point2D(2,-0.2);
        Point2D p5 = new Point2D(3,0.3);
        Point2D p6 = new Point2D(3,-0.3);
        Point2D[] aPoints = {p1, p3, p5};
        Point2D[] bPoints = {p2, p4, p6};


        ClosestAntennaPair pair = new ClosestAntennaPair(aPoints, bPoints);
        assertEquals(0.2, pair.distance());
        assertTrue(pair.getCounter() > 1);
    }

    // recursive case: aPoints and bPoints are well mixed
    @Test
    @Tag("score:3")
    @DisplayName("Recursive case 5")
    void recursiveTest5() {
        Point2D p1 = new Point2D(-0.2, 0.4);
        Point2D p2 = new Point2D(0.15, -0.1);
        Point2D p3 = new Point2D(-0.06, 0.15);
        Point2D p4 = new Point2D(0.3, 0.5);
        Point2D p5 = new Point2D(-0.15, 0.25);
        Point2D p6 = new Point2D(0.15, 0.3);
        Point2D p7 = new Point2D(0.35, -0.1);

        Point2D[] aPoints = {p1, p3, p4, p7};
        Point2D[] bPoints = {p2, p5, p6};

        ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(aPoints, bPoints);
        assertEquals(p3.distanceTo(p5), closestAntennaPair.distance());
        assertTrue(closestAntennaPair.getCounter() > 1);
    }

    // Test with random points. Feel free to use this as a template to make your own tests!
    @Test
    @Tag("score:2")
    @DisplayName("Recursive case 6")
    void recursiveTest6() {
        // The test may take too long and potentially lead to a timeout error if n > 10000
        int n = 100;
        // Each point in A will take value in the box [aXMin, aXMax) x [aYMin, aYMax)
        // and each point in B will be in the box [bXMin, bXMax) x [bYMin, bYMax).
        // Feel free to modify the values to change the size and location of the boxes.
        // However, if your box is invalid, this test will fail with a RunTimeException.
        double aXMin = -1;
        double aXMax = 1;
        double aYMin = 1;
        double aYMax = 2;
        double bXMin = 1;
        double bXMax = 2;
        double bYMin = 0;
        double bYMax = 4;

        if (aXMax < aXMin || aYMax < aYMin || bXMax < bXMin || bYMax < bYMin) {
            throw new RuntimeException("Box constraint is not valid");
        }

        Point2D[] aPoints = new Point2D[n];
        Point2D[] bPoints = new Point2D[n];
        for (int i = 0; i < n; i++) {
            double aX = aXMin + Math.random() * (aXMax - aXMin);
            double aY = aYMin + Math.random() * (aYMax - aYMin);
            double bX = bXMin + Math.random() * (bXMax - bXMin);
            double bY = bYMin + Math.random() * (bYMax - bYMin);
            aPoints[i] = new Point2D(aX, aY);
            bPoints[i] = new Point2D(bX, bY);
        }
        ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(aPoints, bPoints);
        ClosestAntennaPairBruteForce closestAntennaPairBruteForce = new ClosestAntennaPairBruteForce(aPoints, bPoints);
        assertEquals(closestAntennaPairBruteForce.getClosestDistance(), closestAntennaPair.distance());
    }
}