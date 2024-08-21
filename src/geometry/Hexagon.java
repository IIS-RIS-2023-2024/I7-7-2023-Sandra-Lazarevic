package geometry;

import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.Color;
import java.io.Serializable;

public class Hexagon implements Serializable
{
    private static final long serialVersionUID = 1L;
    private boolean selected;
    private int x;
    private int y;
    private int r;
    private Color borderColor;
    private Color areaColor;
    
    public Hexagon() {}
    
    public Hexagon(final int x, final int y, final int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
    
    public Hexagon(final int x, final int y, final int r, Color borderColor, Color areaColor) {
       this(x,y,r);
       this.borderColor=borderColor;
       this.areaColor=areaColor;
    }
    
    public Hexagon(final int x, final int y, final int r, boolean selected,Color borderColor, Color areaColor) {
    	this(x,y,r,borderColor,areaColor);
    	this.selected=selected;
    }
    public Hexagon(int x, int y, int radius, Color borderColor, Color areaColor, boolean selected) {
        this.x = x;
        this.y = y;
        this.r = radius;
        this.borderColor = borderColor;
        this.areaColor = areaColor;
        this.selected = selected;
    }
	public void paint(final Graphics g) {
		Polygon hexagon = createHexagon();
        g.setColor(areaColor);
        g.fillPolygon(hexagon);
        g.setColor(borderColor);
        g.drawPolygon(hexagon);
        if (selected) {
            drawSelectionRect(g, hexagon);
        }
    }
	
	public Polygon createHexagon() {
	    final int[] xPoints = new int[6];
	    final int[] yPoints = new int[6];
	    final Polygon plg = new Polygon();
	    for (int i = 0; i < 6; ++i) {
	        xPoints[i] = (int)(this.x + this.r * Math.cos(i * 2 * Math.PI / 6.0));
	        yPoints[i] = (int)(this.y + this.r * Math.sin(i * 2 * Math.PI / 6.0));
	        plg.addPoint(xPoints[i], yPoints[i]);
	    }
	    return plg;
	}

    
    private void drawSelectionRect(Graphics g, Polygon hexagon) {
        g.setColor(Color.BLUE);
        for (int i = 0; i < 6; i++) {
            g.drawRect(hexagon.xpoints[i] - 2, hexagon.ypoints[i] - 2, 5, 5);
        }
    }
    
    public boolean doesContain(int x, int y) {
        Polygon polygon = createHexagon();
        return polygon.contains(x, y);
    }
    
    public int getX() {
        return this.x;
    }
    
    public void setX(final int x) {
        this.x = x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setY(final int y) {
        this.y = y;
    }
    
    public int getR() {
        return this.r;
    }
    
    public void setR(final int r) {
        this.r = r;
    }
    
    public Color getBorderColor() {
        return this.borderColor;
    }
    
    public Color getAreaColor() {
        return this.areaColor;
    }
    
    public void setBorderColor(final Color borderColor) {
        this.borderColor = borderColor;
    }
    
    public void setAreaColor(final Color areaColor) {
        this.areaColor = areaColor;
    }
    
    public boolean isSelected() {
        return this.selected;
    }
    
    public void setSelected(final boolean selected) {
        this.selected = selected;
    }
     
}