package com.onurdemir.playground;

/*
By default, %f displays exactly six digits past the decimal. If you want to display only one digit after the decimal, you can use %.1f instead of %f.
You can also specify a minimum width for the output, and you can pad the output with spaces or zeros.
The following table summarizes the most common format specifiers for floating-point numbers:
*/

/*
Özet tablo
Format	Anlamı	Sonuç
%f	Varsayılan 6 hane hassasiyet	3.141593
%.3f	Virgülden sonra 3 hane	3.142
%12.8f	Toplam min. 12 karakter genişlik + 8 hane hassasiyet, boşlukla doldur	3.14159265
%12.2f	Toplam min. 12 karakter genişlik + 2 hane hassasiyet, boşlukla doldur	3.14
%012f	Toplam min. 12 karakter genişlik, varsayılan 6 hane hassasiyet, sıfırla doldur	00003.141593
 */

public class RepeatDemo {

    public static void main(String[] args) {
        var pi = 3.14159265359;
        System.out.format("[%-12.2f]", pi); // [3.14        ]  → sola yasla, sağa boşluk
        System.out.format("[%+.2f]", pi);   // [+3.14]         → pozitif işaretini göster
        System.out.format("[% .2f]", pi);   // [ 3.14]         → pozitifse başına boşluk koy
        System.out.format("[%,.2f]", 12345.678); // [12,345.68] → binlik ayırıcı virgül
        System.out.format("[%(.2f]", -pi);  // [(3.14)]        → negatif sayıyı parantez içinde göster

        // test method
        System.out.println("[" + customPad("3.14", 12, '*') + "]"); // [********3.14]
    }

    static String customPad(String s, int width, char padChar) {
        if (s.length() >= width) {
            return s;
        }
        return String.valueOf(padChar).repeat(width - s.length()) + s;
    }
}