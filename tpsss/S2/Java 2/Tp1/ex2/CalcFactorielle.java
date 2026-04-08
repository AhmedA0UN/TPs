// Classe Exception personnalisée
class ExceptionNegatif extends Exception {
    public ExceptionNegatif(String message) {
        super(message);
    }
}

public class CalcFactorielle {

    // Méthode fact() qui calcule la factorielle
    public static int fact(int k) throws ExceptionNegatif {
        if (k < 0) {
            throw new ExceptionNegatif("Impossible de calculer la factorielle d'un entier négatif : " + k);
        }
        int f = 1;
        for (int i = 1; i <= k; i++) {
            f = f * i;
        }
        return f;
    }

    // Méthode main()
    public static void main(String[] args) {
        try {
            // Vérifier qu'il y a bien un argument
            if (args.length == 0) {
                throw new ArrayIndexOutOfBoundsException("Aucun argument fourni !");
            }

            // Essayer de convertir l'argument en entier
            int n = Integer.parseInt(args[0]);

            // Calculer la factorielle
            int resultat = fact(n);

            // Afficher le résultat
            System.out.println(n + "! = " + resultat);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Exception : Aucun argument fourni.");
            System.out.println("Usage : java CalcFactorielle <entier>");
        } catch (NumberFormatException e) {
            System.out.println("Exception : L'argument doit être un entier valide. (" + e.getMessage() + ")");
            System.out.println("Usage : java CalcFactorielle <entier>");
        } catch (ExceptionNegatif e) {
            System.out.println("Exception : " + e.getMessage());
        }
    }
}