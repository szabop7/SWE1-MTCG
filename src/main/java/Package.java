import lombok.Getter;
import lombok.Setter;

public class Package {



    @Setter
    @Getter
    private Card [] cards=new Card [5];

    public Package(Card[] cards) {
        this.cards = cards;
    }
}
