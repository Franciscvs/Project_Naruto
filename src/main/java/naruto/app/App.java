package naruto.app;

import naruto.factory.*;
import naruto.model.*;
import naruto.repo.MissionRepository;
import naruto.repo.NinjaRepository;
import naruto.service.*;

public class App {
    public static void main(String[] args) {
        // Repos y servicios
        NinjaRepository ninjas = new NinjaRepository();
        MissionRepository missions = new MissionRepository();
        TrainingService training = new TrainingService();
        CombatService combat = new CombatService();

        // Fábricas disponibles
        NinjaFactory konoha = new KonohaFactory();
        NinjaFactory suna   = new SunaFactory();
        NinjaFactory kiri   = new KiriFactory();

        // Semillas de demo
        ninjas.add(konoha.createBasicNinja("Naruto Uzumaki", Rank.GENIN));
        ninjas.add(suna.createBasicNinja("Gaara", Rank.CHUNIN));
        ninjas.add(kiri.createBasicNinja("Zabuza Momochi", Rank.JONIN));
        missions.add(new Mission("Escoltar al puente de Tazuna", MissionRank.C, 15000, Rank.GENIN));
        missions.add(new Mission("Contener invasión a Konoha", MissionRank.S, 500000, Rank.JONIN));

        // Lanzar menú interactivo estilo juego
        Menu menu = new Menu(ninjas, missions, training, combat,
                new NinjaFactory[]{konoha, suna, kiri},
                new String[]{"KonohaFactory", "SunaFactory", "KiriFactory"});
        menu.run();
    }
}
