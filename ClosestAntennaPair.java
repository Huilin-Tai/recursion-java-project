import java.util.Arrays;

public class ClosestAntennaPair {

    private double closestDistance = Double.POSITIVE_INFINITY;
    private long counter = 0;

    public ClosestAntennaPair(Point2D[] aPoints, Point2D[] bPoints) {
        // Insert your solution here.
        int nA = aPoints.length;
        if (nA == 0) return;

        int nB = bPoints.length;
        if (nB == 0) return;

        if(nA==1&&nB==1){
            closestDistance=aPoints[0].distanceTo(bPoints[0]);
            return;
        }

        // sort by x-coordinate (breaking ties by y-coordinate via stability)
        Arrays.sort(aPoints, Point2D.Y_ORDER);
        Arrays.sort(bPoints, Point2D.Y_ORDER);

        Point2D[] aPointsSortedByY = new Point2D[nA];
        Point2D[] bPointsSortedByY = new Point2D[nB];

        for (int i = 0; i < nA; i++) {
            aPointsSortedByY[i] = aPoints[i];
        }

        for (int i = 0; i < nB; i++) {
            bPointsSortedByY[i] = bPoints[i];
        }


        Arrays.sort(bPoints, Point2D.X_ORDER);
        Arrays.sort(aPoints, Point2D.X_ORDER);

        Point2D[] aPointsSortedByX = new Point2D[nA];
        Point2D[] bPointsSortedByX = new Point2D[nB];

        for (int i = 0; i < nA; i++) {
            aPointsSortedByX[i] = aPoints[i];
        }

        for (int i = 0; i < nB; i++) {
            bPointsSortedByX[i] = bPoints[i];
        }


        // auxiliary array
        Point2D[] auxA = new Point2D[nA];
        Point2D[] auxB = new Point2D[nB];


        closest(aPointsSortedByX, bPointsSortedByX, aPointsSortedByY, bPointsSortedByY, auxA, auxB, 0, 0, nA - 1,nB-1);
    }

