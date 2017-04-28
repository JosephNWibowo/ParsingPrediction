/**
 * Created by Joey on 3/29/2017.
 */

import java.io.*;
import java.util.Scanner;



public class MainParsingPrediction {
    public static void main(String[] args) throws IOException
    {
        int branchesCount = 0;
        int forwardBranch = 0;
        int backwardBranch = 0;
        int takenForBranch = 0;
        int takenBackBranch = 0;
        int mispredictions;
        double mispredictionDec;

        /**
         * creating a file from the li1trace.txt and scanning it form the argument
         *
         * Note to self: below code is the same as
         * String path = args[0] // taking arguments as the path
         * File tempFile = new File(path);
         * Scanner txtFile = new Scanner(tempFile);
         */

        String path = args[0];
        Scanner txtFile = new Scanner(new File(path));


        /**
         * Scanning the file line by line and splitting it to put into an String array
         * if the 2nd element equals "2" then skip an iteration so it doesnt store to the array
         *
         * taking the 1st address and 2nd address as (hex to int)
         * and decide the direction forward or backward depending on the result of the 2 hex to int subtraction
         * tally for both
         * inside the forward tally, theres a tally for a misprediction since forward means "not taken"
         * same with backward tally, since backward means "taken"
         */
        while (txtFile.hasNext()) {
            String[] lineSplit = txtFile.nextLine().split(" ");
            branchesCount++;
            String hexA = lineSplit[0];
            String hexB = lineSplit[2];
            boolean direction = forwardOrBackward(hexA, hexB);
            if(direction && !lineSplit[1].equals("2"))
            {
                forwardBranch++;
                if(lineSplit[3].equals("1"))
                {
                    takenForBranch++;
                }
            }
            if(!direction && !lineSplit[1].equals("2"))
            {
                backwardBranch++;
                if(lineSplit[3].equals("1"))
                {
                    takenBackBranch++;
                }
            }

            //just to print the text trace file
            for(int i = 0; i < lineSplit.length ; i++) {
                System.out.print(lineSplit[i] + " ");
            }
            System.out.println();

            if(lineSplit[1].equals("2"))
            {
                branchesCount--;
            }
        }

        mispredictions = takenForBranch + (backwardBranch - takenBackBranch);
        mispredictionDec = (double)mispredictions / (double)branchesCount;

        System.out.println("Number of Branches = " + branchesCount);
        System.out.println("Number of forward branches = " + forwardBranch);
        System.out.println("Number of forward taken branches = " + takenForBranch);
        System.out.println("Number of backward branches = " + backwardBranch);
        System.out.println("Number of backward taken branches = " + takenBackBranch);
        System.out.println("Number of mispredictions = " + mispredictions + " (" + mispredictionDec + ")" );
    }

    public static boolean forwardOrBackward(String A, String B) {
        Integer hexAToInt = Integer.parseInt(A, 16);
        Integer hexBToInt = Integer.parseInt(B, 16);
        int temp = hexAToInt - hexBToInt;

        if (temp < 0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }
}
