import lombok.Getter;
import lombok.Setter;

public class Spell extends Card{


    @Setter
    @Getter
    private Element element;

    @Setter
    @Getter
    private String rarity;

    @Setter
    @Getter
    private int damage;



    public Spell(String name, String description, Element element,String rarity, int damage) {
        super(name, description);
        this.element=element;
        this.rarity=rarity;
        this.damage=damage;
    }
}
