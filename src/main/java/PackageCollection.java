import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

public class PackageCollection {

    @Setter
    @Getter
    private static ArrayList<Package> packageCollection=new ArrayList<>();

    public PackageCollection(ArrayList<Package> packageCollection) {
        this.packageCollection = packageCollection;
    }

    public static void add(Package p)
    {
        packageCollection.add(p);
    }
}
