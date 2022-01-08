package matador;


import java.io.*;
import java.util.HashMap;

public class Oversaetter {

    private static Oversaetter instance;

    private static String filPlacering = "/sprog/";
    private static String sprog = "dansk";

    private HashMap<String, String> oversaettelser;

    private Oversaetter() {
        this.oversaettelser = new HashMap<>();
        this.parseFil(Oversaetter.filPlacering + Oversaetter.sprog + ".txt");
    }

    public static Oversaetter getInstance() {
        if (Oversaetter.instance == null) {
            Oversaetter.instance = new Oversaetter();
            return Oversaetter.instance;
        } else {
            return Oversaetter.instance;
        }
    }

    public static String t(String stringKey) {
        return Oversaetter.getInstance().get(stringKey);
    }

    public static String t(String stringKey, String[] variabler) {
        return Oversaetter.getInstance().get(stringKey, variabler);
    }

    public static Oversaetter setSprog(String sprog) {
        if (sprog.equals(Oversaetter.sprog)) {
            return Oversaetter.instance;
        }
        Oversaetter.sprog = sprog;
        Oversaetter.instance = new Oversaetter();
        return Oversaetter.instance;
    }

    public static String getSprog() {
        return Oversaetter.sprog;
    }

    private String get(String stringKey) {
        if (this.oversaettelser.containsKey(stringKey)) {
            String oversaettelse = this.oversaettelser.get(stringKey);
            if (oversaettelse.equals("")) {
                return stringKey;
            }
            return oversaettelse;
        } else {
            return stringKey;
        }
    }

    private String get(String stringKey, String[] variabler) {
        String oversaettelse = this.t(stringKey);
        if (oversaettelse.equals(stringKey)) {
            return oversaettelse;
        }
        int i = 0;
        while (i < variabler.length) {
            oversaettelse = oversaettelse.replaceAll("\\{\\{ ?#" + i + " ?\\}\\}", variabler[i]);
            i++;
        }
        return oversaettelse;
    }

    private String parseValue(String string) {
        string.replace(" \\n ", "\n");
        string.replace("\\n", "\n");
        return string;
    }

    private void parseFil(String filSti) {
        try {
            InputStream inputS = this.getClass().getResourceAsStream(filSti);
            BufferedReader buffR = new BufferedReader(new InputStreamReader(inputS));
            String nuvaerendeLinje;
            if ((nuvaerendeLinje = buffR.readLine()) != null) {
                do {
                    if (!nuvaerendeLinje.equals("")) {
                        String[] keyVaerdi = nuvaerendeLinje.split(":", 2);
                        String key = keyVaerdi[0];
                        String val = keyVaerdi.length == 1 ? "" : this.parseValue(keyVaerdi[1]);
                        if (this.oversaettelser.containsKey(key)) {
                        } else {
                            this.oversaettelser.put(key, val);
                        }
                    }
                } while ((nuvaerendeLinje = buffR.readLine()) != null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setFilPlacering(String sti)
    {
        Oversaetter.filPlacering = sti;
    }
}