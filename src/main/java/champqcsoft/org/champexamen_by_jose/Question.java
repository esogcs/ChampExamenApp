package champqcsoft.org.champexamen_by_jose;

public class Question {

    // variables
    private String correctAnswer;
    private String questionText;
    private QuestionType questionType;

    // empty constructor
    public Question() {
        this.correctAnswer = "";
        this.questionText = "";
        this.questionType = null;
    }

    // constructor with arguments
    public Question(String correctAnswer, String questionText, QuestionType questionType) {
        this.correctAnswer = correctAnswer;
        this.questionText = questionText;
        this.questionType = questionType;
    }

    // getters & setters
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }


    // checkAnswer method
    public boolean checkAnswer(String answer) {
        return answer.equalsIgnoreCase(correctAnswer);
    }

    // toString method
    @Override
    public String toString() {
        return questionText;
    }

}
