package main;

import java.util.*;
import java.lang.reflect.*;


public class GameEngine { //#9 below, class design with methods, constructors, etc.
    //ArrayList of generic Objects so that user can pass in any type of shape
    private ArrayList<BaseShape> shapes = new ArrayList<BaseShape>(); //#11 - class composition - generic arrayList of any type (shapes)
    //Also #13^
    //#23 - Polymorphism (casting classes to BaseShape)
    
    //Console/screen width and height
    private int width; //#3 - int
    private int height;

    //Internal screen render buffer
    private String[] renderBuffer;

    //Use optimized rendering - faster, but clips incorrectly
    boolean useOptimizedRendering = false;

    //Debug mode
    boolean debugMode = false; //#3 - boolean
    
    /*
     * CONSTRUCTOR
     */

    public GameEngine(BaseShape ...shapePassIn) { //Spread operator, #23 polymorphism
        //Setup width & height
        width = 100;
        height = 25;

        if (debugMode) {
            System.out.println("Number of shapes: "+shapePassIn.length);
        }

        for (BaseShape s : shapePassIn) { //Add shapes into object array
            shapes.add(s); //#4 - for-each loop
        }
        clearTerminal(); //Fix for BlueJ putting a random space in the beginning of every line
    }

    public void setDisplaySize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /*
     * GETTERS/SETTERS
     */
    
    public BaseShape getShapeIdx(int index) {
        return shapes.get(index);
    }
    
    /*
     * RENDERING/BUFFERING
     */

    public void constructRenderBuffer() { //#10c
        renderBuffer = new String[height];
        
        String blankRow = "";
        for (int j=0; j<width; j++) {
            blankRow+=" ";
        }

        for (int i=0; i<height; i++) {
            renderBuffer[i] = blankRow;
        }
    }
    
    //#10c
    public void constructRenderBuffer(int overriddenHeight, int overriddenWidth) { //If you want a larger render buffer than is normally allotted
        renderBuffer = new String[overriddenHeight];
        
        String blankRow = "";
        for (int j=0; j<overriddenWidth; j++) {
            blankRow+=" ";
        }

        for (int i=0; i<overriddenHeight; i++) {
            renderBuffer[i] = blankRow;
        }
    }

    public String retreiveRenderBuffer() { //#10a
        String output = "";
        for (String s : renderBuffer) {
            output+=s+"\n";
        }
        return output;
    }

    public String render() {
        constructRenderBuffer();

        for (int i=0; i<shapes.size(); i++) { //#4 - for loop
            if (debugMode) {
                System.out.println("Rendering: "+i);
            }
            
            BaseShape shapeIdx = getShapeIdx(i);
            
            if (shapeIdx.getVisible()) { //Make sure it's visible; otherwise don't display it
	            String[] shapeMesh = shapeIdx.getStringTable();
	            Position shapePos = shapeIdx.getPosition();
	            if (debugMode == true) { //#1 - logical operator
	                System.out.println(shapePos);
	            }
	
	            for (int j=0; j<shapeMesh.length; j++) { //for every line (row) of mesh
	                if (useOptimizedRendering == true) { //Optimized rendering is far faster, but incorrectly clips shape's boundaries
	                    int yWithOff = j+shapePos.y; //^#1, logical operator
	                    int xWithOff = shapePos.x;
	                    int sWidth = shapeMesh[j].length();
	                    if (debugMode) {
	                        System.out.println("Width: "+sWidth);
	                    }
	
	                    try {
	                        String beforeShapeInsertion = renderBuffer[yWithOff].substring(0,xWithOff); //#7 - string method
	                        String shapeInsertion = shapeMesh[j];
	                        String afterShapeInsertion = renderBuffer[yWithOff].substring(xWithOff+sWidth);
	
	                        renderBuffer[yWithOff] = beforeShapeInsertion+shapeInsertion+afterShapeInsertion;
	                    } catch (StringIndexOutOfBoundsException e) {
	                        System.out.println("Error rendering shape at index: "+i+" because it's too thicc");
	                    }
	                } else {
	                    int yWithOff = j+shapePos.y;
	                    int xWithOff = shapePos.x;
	                    int sWidth = shapeMesh[j].length();
	
	                    try {
	                        String beforeShapeInsertion = renderBuffer[yWithOff].substring(0,xWithOff);
	                        String afterShapeInsertion = renderBuffer[yWithOff].substring(xWithOff+sWidth);
	
	                        String whereShapeWas = renderBuffer[yWithOff].substring(xWithOff, xWithOff+sWidth);
	                        String whatShapeIs = shapeMesh[j];
	
	                        String shapeInsertion = "";
	
	                        for (int z = 0; z<shapeMesh[j].length(); z++) { //#18 - EC because of triple nested for loop
	                            String shapeChar = whatShapeIs.substring(z, z+1);
	                            String beforeChar = whereShapeWas.substring(z, z+1);
	                            if (shapeChar.equals(" ")) { //Only copy if it's not blank
	                            	//#7 - string method^
	                            	shapeInsertion+=beforeChar;
	                            } else {
	                                shapeInsertion+=shapeChar;
	                            }
	                        }
	                        renderBuffer[yWithOff] = beforeShapeInsertion+shapeInsertion+afterShapeInsertion;
	                    } catch (StringIndexOutOfBoundsException e) {
	                        System.out.println("Error rendering shape at index: "+i+" because it's too thicc");
	                    }
	                }
	            }
            }
        }
        return retreiveRenderBuffer();
    }

    /*
     * CLEAR TERMINAL
    */

    public void clearTerminal() {
        //System.out.print('\u000C');
    	for (int i=0; i<height; i++) { //thanks eclipse for not allowing me to use a proper clear function smh my head
    		System.out.println();
    	}
    }
    
    /*
     * TOSTRING
     */
    
    public String toString() {
        String output = "";
        for (Object s : shapes) {
            output+= s+"\n";
        }
        return output;
    }

}