# Java Memory & Reference Model

<table>
<tr>
<th width="50%">English</th>
<th width="50%">Türkçe</th>
</tr>

<!-- SECTION: General Architecture -->
<tr>
<td>

## General Architecture — Layers

```
+-------------------------------------+
|           JAVA CODE                  |
|   Object obj = new Object();        |
|   (reference - opaque handle)       |
+----------------+--------------------+
                 |
                 v
+-------------------------------------+
|            JVM HEAP                  |
|                                     |
| reference -> oop (ordinary object   |
|                    pointer)         |
| Compressed Oops: 32-bit pointer,    |
|   8-byte aligned shift              |
|                                     |
| GC manages this area,               |
| moves objects, updates addresses    |
+----------------+--------------------+
                 |
                 v
+-------------------------------------+
|  OS VIRTUAL MEMORY                  |
|  (Process Address Space)            |
|                                     |
| Virtual address space belonging     |
| to the JVM process                  |
|                                     |
| Each process has its own isolated   |
| 0x0000..0xFFFF space                |
|                                     |
| Translated to physical address      |
| via Page Table                      |
+----------------+--------------------+
                 | (MMU + Page Table)
                 v
+-------------------------------------+
|         PHYSICAL RAM                |
|                                     |
| Actual electrical memory location   |
| Only OS kernel and CPU MMU access   |
| User programs can never see this    |
+-------------------------------------+
```

</td>
<td>

## Genel Mimari — Katmanlar

```
+-------------------------------------+
|           JAVA KODU                  |
|   Object obj = new Object();        |
|   (reference - opaque handle)       |
+----------------+--------------------+
                 |
                 v
+-------------------------------------+
|            JVM HEAP                  |
|                                     |
| reference -> oop (ordinary object   |
|                    pointer)         |
| Compressed Oops: 32-bit pointer,    |
|   8-byte aligned shift              |
|                                     |
| GC bu alani yonetir,                |
| objeleri tasir, adresleri gunceller |
+----------------+--------------------+
                 |
                 v
+-------------------------------------+
|  OS SANAL BELLEK                    |
|  (Process Adres Alani)              |
|                                     |
| JVM process'e ait                   |
| sanal adres alani                   |
|                                     |
| Her process kendi izole             |
| 0x0000..0xFFFF alanina sahip        |
|                                     |
| Page Table ile                      |
| fiziksel adrese cevrilir            |
+----------------+--------------------+
                 | (MMU + Page Table)
                 v
+-------------------------------------+
|         FIZIKSEL RAM                |
|                                     |
| Gercek elektriksel bellek konumu    |
| Sadece OS kernel ve CPU MMU erisir  |
| Kullanici programlari goremez       |
+-------------------------------------+
```

</td>
</tr>

<!-- SECTION: Java Reference vs C/C++ Pointer -->
<tr>
<td>

## Java Reference vs C/C++ Pointer

```
C/C++:
  pointer (0x7fff1234)
    ---> OS Virtual Memory
    ---> Physical RAM
  (holds this address directly)

Java:
  reference (opaque)
    ---> JVM Heap (oop)
    ---> OS Virtual Memory
    ---> Physical RAM
  ^ GC can move it
  ^ address changes
```

| | C/C++ Pointer | Java Reference |
|---|---|---|
| Held value | OS virtual addr | JVM oop (opaque) |
| Pointer arithmetic | Yes (`p+1`) | No |
| See the value? | Yes | No |
| Value changes? | No | Yes (GC) |
| Arbitrary access | Yes | Impossible |
| Dangling pointer | Possible | Never |
| Layers | 2 | 3 |

</td>
<td>

## Java Reference vs C/C++ Pointer

```
C/C++:
  pointer (0x7fff1234)
    ---> OS Sanal Bellek
    ---> Fiziksel RAM
  (dogrudan bu adresi tutuyor)

Java:
  reference (opaque)
    ---> JVM Heap (oop)
    ---> OS Sanal Bellek
    ---> Fiziksel RAM
  ^ GC tasiyabilir
  ^ adres degisir
```

| | C/C++ Pointer | Java Reference |
|---|---|---|
| Tuttugu deger | OS sanal adres | JVM oop (opaque) |
| Pointer aritmetigi | Var (`p+1`) | Yok |
| Degeri gorebilir misin? | Evet | Hayir |
| Deger degisir mi? | Hayir | Evet (GC) |
| Rastgele erisim | Evet | Imkansiz |
| Dangling pointer | Olabilir | Olmaz |
| Katman sayisi | 2 | 3 |

