package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends SurfaceShape {

	private static final long serialVersionUID = 1L;
	private Point upperLeftPoint;
	private int height;
	private int width;
	
	public Rectangle() {}
	
	public Rectangle(Point upperLeftPoint, int height, int width) {
		this.upperLeftPoint = upperLeftPoint;
		this.height = height;
		this.width = width;
	}
	
	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected) {
		this(upperLeftPoint, height, width);
		setSelected(selected);
	}
	
	public Rectangle(Point upperLeft, int width, int height, Color color, Color innerColor) {
		this(upperLeft,width,height);
		setColor(color);
		setInnerColor(innerColor);
	}
	
	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected, Color color) {
		this(upperLeftPoint, height, width, selected);
		setColor(color);
	}
	
	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected, Color color, Color innerColor) {
		this(upperLeftPoint, height, width, selected, color);
		setInnerColor(innerColor);
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Rectangle) {
			return (int) (this.area() - ((Rectangle) o).area());
		}
		return 0;
	}
	@Override
	public void moveTo(int x, int y) {
		upperLeftPoint.moveTo(x, y);
	}

	@Override
	public void moveBy(int byX, int byY) {
		this.upperLeftPoint.moveBy(byX, byY);
	}

	/*@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawRect(this.upperLeftPoint.getX(), this.upperLeftPoint.getY(), this.width, this.height);
		
		this.fill(g);
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(getUpperLeftPoint().getX() - 3, getUpperLeftPoint().getY() - 3, 6, 6);
			g.drawRect(getUpperLeftPoint().getX() + getWidth() - 3, getUpperLeftPoint().getY() - 3, 6, 6);
			g.drawRect(getUpperLeftPoint().getX() - 3, getUpperLeftPoint().getY() + getHeight() - 3, 6, 6);
			g.drawRect(getUpperLeftPoint().getX() + getWidth() - 3, getUpperLeftPoint().getY() + getHeight() - 3, 6, 6);
		}
	}*/
	
	@Override
    public void draw(Graphics g) {
        drawRectangle(g);
        fill(g);
        if (isSelected()) {
        	drawSelectionRect(g);
        }
    }

    private void drawRectangle(Graphics g) {
        g.setColor(getColor());
        g.drawRect(upperLeftPoint.getX(), upperLeftPoint.getY(), width, height);
    }

    private void drawSelectionRect(Graphics g) {
        g.setColor(Color.BLUE);
        drawMarker(g, upperLeftPoint.getX(), upperLeftPoint.getY());
        drawMarker(g, upperLeftPoint.getX() + width, upperLeftPoint.getY());
        drawMarker(g, upperLeftPoint.getX(), upperLeftPoint.getY() + height);
        drawMarker(g, upperLeftPoint.getX() + width, upperLeftPoint.getY() + height);
    }

    // Draw a single marker at a specified position
    private void drawMarker(Graphics g, int x, int y) {
        g.drawRect(x - 3, y - 3, 6, 6);
    }
	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillRect(this.upperLeftPoint.getX()+1, this.getUpperLeftPoint().getY()+1, width-1, height-1);
	}
	
	public double area() {
		return height * width;
	}
	
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Rectangle)) return false;
        Rectangle other = (Rectangle) obj;
        return upperLeftPoint.equals(other.upperLeftPoint) &&
               height == other.height &&
               width == other.width;
    }
    
    public boolean contains(int x, int y) {
        return x >= upperLeftPoint.getX() &&
               y >= upperLeftPoint.getY() &&
               x <= upperLeftPoint.getX() + width &&
               y <= upperLeftPoint.getY() + height;
    }
	
    public boolean contains(Point p) {
        return contains(p.getX(), p.getY());
    }
	
	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}
	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	
	public String toString() {
		return "Upper left point=" + upperLeftPoint + ", width=" + width + ", height=" + height;
	}
	
    @Override
    public Rectangle clone() {
        return new Rectangle(new Point(upperLeftPoint.getX(), upperLeftPoint.getY()), height, width, isSelected(), getColor(), getInnerColor());
    }
	
}
