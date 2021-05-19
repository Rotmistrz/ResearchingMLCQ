import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class ResearchingMLCQProgram {
    public static void main(String[] args) {
        String filename = "./resources/MLCQCodeSmellSamples-32.csv";
        String resultsDir = "./results/";

        String resultsCodeDir = resultsDir + "code/";

        try {
            AnalyzerMLCQ analyzer = new AnalyzerMLCQ(filename, "./logs/", ';');

            List<CodeReview> reviews = analyzer.prepareReviews(filename);

            int expectedFiles = reviews.size();

            List<CodeReview> reachableReviews = analyzer.receiveCodeSamples(resultsCodeDir, reviews, 10);

            int actualFiles = reachableReviews.size();

            if (expectedFiles == actualFiles) {
                System.out.println("Receiving files completed successfully!");
            } else {
                System.out.println("Receiving files failed. " + (expectedFiles - actualFiles) + " reviews have not been processed successfully.");
            }
        } catch (Exception e) {
            System.out.println("Błąd: " + e.getMessage());
        }
    }


//    public static Document getDocument(String url, int maxAttempts) throws InterruptedException {
//        boolean isDownloaded = false;
//        int attempt = 0;
//        Document result = null;
//
//        while (!isDownloaded && attempt < maxAttempts) {
//            try {
//                result = Jsoup.connect(url).get();
//
//                return result;
//            } catch (IOException e) {
//                attempt++;
//                TimeUnit.SECONDS.sleep(2);
//            }
//        }
//
//        return null;
//    }


// old main - 19.05.2021

//    public static void main(String[] args) {
//        String filename = "./resources/MLCQCodeSmellSamples.csv";
//        String resultsDir = "./results/";
//
//        try {
//            List<CodeReview> reviews = new CsvToBeanBuilder(new FileReader(filename)).withType(CodeReview.class).withSeparator(';').build().parse();
//
//            Document document;
//            Elements lines;
//            String sampleOutputPath;
//            HashMap<Integer, String> failures = new HashMap<>();
//            Set<Integer> receivedSamples = new HashSet<>();
//            int attemptsNumber = 10;
//            LinkedList<String> failureLogs = new LinkedList<>();
//
//            int expectedFiles = 0;
//            int actualFiles = 0;
//
//            for (CodeReview review : reviews) {
//                if (!receivedSamples.contains(review.sampleID)) {
//                    expectedFiles++;
//
//                    document = getDocument(review.link, attemptsNumber);
//
//                    if (document != null) {
//                        lines = document.select(".type-java .js-file-line");
//                        sampleOutputPath = resultsDir + review.sampleID + ".java";
//
//                        BufferedWriter writer = new BufferedWriter(new FileWriter(sampleOutputPath, false));
//
//                        for (Element line : lines) {
//                            writer.append(line.wholeText() + "\n");
//                        }
//
//                        writer.close();
//
//                        actualFiles++;
//                        receivedSamples.add(review.sampleID);
//                        System.out.println("Sample " + review.sampleID + " saved!");
//                    } else {
//                        failures.put(review.sampleID, review.link);
//
//                        System.out.println(review.reviewID + ", " + review.sampleID + ": problem with receiving the code.");
//
//                        failureLogs.add(review.reviewID + ", " + review.sampleID + ", " + review.codeSmell + ", " + review.severity + ", " + review.link);
//                    }
//                }
//            }
//
//            if (expectedFiles == actualFiles) {
//                System.out.println("Receiving files completed successfully!");
//            } else {
//                System.out.println("Receiving files failed. " + (expectedFiles - actualFiles) + " files have not been downloaded. Below are listed failure details:\n");
//
//                for (String logLine : failureLogs) {
//                    System.out.println(logLine);
//                }
//
////                for (int sampleID : failures.keySet()) {
////                    System.out.println(sampleID + ", " + failures.get(sampleID));
////                }
//            }
//
//            System.out.println("There are " + receivedSamples.size() + " samples received but " + failures.size() + " cannot be processed.");
//        } catch (Exception e) {
//            System.out.println("Błąd: " + e.getMessage());
//        }
//    }
}
