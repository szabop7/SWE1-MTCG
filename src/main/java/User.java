import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class User {

    @Setter
    @Getter
    private String username;

    @Setter
    @Getter
    private String password;

    @Setter
    @Getter
    private boolean admin;

    @Setter
    @Getter
    public int coins=20;

    @Setter
    @Getter
    public ArrayList<Card> stack;


    @Setter
    @Getter
    public ArrayList<Deck> decks;

    public User(String username, String password, boolean admin, ArrayList<Card> stack, ArrayList<Deck> decks) {
        this.username = username;
        this.password = password;
        this.admin=admin;
        this.stack = stack;
        this.decks = decks;
    }

    public boolean create_Package(Card c1, Card c2, Card c3, Card c4, Card c5) throws Exception {
        if(admin==true)
        {
            Package p=new Package(new Card[]{c1, c2, c3, c4, c5});
            PackageCollection.add(p);
            return true;
        }
        else {
            throw new Exception("Der User muss ein Admin sein, damit er neue Packages erstellen kann!");
        }
    }

    public boolean battle(Deck deck)
    {
        if(deck!=null)
        {
            //Implementation missing
            return true;
        }
        return false;
    }

    public Package buy() throws Exception {
        if(coins>=5)
        {
            coins=coins-5;
            Card c1=new Monster("Frog","The Destroyer", Element.water,"Selten");
            Card c2=new Monster("Buffalo","Beast", Element.normal,"Common");
            Card c3=new Spell("Storm","Hurricane", Element.normal);
            Card c4=new Spell("Blizzard","The Destroyer", Element.water);
            Card c5=new Spell("Firerain","Holy Wrath", Element.fire);
            Card [] cards2=new Card[5];
            cards2[0]=c1;
            cards2[1]=c2;
            cards2[2]=c3;
            cards2[3]=c4;
            cards2[4]=c5;
            Package p=new Package(cards2);
            stack.add(c1);
            stack.add(c2);
            stack.add(c3);
            stack.add(c4);
            stack.add(c5);
            return p;
        }
        throw new Exception( "Leider nicht genügend Münzen vorhanden");
    }

}
