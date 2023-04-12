package main;                                   // folder
import java.io.PrintStream;                     //imported for printing files
import java.util.Arrays;                        //imported for use of arrays
import java.io.File;                            //imported for files
import java.io.FileNotFoundException;
import java.util.Random;                        //imported for random utils
import java.util.Scanner;                       //import scanner for input

public class wordle {
    public static String secret = secret();     //setting secret as a static global var
    public static String stringGuess = "";      //string guess needs to be global ~ history
    public static String stringHistory = "";    //string history ~ for history
    public static String guess = "";            //guess for

    public static <secretArray> void main(String[] args) {
        game();                                //do this so I can loop game in while
    }
    public static void game(){
        try {
            PrintStream file = new PrintStream("History.txt");      //setting up print stream invirement
            Scanner keyboard = new Scanner(System.in);                      //Scanner
            boolean run = true;                                             // var setup to allow for reset/stop of game
            while (run) {                                                   // boolean while loop
                System.out.print(">");                                      // for the effect
                guess = keyboard.nextLine();                                // reading global varable
                file.println(stringGuess + " : " + stringHistory);          // writes strings into files
                if (guess.equals("g")) {                                    //a check to see if the input is something else such as the guess/history
                    history();                                              //calls history as asked
                    message("Secret: " + secret);                        //print out the secret
                    break;                                                  //break to end code
                }
                if (guess.equals("s")) {                                    //ask for secret
                    message("Secret: " + secret);                        //print out secret
                    continue;                                               //loop to top
                }
                if (guess.equals("h")) {                                    //prints out history
                    history();
                    continue;
                }
                if (guess.length() == secret.length()) {                    //length check to see if they're the same and if so check.
                    solve(secret, guess);                                   //solve void function to reset global variables
                    boolean result = Arrays.equals(secret.toCharArray(), guess.toCharArray()); // boolean that returns if the arrays are the same if so then you win
                    if (result) {                                           //boolean if statement to return histoy and start another game
                        history();                                          //calls history
                        message("You got it!");                         //print they got it
                        message("Another Game(yes/no)?");               //print another game (y/n?)
                        String game = keyboard.nextLine().toLowerCase().substring(0,1); //scanner make sure they do yes and no and it makes it y or n
                        switch (game) {                                     //basic switch statement instead of if
                            case "y":                                       //case boolean = y or n
                                secret = secret();                                   //have to recall secret to reset it
                                game();                                     //call game
                                continue;                                   //starting over
                            case "n":
                                message("Bye!");
                                run = false;                                //break out of while loop
                        }
                    }
                }
                else {message("Invalid Input.");continue;}              //starting over
            }
        }
        catch (Exception error){message("error");error.printStackTrace();}//for errors in reading
    }
    public static void message(String m) {//my message function
        System.out.println(m);
    }
    public static String[] read(int len) {//read function
        String[] array = new String[0];//array for containing words
        String n[] = new String[0]; //array for adding to the array
        try { //try catch for reading
            File words = new File("allWords.txt"); // large word file read 3-10 words ~ worddata.txt
            Scanner wf = new Scanner(words); //word file
            while (wf.hasNextLine()) { //reading each line of the wordfile
                String word = wf.nextLine(); //assining a varable to a word
                if (word.length() == len) { //getting appropriate len of word
                    n = Arrays.copyOf(array, array.length + 1); //adding to copy of array with a new one
                    n[array.length] = word; //adding word into array at postion of the end of the old array
                }
                array = n; // setting array to its right full array
            }
            wf.close(); //close file
        }
        catch (FileNotFoundException error) {message("An error occurred");error.printStackTrace();}//for errors
        return array;
    }
    public static void history() throws Exception { //throws exeption for error
        message("History");
        int i = 0; //needed for counter
        Scanner keyboard = new Scanner(new File("history.txt")); //scanning history in
        while (keyboard.hasNext()) {
            String str = keyboard.nextLine();
            if(str.length() > 2){ //makes sure to ignore the first random array thats empty
                System.out.println(i + ": " + str); //to add to count
            }
            i++;
        }
        keyboard.close();
    }
    public static String secret() { //my secret function
        Random rand = new Random();//new random
        Scanner keyboard = new Scanner(System.in); //scanner to receive input
        message("#########################################################\n" + "# Let’s play Wordle.                                    #\n" + "# Your goal is to guess a secret word.                  #\n" + "# The word may have duplicated letters.                 #\n" + "# For each guess, you receive a feedback.               #\n" + "# ’H’ for hits, ’m’ for misss, and ’-’ for others.      #\n" + "# Your commands are as follows                          #\n" + "#   s for showing the secret,                           #\n" + "#   h for show the history, and                         #\n" + "#   g for giving up and terminating the present puzzle. #\n" + "#########################################################");
        System.out.print("Choose the length for the secret:");
        int choice = keyboard.nextInt(); // read input int
        String n[] = read(choice); //getting array from read function
        int result = rand.nextInt(n.length - 1 - 0) + 0;                    // random from high to low
        String sec = n[result];
        return sec;
    }
    public static void solve(String sec, String gus) { //string secret string guesses
        int len = sec.length(); //len = length of secret
        char[] sCA = sec.toCharArray();//Guess character array
        char[] gCA = gus.toCharArray();//Secret character array
        char[] rCA = new char[sec.length()];  //same length array
        char[] HistoryofguessArray[] = {gCA};//histry array duplicate
        char[] HistoryofoutcomeArray[] = {rCA};//histry array duplicate
        for (int i = 0; i < len; i++) {
            rCA[i] = '-'; //fill in the blans I couldnt get arrays.fill(ar,num) to work
        }
        for (int i = 0; i < len; i++) {
            if (gCA[i] == sCA[i])//if the two letters are equal
                rCA[i] = 'H';//the output will be a match
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (rCA[i] == 'H') //otherwise it will be a match / hit
                    continue;//loop to top becuase hit and needs to re look for var in the next spot
                if (gCA[i] == sCA[j] && rCA[j] != 'H') {//then if its not a match but its in the array then find the location and set it as a miss
                    rCA[i] = 'm';//set miss
                }
            }
        }
        for (char[] array : HistoryofguessArray) {// for loop similar to a python loop and it concatonates the array into a string then calls a global varabile
            stringGuess = (Arrays.toString(array));
            System.out.println(rCA);
        }
        for (char[] array2 : HistoryofoutcomeArray) {// for loop similar to a python loop and it concatonates the array into a string then calls a global varabile
            stringHistory = (Arrays.toString(array2));
        }
    }
}