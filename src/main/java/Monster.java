import lombok.Getter;
import lombok.Setter;

public class Monster extends Card {

    @Setter
    @Getter
    private Element element;

    @Setter
    @Getter
    private String rarity;

    @Setter
    @Getter
    private int damage;

    @Setter
    @Getter
    private String monstertype;


    public Monster(String name, String description, Element element, String rarity, int damage,String monstertype) {
        super(name, description);
        this.element=element;
        this.rarity=rarity;
        this.damage=damage;
        this.monstertype=monstertype;
    }
}
