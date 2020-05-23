package main;

public class Rectangle extends BaseShape {
    //Basic parameters; height width and length
    private int width;
    private int height;

    /*
    * CONSTRUCTORS
    */
    public Rectangle() {
        width = 40;
        height = 10;
        setFilled(true);
        show();

        //charTableInternal = new char[height][width]; //Not needed right now because of no collision detection
        //regenCharTable(); //Not needed right now because of no collision detection

        stringTableInternal = new String[height];
        regenStringTable();
    }

    public Rectangle(int x, int y, int width, int height, boolean filled) {
        this.width = width*2; //because height is x2 in java, multiply width by 2
        this.height = height;
        setFilled(filled);
        show();

        //charTableInternal = new char[height][width]; //Not needed right now because of no collision detection
        //regenCharTable(); //Not needed right now because of no collision detection

        stringTableInternal = new String[height];
        regenStringTable();

        setPosition(x,y); //update position onscreen
    }

    /*
    * Getters/Setters
    */

    public int getWidth() { //#10a
        return width;
    }
    public void setWidth(int newWidth) { //#10b
        width = newWidth;
        regenStringTable();
    }

    public int getHeight() {
        return height;
    }
    public void setHeight(int newHeight) {
        height = newHeight;
        regenStringTable();
    }

    /*
    * StringTable/CharTable implementation (mesh geometry)
    */

    public char[][] regenCharTable() {
        char[][] charTable = new char[height][width];
        char fillChar = getFillChar();
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                if (getFilled()) {
                    charTable[i][j] = fillChar;
                } else {
                    if ((i == 0 || i == height) && (j == 0 || j ==width)) {
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

    public char[][] getCharTable() {
        return charTableInternal;
    }

    public String[] regenStringTable() {
        String[] stringTable = new String[height];
        char fillChar = getFillChar();
        for (int i=0; i<height; i++) {
            stringTable[i] = "";
            for (int j=0; j<width; j++) {
                if (getFilled()) {
                    stringTable[i] += fillChar;
                } else {
                    if ((i == 0 || i == height) && (j == 0 || j == width)) {
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

    public String[] getStringTable() {
        return stringTableInternal;
    }
    
    /*
     * TOSTRING
     */
    public String toString() {
        return "Type: Rectangle, width: "+width+", height: "+height+", fillChar: "+getFillChar()+", isFilled: "+getFilled()+", position: "+getPosition();
    }
}