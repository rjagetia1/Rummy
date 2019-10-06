/*****************************************************************************
 *  Author: Rahul Jagetia
 *  Date: 10/06/2019
 *  Project Description: Class to define Card for Rummy. Provides suit and
 *  rank for each card, and provides comparable function to compare different
 *  cards to each other
 *
 **************************************************************************** */


public class Card implements Comparable<Card> {

    private String suit;
    private int rank;


    public Card(int rank, String suit){
        this.suit = suit;
        this.rank = rank;
    }

    public boolean equals(Card other){
        return this.compareTo(other) == 0;
    }

    public String toString() {
        String answer = "";
        if(rank == 1) answer += "Ace of ";
        else if(rank == 11) answer += "Jack of ";
        else if(rank == 12) answer += "Queen of ";
        else if(rank == 13) answer += "King of ";
        else answer += "" + rank + " of ";

        return answer + suit;
    }

    public int compareTo(Card other) {
        if (suitValue(this) - suitValue(other) == 0)
            return this.rank - other.rank;
        else
            return suitValue(this) - suitValue(other);
    }

    private int suitValue (Card current) {
        int suitValue;
        if (current.suit.equals("Diamonds"))
            suitValue = 1;
        else if (current.suit.equals("Clubs"))
            suitValue = 2;
        else if (current.suit.equals("Spades"))
            suitValue = 3;
        else
            suitValue = 4;
        return suitValue;
    }
}
