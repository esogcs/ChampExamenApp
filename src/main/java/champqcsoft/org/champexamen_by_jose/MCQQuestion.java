package champqcsoft.org.champexamen_by_jose;

import java.util.LinkedList;

public class MCQQuestion extends Question {

    private LinkedList<String> options = new LinkedList<>();

    public MCQQuestion() {
        this.options = new LinkedList<>();
    }

    public MCQQuestion(String correctAnswer, String questionText, QuestionType questionType, LinkedList<String> options ) {
        super(correctAnswer, questionText, QuestionType.MCQ);
        this.options = options;
    }

    public LinkedList<String> getOptions() {
        return options;
    }

    public void setOptions(LinkedList<String> options) {
        this.options.clear();
        this.options.addAll(options);
    }

    @Override
    public String toString() {

        String str = getQuestionText() + '\n';
        for (String option : options) {
            str += option + '\n';
        }
        return str;
    }
}