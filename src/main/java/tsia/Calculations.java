package tsia;

import java.awt.*;

public class Calculations {

    /**
     * @param p first point
     * @param q second point
     * @param r third point
     * @return - x, the orientations of the three points.
     * x < 0 => the points are turning to the left;
     * x == 0 => the points are collinear;
     * x > 0 => the points are turning right;
     */
    static double orientation(Point p, Point q, Point r) {
        return (p.x * q.y + q.x * r.y + r.x * p.y) -
                (q.y * r.x + r.y * p.x + p.y * q.x);
    }

    /**
     * @param s - first segment
     * @param p - second segment
     * @return true if the segments intersect, false if they don't
     */
    static boolean doIntersect(Segment s, Segment p) {
        Point s1 = s.p1;
        Point s2 = s.p2;
        Point p1 = p.p1;
        Point p2 = p.p2;
        return orientation(p2, p1, s1) * orientation(p2, p1, s2) < 0 &&
                orientation(s2, s1, p1) * orientation(s2, s1, p2) < 0;
    }

    public static Point getIntersectionPoint(Segment s1, Segment s2) {
        if (!doIntersect(s1, s2))
            throw new RuntimeException("The segments don't intersect");

        /*
        * a * x + b * y + c = 0 - the equation of the segment S((x1, y1), (x2, y2))
        * a = y2 - y1
        * b = x1 - x2
        * c = y1 * x2 - x1 * y2
        *
        * a1 * x + b1 * y + c = 0 - equation of the 1st segment
        * a2 * x + b2 * y + c = 0 - equation of the 2nd segment
        * Solving this set of equations, allows us to find the value of x and y,
        * and those are the points where the segments are intersecting
        * -> x = (c2 * b1 - c1 * b2) / (a1 * b2 - a2 * b1)
        * -> y = (c2 * a1 - c1 * a2) / (b1 * a2 - b2 * a1)
        * */

        double a1 = s1.y2 - s1.y1;
        double b1 = s1.x1 - s1.x2;
        double c1 = s1.y1 * s1.x2 - s1.x1 * s1.y2;

        double a2 = s2.y2 - s2.y1;
        double b2 = s2.x1 - s2.x2;
        double c2 = s2.y1 * s2.x2 - s2.x1 * s2.y2;

        double x = (c2 * b1 - c1 * b2) / (a1 * b2 - a2 * b1);
        double y = (c2 * a1 - c1 * a2) / (b1 * a2 - b2 * a1);

        int ix = (int) Math.round(x);
        int iy = (int) Math.round(y);

        return new Point(ix, iy);
    }

}
