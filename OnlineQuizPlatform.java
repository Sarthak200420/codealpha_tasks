import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Question {
    String questionText;
    String[] options;
    int correctAnswerIndex;

    public Question(String questionText, String[] options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }
}

public class OnlineQuizPlatform {
    private List<Question> questions;

    public OnlineQuizPlatform() {
        questions = new ArrayList<>();
        loadQuestions();
    }

    private void loadQuestions() {
        questions.add(new Question("What is the capital of France?",
                new String[] { "1. Berlin", "2. Madrid", "3. Paris", "4. Rome" }, 2));
        questions.add(new Question("Which planet is known as the Red Planet?",
                new String[] { "1. Earth", "2. Mars", "3. Jupiter", "4. Venus" }, 1));
        questions.add(new Question("What is the largest ocean on Earth?",
                new String[] { "1. Atlantic Ocean", "2. Indian Ocean", "3. Arctic Ocean", "4. Pacific Ocean" }, 3));
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        for (Question q : questions) {
            System.out.println(q.questionText);
            for (String option : q.options) {
                System.out.println(option);
            }
            System.out.print("Your answer (1-4): ");
            int answer = scanner.nextInt() - 1;

            if (answer == q.correctAnswerIndex) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Wrong! Correct answer: " + (q.correctAnswerIndex + 1) + "\n");
            }
        }

        System.out.println("Quiz finished! Your score: " + score + "/" + questions.size());
        scanner.close();
    }

    public static void main(String[] args) {
        OnlineQuizPlatform quizPlatform = new OnlineQuizPlatform();
        quizPlatform.startQuiz();
    }
}
