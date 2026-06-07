# Java Tokens

<table>
<tr>
<th width="50%">English</th>
<th width="50%">Türkçe</th>
</tr>

<!-- SECTION: Overview -->
<tr>
<td>

## Overview

The Java compiler breaks source code into **tokens** — the smallest meaningful units. There are 5 token types:

1. Keywords
2. Identifiers
3. Literals
4. Separators
5. Operators

</td>
<td>

## Genel Bakis

Java derleyicisi kaynak kodu **token**'lara ayirir — en kucuk anlamli birimler. 5 token tipi vardir:

1. Keywords (Anahtar Kelimeler)
2. Identifiers (Tanimladilar)
3. Literals (Sabit Degerler)
4. Separators (Ayiricilar)
5. Operators (Islecler)

</td>
</tr>

<!-- SECTION: Keywords -->
<tr>
<td>

## 1. Keywords (53 reserved words)

Reserved words that have a predefined meaning. Cannot be used as identifiers.

**Access modifiers:**
`public` `private` `protected`

**Class/Object:**
`class` `interface` `enum` `record` `extends` `implements` `abstract` `final` `static` `new` `this` `super` `instanceof`

**Data types:**
`byte` `short` `int` `long` `float` `double` `char` `boolean` `void`

**Control flow:**
`if` `else` `switch` `case` `default` `for` `while` `do` `break` `continue` `return` `yield`

**Exception handling:**
`try` `catch` `finally` `throw` `throws`

**Other:**
`package` `import` `synchronized` `volatile` `transient` `native` `strictfp` `assert` `var` `sealed` `permits` `non-sealed`

**Unused but reserved:**
`goto` `const`

**Literal keywords:**
`true` `false` `null`

</td>
<td>

## 1. Keywords (53 ayrılmis kelime)

Onceden tanimli anlami olan kelimeler. Identifier olarak kullanilamaz.

**Erisim belirleyiciler:**
`public` `private` `protected`

**Sinif/Nesne:**
`class` `interface` `enum` `record` `extends` `implements` `abstract` `final` `static` `new` `this` `super` `instanceof`

**Veri tipleri:**
`byte` `short` `int` `long` `float` `double` `char` `boolean` `void`

**Akis kontrolu:**
`if` `else` `switch` `case` `default` `for` `while` `do` `break` `continue` `return` `yield`

**Istisna yonetimi:**
`try` `catch` `finally` `throw` `throws`

**Diger:**
`package` `import` `synchronized` `volatile` `transient` `native` `strictfp` `assert` `var` `sealed` `permits` `non-sealed`

**Kullanilmayan ama ayrilmis:**
`goto` `const`

**Literal anahtar kelimeler:**
`true` `false` `null`

</td>
</tr>

<!-- SECTION: Identifiers -->
<tr>
<td>

## 2. Identifiers

Names given to classes, methods, variables, etc.

**Rules:**
- Must start with: letter, `_`, or `$`
- Can contain: letters, digits, `_`, `$`
- Cannot be a keyword
- Case-sensitive (`name` != `Name`)

**Examples:**
```java
int age;           // valid
String _name;      // valid
double $price;     // valid
// int 2count;     // INVALID - starts with digit
// int class;      // INVALID - keyword
```

</td>
<td>

## 2. Identifiers (Tanimladilar)

Sinif, method, degisken vb. icin verilen isimler.

**Kurallar:**
- Harf, `_` veya `$` ile baslamali
- Harf, rakam, `_`, `$` icerebilir
- Keyword olamaz
- Buyuk/kucuk harf duyarli (`name` != `Name`)

**Ornekler:**
```java
int age;           // gecerli
String _name;      // gecerli
double $price;     // gecerli
// int 2count;     // GECERSIZ - rakamla basliyor
// int class;      // GECERSIZ - keyword
```

</td>
</tr>

<!-- SECTION: Literals -->
<tr>
<td>

## 3. Literals

Fixed values written directly in code.

| Type | Examples |
|---|---|
| Integer | `42` `0xFF` `0b1010` `1_000_000` |
| Long | `42L` |
| Float | `3.14f` |
| Double | `3.14` `2.5e10` |
| Char | `'A'` `'\n'` `'\u0041'` |
| String | `"hello"` `"""text block"""` |
| Boolean | `true` `false` |
| Null | `null` |

**Underscore in numbers (Java 7+):**
```java
int million = 1_000_000;
long card = 1234_5678_9012_3456L;
```

</td>
<td>

## 3. Literals (Sabit Degerler)

