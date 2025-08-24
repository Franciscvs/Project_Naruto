package naruto.factory;

import naruto.model.*;

public class KiriFactory extends NinjaFactory {
    @Override
    public Ninja createBasicNinja(String name, Rank rank) {
        // Kiri (Niebla) con afinidad a Suiton
        Stats stats = new Stats(55, 50, 75);
        Jutsu waterDragon = new Jutsu("Suiton: Suiryūdan", Element.SUITON, 30, 20);
        Jutsu hiddenMist = new Jutsu("Kirigakure no Jutsu", Element.SUITON, 15, 10);
        return base(name, rank, Village.KIRI, stats, waterDragon, hiddenMist);
    }
}
