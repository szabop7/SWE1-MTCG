import lombok.Getter;
import lombok.Setter;

public class Spell extends Card{


    @Setter
    @Getter
    private Element type;

    public Spell(String name, String description, Element type) {
        super(name, description);
        this.type=type;
    }
}