</td>
</tr>

<!-- SECTION: Page Table -->
<tr>
<td>

## What is a Page Table?

```
+--------------+    +--------------+    +--------------+
| Process(JVM) |    |  PAGE TABLE  |    | PHYSICAL RAM |
|              |    |  (OS Kernel) |    |              |
| Virtual Addr |--->| VA->PA map   |--->| Physical Addr|
| 0x00400000   |    | 0x004 -> F5  |    | Frame F5     |
| 0x00401000   |    | 0x005 -> A2  |    | Frame A2     |
+--------------+    +--------------+    +--------------+
```

- Each process has its OWN page table
- Same virtual address maps to different physical frames in different processes
- That's why processes can't access each other's memory
- MMU performs this translation at hardware level

**Page:** Virtual memory divided into 4KB pages  
**Frame:** Physical RAM divided into same-sized frames  
**Page Table:** Maps page to frame  
**TLB:** Page table cache for speed

</td>
<td>

## Page Table Nedir?

```
+--------------+    +--------------+    +--------------+
| Process(JVM) |    |  PAGE TABLE  |    | FIZIKSEL RAM |
|              |    |  (OS Kernel) |    |              |
| Sanal Adres  |--->| SA->FA map   |--->| Fiziksel Adr |
| 0x00400000   |    | 0x004 -> F5  |    | Frame F5     |
| 0x00401000   |    | 0x005 -> A2  |    | Frame A2     |
+--------------+    +--------------+    +--------------+
```

- Her process'in AYRI page table'i var
- Ayni sanal adres, farkli process'lerde farkli fiziksel frame'e gider
- Bu yuzden process'ler birbirinin bellegine erisemez
- MMU bu cevirimi donanim seviyesinde yapar

**Page:** Sanal bellek 4KB sayfalara bolunur  
**Frame:** Fiziksel RAM ayni boyutta frame'lere bolunur  
**Page Table:** Page'in hangi frame'e eslendigini tutar  
**TLB:** Page table cache'i — hizlandirir

</td>
</tr>

<!-- SECTION: JVM Heap Detail -->
<tr>
<td>

## JVM Heap Detail

```
+------------------------------------------+
|               JVM HEAP                   |
|                                          |
| +--------+  +-------------------------+ |
| | YOUNG  |  |     OLD GENERATION      | |
| | GEN    |  |                         | |
| |        |  | Long-lived objects here  | |
| | Eden   |  |                         | |
| | S0|S1  |  | Major GC / Full GC      | |
| |        |  | cleans this area        | |
| +--------+  +-------------------------+ |
|                                          |
| +--------------------------------------+ |
| |       METASPACE (off-heap)           | |
| | Class metadata, method bytecode      | |
| +--------------------------------------+ |
+------------------------------------------+
```

- `new Object()` -> placed in Eden
- Minor GC -> survivors move between S0/S1
- Aged objects -> promoted to Old Gen
- When GC moves objects, ALL refs updated

</td>
<td>

## JVM Heap Detay

```
+------------------------------------------+
|               JVM HEAP                   |
|                                          |
| +--------+  +-------------------------+ |
| | YOUNG  |  |     OLD GENERATION      | |
| | GEN    |  |                         | |
| |        |  | Uzun yasayan objeler     | |
| | Eden   |  | burada                  | |
| | S0|S1  |  |                         | |
| |        |  | Major GC / Full GC      | |
| |        |  | bu alani temizler       | |
| +--------+  +-------------------------+ |
|                                          |
| +--------------------------------------+ |
| |       METASPACE (off-heap)           | |
| | Class metadata, method bytecode      | |
| +--------------------------------------+ |
+------------------------------------------+
```

- `new Object()` -> Eden'a yerlesir
- Minor GC -> hayatta kalanlar S0/S1 arasi
- Yaslanan objeler -> Old Gen'e promote
- GC tasidikca TUM reference'lar guncellenir

</td>
</tr>

<!-- SECTION: Compressed Oops -->
<tr>
<td>

## Compressed Oops (64-bit JVM)

```
If Heap < 32GB:

  32-bit oop
    ---> (oop << 3)
    ---> actual heap address (64-bit)
```

- Objects are 8-byte aligned (last 3 bits always 000)
- No need to store 3 bits -> 32-bit can address 2^35 = 32GB
- Pointer size halved -> less memory, better cache

</td>
<td>

## Compressed Oops (64-bit JVM)

