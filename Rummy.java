/*****************************************************************************
 *  Author: Rahul Jagetia
 *  Date: 10/06/2019
 *  Project Description: Two-player game of gin rummy
 *     Rules for Rummy:
 *     -Need three pairs of 3 and one pair of four straight numbers with the
 *     same suit (i.e. 3, 4, and 5 of spades is a
 *     - pair of three)
 *     -Every turn, have two options: Pick up bottom card or pick card from deck
 *     -After picking up card, remove a card
 *     -Repeat until someone can "show" aka they have all the sequences necessary
 *     -Cards for each person is stored in a separate text file, and the picked up
 *     card is also stored in a separate text file, so no cheating!
 *
 * NOTE: Since each game of rummy is treated individually, there are no points
 * in this game.
 **************************************************************************** */

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
public class Rummy {

    public static void main(String[] args) throws FileNotFoundException {
        File file1 = new File("hand1.txt");
        File file2 = new File("hand2.txt");
        File file3 = new File("pickedUpCard.txt");
        PrintStream firstHand = new PrintStream (file1);
        PrintStream secondHand = new PrintStream (file2);
        PrintStream pickedUpCard = new PrintStream(file3);
        PrintStream console = System.out;
        Deck deck = new Deck();
        Card [] hand1 = new Card[13];
        Card [] hand2 = new Card[13];

        for (int i = 0; i < 13; i++) {
            hand1[i] = deck.dealACard();
            hand2[i] = deck.dealACard();
        }

        Arrays.sort(hand1);
        Arrays.sort(hand2);

        for (int i = 0; i < 13; i++) {
            System.setOut(firstHand);
            System.out.println(i + " " + hand1[i]);
        }

        for (int i = 0; i < 13; i++) {
            System.setOut(secondHand);
            System.out.println(i + " " + hand2[i]);
        }

        Card currentCard;
        Card currentCardDown = deck.dealACard();
        Scanner keyboard = new Scanner(System.in);
        String answer;
        int replaceCard;
        int counter = 1;
        int currentHandPlaying;
        String firstAnswer;

        while (deck.numberOfCards > 0) {
            currentHandPlaying = counter % 2;
            if (currentHandPlaying == 0)
                currentHandPlaying = 2;
            System.setOut(console);

            System.out.println("The player playing right now is player " + currentHandPlaying);
            System.out.println("The current card on the ground is " + currentCardDown);

            System.out.println("Would you like to pick up a card from the deck or pick up the card that is down? " +
                    "1 or 2");
            firstAnswer = keyboard.next();

            if (firstAnswer.equals("1")) {
                System.setOut(pickedUpCard);
                file3 = new File("pickedUpCard.txt");
                pickedUpCard = new PrintStream(file3);
                currentCard = deck.dealACard();
                System.out.println("The card you picked up is "+ currentCard);
                System.setOut(console);
                System.out.println("Would you like to keep the card? y or n");
                answer = keyboard.next();

                if (answer.equals("n")) {
                    currentCardDown = currentCard;
                }

                else if (answer.equals("y")) {
                    System.out.println("What card NUMBER would you like to replace it with? ");
                    replaceCard = keyboard.nextInt();

                    if (currentHandPlaying == 1) {
                        currentCardDown = hand1[replaceCard];
                        hand1[replaceCard] = currentCard;
                        file1 = new File("hand1.txt");
                        firstHand = new PrintStream(file1);
                        for (int i = 0; i < 13; i++) {
                            System.setOut(firstHand);
                            System.out.println(i + " " + hand1[i]);
                        }
                        Arrays.sort(hand1);
                    }
                    else if (currentHandPlaying == 2) {
                        currentCardDown = hand2[replaceCard];
                        hand2[replaceCard] = currentCard;
                        file2 = new File("hand2.txt");
                        secondHand = new PrintStream(file2);
                        for (int i = 0; i < 13; i++) {
                            System.setOut(secondHand);
                            System.out.println(i + " " + hand2[i]);
                        }
                        Arrays.sort(hand2);
                    }
                }
            }

            else if (firstAnswer.equals("2")) {
                currentCard = currentCardDown;
                System.out.println("What card NUMBER would you like to replace it with? ");
                replaceCard = keyboard.nextInt();

                if (currentHandPlaying == 1) {
                    currentCardDown = hand1[replaceCard];
                    hand1[replaceCard] = currentCard;
                    file1 = new File("hand1.txt");
                    firstHand = new PrintStream(file1);
                    for (int i = 0; i < 13; i++) {
                        System.setOut(firstHand);
                        System.out.println(i + " " + hand1[i]);
                    }
                    Arrays.sort(hand1);
                } else if (currentHandPlaying == 2) {
                    currentCardDown = hand2[replaceCard];
                    hand2[replaceCard] = currentCard;
                    file2 = new File("hand2.txt");
                    secondHand = new PrintStream(file2);
                    for (int i = 0; i < 13; i++) {
                        System.setOut(secondHand);
                        System.out.println(i + " " + hand2[i]);
                    }
                    Arrays.sort(hand2);
                }
            }
            else {
                System.out.println("Invalid input");
                continue;
            }
            System.out.println("Can you show? y or n");
            answer = keyboard.next();
            if (answer.equals("y")) {
                System.out.println("Current cards: ");
                if (currentHandPlaying == 2) {
                    for (int i = 0; i < 13; i++)
                        System.out.println(hand2[i]);
                }
                else
                    for (int i = 0; i < 13; i++)
                        System.out.println(hand1[i]);
            }
            else {
                System.out.println();
                counter++;
            }
        }
    }

}
