public class Exceptions {
    /**
     * Výjimka signalizující, že se pokoušíme přidat více obsahu, než umožňuje kapacita nádrže.
     */
        public static class PlnaNadrzException extends Exception {
        /**
         * Vytváří instanci výjimky s danou zprávou.
         * @param message Zpráva popisující chybu.
         */
        public PlnaNadrzException(String message) {
            super(message);
        }
    }

    /**
     * Výjimka signalizující, že se pokoušíme odebrat více obsahu, než je v nádrži dostupné.
     */
    public static class PrazdnaNadrzException extends Exception {
        /**
         * Vytváří instanci výjimky s danou zprávou.
         * @param message Zpráva popisující chybu.
         */
        public PrazdnaNadrzException(String message) {
            super(message);
        }
    }
}
