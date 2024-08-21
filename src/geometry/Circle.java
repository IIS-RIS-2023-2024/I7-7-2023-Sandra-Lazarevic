package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends SurfaceShape {

	private static final long serialVersionUID = 1L;
	private Point center;
	private int radius;
	
	public Circle() {}
	
	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}
	
	public Circle(Point center, int radius, boolean selected) {
		this(center, radius);
		//this.selected(selected);
	}
	
	public Circle(Point center, int radius, boolean selected, Color color) {
		this(center, radius, selected);
		setColor(color);
	}
	
	public Circle(Point center, int r, Color color, Color innerColor) {
		this(center, r);
		setColor(color);
		setInnerColor(innerColor);
	}
	
	public Circle(Point center, int radius, boolean selected, Color color, Color innerColor) {
		this(center, radius, selected, color);
		setInnerColor(innerColor);
		setSelected(selected);
	}
		
	@Override
	public int compareTo(Object o) {
		if (o instanceof Circle) {
			return (this.radius - ((Circle) o).radius);
		}
		return 0;
	}
	
	@Override
	public void moveTo(int x, int y) {
		center.moveTo(x, y);	
	}
	
	@Override
	public void moveBy(int byX, int byY) {
		this.center.moveBy(byX, byY);
	}
	
	@Override
	public void fill(Graphics g) {
	    g.setColor(getInnerColor());
	    g.fillOval(this.center.getX() - radius, this.center.getY() - radius, radius * 2, radius * 2);
	}

	/*@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawOval(this.center.getX() - radius	, this.center.getY() - radius, radius*2, radius*2); 
		this.fill(g);
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.center.getX() - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() - radius - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() + radius - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() - 3, this.center.getY() - radius - 3, 6, 6);
			g.drawRect(this.center.getX() - 3, this.center.getY() + radius - 3, 6, 6);
		}
		
	}*/
	
	@Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.drawOval(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2);
        fill(g); 
        if (isSelected()) {
            g.setColor(Color.BLUE);
            drawSelection(g);
        }
    }
	//pojednostavljena metoda
    private void drawSelection(Graphics g) {
        int x = center.getX();
        int y = center.getY();
        int size = 6;
        g.drawRect(x - size / 2, y - size / 2, size, size);
        g.drawRect(x - radius - size / 2, y - size / 2, size, size);
        g.drawRect(x + radius - size / 2, y - size / 2, size, size);
        g.drawRect(x - size / 2, y - radius - size / 2, size, size);
        g.drawRect(x - size / 2, y + radius - size / 2, size, size);
    }
	
	public double area() {
		return radius * radius * Math.PI;
	}
	
	/*public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle prosledjeni = (Circle) obj;
			if (this.center.equals(prosledjeni.getCenter()) &&
					this.radius == prosledjeni.getRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}*/
	
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Circle other = (Circle) obj;
        return radius == other.radius && center.equals(other.center);
    }
	
	public boolean contains(int x, int y) {
		return center.distance(x, y) <= radius;
	}
	
	public boolean contains(Point p) {
		return center.distance(p.getX(), p.getY()) <= radius;
	}
	
	public Point getCenter() {
		return center;
	}

	/*public void setCenter(Point center) {
		this.center = center;
	}*/
    public void setCenter(Point center) {
        this.center = center != null ? center : new Point();
    }

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) throws Exception {
		if (radius > 0) {
			this.radius = radius;
		} else {
			throw new NumberFormatException("Radius has to be a value greater then 0!");
		}
		
	}

	public String toString() {
		return "Center=" + center + ", radius=" + radius;
	}
	
	/*@Override
	public Circle clone() {
		Circle circle = new Circle(new Point(), radius, getColor(), getInnerColor());
		circle.getCenter().setX(this.getCenter().getX()); 
		circle.getCenter().setY(this.getCenter().getY()); 
		try {
			circle.setRadius(this.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		circle.setInnerColor(this.getInnerColor());
		circle.setColor(this.getColor());
		return circle;
	}*/
	
	@Override
	public Circle clone() {
	    return new Circle(new Point(center.getX(), center.getY()), radius, getColor(), getInnerColor());
	}

}
