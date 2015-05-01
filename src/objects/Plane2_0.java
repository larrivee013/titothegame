package objects;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import windows.MainFrame;

/**
 * 
 * @author CharlesPhilippe
 *
 */
@Deprecated
public class Plane2_0 {
	/**
	 * The position of the plane
	 */
	private DoublePoint position;
	/**
	 * The width/ the side adjacent to the angle
	 */
	private double width = 1;
	/**
	 * 
	 */
	private double length;
	/**
	 * 
	 */
	private DoublePoint anchor1;
	/**
	 * 
	 */
	private DoublePoint anchor2;
	/**
	 * 
	 */
	private BufferedImage texture;
	/**
	 * 
	 */
	private int isUsed = -1;
	/**
	 * 
	 */
	private boolean isVisible = false;
	/**
	 * 
	 */
	private double angle;
	/**
	 * The slope of the plane
	 */
	private double m;
	/**
	 * 
	 */
	private double b = 1;
	/**
	 * 
	 */
	private double c;
	/**
	 * Constant that determines easily the where the plane is facing
	 */
	private int planeVariable;
	/**
	 * 
	 */
	private double frictionConstant = 0.5;
	/**
	 * 
	 */
	private boolean isMoving;
	/**
	 * 
	 */
	private DoublePoint defaultPosition = new DoublePoint(1, 1);
	/**
	 * 
	 */
	private int maisonNumber = -1;
	
	private double dy;
	private double dx;
	
	
	
	
	
	
	
	
	/**
	 * Constructs a new plane at a specified position width a specified angle and width
	 * @param x
	 * @param y
	 * @param angle
	 * @param width
	 */
	public Plane2_0(double x, double y, double angle, double width, int maisonNumber){
		this.setMaisonNumber(maisonNumber);
		this.width = width;
		this.angle = angle;
		if (this.angle >= Math.PI/2 && this.angle <= Math.PI || this.angle >= 3*Math.PI/2)
			setPlaneVariable(0);
		else
			setPlaneVariable(1);
		
		this.position = new DoublePoint(x, y);
		setDefaultPosition(position);
		setFormula();
		setAnchors();
		setLength();
		
		texture = MainFrame.getTl().planeTexture;
	}
	
	
	
	/**
	 * Sets the anchors
	 */
	public void setAnchors(){
		this.anchor1 = this.position;
		this.anchor2 = new DoublePoint(position.x + width, getY(this.position.x + width));
		setFormula();
	}
	
	/**
	 * Returns the position in y of the specified x value
	 * @param x
	 * @return
	 */
	public double getY(double x){
		return m*x + c;
	}
	
	/**
	 * Sets the slope in the form mX + bY + c = 0
	 */
	public void setFormula(){
		
		this.m = Math.tan(angle);
		
		c = (b*position.y)-(m*position.x);
	}
	
	/**
	 * Returns the distance between a point and a plane
	 * @param point
	 * @return
	 */
	public double pointDistance(DoublePoint point){
		
		return Math.abs(-m * point.x + b * point.y - c)/ Math.sqrt((m * m) + (b * b));
		
	}
	
	/**
	 * Returns the angle of contact of a vector hitting the plane
	 * @param vx
	 * @param vy
	 * @return
	 */
	public double angleOfContact(double vx, double vy){
		double t;
		double o = Math.PI -angle;
		
		if (vx < 0){
			t = Math.atan(vx/vy);
			
			t += (Math.PI/2);
			
			t -= o;
			
		}
		else{
			t = Math.atan(vy/vx);
			t +=  o;
		}
		
		return t;
	}
	
	/**
	 * Returns the position of the Plane
	 * @return
	 */
	public DoublePoint getPosition() {
		return position;
	}
	
	/**
	 * Sets the position
	 * @param position
	 */
	public void setPosition(DoublePoint position) {
		this.position = position;
		//TODO;
	}
	
