import lombok.Getter;
import lombok.Setter;

public class Card {

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String description;

    @Setter
    @Getter
    private String element;

    @Setter
    @Getter
    private String rarity;

    @Setter
    @Getter
    private int damage;

    @Setter
    @Getter
    private String monstertype;

    public Card(String name, String description, String element, String monstertype, int damage) {
        this.name = name;
        this.description = description;
        this.element=element;
        this.monstertype=monstertype;
        this.damage=damage;
    }


}
