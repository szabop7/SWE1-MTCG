import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class PackageCollection {




    @Setter
    @Getter
    private ArrayList<Package> packages;


    public PackageCollection(ArrayList<Package> packages) {
        this.packages = packages;
    }

    public static void add(Package p)
    {
        packages.add(p);
    }
}
