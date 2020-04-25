package main;

public class BitMap extends BaseShape {
    //Basic parameters; height width
    private int width;
    private int height;

    /*
    * CONSTRUCTORS
    */
    public BitMap() {
        width = 40;
        height = 10;
        show();

        //charTableInternal = new char[height][width]; //Not needed right now because of no collision detection
        //regenCharTable(); //Not needed right now because of no collision detection

        stringTableInternal = new String[1];
        stringTableInternal[0] = "BitMap empty";
    }

    public BitMap(int x, int y, String[] BMP) {
    	this.height = BMP.length;
    	//Find max width
    	this.width = BMP[0].length(); //#14 - find max, #15 lol
    	for (int i=1; i<BMP.length; i++) {
    		int bmpRowLen = BMP[i].length();
    		if (bmpRowLen > this.width) {
    			this.width = bmpRowLen;
    		}
    	}
    	show();

        //charTableInternal = new char[height][width]; //Not needed right now because of no collision detection
        //regenCharTable(); //Not needed right now because of no collision detection

        stringTableInternal = BMP;

        setPosition(x,y); //update position onscreen
    }

    /*
    * Getters/Setters
    */

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /*
    * CharTable implementation (mesh geometry)
    */

    public char[][] regenCharTable() {
        char[][] charTable = new char[height][width];
        for (int i=0; i<stringTableInternal.length; i++) { //Essentially just make clone of StringTable
        	for (int j=0; j<stringTableInternal[i].length(); i++) {
        		charTable[i][j] = stringTableInternal[i].charAt(j); //Take a single char at a time (cast)
        	}
        }
        charTableInternal = charTable;
        return charTable; //and return
    }
    
    public void setStringTable(String[] BMP) {
    	this.height = BMP.length;
    	//Find max width
    	this.width = BMP[0].length();
    	for (int i=1; i<BMP.length; i++) {
    		int bmpRowLen = BMP[i].length();
    		if (bmpRowLen > this.width) {
    			this.width = bmpRowLen;
    		}
    	}
    	
        stringTableInternal = BMP;
    }
    
    /*
     * TOSTRING
     */
    public String toString() {
        return "Type: BitMap, width: "+width+", height: "+height+", position: "+getPosition();
    }
}