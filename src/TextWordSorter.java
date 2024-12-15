import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class TextWordSorter {

    ArrayList<WordCapsule> getWordsFreq(String filepath) {

        FileReaderAndWriter rw = new FileReaderAndWriter();
        StringBuilder sb = new StringBuilder().append(rw.readAllTextFromFile(filepath));

        String[] wordsStringArray = sb.toString()
                .replaceAll("[^\\p{L}\\p{N}'\\s-]", "") // Allow letters, numbers, apostrophes, spaces, and hyphens
                .replaceAll("[\\s]+", " ")             // Normalize all whitespace to single spaces
                .trim().toLowerCase()                                // Remove leading and trailing spaces and to lowercase
                .split(" ");                                // Split by spaces

        ArrayList<WordCapsule> words = new ArrayList<>();

        HashMap<String,Integer> wordsFreqMap = new HashMap<>();

        for (String w : wordsStringArray) {
           wordsFreqMap.put(w, wordsFreqMap.getOrDefault(w, 0) + 1);
        }
        //System.out.println(wordsFreqMap);//Uncomment if you want to se the unsorted hashmap

        // Convert map entries into WordCapsule objects and add to the list
        for (Map.Entry<String, Integer> entry : wordsFreqMap.entrySet()) {
            words.add(new WordCapsule(entry.getKey(), entry.getValue()));
        }

// Sort the list (optional, if needed by frequency)
        words.sort(new WordCapsule());
        return words;
    }
}


class WordCapsule implements Comparator<WordCapsule> {
    String word;
    int freq;

    public WordCapsule() {
    }

    public WordCapsule(String word, int freq) {
        this.word = word;
        this.freq = freq;
    }

    @Override
    public String toString() {
        return this.word + " " + this.freq;
    }

    @Override
    public int compare(WordCapsule o1, WordCapsule o2) {
        return o2.freq - o1.freq;
    }
}

class TextWordSorterSandBox {
    public static void main(String[] args) {
        // Start the timer
        long startTime = System.currentTimeMillis();

        File kolobok =new File("kolobok.txt");
        File ivasykTelesyk =new File("IvasykTelesyk.txt");
        File textToSort;

        //textToSort = kolobok;
        textToSort = ivasykTelesyk;


        TextWordSorter mySorter = new TextWordSorter();

        ArrayList<WordCapsule> wordsFreq = mySorter.getWordsFreq(textToSort.getPath());

        for (WordCapsule wc : wordsFreq) {
            System.out.println(wc);
        }
        // Stop the timer and calculate elapsed time
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        // Print the elapsed time
        System.out.println("--===||| Execution Time: " + elapsedTime + " ms |||===--");
    }
}
