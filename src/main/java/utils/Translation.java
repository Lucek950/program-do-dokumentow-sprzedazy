package utils;

public class Translation {
    public static String numberToText(long number) {
        String[] unity = {"", "jeden ", "dwa ", "trzy ", "cztery ",
                "piêæ ", "szeœæ ", "siedem ", "osiem ", "dziewiêæ ",};

        String[] teen = {"", "jedenaœcie ", "dwanaœcie ", "trzynaœcie ",
                "czternaœcie ", "piêtnaœcie ", "szesnaœcie ", "siedemnaœcie ",
                "osiemnaœcie ", "dziewiêtnaœcie ",};

        String[] dozens = {"", "dziesiêæ ", "dwadzieœcia ",
                "trzydzieœci ", "czterdzieœci ", "piêædziesi¹t ",
                "szeœædziesi¹t ", "siedemdziesi¹t ", "osiemdziesi¹t ",
                "dziewiêædziesi¹t ",};

        String[] hundreds = {"", "sto ", "dwieœcie ", "trzysta ", "czterysta ",
                "piêæset ", "szeœæset ", "siedemset ", "osiemset ",
                "dziewiêæset ",};

        String[][] group = {{"", "", ""},
                {"tysi¹c ", "tysi¹ce ", "tysiêcy "},
                {"milion ", "miliony ", "milionów "},
                {"miliard ", "miliardy ", "miliardów "},
                {"bilion ", "biliony ", "bilionów "},
                {"biliard ", "biliardy ", "biliardów "},
                {"trylion ", "tryliony ", "trylionów "},};

// INICJACJA ZMIENNYCH
        long j/* jednoœci */, n/* nastki */, d/* dziesi¹tki */, s/* setki */, g = 0/* grupy */, k  /* koñcówwki */;
        StringBuilder inWords = new StringBuilder();
        String chars = "";

// OPERACJA DOTYCZ¹CA ZNAKU

        if (number < 0) {
            chars = "minus ";
            number = -number; // bezwglêdna wartoœæ poniewa¿, jeœli bêdziemy
// operowaæ na liczbie z minusem tablica bêdzie
// przyjmowa³a wartoœci ujemne i zwróci nam b³¹d
        }
        if (number == 0) {
            chars = "zero ";
        }

// PÊTLA G£ÓWNA
        while (number != 0) {
            s = number % 1000 / 100;
            d = number % 100 / 10;
            j = number % 10;

            if (d == 1 & j > 0) // if zajmuj¹cy siê nastkami
            {
                n = j;
                d = 0;
                j = 0;
            } else {
                n = 0;
            }

// <---- KOÑCÓWKI

            if (j == 1 & s + d + n == 0) {
                k = 0;

                if (s + d == 0 && g > 0) // jeœli nie bêdzie dziesi¹tek ani setek, wtedy otrzymamy sam¹ grupê
                { // przyk³adowo 1000 - wyœwietli nam siê "tysi¹c", jeœli
// zakomentujemy tego if'a to otrzymamy "jeden tysi¹c"
                    j = 0;
                    inWords.insert(0, group[(int) g][(int) k]);
                }
            } else if (j == 2) {
                k = 1;
            } else if (j == 3) {
                k = 1;
            } else if (j == 4) {
                k = 1;
            } else {
                k = 2;
            }

// KONIEC KOÑCÓWEK -->

            if (s + d + n + j > 0) {
                inWords.insert(0, hundreds[(int) s] + dozens[(int) d] + teen[(int) n]
                        + unity[(int) j] + group[(int) g][(int) k]);
            }

// POZBYWAMY SIÊ TYCH LICZBY KTÓRE JU¯ PRZEROBILIŒMY czyli
// przyk³adowo z 132132 zostaje nam 132 do obróbki
            number = number / 1000;
// ORAZ ZWIÊKSZAMY G KTÓRE ODPOWIEDZIALNE JEST ZA NUMER POLA W
// TABLICY WIELOWYMIAROWEJ
            g = g + 1;
        }
// KONIEC PÊTLI G£ÓWNEJ

// DODANIE ZNAKU I ZWRÓCENIE METODY
        inWords.insert(0, chars);
        return inWords.toString();
    }
}
