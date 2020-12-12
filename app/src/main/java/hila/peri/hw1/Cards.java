package hila.peri.hw1;
import java.util.ArrayList;
import java.util.Collections;

public class Cards {
    private ArrayList<Cards> cards;
    private String imageName;
    private int winThisTurn;

    public Cards() {}

    public Cards(String imageName, int strength) {
        this.imageName = imageName;
        this.winThisTurn = strength;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getWinThisTurn() {
        return winThisTurn;
    }

    public void setWinThisTurn(int winThisTurn) {
        this.winThisTurn = winThisTurn;
    }

    public boolean isStronger(Cards card){
        return this.winThisTurn > card.winThisTurn;
    }
}
    class Deck {
    private ArrayList<Cards> cards;

    public Deck() {
        this.cards = new ArrayList<>();
    }

    public ArrayList<Cards> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Cards> cards) {
        this.cards = cards;
    }

    public void shuffleCards() {
        Collections.shuffle(cards);
    }

    public Cards getCard() {
        if (!isEmpty()) {
            return cards.remove(0);
        }
        return null;
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public void addCard(String imageName, int strength) {
        cards.add(new Cards(imageName, strength));
    }
}
