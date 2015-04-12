package objects;

import java.awt.Point;

public class DoubleRectangle {
	
	private DoublePoint position;
	private double width, height;
	
	public DoubleRectangle(DoublePoint position, double width, double height){
		this.position = position;
		this.width = width;
		this.height = height;
	}
	
	public boolean contains(DoublePoint p){
		if(p.x>position.x && p.x<position.x+width && p.y>position.y && p.y<position.y+height)
			return true;
		else
			return false;
		
	}

	public DoublePoint getPosition() {
		return position;
	}

	public void setPosition(DoublePoint position) {
		this.position = position;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	
}
