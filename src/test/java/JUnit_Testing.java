import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JUnit_Testing {

    @Test
    public void test_buy() throws Exception {

        Card c1=new Monster("Frog","The Destroyer", Element.water,"Rare");
        Card c2=new Monster("Buffalo","Beast", Element.normal,"Common");
        Card c3=new Spell("Storm","Hurricane", Element.normal);
        Card c4=new Spell("Blizzard","The Destroyer", Element.water);
        Card c5=new Spell("Firerain","Holy Wrath", Element.fire);
        ArrayList<Card> al=new ArrayList<>();
        al.add(c1);
        al.add(c2);
        al.add(c3);
        al.add(c4);
        al.add(c5);
        Card [] c=new Card[4];
        c[0]=c1;
        c[1]=c2;
        c[2]=c3;
        c[3]=c4;
        Card [] cards2=new Card[5];
        cards2[0]=c1;
        cards2[1]=c2;
        cards2[2]=c3;
        cards2[3]=c4;
        cards2[4]=c5;
        Deck d=new Deck(c);
        Package p=new Package(cards2);
        ArrayList <Deck> ald=new ArrayList<>();
        ald.add(d);

        User u=new User("Franz", "123",false, al,ald);
        u.buy();
        assertEquals(15,u.coins);
        assertEquals(10,u.getStack().toArray().length);
    }

    @Test
    public void test_battle()
    {
        Card c1=new Monster("Frog","The Destroyer", Element.water,"Rare");
        Card c2=new Monster("Buffalo","Beast", Element.normal,"Common");
        Card c3=new Spell("Storm","Hurricane", Element.normal);
        Card c4=new Spell("Blizzard","The Destroyer", Element.water);
        Card c5=new Spell("Firerain","Holy Wrath", Element.fire);
        ArrayList<Card> al=new ArrayList<>();
        al.add(c1);
        al.add(c2);
        al.add(c3);
        al.add(c4);
        al.add(c5);
        Card [] c=new Card[4];
        c[0]=c1;
        c[1]=c2;
        c[2]=c3;
        c[3]=c4;
        Card [] cards2=new Card[5];
        cards2[0]=c1;
        cards2[1]=c2;
        cards2[2]=c3;
        cards2[3]=c4;
        cards2[4]=c5;
        Deck d=new Deck(c);
        Package p=new Package(cards2);
        ArrayList <Deck> ald=new ArrayList<>();
        ald.add(d);

        User u=new User("Franz", "123",false, al,ald);
        
        assertEquals(true, u.battle(u.decks.get(0)));
    }

}
