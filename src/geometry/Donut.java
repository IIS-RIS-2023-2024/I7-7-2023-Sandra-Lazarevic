package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Donut extends Circle {
	
	private static final long serialVersionUID = 1L;
	private int innerRadius;
	
	public Donut() {}
	
	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius;
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		setSelected(selected);
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color) { 
		this(center, radius, innerRadius, selected);
		setColor(color);
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color, Color innerColor) { 
		this(center, radius, innerRadius, selected, color);
		setInnerColor(innerColor);
	}
	
	/*public void draw(Graphics g) {
		super.draw(g);
		g.setColor(getColor());
		g.drawOval(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius, this.innerRadius * 2, this.innerRadius * 2);
	}
	
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		super.fill(g);
		g.setColor(Color.WHITE);
		g.fillOval(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius, this.innerRadius * 2, this.innerRadius * 2);
	}*/
	//pojednostavljena metoda
	@Override
    public void draw(Graphics g) {
        super.draw(g);
        drawInnerCircle(g);
    }

	@Override
	public void fill(Graphics g) {
	    g.setColor(Color.RED);
	    g.fillOval(0, 0, getRadius() * 2, getRadius() * 2);
	    
	    g.setColor(Color.WHITE);
	    g.fillOval(innerRadius, innerRadius, (getRadius() - innerRadius) * 2, (getRadius() - innerRadius) * 2);
	}

    private void drawInnerCircle(Graphics g) {
        g.setColor(getColor());
        g.drawOval(getCenter().getX() - innerRadius, getCenter().getY() - innerRadius, innerRadius * 2, innerRadius * 2);
    }

    private void fillInnerCircle(Graphics g) {
        g.setColor(getInnerColor());
        g.fillOval(getCenter().getX() - innerRadius, getCenter().getY() - innerRadius, innerRadius * 2, innerRadius * 2);
    }
	
    @Override
    public int compareTo(Object o) {
        if (o instanceof Donut) {
            Donut other = (Donut) o;
            int radiusComparison = Integer.compare(this.getRadius(), other.getRadius());
            if (radiusComparison != 0) {
                return radiusComparison;
            }
            return Integer.compare(this.innerRadius, other.innerRadius);
        }
        return 0;
    }

	
	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}
	
	/*public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut d = (Donut) obj;
			if (this.getCenter().equals(d.getCenter()) &&
					this.getRadius() == d.getRadius() &&
					this.innerRadius == d.getInnerRadius()) {
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
        if (!(obj instanceof Donut)) return false;
        Donut other = (Donut) obj;
        return this.getCenter().equals(other.getCenter()) &&
               this.getRadius() == other.getRadius() &&
               this.innerRadius == other.innerRadius;
    }
	
	/*public boolean contains(int x, int y) {
		double dFromCenter = this.getCenter().distance(x, y);
		return super.contains(x, y) && dFromCenter > innerRadius;
	}*/
	
	@Override
	public boolean contains(int x, int y) {
	    double distance = getCenter().distance(x, y);  
	    return distance <= getRadius() && distance >= innerRadius;
	}

	
	/*public boolean contains(Point p) {
		double dFromCenter = this.getCenter().distance(p.getX(), p.getY());
		return super.contains(p.getX(), p.getY()) && dFromCenter > innerRadius;
	}*/
    
	public boolean contains(Point p) {
	    double distanceFromCenter = Math.sqrt(Math.pow(p.getX() - getCenter().getX(), 2) + Math.pow(p.getY() - getCenter().getY(), 2));
	    return distanceFromCenter <= getRadius() && distanceFromCenter >= innerRadius;
	}

	public int getInnerRadius() {
		return this.innerRadius;
	}
	
	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	
	@Override
	public String toString() {
	    return "Center=" + getCenter().toString() + ", radius=" + getRadius() + ", inner radius=" + innerRadius;
	}

}
