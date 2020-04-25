
public abstract class BaseShape implements ShapeTemplate {
	//Positional coordinates on the screen with (0, 0) at top left
    protected Position position = new Position();
    
    //Show/hide?
    private boolean isVisible = true;
    //Filled?
    private boolean filled;
    
    public boolean getVisible() {
    	return isVisible;
    }
    
    public void show() {
    	isVisible = true;
    }
    
    public void hide() {
    	isVisible = false;
    }
    
  //What character are we using to render?
    private char fillChar = 'a';
    
    public char getFillChar() {
        return fillChar;
    }
    public void setFillChar(char newChar) {
        fillChar = newChar;
        regenStringTable();
    }

    public boolean getFilled() {
        return filled;
    }
    public void setFilled(boolean isFilled) {
        filled = isFilled;
        regenStringTable();
    }
    
    //charTable and StringTable for rendering
    protected char[][] charTableInternal;
    protected String[] stringTableInternal;
    
    
    //CharTable basic stuff
    public char[][] getCharTable() {
        return charTableInternal;
    }
    
    public String[] getStringTable() {
        return stringTableInternal;
    }
    
    /*
     * GetPosition
    */

    public Position getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        position.x = x;
        position.y = y;
    }
    
    public abstract String[] regenStringTable();
    public abstract char[][] regenCharTable();
    public abstract String toString();
	
}
