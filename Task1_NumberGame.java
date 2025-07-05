import java.util.Random;
import java.util.Scanner;

public class Task1_NumberGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        int maxRounds = 5;
        int round = 1;
        int totalScore = 0;

        System.out.println(" Welcome to the Number Guessing Game!");
        System.out.println("Guess the number between 1 and 100.");

        while (true) {
            int numberToGuess = random.nextInt(100) + 1;
            int attemptsLeft = 7;
            int attemptsUsed = 0;
            boolean guessedCorrectly = false;

            System.out.println("\n Round " + round);
            System.out.println("You have " + attemptsLeft + " attempts to guess the number.");

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");
                int userGuess = sc.nextInt();
                attemptsUsed++;

                if (userGuess == numberToGuess) {
                    System.out.println(" Congratulations! You guessed the correct number.");
                    guessedCorrectly = true;
                    totalScore += (10 - attemptsUsed);  // more points for fewer attempts
                    break;
                } else if (userGuess > numberToGuess) {
                    System.out.println(" Too high!");
                } else {
                    System.out.println(" Too low!");
                }

                attemptsLeft--;
                if (attemptsLeft > 0) {
                    System.out.println("Attempts left: " + attemptsLeft);
                }
            }

            if (!guessedCorrectly) {
                System.out.println(" You’ve used all attempts! The number was: " + numberToGuess);
            }

            System.out.println(" Your current score: " + totalScore);

            System.out.print("\nDo you want to play another round? (yes/no): ");
            String playAgain = sc.next();

            if (!playAgain.equalsIgnoreCase("yes") || round >= maxRounds) {
                System.out.println("\n Game Over!");
                System.out.println("Total rounds played: " + round);
                System.out.println("Final score: " + totalScore);
                break;
            }

            round++;
        }

        sc.close();
    }
}