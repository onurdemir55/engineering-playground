package com.onurdemir.playground.langfeatures;

public class TextBlockDemo {

    public static void main(String[] args) {

        // 1. Basic usage - starts with """, content must begin on the next line
        //    Temel kullanım - """ ile başlar, yeni satırda içerik başlar
        String html = """
                <html>
                    <body>
                        <p>Merhaba</p>
                    </body>
                </html>
                """;
        System.out.println(html);

        // 2. Closing """ position determines indentation - closer to left means less indent
        //    Kapanış """ konumu girintiyi belirler - sola yakınsa girinti azalır
        String noIndent = """
No indent
because closing is at far left
""";
        System.out.println(noIndent);

        // 3. No escape needed - quotes can be used directly
        //    Escape karakterlere gerek yok - tırnak doğrudan kullanılır
        String json = """
                {
                    "name": "Onur",
                    "age": 30
                }
                """;
        System.out.println(json);

        // 4. \s → preserves trailing whitespace (normally stripped)
        //    \s → satır sonundaki boşlukları korur (normalde silinir)
        String trailing = """
                line1\s\s\s
                line2
                """;
        System.out.println(trailing);

        // 5. \ → line continuation (no newline inserted)
        //    \ → satır sonu birleştirme (yeni satır eklenmez)
        String singleLine = """
                This merges \
                into a single \
                line""";
        System.out.println(singleLine);

        // 6. formatted() for variable interpolation - works like String.format()
        //    formatted() ile değişken yerleştirme - String.format() gibi çalışır
        String name = "Onur";
        int age = 30;
        String formatted = """
                Name: %s
                Age: %d
                """.formatted(name, age);
        System.out.println(formatted);

        // 7. SQL query - improved readability
        //    SQL sorgusu - okunabilirlik artışı
        String sql = """
                SELECT u.name, u.email
                FROM users u
                WHERE u.active = true
                ORDER BY u.name
                """;
        System.out.println(sql);
    }
}
