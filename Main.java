abstract class Növény {
    private String szín;
    private double vízmennyiség;

    public Növény(String szín) {
        this.szín = szín;
        this.vízmennyiség = 0;
    }

    public bool vanVízszükség() {
        return vízmennyiség < szükségesVízmennyiség();
    }

    public void öntöz(int mennyiség) {
        double felvehetőMennyiség = mennyiség * vízfelvehetőség();
        vízmennyiség += felvehetőMennyiség;
    }

    protected abstract double vízfelvehetőség();

    protected abstract double szükségesVízmennyiség();

    @Override
    public String toString() {
        return "A " + szín + " növénynek vízre van szüksége";
    }
}

class Virág extends Növény {
    public Virág(String szín) {
        super(szín);
    }

    @Override
    protected double vízfelvehetőség() {
        return 0.75;
    }

    @Override
    protected double szükségesVízmennyiség() {
        return 5;
    }
}

class Fa extends Növény {
    public Fa(String szín) {
        super(szín);
    }

    @Override
    protected double vízfelvehetőség() {
        return 0.4;
    }

    @Override
    protected double szükségesVízmennyiség() {
        return 10;
    }
}

class Kert {
    private Növény[] növények;

    public Kert(Növény...növények) {
        this.növények = növények;
    }

    public void öntöz(int mennyiség) {
        System.out.println("Öntözés " + mennyiség);

        for (Növény növény : növények) {
            if (növény.vanVízszükség()) {
                növény.öntöz(mennyiség / növények.length);
            }
        }
    }

    public void mutat() {
        System.out.println("A kert:");

        for (Növény növény : növények) {
            System.out.println("    " + növény);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Virág sárgaVirág = new Virág("sárga");
        Virág kékVirág = new Virág("kék");
        Fa loncFa = new Fa("lonc");
        Fa narancssárgaFa = new Fa("narancssárga");

        Kert kert = new Kert(sárgaVirág, kékVirág, loncFa, narancssárgaFa);

        kert.mutat();
        System.out.println();

        kert.öntöz(40);
        kert.mutat();
        System.out.println();

        kert.öntöz(70);
        kert.mutat();
    }
}
