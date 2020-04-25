public class Square extends BaseShape {
    //Basic parameters; height and length as well as what character to fill with
    private int sideLength;

    /*
    * CONSTRUCTORS
    */
    public Square() {
        sideLength = 10;
        setFilled(true);
        show();

        //charTableInternal = new char[height][width]; //Not needed right now because of no collision detection
        //regenCharTable(); //Not needed right now because of no collision detection

        stringTableInternal = new String[sideLength];
        regenStringTable();
    }

    public Square(int x, int y, int sideLength, boolean filled) {
        this.sideLength = sideLength;
        setFilled(filled);
        show();

        //charTableInternal = new char[height][width]; //Not needed right now because of no collision detection
        //regenCharTable(); //Not needed right now because of no collision detection

        stringTableInternal = new String[sideLength];
        regenStringTable();

        setPosition(x,y); //update position onscreen
    }

    /*
    * Getters/Setters
    */

    public int getSideLength() {
        return sideLength;
    }
    public void setSideLength(int newSL) {
        sideLength = newSL;
        regenStringTable();
    }
    
    public int getWidth() {
    	return getSideLength();
    }
    
    public void setWidth(int newSL ) {
    	setSideLength(newSL);
    }
    
    public int getHeight() {
    	return getSideLength();
    }
    
    public void setHeight(int newSL) {
    	setSideLength(newSL);
    }
    
    

    /*
    * StringTable/CharTable implementation (mesh geometry)
    */

    public char[][] regenCharTable() {
        char[][] charTable = new char[sideLength][sideLength];
        char fillChar = getFillChar();
        for (int i=0; i<sideLength; i++) {
            for (int j=0; j<sideLength; j++) {
                if (getFilled()) {
                    charTable[i][j] = fillChar;
                } else {
                    if ((i == 0 || i == sideLength) && (j == 0 || j == sideLength)) {
                        charTable[i][j] = fillChar;
                    } else {
                        charTable[i][j] = ' '; //otherwise fill with space
                    }
                }
            }
        }
        charTableInternal = charTable; //set internal char table to what we just generated
        return charTable; //and return
    }

    public String[] regenStringTable() {
        String[] stringTable = new String[sideLength];
        for (int i=0; i<sideLength; i++) {
            stringTable[i] = "";
            for (int j=0; j<sideLength*2; j++) {
                if (filled) {
                    stringTable[i] += fillChar;
                } else {
                    if ((i == 0 || i == sideLength) && (j == 0 || j == sideLength)) {
                        stringTable[i] += fillChar;
                    } else {
                        stringTable[i] += " "; //otherwise fill with space
                    }
                }
            }
        }
        stringTableInternal = stringTable;
        return stringTable;
    }
    
    /*
     * TOSTRING
     */
    public String toString() {
        return "Type: Square, sideLength: "+sideLength+", fillChar: "+fillChar+", isFilled: "+filled;
    }
}