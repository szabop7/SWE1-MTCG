import lombok.Getter;
import lombok.Setter;

public class Deck{

    @Getter
    @Setter
    private Card [] cards=new Card[4];

    public Deck(Card[] cards) {
        this.cards = cards;
    }
}
