package lab06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class AnagramUtil {

    // Reads words from a file (assumed to contain one word per line)
    // Returns the words as an array of strings.
    public static SortedString[] readFile(String filename)
    {
        ArrayList<SortedString> results = new ArrayList<SortedString>();
        try
        {
            BufferedReader input = new BufferedReader(new FileReader(filename));
            while(input.ready())
            {
                results.add(new SortedString(input.readLine()));
            }
            input.close();
        }
        catch(Exception e)
        {e.printStackTrace();}
        SortedString[] retval = new SortedString[1];
        return results.toArray(retval);
    }

    public static String[] getLargestAnagramGroup(String filename){
        SortedString[] words = readFile(filename);
        String[] toReturn = getLargestAnagramGroup(words);
        return toReturn;
    }

    public static String[] getLargestAnagramGroup(SortedString[] stringList){

        /* Initialize a sorting algorithm of type SortedString using either
         MergeSort or InsertionSort */
    	
    
    	InsertionSort<SortedString> inss = new InsertionSort<SortedString>();
    	//MergeSort<SortedString> inss = new MergeSort<SortedString>();
    
    	
        /* sort the input string list */
    	 SortedString[] sortedStringList = inss.sort(stringList);
        /* The case where stringList is of size 1 or less */
    	 String[] temp = new String[0]; 
    	 if(sortedStringList[0]==null) {
    		 return temp;
    	 }
    	 if(sortedStringList.length<=1) {
    		 temp = new String[1]; 
    		 temp[0] = stringList[0].getUnsorted();
    		 return temp;
    	 }
        /* The variables for the logic following */
        int end = 0, length = 1, i = 0, maxLength = 0;
        for (i = 0; i< sortedStringList.length-1; i++) {
        	if(sortedStringList[i].compareTo(sortedStringList[i+1])==0) {
        		length++;
        	}
        	else {
            	if(maxLength < length) {
            	end = i;
            	maxLength = length;
            }
            length = 1;
            }
        }
        if(maxLength < length) {
        	end = i;
        	maxLength = length;
        }


        /* Prepare to return. The following is almost the answer except
           for one thing...
        */
        String[] toReturn = new String[maxLength];
        for (int j = 0; j < maxLength; j++)
            toReturn[j] = stringList[j+end-maxLength+1].getUnsorted();

        return toReturn; // return the right thing
    }

    public static boolean areAnagrams(SortedString str1, SortedString str2){
       return str1.compareTo(str2)==0;
    }

}
