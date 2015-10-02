/* Gauss Algorithm
 * Calculate solution of linear equations or invert a matrix
 */
import java.util.*;
class Vec {
	public double[] co; public int sz;
	public Vec(int n) {co=new double[n];sz=n;}
	public Vec neg() {
		Vec r = new Vec(sz);
		for (int i=0; i<sz; i++)
			r.co[i] = -co[i];
		return r;
	}
	public Vec add(Vec v) {
		Vec r = new Vec(sz);
		for (int i=0; i<sz; i++)
			r.co[i] = co[i]+v.co[i];
		return r;
	}
	public Vec sub(Vec v) {
		Vec r = new Vec(sz);
		for (int i=0; i<sz; i++)
			r.co[i] = co[i]-v.co[i];
		return r;
	}
	public Vec mul(Vec v) {
		Vec r = new Vec(sz);
		for (int i=0; i<sz; i++)
			r.co[i] = co[i]*v.co[i];
		return r;
	}
}
class Mat {
	public double[][] el; public int m,n;
	public Mat(int a, int b) { m=a;n=b;el = new double[m][n]; }

	//LGS lösen, Voraussetzung: es gibt Lösung (also nur MxM mat.)
	public Vec solveLGS(Vec in) {
		Mat inp = new Mat(m,n);
		for (int i=0;i<m;i++)
			inp.el[i][0] = in.co[i];
		Mat re = gaussJordan(inp);
		Vec r = new Vec(m);
		for (int i=0; i<m; i++)
			r.co[i] = re.el[i][0];
		return r;
	}

	//Gauss-Jordan-Algorithmus für zwei MxN-Matrizen
	//Setzt voraus, dass Lösung existiert! => Nur bei MxM-Matrizen sinnvoll
	public Mat gaussJordan(Mat in) {
		double[][] ext = new double[m][2*n];
		for (int i=0; i<m; i++) { //erweiterte Matrix erstellen
			for (int j=0; j<n; j++)
				ext[i][j] = el[i][j];
			for (int j=0; j<n; j++)
				ext[i][j+n] = in.el[i][j];
		}
		for (int lc=0; lc<m && lc<n; lc++){ //für jede restmatrix schritte durchf.
			int c=lc,l=lc; //finde spalte mit zelle != 0
br:
			for(;c<n;c++,l=lc)
				for(;l<m;l++)
					if (!(ext[l][c]==0))
						break br;

			//zelle mit gewähltem element nach oben schieben und alle anderen
			//Elemente durch dieses teilen
			double[] tmp = new double[2*n];
			double top = ext[l][c];
			//if(top == 0) // Dies ist erforderlich, wenn keine Lösung existiert/das System überbestimmt ist
			//	break;
			if (l>lc)
				tmp = ext[lc].clone();
			for (int j=lc; j<2*n; j++)
				ext[lc][j] = ext[l][j]/top;
			if (l>lc)
				ext[l] = tmp.clone();

			// Erstes Element jeder Zeile durch Subtraktion von Vielfachen der ersten Zeile auf 0 bringen
			for (int i=lc+1; i<m; i++)
				for (int j=2*n-1; j>=c; j--)
					ext[i][j] -= ext[i][c]*ext[lc][j];
		}
		// Aus oberer Dreiecksmatrix Einheitsmatrix erstellen
		for (int i=m-1; i>0; i--)
			for (int i2=i-1; i2>=0; i2--)
				for (int j=2*n-1; j>i2; j--)
					ext[i2][j] -= ext[i2][i]*ext[i][j];
		// Ergebnismatrix erstellen
		Mat r = new Mat(m,n);
		for (int i=0; i<m; i++)
			for (int j=0; j<n; j++)
				r.el[i][j] = ext[i][j+n];
		return r;
	}

	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		int T = sc.nextInt();
		while (T --> 0) {
			Mat m = new Mat(7,7);
			for(int i = 0; i < 7; i++)
				for(int j = 0; j < 7; j++)
					m.el[i][j] = sc.nextInt();

			Mat unit = new Mat(7,7);
			for(int i = 0; i < 7; ++i)
				unit.el[i][i] = 1;

			Mat res = m.gaussJordan(unit);
			for(int i = 0; i < 7; ++i) {
				for(int j = 0; j < 7; ++j)
					System.out.printf("%.03f \t", res.el[i][j]);
				System.out.println();
			}
			System.out.println();
		}
		Mat m2 = new Mat(3,3);
		double[][] in = {{1,1,1},{4,2,1},{9,3,1}};
		m2.el = in;
		Vec v2 = new Vec(3);
		double[] co = {0,1,3};
		v2.co =  co;
		Vec result = m2.solveLGS(v2);
		System.out.println(Arrays.toString(result.co));
	}
}
