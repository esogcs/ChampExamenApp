package champqcsoft.org.champexamen_by_jose;

import java.io.FileNotFoundException;
import java.util.LinkedList;

// This class will test my methods in the QuestionBank Class
public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        // Create a new instance of QuestionBank
        QuestionBank qb = new QuestionBank();

        // Test readTFQ method
        testReadTFQ(qb);

        // Test readMCQ method
        testReadMCQ(qb);

        // Test getQuestion method
        testGetQuestion(qb);

        // Test checkAnswer method
        testCheckAnswer(qb);

        // Test selectRandQuestions method
        testSelectRandQuestions(qb);
    }

    // Method to test the readTFQ method
    private static void testReadTFQ(QuestionBank qb) throws FileNotFoundException {
        System.out.println("Testing readTFQ method:");
        qb.readTFQ("C:\\Users\\super\\IdeaProjects\\ChampExamen_by_Jose\\src\\main\\resources\\champqcsoft\\org\\champexamen_by_jose\\tfq.txt");
        System.out.println("readTFQ method test complete.\n");
    }

    // Method to test the readMCQ method
    private static void testReadMCQ(QuestionBank qb) throws FileNotFoundException {
        System.out.println("Testing readMCQ method:");
        qb.readMCQ("C:\\Users\\super\\IdeaProjects\\ChampExamen_by_Jose\\src\\main\\resources\\champqcsoft\\org\\champexamen_by_jose\\tfq.txt");
        System.out.println("readMCQ method test complete.\n");
    }

    // Method to test the getQuestion method
    private static void testGetQuestion(QuestionBank qb) {
        System.out.println("Testing getQuestion method:");
        Question question = qb.getQuestion(24); // Index 24 for testing, change as needed
        System.out.println("Retrieved question: " + question);
        System.out.println("getQuestion method test complete.\n");
    }

    // Method to test the checkAnswer method
    private static void testCheckAnswer(QuestionBank qb) {
        System.out.println("Testing checkAnswer method:");
        Question question = qb.getQuestion(24); // Get a question for testing
        boolean isCorrect = question.checkAnswer("true"); // Check answer
        System.out.println("Is the answer correct? " + isCorrect);
        System.out.println("checkAnswer method test complete.\n");
    }

    // Method to test the selectRandQuestions method
    private static void testSelectRandQuestions(QuestionBank qb) {
        System.out.println("Testing selectRandQuestions method:");
        // Generate an array of indices for testing
        int[] indices = {0, 2, 4};
        LinkedList<Question> selectedQuestions = qb.selectRandQuestions(indices);
        // Print selected questions
        System.out.println("Selected questions:");
        for (Question selectedQuestion : selectedQuestions) {
            System.out.println(selectedQuestion);
        }
        System.out.println("selectRandQuestions method test complete.\n");
    }
}





