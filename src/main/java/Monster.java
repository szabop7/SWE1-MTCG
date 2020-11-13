import lombok.Getter;
import lombok.Setter;

public class Monster extends Card {

    @Setter
    @Getter
    private Element type;

    @Setter
    @Getter
    private String rarity;


    public Monster(String name, String description, Element type, String rarity) {
        super(name, description);
        this.type=type;
        this.rarity=rarity;
    }
}
