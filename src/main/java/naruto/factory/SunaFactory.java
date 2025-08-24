package naruto.factory;

import naruto.model.*;

public class SunaFactory extends NinjaFactory {
    @Override
    public Ninja createBasicNinja(String name, Rank rank) {
        // Suna (Arena) suele tener buen control defensivo
        Stats stats = new Stats(50, 70, 60);
        Jutsu sandShield = new Jutsu("Suna no Tate", Element.DOTON, 10, 5);
        Jutsu sandBullet = new Jutsu("Sabaku Dangan", Element.DOTON, 25, 15);
        return base(name, rank, Village.SUNA, stats, sandShield, sandBullet);
    }
}
