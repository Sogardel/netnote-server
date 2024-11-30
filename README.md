# netnote-server

**Clasa Note** reprezintă o notiță și trebuie să conțină cel puțin două câmpuri: id (identificatorul notiței) și content (conținutul notiței).
Este important ca această clasă să aibă un constructor fără argumente și metode getter/setter pentru fiecare câmp, deoarece Jackson le utilizează pentru a deserializa datele din corpul cererii 
(JSON).</br>
**Scopul:**</br>
Clasa Note reprezintă o notiță de tip text care are un identificator unic (id) și un conținut (content). Aceasta este utilizată pentru a stoca datele unei notițe pe care le trimite un client la server sau le salvează pe server.</br>

**Responsabilități principale:** </br>
Stocarea datelor unei notițe: Aceasta include id și content.</br>
Serializarea și deserializarea JSON: Clasa Note trebuie să aibă un constructor implicit și getter/setter pentru fiecare câmp pentru ca Jackson să poată converte obiectele Note într-un format JSON și invers.</br>
Furnizarea de metode pentru accesul la date: Prin intermediul metodelor getter și setter, clasa permite manipularea datelor notiței.
**Exemplu de utilizare:** </br>
Când un client trimite o cerere POST pentru a adăuga o notiță, corpul cererii este deserializat într-un obiect de tip Note pe care serverul îl poate salva.</br>

**Clasa Collection** reprezintă o colecție de notițe. Fiecare colecție are un nume și un map (ConcurrentHashMap) care conține notițele.
Fiecare colecție are un nume unic și conține mai multe note.</br>

**Scopul:** </br>
Clasa Collection reprezintă o colecție de notițe. Fiecare colecție are un nume și o mapare între ID-ul notiței și obiectele Note. Aceasta clasa gestionează operațiile de adăugare a notițelor într-o colecție.</br>

**Responsabilități principale:** </br>
Stocarea notițelor: Colecția conține o mapare între id-urile notițelor și obiectele Note. Folosește un ConcurrentHashMap pentru a stoca notițele, care permite accesul concurent într-un mediu multi-threaded.</br>
Manipularea colecției: Permite adăugarea unei notițe la colecție prin metoda addNote.</br>
Gestionarea numelui colecției: Fiecare colecție are un nume unic care este folosit pentru a o identifica pe server.</br>
**Exemplu de utilizare:** </br>
Atunci când un client adaugă o notiță printr-o cerere POST, notița este adăugată într-o colecție existentă sau într-una nouă, dacă nu există.</br>

**Clasa CollectionController** Aceasta este clasa de controler care gestionează cererile HTTP pentru colecții și note. </br>
**Scopul:** </br>
Clasa CollectionController este un controller Spring care gestionează cererile HTTP primite de la client. Aceasta implementează logica de procesare a cererilor pentru adăugarea de notițe într-o colecție specifică și pentru salvarea/încărcarea colecțiilor din fișiere.</br>

**Responsabilități principale:** </br>
Gestionarea cererilor HTTP: Metodele controllerului răspund la cererile POST (pentru adăugarea notițelor) și la cererile GET (pentru obținerea colecțiilor).</br>
Manipularea datelor colecțiilor: Controllerul folosește clasa Collection pentru a adăuga notițe și pentru a salva colecțiile într-un fișier JSON.</br>
Salvarea și încărcarea colecțiilor din fișier: Atunci când se adaugă o notiță sau la pornirea serverului, colecțiile sunt salvate/încărcate din fișierul collections.json pentru a păstra persistente datele.</br>
**Exemplu de utilizare:** </br>
Când clientul trimite o cerere POST pentru a adăuga o notiță, controllerul validează cererea și o adaugă la colecția specificată, apoi salvează colecțiile într-un fișier JSON.</br>

**Clasa NetNoteApplication** sau clasa principală Spring Boot </br>

**Scopul:** </br>
Clasa Application este punctul de intrare al aplicației Spring Boot. Aceasta clasează aplicația și o inițializează pentru a rula serverul Spring Boot. </br>

**Responsabilități principale:** </br>
Pornirea aplicației Spring Boot: Clasa Application conține metoda main(), care utilizează anotarea @SpringBootApplication pentru a inițializa și porni aplicația Spring Boot.</br>
Configurarea componentelor Spring Boot: Aceasta permite Spring să detecteze și să configureze automat toate componentele (controller, repository, etc.) ale aplicației. </br>
**Exemplu de utilizare:** </br>
La pornirea aplicației, Spring Boot configurează contextul aplicației și pornește serverul web pe portul 8080. Acesta va asculta cererile HTTP și va răspunde cu datele corespunzătoare din colecțiile de notițe. </br>

**Instrucțiuni pentru rulare** </br>
Asigură-te că ai instalat următoarele: </br>

Java 17+ </br>
Orice IDE cu suport Maven.</br>
Maven (dacă rulezi din terminal).</br>
Descarcă codul sursă și incarca proiectul în IDE</br>
Rulează aplicația:</br>

În Eclipse:</br>
Click dreapta pe NetNoteApplication.java și selectează Run As -> Maven build. La goal pui spring-boot:run</br>
În terminal:</br>
mvn spring-boot:run</br>
Testează API-ul:</br>

Folosește un browser sau Postman pentru a accesa:</br>
GET http://localhost:8080/netnote</br>
POST http://localhost:8080/netnote/my-notes</br>
GET http://localhost:8080/netnote/my-notes/notes</br>
POST http://localhost:8080/netnote/my-notes/notes cu corp:</br>
json</br></br>
{
    "id": "1",
    "content": "Aceasta este prima o notita!"
}
</br>

