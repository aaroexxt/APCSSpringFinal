package main;

import java.util.Scanner;

public class GameEngineTest {
	private static double allRowTime = 0;
	public static void insertionSort(double array[]) {   
        for (int i = 1; i < array.length; i++) {  
            double elem = array[i];  
            int prev = i-1;  
            while ((prev > -1) && (array[prev] > elem)) {  
                array[prev+1] = array[prev]; //shift elem
                prev--;
            }  
            array[prev+1] = elem;  
        }  
    } 
    public static void main(String[] args) throws Exception {
        /*
         * DEMO 1: AMERICAN FLAG
         * This demo shows how compositing multiple shapes into a single GameEngine object can result in complex or cool graphics (or you could use a BitMap)
        */
        Rectangle flagBackground = new Rectangle();
        flagBackground.setFillChar('-');
        Square stars = new Square(0, 0, 5, true);
        stars.setFillChar('o');

        Rectangle flagPart1 = new Rectangle(0, 0, 20, 1, true);
        Rectangle flagPart2 = new Rectangle(0, 2, 20, 1, true);
        Rectangle flagPart3 = new Rectangle(0, 4, 20, 1, true);
        Rectangle flagPart4 = new Rectangle(0, 6, 20, 1, true);
        Rectangle flagPart5 = new Rectangle(0, 8, 20, 1, true);
        
        //Construct a GameEngine
        GameEngine americanFlag = new GameEngine(flagBackground, flagPart1, flagPart2, flagPart3, flagPart4, flagPart5, stars);
        americanFlag.setDisplaySize(50, 25);
        RenderHandler RHFlag = new RenderHandler(americanFlag);
        RHFlag.setFPS(3); //Only render it once
        
        /*
         * DEMO 2: RECTANGLE SCROLLING
         * This demo shows how powerful the tools are in GameEngine to control objects on a frame-by-frame basis
        */

        Rectangle rect = new Rectangle();
        GameEngine scrollTest = new GameEngine(rect);
        scrollTest.setDisplaySize(70,25);
        RenderHandler RHScroll = new RenderHandler(scrollTest);
        
        /*
         * DEMO 3: SIMPLE ANIMATIONS
         */
        Scanner in = new Scanner(System.in); //#17
        double[] rowStrokeLengths = new double[10];
        allRowTime = 0;
        for (int i=0; i<10; i++) {
        	System.out.println("Input row stroke time in s for stroke "+i);
        	double len = in.nextDouble();
        	rowStrokeLengths[i] = len;
        	allRowTime+=len;
        }
        
        System.out.println("Total row stroke time: "+allRowTime);
        insertionSort(rowStrokeLengths); //sort rowStrokeLenghts w/insertion sort #24
        
        BitMap canoe = new BitMap();
        String[] canoeF1 = {
        		"       o,    o__        ",
        		"      </     [\\\\/       ",
        		"   (`-/-------/----')   ",
        		"     @       @          "
        };
        String[] canoeF2 = {
        		"      o_/|   o_.",
        		"       [\\\\_|   [\\\\_\\\\",
        		"   (`----|-------\\\\-')",
        		"         @        @"
        };
        canoe.setStringTable(canoeF1);
        
        
        Rectangle water = new Rectangle(0, 0,30,1,true);
        water.setFillChar('~');
        
        
        //Shape input from user
        System.out.println("Input X position:");
        double userX = in.nextDouble(); //#3 - double
        userX *= Math.pow(Math.random()*100, 0); //Oh yeahhhhh
        System.out.println("Input Y position:");
        double userY = in.nextDouble();
        userY *= Math.pow(Math.random()*100, 0); //Oh yeahhhhhhhhhhh
        //#6 - calls to Math.pow and Math.random
        if (userX > 5 || userY > 25) { // sanity check, #1 - relational operator
        	throw new Exception(); //#2, if then else #1
        } else {
        	canoe.setPosition((int)userX, (int)userY); //#8 - casting
        	water.setPosition((int)userX,(int)userY+4);
        }
        
        GameEngine canoeTest = new GameEngine(water, canoe);
        canoeTest.setDisplaySize(70, 25);
        RenderHandler RHCanoe = new RenderHandler(canoeTest);
        RHCanoe.setFPS(15);
        
        /*
         * DEMO 4: SIMPLE SCENE ANIMATION
         */
//        BitMap player = new BitMap();
//        String 
//        BitMap background = new BitMap();
//        String[] hoopAndCourt = {
//        		"         -----------",
//        		"         |         |",
//        		"         |   ----  |",
//        		"         |   |  |  |",
//        		"         |   ----  |",
//        		"         ----   ----",
//        		"             |  |",
//        		"             |  |",
//        		"             |  |",
//        		"             |  |",
//        		"             |  |",
//        		"----------------------------"
//        };
//        background.setStringTable(hoopAndCourt);
//        GameEngine sceneTest = new GameEngine(background, player);
        /*
         * DEMO 5: TEXT
         */
        
        TextGenerator tg = new TextGenerator();
        BitMap text1 = new BitMap();
        text1.setStringTable(tg.generateASCII("text test"));
        
        System.out.println("Input seconds to render text (recommended 7, in seconds):");
        int renderTime = in.nextInt();
        
        BitMap text2 = new BitMap();
        text2.setStringTable(tg.generateASCII("APCS Fall Final"));
        text2.setPosition(0, 6);
        
        BitMap textFrameNumber = new BitMap();
        textFrameNumber.setStringTable(tg.generateASCII("Frame 0"));
        textFrameNumber.setPosition(0, 12);
        
        GameEngine textTest = new GameEngine(text1, text2, textFrameNumber);
        textTest.setDisplaySize(200, 25);
        RenderHandler RHText = new RenderHandler(textTest);
        RHText.setFPS(10);
        
        /*
         * DEMO 6: COMPLEX SCENE ANIMATION
         */
        
        //Check GameEngineTest2 for this^
        
        /*
         * SETUP DEMO LINKING
         */
        
        //Setup onEnd events so that demo runs one after another
        class OnEndEvents { //Class to store onEnd events
        	public Void EndFlag() {
        		System.out.println("RenderFlag ended");
    			RHScroll.renderFor(5000);
    			return null;
        	}
        	public Void EndScroll() {
        		System.out.println("RenderScroll ended");
    			RHCanoe.renderFor((int)Math.ceil(allRowTime)*1000); //Render for allRowTime*1000 because ms, should be shortest to fastest row
    			return null;
        	}
        	public Void EndCanoe() {
        		System.out.println("RenderCanoe ended");
    			RHText.renderFor(renderTime*1000);
    			return null;
        	}
        	public Void EndText() {
        		System.out.println("Demo finished");
				return null;
        	}
        }
        OnEndEvents endEvents = new OnEndEvents(); //instantiate
        RHFlag.setOnEnd(endEvents::EndFlag); //pass using lambda
        RHScroll.setOnEnd(endEvents::EndScroll);
        RHCanoe.setOnEnd(endEvents::EndCanoe);
        RHText.setOnEnd(endEvents::EndText);
        
        class OnFrameEvents { //Class to store onFrame events
        	public Void FrameScroll(int fCount) {
    			Position rectPos = rect.getPosition();
    			System.out.println("FrameCount:"+fCount);
        		while (fCount > 20) {
        			fCount-=20;
        		}
    			if (fCount <= 10) { //#2 - if then else
        			rect.setPosition(rectPos.x+1, rectPos.y+1);
        		} else {
        			rect.setPosition(rectPos.x-1, rectPos.y-1);
        		}
    			return null;
        	}
        	public Void FrameCanoe(int fCount) {
        		double actualTimePassed = fCount/15; //15 fps rendering, so seconds passed is fCount/15
        		double timePassed = 0;
        		for (int i=0; i<rowStrokeLengths.length; i++) {
        			timePassed+=rowStrokeLengths[i];
        			if (timePassed > actualTimePassed) {
        				canoe.setStringTable((i % 2 == 0) ? canoeF1: canoeF2); //set string table to alternate based on current iteration
        				break;
        			}
        			
        		}
        		
        		Position canoePos = canoe.getPosition();
        		canoe.setPosition((canoePos.x > 35) ? 0 : canoePos.x+1, canoePos.y);
        		return null;
        	}
        	public Void FrameText(int fCount) {
        		textFrameNumber.setStringTable(tg.generateASCII("Frame "+Integer.toString(fCount)));
        		
        		//Switch statement
        		switch(fCount) { //#5 - switch statement
	        		case 5:
	        	        text1.setStringTable(tg.generateASCII("h"));
	        	        break;
	        		case 10:
	        	        text1.setStringTable(tg.generateASCII("hi"));
	        	        break;
	        		case 15:
	        	        text1.setStringTable(tg.generateASCII("hi t"));
	        	        break;
	        		case 20:
	        	        text1.setStringTable(tg.generateASCII("hi th"));
	        	        break;
	        		case 25:
	        	        text1.setStringTable(tg.generateASCII("hi the"));
	        	        break;
	        		case 30:
	        	        text1.setStringTable(tg.generateASCII("hi ther"));
	        	        break;
	        		case 35:
	        	        text1.setStringTable(tg.generateASCII("hi there"));
	        	        break;
	        		case 99:
	        			text1.setStringTable(tg.generateASCII("epstein didn't"));
	        			text2.setStringTable(tg.generateASCII("kill himself"));
        		}
        		return null;
        	}
        }
        OnFrameEvents frameEvents = new OnFrameEvents();
        RHScroll.setOnFrame(frameEvents::FrameScroll);
        RHCanoe.setOnFrame(frameEvents::FrameCanoe);
        RHText.setOnFrame(frameEvents::FrameText);
        
        //Start first demo
        RHFlag.renderFor(2000);
        //RHCanoe.renderFor(5000);
        

    }
}