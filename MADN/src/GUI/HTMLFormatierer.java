package GUI;


/**
 * Eine Klasse, um das Logging als HTML auszugeben.
 * Zum benuetzen einfach Nachrichten hinzufuegen und dann konstruiereHTML() aufrufen
 */
public class HTMLFormatierer {

    /** Alle Nachrichten, die geschrieben werden sollen */
    private StringBuilder builderNachrichten;
    private StringBuffer bufferNachrichten;
    private final String htmlAnfang = "<html>" +
                                        "<style>" +
                                            ".error{color:red;}" +
                                            ".warning{color:#DAA520;}" +
                                        "</style>" +
                                        "<body>";
    private final String htmlEnde =     "</body>" +
                                    "</html>";

    private final String divStart = "<div>";
    private final String divEnde = "</div";

    private final String divFehelerStart = "<div class=\"error\">";
    private final String divWarnungStart = "<div class=\"warning\">";

    /**
     * Erstellt einen neuen HTML Formatierer
     * @param benuetzeStringBuilder Wenn false, wird StringBuffer (Threadsafe) benutzt (
     */
    public HTMLFormatierer(boolean benuetzeStringBuilder) {
        if (benuetzeStringBuilder) {
            this.builderNachrichten = new StringBuilder();
        } else {
            this.bufferNachrichten = new StringBuffer();
        }
    }

    /**
     * Loescht alle Zeilen
     */
    public void allesLoeschen() {
        if (this.bufferNachrichten != null) {
            this.bufferNachrichten = new StringBuffer();
        } else {
            this.builderNachrichten = new StringBuilder();
        }
    }

    /**
     * Schreibt eine Zeile ohne Farbe
     * @param nachricht Die Nachricht, die geschrieben werden soll
     */
    public void schreibeInfo(String nachricht) {
        this.schreibeText(this.divStart + nachricht + this.divEnde);
    }

    /**
     * Schreibt eine Zeile mit der Frabe fuer "Warnung"
     * @param nachricht Die Nachricht die geschrieben werden soll
     */
    public void schreibeWarnung(String nachricht) {
        this.schreibeText(this.divWarnungStart + nachricht + this.divEnde);
    }

    /**
     * Schreibt eine Zeile mit der Farbe fuer "Fehler"
     * @param nachricht Die Fehlernachricht
     */
    public void schreibeFehler(String nachricht) {
        this.schreibeText(this.divFehelerStart + nachricht + this.divEnde);
    }

    /**
     * Konstruiert einen fertigen HTML-String aus den gespeicherten Nachrichten
     * @return Den konstruierten String (ohne new lines!)
     */
    public String konstruiereHTML() {

        String html = "";

        if (this.bufferNachrichten != null) {
            html = this.bufferNachrichten.substring(0);
        } else {
            html = this.builderNachrichten.substring(0);
        }
        return this.htmlAnfang + html + this.htmlEnde;
    }

    private void schreibeText(String s) {
        if (this.builderNachrichten != null) {
            this.builderNachrichten.append(s);
        } else {
            this.bufferNachrichten.append(s);
        }
    }
}