    public double closest(Point2D[] aPointsSortedByX, Point2D[] bPointsSortedByX, Point2D[] aPointsSortedByY, Point2D[] bPointsSortedByY, Point2D[] auxA, Point2D[] auxB, int lowA, int lowB, int highA, int highB) {

        // please do not delete/modify the next line!
        counter++;

        // Insert your solution here and modify the return statement.

        //  low and high refer to indices in the list of points.

        // base case is that low==high (one point) or low > high (zero points); in either case, return POSITIVE_INFINITY
        if (lowA<0||highA < lowA) return Double.POSITIVE_INFINITY;
        if (lowB<0||highB < lowB) return Double.POSITIVE_INFINITY;
        // one point to one point, return the distance
        if(lowA==highA&&lowB==highB){
            return aPointsSortedByX[lowA].distanceTo(bPointsSortedByX[lowB]);
        }

        //  otherwise at least one point is present in the low,high interval
        int midA = lowA + (highA - lowA) / 2;            // if low==high then mid==low
        Point2D medianA = aPointsSortedByX[midA];

        int midB = lowB + (highB - lowB) / 2;            // if low==high then mid==low
        Point2D medianB = aPointsSortedByX[midB];

        // compute closest pair with such that both points in the pair are either in left subarray or both points are in right subarray
        // the two closest calls below will return with pointsSortedByY[low .. mid] and pointsSortedByY[mid+1 .. high] sorted by Y
        // then the pointsSortedByY will be merged so the range [low,high] is sorted by Y
        double delta1 =Double.POSITIVE_INFINITY;
        if(lowA>=0&&midA>=lowA&&lowB>=0&&midB >= lowB){
            if(lowA==midA&&lowB==midB){
                delta1=aPointsSortedByX[lowA].distanceTo(bPointsSortedByX[lowB]);
            }else{
                delta1 = closest(aPointsSortedByX, bPointsSortedByX, aPointsSortedByY, bPointsSortedByY, auxA, auxB, lowA, lowB, midA, midB);
            }
        }
        double delta2 =Double.POSITIVE_INFINITY;
        if(lowA>=0&&midA>=lowA&&midB+1>=0&& highB>= midB+1){
            if(lowA==midA&&midB+1==highB){
                delta2=aPointsSortedByX[lowA].distanceTo(bPointsSortedByX[midB+1]);
            }else{
                delta2 = closest(aPointsSortedByX, bPointsSortedByX, aPointsSortedByY, bPointsSortedByY, auxA, auxB, lowA, midB+1, midA, highB);
            }
        }
        double delta3 =Double.POSITIVE_INFINITY;
        if(midA+1>=0&&highA>=midA+1&&lowB>=0&&midB >= lowB){
            if(midA+1==highA&&lowB==midB){
                delta3=aPointsSortedByX[midA+1].distanceTo(bPointsSortedByX[lowB]);
            }else{
                delta3 = closest(aPointsSortedByX, bPointsSortedByX, aPointsSortedByY, bPointsSortedByY, auxA, auxB, midA+1, lowB, highA, midB);
            }
        }
        double delta4 =Double.POSITIVE_INFINITY;
        if(midA+1>=0&&highA>=midA+1&&midB+1>=0&&highB >= midB+1){
            if(midA+1==highA&&midB+1==highB){
                delta4=aPointsSortedByX[midA+1].distanceTo(bPointsSortedByX[midB+1]);
            }else{
                delta4 = closest(aPointsSortedByX, bPointsSortedByX, aPointsSortedByY, bPointsSortedByY, auxA, auxB, midA+1, midB+1, highA, highB);
            }
        }

        double delta = Math.min(Math.min(Math.min(delta1, delta2), delta3), delta4);
        closestDistance=Math.min(closestDistance,delta);

        // As mentioned above,  now merge back so that pointsSortedByY[low..high] are sorted by y-coordinate.
        // We know the low < high.  Also see preconditions on merge().
        merge(aPointsSortedByY, auxA, lowA, midA, highA);
        merge(bPointsSortedByY, auxB, lowB, midB, highB);

        // copy potential point from A to auxA
        int mA = 0;
        for (int i = lowA; i <= highA; i++) {
            if (Math.abs(aPointsSortedByY[i].x() - medianA.x()) < delta)
                auxA[mA++] = aPointsSortedByY[i];
        }
        // copy potential point from B to auxB
        int mB = 0;
        for (int i = lowB; i <= highB; i++) {
            if (Math.abs(bPointsSortedByY[i].x() - medianB.x()) < delta)
                auxB[mB++] = bPointsSortedByY[i];
        }

        // compute between auxA and auxB, update closestDistance if the distance is maller
        for (int i = 0; i < mA; i++) {

            // a geometric packing argument shows that this loop iterates at most 7 times
            // (we don't need to test explicitly that the max is 7)

            for (int j = 0; j < mB && Math.abs(auxB[j].y() - auxA[i].y()) < delta; j++) {
                double distance = auxA[i].distanceTo(auxB[j]);
                if (distance < delta) {
                    delta = distance;
                    if (distance < closestDistance)
                        closestDistance = delta;
                }
            }
        }

        return delta;
    }

    public double distance() {
        return closestDistance;
    }

    public long getCounter() {
        return counter;
    }

    // stably merge a[low .. mid] with a[mid+1 ..high] using aux[low .. high]
    // precondition: a[low .. mid] and a[mid+1 .. high] are sorted subarrays, namely sorted by y coordinate
    // this is the same as in ClosestPair
    private static void merge(Point2D[] a, Point2D[] aux, int low, int mid, int high) {
        // copy to aux[]
        // note this wipes out any values that were previously in aux in the [low,high] range we're currently using

        for (int k = low; k <= high; k++) {
            aux[k] = a[k];
        }

        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid) a[k] = aux[j++];   // already finished with the low list ?  then dump the rest of high list
            else if (j > high)
                a[k] = aux[i++];   // already finished with the high list ?  then dump the rest of low list
            else if (aux[i].compareByY(aux[j]) < 0)
                a[k] = aux[i++]; // aux[i] should be in front of aux[j] ? position and increment the pointer
            else a[k] = aux[j++];
        }
    }
}
