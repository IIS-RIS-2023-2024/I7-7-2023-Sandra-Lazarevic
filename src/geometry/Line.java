package geometry;

import java.awt.Color;
import java.awt.Graphics;


public class Line extends Shape {
	
	private static final long serialVersionUID = 1L;
	private Point startPoint;
	private Point endPoint;
	
	public Line() {
		
	}
	
	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	public Line(Point startPoint, Point endPoint, boolean selected) {
		this(startPoint, endPoint);
		setSelected(selected);
	}
	
	public Line(Point startPoint, Point endPoint, Color color) {
		this(startPoint, endPoint);
		setColor(color);
	}
	
	public Line(Point startPoint, Point endPoint, boolean selected, Color color) {
		this(startPoint, endPoint, selected);
		setColor(color);
	}
	
	@Override
	public int compareTo(Object o) {
		if (o instanceof Line) {
			return (int) (this.length() - ((Line) o).length()); 
		}
		return 0;
	}
	
	@Override
	public void moveBy(int byX, int byY) {
		this.startPoint.moveBy(byX, byY);
		this.endPoint.moveBy(byX, byY);
	}
	
	public Point getMiddlePointOfLine() {
		int middleByX = (this.startPoint.getX() + this.endPoint.getX()) / 2;
		int middleByY = (this.startPoint.getY() + this.endPoint.getY()) / 2;
		return new Point(middleByX, middleByY);
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(this.startPoint.getX(), this.startPoint.getY(), this.endPoint.getX(), this.endPoint.getY());
		
		if (isSelected()) {
            g.setColor(Color.BLUE);
			drawSelectionRect(g, startPoint);
            drawSelectionRect(g, endPoint);
            drawSelectionRect(g, getMiddlePointOfLine());
		}
		
	}
	
	private void drawSelectionRect(Graphics g, Point p) {
        g.drawRect(p.getX() - 3, p.getY() - 3, 6, 6);
    }
	
	public double length() {
		return startPoint.distance(endPoint.getX(), endPoint.getY());
	}
	
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Line)) return false;
        Line other = (Line) obj;
        return startPoint.equals(other.getStartPoint()) && endPoint.equals(other.getEndPoint());
    }
	
	public boolean contains(int x, int y) {
		if ((startPoint.distance(x, y) + endPoint.distance(x, y)) - length() <= 0.05) {
			return true;
		} else {
			return false;
		}
	}
	
	public Point getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	public Point getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
	
	public String toString() {
		return startPoint + "-->" + endPoint; 
	}
	
    @Override
    public Line clone() {
        return new Line(new Point(startPoint.getX(),startPoint.getY()), new Point(endPoint.getX(),endPoint.getY()), isSelected(), getColor());
    }

	@Override
	public void moveTo(int x, int y) {
		// TODO Auto-generated method stub
	}
	
}
