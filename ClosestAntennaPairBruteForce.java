
// A brute force method to find the closest distance between points in A and B by pairwise comparison.
public class ClosestAntennaPairBruteForce {
    private double closestDistance = Double.POSITIVE_INFINITY;

    public ClosestAntennaPairBruteForce(Point2D[] aPoints, Point2D[] bPoints) {
        for (Point2D aPoint : aPoints) {
            for (Point2D bPoint : bPoints) {
                double curDistance = aPoint.distanceTo(bPoint);
                if (curDistance < closestDistance)
                    closestDistance = curDistance;
            }
        }
    }

    public double getClosestDistance() {
        return closestDistance;
    }
}
