# Etapa I POO

_Patrasc Andrea - Eduardo, 321CDb_
## Pachete si Entitati

* data (pachet cu clase ce retin informatiile parsate din json. In implementarea efectiva vom folosi doar _ActionData_.)

  * **ActionData** 
  * **AnnualChange**
  * **ChildUpdate**
  * **InitialData**

* database
  * **MainDB** : Clasa care reprezinta baza de date principala a programului. Este de tip **Singleton**
             si contine doar informatii esentiale legate de Santa (budget, lista de cadouri) si lista de copii.

* designpatterns
  * command
    * **Client** : Clientul care va executa actiunile/comenzile prin invoker
    * **Command** : Interfata ce contine metodele implementate de comenzi (comenzile sunt incapsulate in clase)
    * **Invoker**
    * **ProcessChildListCommand**
    * **SendGiftCommand**
    * **UpdateOldDataCommand**
  * factory
    * **ChildFactory** : Clasa care creeaza diferite instante/subclase ale clasei **Child**
                         in functie de varsta.
  * strategy
    * **AverageScoreStrategy** : Interfata care va defini diferitele strategii de calculare a 
                                 average score-ului fiecarui copil in functie varsta.
    * **GiftFactory** : Clasa care genereaza diferite metode de asignare a cadourilor
    * **GiftStrategy** : Interfata care va fi implementata de clase ce incapsuleaza cei 3 algoritmi de
                          asignare a cadourilor.
    * **IdStrategy** : Clasa care incapsuleaza algoritmul de asignare a cadourilor dupa ID-ul copiilor.
    * **NiceScoreStrategy** : Clasa care incapsuleaza algoritmul de asignare a cadourilor dupa nice score-ul copiilor.
    * **NiceScoreCityStrategy** : Clasa care incapsuleaza algoritmul de asignare a cadourilor dupa nice score-ul oraselor.
* entities
    * **Child** : Clasa care retine informatiile relevante pentru un copil. Tot aici vom stoca si 
                  si informatiile ce vor fi incluse in output.
    * **Baby** : Contine metoda aferenta de calcul pentur average score.
    * **Kid** : Contine metoda aferenta de calcul pentur average score.
    * **Teen** : Contine metoda aferenta de calcul pentur average score.
    * **YoungAdult**
    * **Gift** : Clasa care retine informatiile relevante legate de cadouri.
    * **Santa** : Clasa care retine informatiile si metodele relevante legate de Santa.
    * **City** : Clasa care retine numele si scorul average al unui oras

* fileio (pachet care contine clase ajutatoare pentru parsarea inputului si pentru retinerea outputului)
    * **InputLoader** : Clasa care realizeaza parsarea din JSON.
    * **AnnualOutput**
    * **Output** : Clasa care va fi "afisata" la output

* utils
    * **Utils** : Clasa cu metode ajutatoare.

* utilsprocess
    * **UtilsProcess** : Clasa cu metode ajutatorea pentru procesarea inputului. Acestea
                         vor fi apelate in cadrul clasei **ProcessInput**

* main
    * **Test** : Clasa care ajuta la testarea individuala a testelor.
    * **Main** : Clasa care apeleaza entry point - ul programului.

```java
processInput.init(input, filePath2);
```
    

* **ProcessInput** : Clasa care implementeaza functionalitatea ceruta in enunt.
                     Contine metoda **init** care apeleaza metodele **processRoundZero** si **processAllRounds**
                     ce vor procesa prima runda (cu datele initiale) si, aferent, restul rundelor.

# Flow-ul programului

Entry point-ul programului se afla in clasa **Main**. Aici se apeleaza metoda **init()** 
din clasa **processInput** in care are loc implementarea efectiva. Metoda **init** va apela **processRoundZero** pentru
prima runda (runda 0) si apoi **processAllRounds** pentru restul rundelor.
In **processRoundZero** au loc urmatorii pasi :

* Se initializeaza baza de data _mainDb_ cu datele initiala (InitialData).
* Se creeaza copiii in functie de varsta.
* Se elimina adultii.
* Se calculeaza average scor pentru fiecare copil si se aplica modificarile aferente in functie de bonus, **BLACK** elf sau **PINK** elf.
* Se calculeaza budget unit pentru Santa.
* Se calculeaza bugetul alocat pentru fiecare copil in functie de budget unit-ul calculat mai sus.
* Se aplica factory ce creaza diferite strategii de asignare a cadourilor si se trimit cadourile prin metoda **sendGifts**
  care va fi comuna la toate cele 3 strategii. (metodele de asignare a cadourilor modifica doar lista de copii data ca input.
  modifica ordinea lor).
* Se trimite lista de copii la output prin metoda **sendToOutput**

In **processAllRounds** procesul este unul analog celui de mai sus , doar ca functionalitatea
se repeta de **numberOfYears** ori , de fiecare data actualizand baza de date cu noile informatii
preluate din vectorul de **AnualChanges**.