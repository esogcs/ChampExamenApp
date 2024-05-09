package champqcsoft.org.champexamen_by_jose;

import java.util.HashMap;
import java.util.LinkedList;


public class Exam {

    // variables
    protected HashMap<Integer,Question> questions;
    protected HashMap<Integer,String> submittedAnswers;

    // constructor with arguments
    public Exam(HashMap<Integer, Question> questions, HashMap<Integer, String> submittedAnswers) {
        this.questions = new HashMap<>();
        this.submittedAnswers = new HashMap<>();
        this.questions.putAll(questions);
        this.submittedAnswers.putAll(submittedAnswers);
    }

    // empty constructor
    public Exam(){
        this.questions = new HashMap<>();
        this.submittedAnswers = new HashMap<>();

    }

    public Exam(LinkedList<Question> questionList){
        this.questions = new HashMap<>();
        this.submittedAnswers = new HashMap<>();

        for(int i = 0; i < questionList.size(); i++){
            this.questions.put(i, questionList.get(i));
            this.submittedAnswers.put(i , "");

        }
    }

    // getters and setters
    public void clearQuestions(){
        this.questions.clear();
    }
    public Question getQuestion(int i){
        return this.questions.get(i);
    }
    public String getSubmittedAnswer(int i){
        return this.submittedAnswers.get(i);
    }

    // print all questions
    public void printAllQuestions(){
        for(int i = 0; i < questions.size(); i++){
            Question q = questions.get(i);
            String submittedAnswer = submittedAnswers.get(i);
            System.out.println("Question: " + (i + 1) + ": " + q.getQuestionText());
            System.out.println("Submitted Answer: " + submittedAnswer);
        }
    }


}
