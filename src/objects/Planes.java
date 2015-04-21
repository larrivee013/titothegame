package objects;

@Deprecated
public class Planes {
	private DoublePoint dp = new DoublePoint(0,0);
	private double width =1;
	private double[] x = new double[3];
	private double[] y = new double[3];
	private double angle;
	private double a;
	private double b = 1;
	private double c;
	private boolean isUsed = false;
	private int planeVariable;
	public Planes(double x, double y, double angle, double width){
		
		this.width = width;
		this.angle = angle;
		if (this.angle >= Math.PI/2 && this.angle <= 3*Math.PI/2)
			setPlaneVariable(0);
		else
			setPlaneVariable(1);
		
		this.dp.x = x;
		this.dp.y = y;
		setFormula();
		setX();
		setY();
	}
	
	public Planes(int[] x, int[] y){
		
	}
	
	public Planes(double a, double b, double c, DoublePoint dp){
		
	}
	/**
	 * Sets the slope in the form ax + by + c = 0
	 */
	public void setFormula(){
		this.a = Math.tan(angle);
		
		c = (b*dp.y)-(a*dp.x);
		/**System.out.println(a + "x" + " + " + b+ "y" + " + " + c +" = 0");
		System.out.println(dp.x + " " + dp.y);
		DoublePoint p = new DoublePoint(4, 1);
		System.out.println("d: " + pointDistance(p) );*/
		
		angleOfContact(5, 5);
		
	}
	
	/**
	 * Returns the distance between a point and a plane
	 * @param point
	 * @return
	 */
	public double pointDistance(DoublePoint point){
		//System.out.println(point.x + " " + point.y);
		//System.out.println("c: " + c);
		return Math.abs(-a * point.x + b * point.y - c)/ Math.sqrt((a * a) + (b * b));
		
	}
	
	public double angleOfContact(double vx, double vy){
		double t;
		double o = Math.PI -angle;
		//System.out.println(Math.toDegrees(t));
		if (vx < 0){
			t = Math.atan(vx/vy);
			//System.out.println("a " + t);
			t += (Math.PI/2);
			//System.out.println("b " + t);
			t -= o;
			//System.out.println("c " + t);
		}
		else{
			t = Math.atan(vy/vx);
			t +=  o;
		}
		//System.out.println(Math.toDegrees(t));
		return t;
	}
	
	public double getY(double x){
		return a*x + c;
	}
	
	
	public void setIncline(double y){
		if (this.y[2] - y >= width){
			this.y[2] = y;
			setAngle(this.x[0] - this.x[2] , this.y[1] - this.y[2]);
		}
		
	}
	
	public DoublePoint getDp() {
		return dp;
	}

	public void setDp(DoublePoint dp) {
		this.dp = dp;
	}


	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
		setFormula();
	}
	
	public void setAngle(double dx, double dy){
		this.angle = Math.PI - Math.atan(dy/dx);
		setFormula();
	}

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	public double getC() {
		return c;
	}

	public void setC(double c) {
		this.c = c;
	}
	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double[] getX() {
		return x;
	}

	public void setX(double[] x) {
		this.x = x;
	}
	public void setX(){
		x[0] = dp.x;
		x[1] = dp.x + width;
		x[2] = dp.x + width;
		
	}

	public double[] getY() {
		return y;
	}

	public void setY(double[] y) {
		this.y = y;
	}
	
	public void setY(){
		y[0] = dp.y;
		y[1] = getY(dp.x + width);
		y[2] = dp.y;
		
		
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	public int getPlaneVariable() {
		return planeVariable;
	}

	public void setPlaneVariable(int planeVariable) {
		this.planeVariable = planeVariable;
	}
	
	


}