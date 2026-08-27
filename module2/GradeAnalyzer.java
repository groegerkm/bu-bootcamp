import java.io.*; 
import java.util.ArrayList;
 
public class GradeAnalyzer {
 
    public static void main(String[] args) {
        // Step 1: read scores from file
        String filename = "C:\\Users\\groeg\\Desktop\\Bootcamp\\Module2\\scores.txt";
        ArrayList<Integer> scores = readScores(filename);

        // Step 2: calculate statistics
        double average = calculateAverage(scores);

        // Step 3: write and print report
        int HighestScore = findHighestScore(scores);
        int LowestScore = findLowestScore(scores);
        String outputfile = "C:\\Users\\groeg\\Desktop\\Bootcamp\\Module2\\report.txt";

        writeReport(scores, filename,
                                   average, HighestScore, LowestScore,
                                   outputfile);

    }
        // Returns a list of valid scores read from the file
   public static ArrayList<Integer> readScores(String filename) {
        ArrayList<Integer> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            while  ((line = reader.readLine()) != null) {
                String trimmedLine = line.trim();
                if (trimmedLine.isEmpty()) {
                    continue;
                }
                try {
                    int value = Integer.parseInt(trimmedLine);
                    scores.add(value);
                } catch (NumberFormatException e) {
                    System.out.println("Warning: '" + line + "' is not a integer");
                    continue;
                }
            }
            if (scores.isEmpty()) {
                System.out.println("Warning: File contains no valid scores.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scores;
    }
    // Step 2: calculate statistics
    // Returns the average of a list of scores, or 0.0 if the list is empty
    public static double calculateAverage(ArrayList<Integer> scores) {
        if (scores.isEmpty()) {
            return 0.0;
        }
        int sum = 0;
        for (int total : scores) {
            sum += total;
        }
        double average = (double) sum / scores.size();
    return (double) average;
    }

        // Finds the highest and lowest scores in the list
    public static int findHighestScore(ArrayList<Integer> scores) {
        int HighestScore = Integer.MIN_VALUE;
        for (int score : scores) {
            if (score > HighestScore) {
                HighestScore = score;
            }
        }
        return HighestScore;
    }

    public static int findLowestScore(ArrayList<Integer> scores) {
        int LowestScore = Integer.MAX_VALUE;
        for (int score : scores) {
            if (score < LowestScore) {
                LowestScore = score;
            }
        }
        return LowestScore;
    }

    // Step 3: write and print report
    // Writes and prints the report
    public static void writeReport(ArrayList<Integer> scores, String filename,
                                   double avg, int high, int low,
                                    String outputFile) {
        // Count the Grade Bands
        int countA = 0;
        int countB = 0;
        int countC = 0;
        int countD = 0;
        int countF = 0;
        for (int score : scores) {
            if (score >= 90) {
                countA++;
            } else if (score >= 80) {
                countB++;
            } else if (score >= 70) {
                countC++;
            } else if (score >= 60) {
                countD++;
            } else {
                countF++;
            }    

        }
        //Count the total number of lines in the input file
        int totalLines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while (reader.readLine() != null) {
                totalLines++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write report
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write("=== Grade Analysis Report ===\n");
            writer.write(String.format("Total scores processed: %d%n", scores.size()));
            writer.write(String.format("Invalid lines skipped: %d%n%n", (totalLines - scores.size())));
            writer.write(String.format("Average score: %.2f%n", avg));
            writer.write(String.format("Highest score: %d%n", high));
            writer.write(String.format("Lowest score: %d%n%n", low));
            writer.write("Grade Distribution:\n");
            writer.write(String.format("A (90-100): %d%n", countA));
            writer.write(String.format("B (80-89): %d%n", countB));
            writer.write(String.format("C (70-79): %d%n", countC));
            writer.write(String.format("D (60-69): %d%n", countD));
            writer.write(String.format("F (below 60): %d%n", countF));
            System.out.println("Report written to " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
}
}