	/**
	 * Returns the width of the plane (the side adjacent to the angle)
	 * @return
	 */
	public double getWidth() {
		return width;
	}
	/**Sets the width the specified width
	 * 
	 * @param width
	 */
	public void setWidth(double width) {
		this.width = width;
		//TODO
	}
	/**
	 * Sets the width of the plane (the side adjacent to the angle) from the current length and angle
	 */
	private void setWidth(){
		setAngle();
		this.width = Math.abs(length * Math.cos(angle));
	}
	/**
	 * Gets the position ( as Anchor1) of the plane
	 * @return
	 */
	public DoublePoint getAnchor1() {
		return anchor1;
	}
	/**
	 * sets the position of the plane 
	 * @param anchor1
	 */
	public void setAnchor1(DoublePoint anchor1) {
		this.anchor1 = anchor1;
		//TODO
	}
	/**
	 * Sets the y coordinate of the anchor1 relative to the current anchor2
	 */
	public void setAnchor1Y(){
		//TODO
		setDy(0);
			this.anchor1.y = this.anchor2.y - dy;
		
	}
	/**
	 * Returns the position of the anchor2
	 * @return the second anchor
	 */
	public DoublePoint getAnchor2() {
		return anchor2;
	}
	/**
	 * Sets the position of the Anchor2
	 * @param anchor2
	 */
	public void setAnchor2(DoublePoint anchor2) {
		//TODO
		this.anchor2 = anchor2;
		
	}
	/**
	 * Sets the x coordinate of the anchor2 relative to the current anchor1
	 */
	public void setAnchor2X(){
		//TODO
		setWidth();
		if (planeVariable == 1)
			this.anchor2.x = this.anchor1.x + width;
		else if ( planeVariable == 0)
			this.anchor2.x = this.anchor1.x - width;
		//this.anchor2.y = getY(this.anchor1.x + width);
		if (!isMoving()){
			setAnchor1Y();
		}
		//(anchor2.x);
	}
	/**
	 * Sets the y coordinate of the anchor2 relative to the current anchor1
	 */
	public void setAnchor2Y(){
		
		//TODO
		setDy(0);
		if (planeVariable == 1)
			this.anchor2.y = this.anchor1.y + dy;
		else 
			this.anchor2.y = this.anchor1.y - dy;
		
	}
	
	/**
	 *  Getting used 
	 * -1 if not used,
	 * 0 if used by a Maison,
	 * and 1 if used by a rope.
	 * @return
	 */
	public int isUsed() {
		return isUsed;
	}
	
	/**
	 * Setting used 
	 * -1 if not used,
	 * 0 if used by a Maison,
	 * and 1 if used by a rope.
	 * @param isUsed
	 */
	public void setUsed(int isUsed) {
		this.isUsed = isUsed;
	}
	/**
	 * 
	 * @return
	 */
	public boolean isVisible() {
		return isVisible;
	}
	/**
	 * 
	 * @param isVisible
	 */
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	/**
	 * 
	 * @return
	 */
	public double getAngle() {
		return angle;
	}
	/**
	 * 
	 * @param angle
	 */
	public void setAngle(double angle) {
		//TODO
		this.angle = angle;
		
		
	}
	/**
	 * 
	 */
	public void setAngle(){
	//TODO
		setDx();
		setDy(1);
		this.angle = Math.PI + Math.atan(dy/dx);
		setFormula();
		
	}
	public double getM() {
		return m;
	}
	public void setM(double m) {
		this.m = m;
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
	public int getPlaneVariable() {
		return planeVariable;
	}
	public void setPlaneVariable(int planeVariable) {
		this.planeVariable = planeVariable;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}
	public void setLength(){
		this.length = Math.abs((double)this.width/Math.cos(angle));
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
	/**
	 * 
	 */
	public void resetPosition(){
		this.anchor1.x = this.defaultPosition.x;
		this.anchor1.y = this.defaultPosition.y;
		this.setAnchor2X();
		this.setAnchor2Y();
		setFormula();
	}
	/**
	 * 
	 * @param position
	 */
	public void setDefaultPosition(DoublePoint position) {
		this.defaultPosition.x = this.position.x;
		this.defaultPosition.y = this.position.y;
		
	}
	/**
	 * 
	 * @return
	 */
	public DoublePoint getDefaultPosition() {
		
		return defaultPosition;
	}

	public int getMaisonNumber() {
		return maisonNumber;
	}

	public void setMaisonNumber(int maisonNumber) {
		this.maisonNumber = maisonNumber;
	}
	
	public BufferedImage getTexture(){
		return texture;
	}
	
	
	public void rotateImage(){
		AffineTransform tx = new AffineTransform();
		
	    tx.rotate(angle - Math.PI, texture.getWidth()/2, texture.getHeight()/2);

	    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
	   	texture = op.filter(texture, null);
	   	//rotated = true;
	}



	public double getFrictionConstant() {
		return frictionConstant;
	}



	public void setFrictionConstant(double frictionConstant) {
		this.frictionConstant = frictionConstant;
	}
	
	public void setDy(int i){
		switch(i){
		
			case 0: this.dy = Math.sqrt((length * length) - (width * width));
					break;
			case 1: this.dy = (anchor1.y - anchor2.y);
					break;
			case 2: this.dy = Math.abs(length * Math.sin(angle));
					break;
		}
	}
	
	public void setDx(){
		this.dx = anchor1.x - anchor2.x;
	}
 
}