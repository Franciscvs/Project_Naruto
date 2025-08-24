package naruto.service;

import naruto.model.Ninja;

public class CombatResult {
    private final Ninja winner;
    private final String log;

    public CombatResult(Ninja winner, String log) {
        this.winner = winner; this.log = log;
    }

    public Ninja getWinner() { return winner; }
    public String getLog() { return log; }
}
