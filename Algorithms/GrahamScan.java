/* Graham Scan
 ** n\log n
 * Finds the convex hull of a given point set \\
 * Input: Points, flag saying whether we want colinear points on hull \\
 * Output: Either minimal or full convex hull
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Stack;

//START
class Point implements Comparable<Point> {
	public int x,y;
	public Point(int a, int b) { x=a; y=b; }
	public int compareTo(Point p) { if (y-p.y == 0) return x-p.x; else return y-p.y; }
	public String toString(){ return x+","+y; }
}
//END

public class GrahamScan {
	public static void main(String[] args) { //new GrahamScan().solve();	}
	//public void solve() {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		ArrayList<Point> ps = new ArrayList<Point>();
		for (int i=0; i<N; i++) {
			ps.add(new Point(sc.nextInt(),sc.nextInt()));
		}
		ArrayList<Point> hull = grahamscan(new ArrayList<Point>(ps),true);
		for (Point p : hull) System.out.println(p);
	}

//START
//kreuzprodukt zw. vektoren bzgl. gleichem startpunkt (p1 -> p2, p1 -> p3)
private static int cross(Point p1, Point p2, Point p3) {
	return (p3.x-p1.x)*(p2.y-p1.y)-(p2.x-p1.x)*(p3.y-p1.y);
}
public static ArrayList<Point> grahamscan(ArrayList<Point> ps, boolean withColinear) {
	Collections.sort(ps); 							  //sortiere nach y, dann x (wichtig für winkel+dist!)
	Stack<Point> hull = new Stack<Point>();
	hull.push(ps.remove(0));						  //startpunkt nehmen
	Collections.sort(ps, new Comparator<Point>() {	  //sortiere nach winkel zu startpunkt
		public Point ref = hull.get(0);
		public int compare(Point p1, Point p2) { return cross(ref, p1, p2); }
	});

	ArrayList<Point> tails = new ArrayList<Point>();
	if (withColinear) { //nimm vorher die problematischen punkte am Ende raus
		tails.add(ps.remove(ps.size()-1));
		while (cross(hull.get(0),tails.get(0),ps.get(ps.size()-1))==0)
			tails.add(ps.remove(ps.size()-1));
	}

	//behalte nur hüllenpunkte (nur "links gehende")
	hull.push(ps.remove(0));
	for (Point p : ps) {
		Point lst = hull.peek();
		Point lst2 = hull.get(hull.size()-2);
		double c = cross(p,lst2,lst);
		while (((!withColinear && c>=0) || (withColinear && c>0)) && hull.size()>2) {
			hull.pop();
			lst = lst2;
			lst2 = hull.get(hull.size()-2);
			c = cross(p,lst2,lst);
		}
		hull.push(p);
	}
	ArrayList<Point> ret = new ArrayList<Point>(hull);

	if (!withColinear && cross(ret.get(0),ret.get(1),ret.get(2))==0) //beachte 2. punkt!
		ret.remove(1);

	if (withColinear) //füge kolineare punkte wieder an die hülle ran falls nötig
		ret.addAll(tails);

	return ret;
}
//END
}
/*
25
0 0
1 1
2 2
-1 -1
-2 -2
0 1
0 2
0 -1
0 -2
1 0
1 2
1 -1
1 -2
2 0
2 1
2 -1
2 -2
-1 0
-1 1
-1 2
-1 -2
-2 0
-2 1
-2 -1
-2 2

11
0 0
-1 1
-2 2
-3 3
2 3
0 3
3 2
3 1
3 0
2 0
1 0

14
-4 4
-2 2
-3 3
-1 1
2 2
1 1
47 47
5 5
60 60
0 60
-60 60
7 7
3 3
0 0

 */
