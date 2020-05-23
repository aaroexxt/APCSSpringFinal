//#22 - interface

//Interface that governs basic shape type
public interface ShapeTemplate { #
	//Show/hide when rendering
	public void show();
	public void hide();
	public boolean getVisible();
	
	//Storage/retreival of shape mesh
	public char[][] regenCharTable();
	public char[][] getCharTable();
	public String[] getStringTable();
	
	//Internal storage of current rendered position
	public Position getPosition();
	public void setPosition(int x, int y);
	
	//Width/height accessor properties
	public int getWidth();
	public int getHeight();
	
	//Fill
	public char getFillChar();
	public void setFillChar(char newChar);
	public boolean getFilled();
	public void setFilled(boolean isFilled);
	
	//ToString
	public String toString();
}