Dogrudan koda yazilan sabit degerler.

| Tip | Ornekler |
|---|---|
| Integer | `42` `0xFF` `0b1010` `1_000_000` |
| Long | `42L` |
| Float | `3.14f` |
| Double | `3.14` `2.5e10` |
| Char | `'A'` `'\n'` `'\u0041'` |
| String | `"hello"` `"""text block"""` |
| Boolean | `true` `false` |
| Null | `null` |

**Sayilarda alt cizgi (Java 7+):**
```java
int milyon = 1_000_000;
long kart = 1234_5678_9012_3456L;
```

</td>
</tr>

<!-- SECTION: Separators -->
<tr>
<td>

## 4. Separators

Structural punctuation that groups or separates code elements.

| Symbol | Purpose |
|---|---|
| `( )` | method call, grouping, cast |
| `{ }` | block body, array init |
| `[ ]` | array access/declaration |
| `;` | statement terminator |
| `,` | parameter/element separator |
| `.` | member access |
| `...` | varargs |
| `@` | annotation |
| `::` | method reference |

**Examples:**
```java
@Override                      // @
int[] arr = {1, 2, 3};       // [] {} ,
System.out.println("hi");    // . ( ) ;
String::valueOf               // ::
void log(String... msgs)     // ...
```

</td>
<td>

## 4. Separators (Ayiricilar)

Kod elemanlarini gruplayan veya ayiran yapisal isaretler.

| Sembol | Amac |
|---|---|
| `( )` | method cagrisi, gruplama, cast |
| `{ }` | blok govdesi, array init |
| `[ ]` | array erisimi/tanimi |
| `;` | ifade sonu |
| `,` | parametre/eleman ayirici |
| `.` | uye erisimi |
| `...` | varargs |
| `@` | annotation |
| `::` | method reference |

**Ornekler:**
```java
@Override                      // @
int[] arr = {1, 2, 3};       // [] {} ,
System.out.println("hi");    // . ( ) ;
String::valueOf               // ::
void log(String... msgs)     // ...
```

</td>
</tr>

<!-- SECTION: Operators -->
<tr>
<td>

## 5. Operators

Symbols that perform operations on operands.

**Arithmetic:**
`+` `-` `*` `/` `%` `++` `--`

**Assignment:**
`=` `+=` `-=` `*=` `/=` `%=`
`&=` `|=` `^=` `<<=` `>>=` `>>>=`

**Comparison:**
`==` `!=` `<` `>` `<=` `>=`

**Logical:**
`&&` `||` `!`

**Bitwise:**
`&` `|` `^` `~` `<<` `>>` `>>>`

**Ternary:**
`? :`

**Lambda:**
`->`

**Type:**
`instanceof`

**Precedence (high to low):**
```
()  []  .
!  ~  ++  --  (cast)
*  /  %
+  -
<<  >>  >>>
<  >  <=  >=  instanceof
==  !=
&  ^  |
&&  ||
? :
=  +=  -=  ...
```

</td>
<td>

## 5. Operators (Islecler)

Operandlar uzerinde islem yapan semboller.

**Aritmetik:**
`+` `-` `*` `/` `%` `++` `--`

**Atama:**
`=` `+=` `-=` `*=` `/=` `%=`
`&=` `|=` `^=` `<<=` `>>=` `>>>=`

**Karsilastirma:**
`==` `!=` `<` `>` `<=` `>=`

**Mantiksal:**
`&&` `||` `!`

**Bit duzeyi:**
`&` `|` `^` `~` `<<` `>>` `>>>`

**Uclu (ternary):**
`? :`

**Lambda:**
`->`

**Tip:**
`instanceof`

**Oncelik (yuksekten dusuge):**
```
()  []  .
!  ~  ++  --  (cast)
*  /  %
+  -
<<  >>  >>>
<  >  <=  >=  instanceof
==  !=
&  ^  |
&&  ||
? :
=  +=  -=  ...
```

</td>
</tr>

<!-- SECTION: What is NOT a token -->
<tr>
<td>

## Not Tokens

These are removed before tokenization:

- **Comments:** `//` `/* */` `/** */`
- **Whitespace:** spaces, tabs, newlines

They are for humans, not for the compiler.

</td>
<td>

## Token Olmayan Seyler

Bunlar tokenization oncesinde kaldirilir:

- **Yorumlar:** `//` `/* */` `/** */`
- **Bosluklar:** space, tab, yeni satir

Derleyici icin degil, insan icin varlar.

</td>
</tr>
</table>
