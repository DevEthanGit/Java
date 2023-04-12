package lab13;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordFisher {

	
	public HashMap<String, Integer> vocabulary;
	public List<String> stopwords; 
	private String inputTextFile;
	private String stopwordsFile;

	WordFisher(String inputTextFile, String stopwordsFile) {
		this.inputTextFile = inputTextFile;
		this.stopwordsFile = stopwordsFile;

		buildVocabulary();
		getStopwords();
	}

	public void buildVocabulary() {
		vocabulary = new HashMap<String, Integer>();
		String reader;
		try {
			reader = new String(Files.readAllBytes(Paths.get(inputTextFile)));
			String[] allWords = reader.toLowerCase().replaceAll("[^a-zA-Z0-9 ]", "").split("\\s+");
			for (String word : allWords) {
			    if (vocabulary.containsKey(word)) {
			        int frequency = vocabulary.get(word);
			        frequency++;
			        vocabulary.put(word, frequency);
			    } else {
			    	vocabulary.put(word, 1);
			    }
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		} 
		
	}

	public void getStopwords() {
		stopwords = new ArrayList<String>();
		String word;

		try {
			BufferedReader input = new BufferedReader(new FileReader(stopwordsFile));
			while ((word = input.readLine()) != null) {
				stopwords.add(word);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getWordCount() {
		int totalWords = vocabulary.values().stream().reduce(0, Integer::sum);
		return totalWords;
	}

	public int getNumUniqueWords() {
		return vocabulary.size();
	}

	public int getFrequency(String word) {
		int frequencey = -1;
		if (vocabulary.containsKey(word)) {
		    frequencey = vocabulary.get(word);
		}
		return frequencey;
	}

	public void pruneVocabulary() {
		 for (String word : stopwords) {
			 	vocabulary.remove(word);
		    }
	}

	public ArrayList<String> getTopWords(int n) {
	    ArrayList<String> topWords = new ArrayList<String>();
	    PriorityQueue<WordNode> queue = new PriorityQueue<>(n, new Comparator<WordNode>() {
	        @Override
	        public int compare(WordNode w1, WordNode w2) {
	            return w2.frequency - w1.frequency;
	        }
	    });

	    
	    for (String word : vocabulary.keySet()) {
	        WordNode node = new WordNode(word, vocabulary.get(word));
	        queue.add(node);
	    }

	    
	    for (int i = 0; i < n; i++) {
	        if (!queue.isEmpty()) {
	            topWords.add(queue.poll().word);
	        }
	    }

	    return topWords;
	}

	
	private class WordNode {
	    String word;
	    int frequency;

	    WordNode(String word, int frequency) {
	        this.word = word;
	        this.frequency = frequency;
	    }
	}


	public ArrayList<String> commonPopularWords(int n, WordFisher other) {
	    
		ArrayList<String> popularWords1 = this.getTopWords(n);

		ArrayList<String> popularWords2 = other.getTopWords(n);

		ArrayList<String> commonWords = new ArrayList<>();
 
	    for (String word1 : popularWords1) {
	        
	        if (popularWords2.contains(word1)) {
	            
	            commonWords.add(word1);
	        }
	    }
	    return commonWords;
	}

}
