package searchengine;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


class WordScrap {

    // variables to keep path to file and list of words
    private String resourceTextFilesDirectory = "src/resources/textFiles/";
    private String AllWordsFilePath = "src/resources/dict/EnglishWords.txt";

    // constructor
    public WordScrap() {
    }

    // constructor with two parameters
    public WordScrap(String resourceTextFilesDirectory, String AllWordsFilePath) {
        this.resourceTextFilesDirectory = resourceTextFilesDirectory;
        this.AllWordsFilePath = AllWordsFilePath;
    }

    // a function that takes file path as an argument and returns a list of words in that text file
    public static List<String> getWords(String filePath) throws IOException {
        List<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineWords = line.split(" ");
                for (String word : lineWords) {
                    // if word lower case is not in the list and word length is greater than 1
                    if (!words.contains(word.toLowerCase()) && word.length() > 1) {
                        // skip word that contain non-alphabetic characters
                        if (word.matches("[a-zA-Z]+")) {
                            words.add(word.toLowerCase());
                        }
                    }
                }
            }
        }
        return words;
    }

    // driver function
    public static void scrap(String[] args) throws IOException {

        // create an object of own class
        WordScrap wS = new WordScrap();

        // get list of all files in the directory
        String[] files = new java.io.File(wS.resourceTextFilesDirectory).list();

        // get list of all words in all files
        List<String> words = new ArrayList<>();
        for (String file : files) {
            words.addAll(getWords(wS.resourceTextFilesDirectory + file));
        }

        // if file exist at AllWordsFilePath path then read all words from AllWordsFilePath file and append to the list of words
        if (new java.io.File(wS.AllWordsFilePath).exists()) {
            words.addAll(getWords(wS.AllWordsFilePath));
        }

        // eliminate duplicates
        List<String> uniqueWords = new ArrayList<>();
        for (String word : words) {
            if (!uniqueWords.contains(word)) {
                uniqueWords.add(word);
            }
        }

        // sort the list of words
        Collections.sort(uniqueWords);

        // save the list of words to a file with each word on a new line
        java.io.PrintWriter writer = new java.io.PrintWriter(wS.AllWordsFilePath, "UTF-8"); 
        for (String word : uniqueWords) {
            writer.println(word);
        }
        writer.close();

        // print the number of words to the console
        System.out.println("Number of words: " + uniqueWords.size());

    }

    public static void main(String[] args) throws IOException {
        scrap(args);
    }

}