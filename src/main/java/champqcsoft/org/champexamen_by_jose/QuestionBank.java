package champqcsoft.org.champexamen_by_jose;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class QuestionBank {

    protected LinkedList<Question> questions = new LinkedList<>();

    public QuestionBank() {
    }

    public QuestionBank(LinkedList<Question> questions) {
        this.questions = questions;
    }

    public void clearQuestions() {
        this.questions.clear();
    }

    public Question getQuestion(int i) {
        return this.questions.get(i);
    }

    public void printAllQuestions() {
        for (Question question : this.questions) {
            System.out.println(question);
        }
    }

    public void readMCQ(String fname) throws FileNotFoundException {
        try {
            File filename = new File(fname);
            Scanner sc = new Scanner(filename);
            boolean sameQuestion;
            while (sc.hasNextLine()) {
                String MCQText = sc.nextLine();
//                System.out.println(MCQText);
                MCQText = MCQText.substring(3);

                LinkedList<String> options = new LinkedList<>();
                sameQuestion = true;
                while (sc.hasNextLine() && sameQuestion) {
                    String option = sc.nextLine();
                    options.add(option.substring(2));
                    if (option.length() > 8 && option.startsWith("ANSWER: ")) {
                        String MCQAnswer = options.getLast().split(" ")[1]; // Skip the word "answer"
                        options.removeLast();
                        MCQQuestion MCQuestion = new MCQQuestion(MCQAnswer, MCQText, QuestionType.MCQ, options);
                        questions.add(MCQuestion);
                        sameQuestion = false;
                    }
                }
                if (sc.hasNextLine()) sc.nextLine();
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
    }

    public void readTFQ(String fname) throws FileNotFoundException {
        try {
            File filename = new File(fname);
            Scanner sc = new Scanner(filename);
            while (sc.hasNextLine()) {
                String TFQText = sc.nextLine();
//                System.out.println(TFQText);
                TFQText = TFQText.substring(3);

                if (sc.hasNextLine()) {
                    String TFQAnswer = sc.nextLine().split(" ")[1]; // Skip the word "answer"
                    TFQuestion TFQuestion = new TFQuestion(TFQAnswer, TFQText, QuestionType.TFQ);
                    questions.add(TFQuestion);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
    }

    public LinkedList<Question> selectRandQuestions(int[] indices) {
        LinkedList<Question> selectedQuestions = new LinkedList<>();
        for (int index : indices) {
            if (index >= 0 && index < this.questions.size()) {
                selectedQuestions.add(this.questions.get(index));
            } else {
                System.out.println("Invalid index: " + index);
            }
        }
        return selectedQuestions;
    }
}