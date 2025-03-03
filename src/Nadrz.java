/**
 * Reprezentuje nádrž, která má omezenou kapacitu a umožňuje přidávání a odebírání obsahu.
 * Při překročení kapacity nebo pokusu o odebrání více než dostupné množství
 * jsou vyvolány odpovídající výjimky.
 */
public class Nadrz {

    private double kapacita;
    private double stav;

    /**
     * Inicializuje nádrž s danou kapacitou.
     * @param kapacita Maximální kapacita nádrže (musí být kladná hodnota).
     */
    public Nadrz(double kapacita) {
        set_kapacita(kapacita);
        stav = 0.0;
    }

    /**
     * Nastavuje kapacitu nádrže (používá se pouze v konstruktoru).
     * @param kapacita Maximální kapacita nádrže.
     */
    private void set_kapacita(double kapacita) {
        if (kapacita > 0.0)
            this.kapacita = kapacita;
    }

    /**
     * Vrací maximální kapacitu nádrže.
     * @return Maximální kapacita nádrže.
     */
    public double get_kapacita() {
        return kapacita;
    }

    /**
     * Přidává obsah do nádrže.
     * @param kolik Množství, které má být přidáno.
     * @return {@code true}, pokud přidání proběhlo úspěšně, {@code false}, pokud je vstup záporný.
     * @throws Exceptions.PlnaNadrzException Pokud by přidání překročilo kapacitu nádrže.
     */
    public boolean add(double kolik) throws Exceptions.PlnaNadrzException {
        if (kolik < 0.0)
            return false;

        double stav_new = stav + kolik;
        if (stav_new > kapacita)
            throw new Exceptions.PlnaNadrzException("Nelze přidat více než kapacita!");

        stav = stav_new;
        return true;
    }

    /**
     * Odebírá obsah z nádrže.
     * @param kolik Množství, které má být odebráno.
     * @return {@code true}, pokud odebrání proběhlo úspěšně, {@code false}, pokud je vstup záporný.
     * @throws Exceptions.PrazdnaNadrzException Pokud by odebrání způsobilo negativní stav nádrže.
     */
    public boolean remove(double kolik) throws Exceptions.PrazdnaNadrzException {
        if (kolik < 0.0)
            return false;

        double stav_new = stav - kolik;
        if (stav_new < 0.0)
            throw new Exceptions.PrazdnaNadrzException("Nelze odebrat více než aktuální stav!");

        stav = stav_new;
        return true;
    }
}