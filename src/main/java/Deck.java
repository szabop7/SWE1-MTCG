import lombok.Getter;
import lombok.Setter;

public class Deck{

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private Card [] cards=new Card[4];

    public Deck(Card[] cards, String username) {
        this.cards = cards;
        this.username=username;
    }
}
