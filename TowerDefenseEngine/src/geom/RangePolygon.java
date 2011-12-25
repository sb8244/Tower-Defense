package geom;

import java.awt.Point;
import java.awt.Polygon;

/**
 * Implements the range of a turret by using a diamond shaped polygon
 * 
 * Adapter pattern
 * 
 * @author Stephen Bussey
 *
 */
public class RangePolygon
{
	private Polygon p;
	
	/**
	 * Creates a new RangePolygon
	 * @param center The center of the polygon	
	 * @param range The distance in pixels of the radius
	 */
	public RangePolygon(Point center, int range)
	{
		p = new Polygon();
		
		int x = (int) center.getX();
		int y = (int) center.getY();
		//creates a diamond
		p.addPoint(x+range+1, y);
		p.addPoint(x, y+range+1);
		p.addPoint(x-range, y);
		p.addPoint(x, y-range-1);
	}
	
	/**
	 * Adapter method to check if the polygon contains a point
	 * @param point
	 * @return True if the RangePolygon contains this point (Creep is in range)
	 */
	public boolean contains(Point point)
	{
		if(point == null)
			return false;
		return p.contains(point);
	}
}