```
Heap < 32GB ise:

  32-bit oop
    ---> (oop << 3)
    ---> gercek heap adresi (64-bit)
```

- Objeler 8-byte aligned (son 3 bit her zaman 000)
- 3 bit saklamaya gerek yok -> 32-bit ile 2^35 = 32GB adreslenebilir
- Pointer boyutu yariya iner -> daha az bellek, daha iyi cache

</td>
</tr>

<!-- SECTION: GC and Reference Updates -->
<tr>
<td>

## GC and Reference Updates

### Pass-by-value of reference

```java
void caller() {
    Object obj = new Object();
    // ref1 -> heap 0x1000
    doSomething(obj);
    // ref2 (copy) -> heap 0x1000
}

void doSomething(Object param) {
    // param is a value copy of
    // obj's reference
    // Both point to same address
}
```

### During GC relocation

```
Before GC:
  caller stack -> obj   -> 0x1000
  method stack -> param -> 0x1000
  Heap: [Object @ 0x1000]

GC moves object (compaction):
  Heap: [Object @ 0x2000]

After GC (ALL refs updated):
  caller stack -> obj   -> 0x2000
  method stack -> param -> 0x2000
```

</td>
<td>

## GC ve Reference Guncellemesi

### Pass-by-value of reference

```java
void caller() {
    Object obj = new Object();
    // ref1 -> heap 0x1000
    doSomething(obj);
    // ref2 (kopya) -> heap 0x1000
}

void doSomething(Object param) {
    // param, obj'nin reference
    // degerinin kopyasi
    // Ikisi de ayni adresi gosterir
}
```

### GC tasima aninda

```
GC oncesi:
  caller stack -> obj   -> 0x1000
  method stack -> param -> 0x1000
  Heap: [Object @ 0x1000]

GC objeyi tasiyor (compaction):
  Heap: [Object @ 0x2000]

GC sonrasi (TUM ref'ler guncellendi):
  caller stack -> obj   -> 0x2000
  method stack -> param -> 0x2000
```

</td>
</tr>

<!-- SECTION: GC Mechanism -->
<tr>
<td>

## GC Reference Update Mechanism

**STW GCs (G1, Parallel, Serial):**

1. All threads stopped at **safepoint**
2. GC scans all roots: stack, register, static
3. Moves reachable objects
4. Updates all references
5. Threads resume — inconsistent state **never** observed

**Concurrent GCs (ZGC, Shenandoah):**

```
Thread reads reference
  -> Load Barrier kicks in
  -> "Was this object moved?"
     -> Yes: get new addr from
        forwarding pointer
     -> No: use current address
```

Threads don't stop. Barrier ensures every read sees the current address.

</td>
<td>

## GC Reference Guncelleme Mekanizmasi

**STW GC'ler (G1, Parallel, Serial):**

1. Tum thread'ler **safepoint**'te durur
2. GC tum root'lari tarar: stack, register, static
3. Reachable objeleri tasir
4. Tum reference'lari gunceller
5. Thread'ler devam eder — tutarsiz an **asla** gozlemlenemez

**Concurrent GC'ler (ZGC, Shenandoah):**

```
Thread reference okur
  -> Load Barrier devreye girer
  -> "Bu obje tasindi mi?"
     -> Evet: forwarding pointer'dan
        yeni adresi al
     -> Hayir: mevcut adresi kullan
```

Thread'ler durmaz. Barrier sayesinde her okuma guncel adresi gorur.

</td>
</tr>

<!-- SECTION: Summary -->
<tr>
<td>

## Summary Rules

1. **Java reference = opaque handle.** Not a real memory address.
2. **GC can move an object at any time.** All references are updated.
3. **Pass-by-value of reference.** Method copy is also tracked by GC.
4. **3-layer abstraction:** Java ref -> JVM oop -> OS virtual addr -> Physical addr.
5. **Page Table** isolates processes, **GC** keeps references consistent.

</td>
<td>

## Ozet Kurallar

1. **Java reference = opaque handle.** Gercek bellek adresi degil.
2. **GC herhangi bir anda objeyi tasiyabilir.** Tum reference'lar guncellenir.
3. **Pass-by-value of reference.** Method'a gecen kopya da GC tarafindan takip edilir.
4. **3 katmanli soyutlama:** Java ref -> JVM oop -> OS sanal adres -> Fiziksel adres.
5. **Page Table** process'leri izole eder, **GC** reference'lari tutarli tutar.

</td>
</tr>
</table>
