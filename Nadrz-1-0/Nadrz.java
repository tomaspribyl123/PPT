public class Nadrz {

    //enum NAPLN { NATURAL95, NATURAL98, NAFTA}

    private double kapacita;

    private double stav;

    // ----

    Nadrz(double kapacita) {
        set_kapacita(kapacita);//this.kapacita = kapacita;
        stav = 0.0;
    }

    private void set_kapacita(double kapacita) {
        if (kapacita > 0.0)
            this.kapacita = kapacita;
    }

    public double get_kapacita()
    {
        return kapacita;
    }

    public boolean add(double kolik) {
        if (kolik < 0.0)
            return false;
        final double stav_new = stav + kolik;
        if (stav_new > kapacita)
            return false;

        stav = stav_new;
        return true;
    }

    public boolean remove(double kolik) {
        if (kolik < 0.0)
            return false;
        final double stav_new = stav - kolik;
        if (stav_new < 0.0)
            return false;

        stav = stav_new;
        return true;
    }
}
