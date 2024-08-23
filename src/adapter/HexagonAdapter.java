package adapter;

import java.awt.Color;
import java.awt.Graphics;
import hexagon.Hexagon;
import geometry.SurfaceShape;

public class HexagonAdapter extends SurfaceShape{
	
	private static final long serialVersionUID = 1L;
	private Hexagon hexagon=new Hexagon(0,0,0);
	
	public HexagonAdapter(int x, int y, int r, Color color, Color innerColor) {
	    initializeHexagon(x, y, r, color, innerColor, false);
	}

	public HexagonAdapter(int x, int y, int r, boolean selected, Color color, Color innerColor) {
	    initializeHexagon(x, y, r, color, innerColor, selected);
	}
    //smanjenje dupliranja koda
	private void initializeHexagon(int x, int y, int r, Color color, Color innerColor, boolean selected) {
	    this.hexagon.setX(x);
	    this.hexagon.setY(y);
	    this.hexagon.setR(r);
	    this.hexagon.setSelected(selected);
	    this.hexagon.setBorderColor(color);
	    this.hexagon.setAreaColor(innerColor);
	}

	@Override
	public int compareTo(Object o) {
		if(o instanceof Hexagon) {
			return (int) (this.hexagon.getR() - ((Hexagon) o).getR());
		}
		return 0;
	}
	
	@Override
	public double area() {
		return (3 * Math.sqrt(3)*Math.pow(hexagon.getR(), 2)/2);
	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}

	@Override
	public void draw(Graphics g) {
		hexagon.paint(g);
	}

	public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}
	
	/*@Override
	public void moveTo(int x, int y) {
		Point center= new Point(hexagon.getX(), hexagon.getY());
		center.moveTo(x, y);
	}*/
	
	//test je vration netacnu vrednost ove metode
	@Override
	public void moveTo(int x, int y) {
	    hexagon.setX(x);
	    hexagon.setY(y);
	}

	/*@Override
	public void moveBy(int byX, int byY) {
		Point center= new Point(hexagon.getX(), hexagon.getY());
		center.moveBy(byX, byY);			
	}*/
	
	//test je vration netacnu vrednost ove metode
	@Override
	public void moveBy(int byX, int byY) {
	    hexagon.setX(hexagon.getX() + byX);
	    hexagon.setY(hexagon.getY() + byY);
	}

	/*@Override
	public String toString() {
		return "Hexagon:" + hexagon.getX() + "," + hexagon.getY() + "," + hexagon.getR() + "," + hexagon.getBorderColor().getRed()+"," + hexagon.getBorderColor().getGreen()+","+hexagon.getBorderColor().getBlue()  + "," + hexagon.getAreaColor().getRed() + "," + hexagon.getAreaColor().getGreen() + "," + hexagon.getAreaColor().getBlue()+","+isSelected();
	} */
	//vise manjih metoda
	@Override
	public String toString() {
	    return String.format("Hexagon:%d,%d,%d,%s,%s,%s", 
	        hexagon.getX(), 
	        hexagon.getY(), 
	        hexagon.getR(), 
	        formatColor(hexagon.getBorderColor()), 
	        formatColor(hexagon.getAreaColor()), 
	        isSelected());
	}

	private String formatColor(Color color) {
	    return String.format("%d,%d,%d", color.getRed(), color.getGreen(), color.getBlue());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof HexagonAdapter) {
			HexagonAdapter temp=(HexagonAdapter) obj;
			if (hexagon.getX() == temp.getHexagon().getX() && 
				hexagon.getY() == temp.getHexagon().getY() &&
				hexagon.getR() == temp.getHexagon().getR()){
				return true;
			}		 
		}
		return false;		
	}
    
    public boolean isSelected() {
    	return this.selected;
    }
    
    public void setSelected(final boolean selected) {
    	hexagon.setSelected(selected);
    	this.selected = selected;
    }
    
    public HexagonAdapter clone() {
		HexagonAdapter hexagonAdapter = new HexagonAdapter(-1,-1,-1,Color.black, Color.black);
		
		hexagonAdapter.getHexagon().setX(this.getHexagon().getX());
		hexagonAdapter.getHexagon().setY(this.getHexagon().getY());
		hexagonAdapter.getHexagon().setR(this.getHexagon().getR());
		hexagonAdapter.getHexagon().setBorderColor(this.getHexagon().getBorderColor());
		hexagonAdapter.getHexagon().setAreaColor(this.getHexagon().getAreaColor());
		
		return hexagonAdapter;
	}
	@Override
	public void fill(Graphics g) {
		// TODO Auto-generated method stub
	}
    

}
