package parsing;

import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.FileReader;
import java.util.ArrayList;

public class Fasta_Parser 
{
//	A static factory method that parses a Fasta file and returns a list of FastaSequence objects
	
	private static String currentLine; // used to traverse the sequence
	
	public static List<FastaSequence> readFastaFile(String filepath) throws Exception
	{
		List<FastaSequence> fastalist = new ArrayList<FastaSequence>(); // fastalist is a list of fasta sequence objects
		BufferedReader fastafile = new BufferedReader(new FileReader(filepath)); //fastafile is all of the info inside the file
		FastaSequence F1 = null; // initialize FastaSequence object
		while ((currentLine = fastafile.readLine()) != null) {
			char firstChar = currentLine.charAt(0);
			if(firstChar == '>') // if header
			{
				String header = currentLine.substring(1).trim(); //remove ">" from the header
				StringBuffer buff = new StringBuffer(); // need a string buffer for sequence
				F1 = new FastaSequence(header, buff); // give values to the new FastaSequence so it's not null
				fastalist.add(F1); // add the FastaSequence object to the fastalist
			}
			if(firstChar != '>')
			{
				F1.getBuffer().append(currentLine); // appends the sequence lines to the buffer object
			}
         }
		fastafile.close(); // close the file
		return fastalist; // return the list of fasta objects
		
	}
	
	//Part1 of Lab 6 ~*~*~*~
	
	//Static method that returns sorted map
	static <K,V extends Comparable<? super V>>
	SortedSet<Map.Entry<K,V>> sortByVal(Map<K,V> map) //SortedSet provides total ordering on its elements
	{
	    SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>> //TreeSet is a NavigableSet based on TreeMap; ordered by following Comparator 
	    (
	        new Comparator<Map.Entry<K,V>>()  //Implement new Comparator to compare values of the map
	        {
	            @Override 
	            public int compare(Map.Entry<K,V> element1, Map.Entry<K,V> element2) //Override to compare element in Map to other mapped elements  
	            {
	                int val = element1.getValue().compareTo(element2.getValue());
	                return val != 0 ? val : 1; //If elements are not equal; 0 else; 1
	            }
	        }
	    );
	    sortedEntries.addAll(map.entrySet()); //Add all sorted elements to sortedEntries
	    
	    try //Where we write the sorted Entries to a file
	    {
	    	PrintStream output = new PrintStream(new File("/Users/Smatlock/output.txt"));//Arbitrary file name
	    	System.setOut(output);
	    	output.print(sortedEntries);
	    }
	    catch(FileNotFoundException fx)
	    {
	    	System.out.println(fx);
	    }
	    
	    return sortedEntries;
	}
	

		public static void main(String[] args) throws Exception
		{
			List<FastaSequence> fastaList = readFastaFile("/Users/Smatlock/pf.fasta");
			Map<String, Integer> fastaMap = new TreeMap<String, Integer>();
			ArrayList<String> seqs = new ArrayList<String>();
			for( FastaSequence fs : fastaList)
			{
				seqs.add(fs.getSequence());
				
			}
			for(String s : seqs)
			{
				int occurrences = Collections.frequency(seqs, s);
				fastaMap.put(s, occurrences);
	
			}
			
			System.out.println(sortByVal(fastaMap) + "\n");
			
		}
}

