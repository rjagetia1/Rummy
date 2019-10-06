/*****************************************************************************
 *  Author: Rahul Jagetia
 *  Date: 10/06/2019
 *  Project Description: Class to define Deck for Rummy. Provides a deck of
 *  cards, and allows users to pull from the "deck" object
 *
 **************************************************************************** */

import java.util.Random;

public class Deck {

        private Card[] theDeck;
        private String suits[] = {"Diamonds", "Clubs", "Spades", "Hearts"};
        public int numberOfCards;



        public Deck() {
            numberOfCards = 52;
            theDeck = new Card[numberOfCards];

            for(int i = 0; i < 52; i++){
                theDeck[i] = new Card((i % 13) + 1, suits[i/13]);
            }
        }

        public Card dealACard(){

            int i = (int) (Math.random() * 51);
            while (theDeck[i] == null) {
                i = (int) (Math.random() * 51);
            }
            Card currentCard = theDeck[i];
            theDeck[i] = null;
            numberOfCards--;
            return currentCard;
        }

        public String toString(){
            return "";
        }

    }
