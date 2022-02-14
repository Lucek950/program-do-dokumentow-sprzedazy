package utils;

public class Translation {
    public static String numberToText(long number) {
        String[] unity = {"", "jeden ", "dwa ", "trzy ", "cztery ",
                "pi�� ", "sze�� ", "siedem ", "osiem ", "dziewi�� ",};

        String[] teen = {"", "jedena�cie ", "dwana�cie ", "trzyna�cie ",
                "czterna�cie ", "pi�tna�cie ", "szesna�cie ", "siedemna�cie ",
                "osiemna�cie ", "dziewi�tna�cie ",};

        String[] dozens = {"", "dziesi�� ", "dwadzie�cia ",
                "trzydzie�ci ", "czterdzie�ci ", "pi��dziesi�t ",
                "sze��dziesi�t ", "siedemdziesi�t ", "osiemdziesi�t ",
                "dziewi��dziesi�t ",};

        String[] hundreds = {"", "sto ", "dwie�cie ", "trzysta ", "czterysta ",
                "pi��set ", "sze��set ", "siedemset ", "osiemset ",
                "dziewi��set ",};

        String[][] group = {{"", "", ""},
                {"tysi�c ", "tysi�ce ", "tysi�cy "},
                {"milion ", "miliony ", "milion�w "},
                {"miliard ", "miliardy ", "miliard�w "},
                {"bilion ", "biliony ", "bilion�w "},
                {"biliard ", "biliardy ", "biliard�w "},
                {"trylion ", "tryliony ", "trylion�w "},};

// INICJACJA ZMIENNYCH
        long j/* jedno�ci */, n/* nastki */, d/* dziesi�tki */, s/* setki */, g = 0/* grupy */, k  /* ko�c�wwki */;
        StringBuilder inWords = new StringBuilder();
        String chars = "";

// OPERACJA DOTYCZ�CA ZNAKU

        if (number < 0) {
            chars = "minus ";
            number = -number; // bezwgl�dna warto�� poniewa�, je�li b�dziemy
// operowa� na liczbie z minusem tablica b�dzie
// przyjmowa�a warto�ci ujemne i zwr�ci nam b��d
        }
        if (number == 0) {
            chars = "zero ";
        }

// P�TLA G��WNA
        while (number != 0) {
            s = number % 1000 / 100;
            d = number % 100 / 10;
            j = number % 10;

            if (d == 1 & j > 0) // if zajmuj�cy si� nastkami
            {
                n = j;
                d = 0;
                j = 0;
            } else {
                n = 0;
            }

// <---- KO�C�WKI

            if (j == 1 & s + d + n == 0) {
                k = 0;

                if (s + d == 0 && g > 0) // je�li nie b�dzie dziesi�tek ani setek, wtedy otrzymamy sam� grup�
                { // przyk�adowo 1000 - wy�wietli nam si� "tysi�c", je�li
// zakomentujemy tego if'a to otrzymamy "jeden tysi�c"
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

// KONIEC KO�C�WEK -->

            if (s + d + n + j > 0) {
                inWords.insert(0, hundreds[(int) s] + dozens[(int) d] + teen[(int) n]
                        + unity[(int) j] + group[(int) g][(int) k]);
            }

// POZBYWAMY SI� TYCH LICZBY KT�RE JU� PRZEROBILI�MY czyli
// przyk�adowo z 132132 zostaje nam 132 do obr�bki
            number = number / 1000;
// ORAZ ZWI�KSZAMY G KT�RE ODPOWIEDZIALNE JEST ZA NUMER POLA W
// TABLICY WIELOWYMIAROWEJ
            g = g + 1;
        }
// KONIEC P�TLI G��WNEJ

// DODANIE ZNAKU I ZWR�CENIE METODY
        inWords.insert(0, chars);
        return inWords.toString();
    }
}
