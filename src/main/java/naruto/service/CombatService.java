package naruto.service;

import naruto.model.Jutsu;
import naruto.model.Ninja;

import java.util.Random;

public class CombatService {
    /**
     * Simulación ligera por rondas con daño basado en ataque + poder del jutsu - defensa/2.
     * El azar se fija con semilla derivada de los nombres para reproducibilidad.
     */
    public CombatResult simulate(Ninja a, Ninja b) {
        StringBuilder log = new StringBuilder();
        int hpA = Math.max(30, a.getStats().getDefense()*5 + a.getStats().getChakra());
        int hpB = Math.max(30, b.getStats().getDefense()*5 + b.getStats().getChakra());
        int chakraA = a.getStats().getChakra();
        int chakraB = b.getStats().getChakra();

        long seed = (a.getName() + "_vs_" + b.getName()).hashCode();
        Random rnd = new Random(seed);

        Ninja attacker = a; Ninja defender = b;
        int hpAtt = hpA; int hpDef = hpB;
        int chakraAtt = chakraA; int chakraDef = chakraB;

        for (int round=1; round<=10 && hpAtt>0 && hpDef>0; round++) {
            Jutsu chosen = attacker.getJutsus().isEmpty() ? null : attacker.getJutsus().get(rnd.nextInt(attacker.getJutsus().size()));
            int jutsuPower = (chosen != null && chakraAtt >= chosen.getChakraCost()) ? chosen.getPower() : 0;
            int jutsuCost  = (jutsuPower > 0) ? (chosen != null ? chosen.getChakraCost() : 0) : 0;
            int baseDamage = attacker.getStats().getAttack();
            int variance   = rnd.nextInt(11) - 5; // [-5, +5]
            int damage     = Math.max(0, baseDamage + jutsuPower + variance - defender.getStats().getDefense()/2);

            chakraAtt -= jutsuCost;
            hpDef -= damage;

            log.append(String.format("Ronda %d: %s ataca%s por %d daño. HP %s=%d%n",
                    round,
                    attacker.getName(),
                    (jutsuPower>0 ? (" usando " + (chosen!=null?chosen.getName():"")) : ""),
                    damage,
                    defender.getName(), Math.max(0, hpDef)));

            // swap roles
            Ninja tmpN = attacker; attacker = defender; defender = tmpN;
            int tmpHP = hpAtt; hpAtt = hpDef; hpDef = tmpHP;
            int tmpCh = chakraAtt; chakraAtt = chakraDef; chakraDef = tmpCh;
        }

        Ninja winner = (hpA==hpB) ? (rnd.nextBoolean()?a:b) : (hpA>hpB? a : b);
        log.append("Ganador: ").append(winner.getName()).append('\n');
        return new CombatResult(winner, log.toString());
    }
}
