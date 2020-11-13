import lombok.Getter;
import lombok.Setter;

public abstract class Card {

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String description;

    public Card(String name, String description) {
        this.name = name;
        this.description = description;
    }


}
