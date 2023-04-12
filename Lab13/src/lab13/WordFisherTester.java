package lab13;

public class WordFisherTester {
	
	public static void main(String[] args) {
		
		WordFisher alice = new WordFisher("texts/carroll-alice.txt", "stopwords.txt");
		System.out.println("Alice: \n"
				+ "word count: " + alice.getWordCount());
		alice.pruneVocabulary();
		System.out.println("word count prune: " + alice.getWordCount());
		System.out.println("Unique: "+alice.getNumUniqueWords());
		System.out.println("Frequencey test thus -1: "+ alice.getFrequency("thus"));
		WordFisher moby = new WordFisher("texts/moby-dick.txt", "stopwords.txt");
		System.out.println("TOP WORDS "+alice.getTopWords(10));
		System.out.println("Moby: \n"
				+ "word count: " + moby.getWordCount());
		moby.pruneVocabulary();
		System.out.println("word count prune: " + moby.getWordCount());
		System.out.println("Unique: " + moby.getNumUniqueWords());
		System.out.println("getFrequency 1226 test whale: "+ moby.getFrequency("whale"));
		System.out.println("getFrequency of handkerchief: " + moby.getFrequency("handkerchief"));
		System.out.println("top words: "+moby.getTopWords(10));
		System.out.println("common words(20): " + alice.commonPopularWords(20,moby));
	}
}
