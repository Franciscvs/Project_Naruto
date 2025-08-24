package naruto.app;

import naruto.builder.NinjaBuilder;
import naruto.factory.NinjaFactory;
import naruto.model.*;
import naruto.repo.MissionRepository;
import naruto.repo.NinjaRepository;
import naruto.service.CombatResult;
import naruto.service.CombatService;
import naruto.service.TrainingService;
import naruto.visitor.*;

import java.util.*;

public class Menu {
    private final NinjaRepository ninjas;
    private final MissionRepository missions;
    private final TrainingService training;
    private final CombatService combat;
    private final NinjaFactory[] factories;
    private final String[] factoryNames;

    private final Scanner in = new Scanner(System.in);

    public Menu(NinjaRepository ninjas, MissionRepository missions,
                TrainingService training, CombatService combat,
                NinjaFactory[] factories, String[] factoryNames) {
        this.ninjas = ninjas;
        this.missions = missions;
        this.training = training;
        this.combat = combat;
        this.factories = factories;
        this.factoryNames = factoryNames;
    }

    public void run() {
        int op;
        do {
            printHeader();
            System.out.println("1) Listar ninjas");
            System.out.println("2) Crear ninja (Builder personalizado)");
            System.out.println("3) Crear ninja (Factory por aldea)");
            System.out.println("4) Entrenar ninja");
            System.out.println("5) Simular combate");
            System.out.println("6) Listar misiones");
            System.out.println("7) Crear misión");
            System.out.println("8) Exportar (Texto/JSON)");
            System.out.println("9) Salir");
            op = readInt("Elige opción: ", 1, 9);
            switch (op) {
                case 1 -> listNinjas();
                case 2 -> createNinjaWithBuilder();
                case 3 -> createNinjaWithFactory();
                case 4 -> trainNinja();
                case 5 -> simulateFight();
                case 6 -> listMissions();
                case 7 -> createMission();
                case 8 -> exportData();
                case 9 -> System.out.println("¡Hasta pronto, shinobi!");
            }
        } while (op != 9);
    }

    private void printHeader() {
        System.out.println("\n==============================");
        System.out.println("  Aldea Oculta de la Hoja – Menú");
        System.out.println("==============================");
    }

    private void listNinjas() {
        System.out.println("\n-- Ninjas --");
        ninjas.getAll().forEach(n -> System.out.println("* " + n));
    }

    private void listMissions() {
        System.out.println("\n-- Misiones --");
        missions.getAll().forEach(m -> System.out.println("* " + m));
    }

    private void createNinjaWithBuilder() {
        System.out.println("\n-- Crear ninja (Builder) --");
        String name = readLine("Nombre: ");
        Rank rank = Rank.values()[readIntEnum("Rango", Rank.values())];
        Village village = Village.values()[readIntEnum("Aldea", Village.values())];
        int atk = readInt("Ataque (0-100): ", 0, 100);
        int def = readInt("Defensa (0-100): ", 0, 100);
        int chak = readInt("Chakra (0-100): ", 0, 100);

        NinjaBuilder nb = new NinjaBuilder()
                .setName(name)
                .setRank(rank)
                .setVillage(village)
                .setStats(atk, def, chak);

        String addMore;
        do {
            addMore = readLine("¿Agregar jutsu? (s/n): ");
            if (addMore.equalsIgnoreCase("s")) {
                String jName = readLine("  Nombre jutsu: ");
                Element el = Element.values()[readIntEnum("  Elemento", Element.values())];
                int pow = readInt("  Poder (0-100): ", 0, 100);
                int cost = readInt("  Costo chakra (0-100): ", 0, 100);
                nb.addJutsu(new Jutsu(jName, el, pow, cost));
            }
        } while (addMore.equalsIgnoreCase("s"));

        ninjas.add(nb.build());
        System.out.println("Ninja creado y agregado al registro.");
    }

    private void createNinjaWithFactory() {
        System.out.println("\n-- Crear ninja (Factory) --");
        for (int i = 0; i < factories.length; i++) {
            System.out.printf("%d) %s%n", i+1, factoryNames[i]);
        }
        int idx = readInt("Elige fábrica: ", 1, factories.length) - 1;
        String name = readLine("Nombre: ");
        Rank rank = Rank.values()[readIntEnum("Rango", Rank.values())];
        Ninja n = factories[idx].createBasicNinja(name, rank);
        ninjas.add(n);
        System.out.println("Ninja creado por fábrica: " + n);
    }

    private void trainNinja() {
        System.out.println("\n-- Entrenar ninja --");
        Ninja n = pickNinja("Elige ninja a entrenar");
        int atk = readInt("+Ataque (puede ser negativo): ", -100, 100);
        int def = readInt("+Defensa (puede ser negativo): ", -100, 100);
        int chak = readInt("+Chakra (puede ser negativo): ", -100, 100);
        training.train(n, atk, def, chak);
        System.out.println("Entrenamiento aplicado. Nuevo estado: " + n);
    }

    private void simulateFight() {
        System.out.println("\n-- Simular combate --");
        Ninja a = pickNinja("Elige primer ninja");
        Ninja b = pickNinja("Elige segundo ninja");
        CombatResult r = combat.simulate(a, b);
        System.out.println("\n=== Simulación de Combate ===");
        System.out.println(r.getLog());
        System.out.println("Ganador: " + r.getWinner().getName());
    }

    private void createMission() {
        System.out.println("\n-- Crear misión --");
        String title = readLine("Título: ");
        MissionRank mr = MissionRank.values()[readIntEnum("Rango misión", MissionRank.values())];
        int reward = readInt("Recompensa (ryō): ", 0, 1_000_000_000);
        Rank minR = Rank.values()[readIntEnum("Rango mínimo", Rank.values())];
        missions.add(new Mission(title, mr, reward, minR));
        System.out.println("Misión creada.");
    }

    private void exportData() {
        System.out.println("\n-- Exportar --");
        System.out.println("1) Texto");
        System.out.println("2) JSON");
        int op = readInt("Formato: ", 1, 2);
        ExportVisitor v = (op == 1) ? new TextExportVisitor() : new JsonExportVisitor();
        v.start();
        ninjas.getAll().forEach(n -> n.accept(v));
        missions.getAll().forEach(m -> m.accept(v));
        v.end();
        System.out.println(v.getResult());
    }

    private Ninja pickNinja(String label) {
        var list = ninjas.getAll();
        if (list.isEmpty()) throw new IllegalStateException("No hay ninjas registrados");
        System.out.println(label + ":");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d) %s%n", i+1, list.get(i).getName());
        }
        int idx = readInt("Elige: ", 1, list.size()) - 1;
        return list.get(idx);
    }

    private int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int v = Integer.parseInt(in.nextLine().trim());
                if (v < min || v > max) throw new NumberFormatException();
                return v;
            } catch (Exception ex) {
                System.out.printf("Valor inválido. Debe estar entre %d y %d.%n", min, max);
            }
        }
    }

    private int readIntEnum(String label, Object[] values) {
        System.out.println(label + ":");
        for (int i = 0; i < values.length; i++) {
            System.out.printf("%d) %s%n", i+1, values[i]);
        }
        return readInt("Elige: ", 1, values.length) - 1;
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        return in.nextLine();
    }
}
