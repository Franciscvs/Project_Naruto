package naruto.util;

import java.util.Scanner;

/** Utilidad opcional si prefieres centralizar lecturas. No usada por defecto en Menu. */
public class Input {
    private final Scanner in = new Scanner(System.in);

    public int readInt(String prompt, int min, int max) {
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

    public String readLine(String prompt) {
        System.out.print(prompt);
        return in.nextLine();
    }
}
