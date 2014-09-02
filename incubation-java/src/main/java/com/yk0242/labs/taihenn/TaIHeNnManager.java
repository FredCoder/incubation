package com.yk0242.labs.taihenn;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** TaIHeNnManager (THM) - class to manage TaIHeNn generation - 
 *   an array arr representing random configurations of 'た','い','へ', and 'ん'. 
 *   Advances arr and returns necessary parameters when called for. <br>
 *   <br>
 *   (NB - is customizable to a degree, but expected strings are fixed to <br>
 *     1) original REPSTR array (たいへん)<br>
 *     2) REPSTR array from middle looped once (へんたい) )
 *  
 */
public class TaIHeNnManager {
	/* constant string */
	private static final char[] REPSTR = {'た','い','へ','ん'};//replacement String
	/* internal vars */
	Random rng = new Random();//Random Number Generator
	private List<Integer> arr = new ArrayList<Integer>(); //list of Ints representing the output char String
	private int taihenCtr = 0; //keeps track of occurrence of たいへん string
	private int hentaiCtr = 0; //keeps track of occurrence of へんたい string
	
	/* ******************/
	/* Public Functions */
	/* ******************/
	/**
	 * advances THM string by one unit.
	 * @return this (THM) (for method chaining)
	 */
	public TaIHeNnManager advance(){
		int repStrLen = REPSTR.length;
		
		//*** increment arr by one
		int i = rng.nextInt(repStrLen);
		arr.add(i);
		
		//*** process taihenCtr
		//if ctr is full and advance is called, reset ctr
		if(taihenCtr == repStrLen) taihenCtr = 0;
		//if expected char appears, then ctr++; else reset ctr
		if(i == taihenCtr) taihenCtr++;
		else taihenCtr = 0;
		
		//*** process hentaiCtr
		//if ctr is full and advance is called, reset ctr
		if(hentaiCtr == repStrLen) hentaiCtr = 0;
		//if expected char appears, then ctr++; else reset ctr
		if(i == (hentaiCtr+repStrLen/2)%repStrLen ) hentaiCtr++;
		else hentaiCtr = 0;
		
		//return this to allow for method chaining
		return this;
	}
	
	/**
	 * gets length of current arr
	 * @return int length of current arr
	 */
	public int getArrLen(){
		return arr.size();
	}
	
	/**
	 * gets last char generated by THM
	 * @return char representing last generated int of this THM
	 */
	public char getLastChar(){
		return REPSTR[arr.get(arr.size()-1)];
	}
	
	/**
	 * @return boolean true if taihenCtr is full; false otherwise
	 */
	public boolean isTaihen(){
		return (taihenCtr == REPSTR.length);
	}
	
	/**
	 * @return boolean true if hentaiCtr is full; false otherwise
	 */
	public boolean isHentai(){
		return (hentaiCtr == REPSTR.length);
	}
	
	
	/* ***********************************************************************/
	/* Functions mainly for testing and debugging purposes (package private) */
	/* ***********************************************************************/
	/**
	 * gets String representing current arr
	 * @return String representing current arr
	 */
	String getStr(){
		StringBuilder sb = new StringBuilder();
		for(int ctr=0; ctr<arr.size(); ctr++){
			int i = arr.get(ctr);
			if (i > REPSTR.length) //JIC
				throw new ArrayIndexOutOfBoundsException("index > repStr.length at ctr "+ctr);
			sb.append(REPSTR[i]);
		}
		return sb.toString();
	}
	
}
