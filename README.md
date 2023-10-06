# TP1 IFT 3913 

- ROMAN GILLES-LESAGE 20175122
- MAHMOUD LABIDI 20176755

## Comment utiliser les .jar 

Tloc : java -jar Tloc.jar "chemin_fichier"

Tassert : java -jar Tassert.jar "chemin_fichier"

Tls : java -jar Tls.jar (à l'intérieur d'un dossier) ou java -jar Tls.jar -o "chemin_dossier_out" "chemin_dossier_in" 

TropComp : 
-    java -jar TropComp.jar "chemin_dossier_in"      ou       java -jar TropComp.jar "chemin_dossier_in" "seuil"
-    java -jar TropComp.jar  -o "chemin_dossier_out" "chemin_dossier_in"      ou      java -jar TropComp.jar  -o "chemin_dossier_out" "chemin_dossier_in" "seuil"


## Précisions sur le TP : 

Les .jar fonctionnent mais nous les avons testés seulement sur environnement linux / mac. Il faut utiliser des chemins absolus pour chaque commande utilisée. 
Pour la question 3, une imprécision de la consigne nous a amené à prendre une décision,
étant donné que nous ne savions pas si le programme devait prendre en paramètre une entrée de dossier 
dans le cas ou il ne génère pas de csv, nous avons préféré procéder de la manière suivante :   
- si on lance le programme sans argument, il faut que celui ci soit éxécuté à l'emplacement du dossier à analyser
- si on lance le programme avec les arguments optionnels, alors on peut l'éxécuter de n'importe où cela ne change rien
Pour la question 4 :
 -si le seuil n'est pas précisé, un seuil de 0.1, soit 10%, est appliqué par défaut.
 - Si aucun chemin de dossier de sortie n'est spécifié, la réponse s'affiche directement dans le terminal. Sinon, un fichier contenant le résultat est créé. Si le fichier est vide, cela signifie qu'aucune classe suspecte n'a été détectée.
## Utilisation d'ia génératives : 
Nous avons utilisé les ias génératives dans notre tp de la manière suivante:

Tloc : code entièrement généré par chat gpt sauf pour la méthode main, il y'a probablement du code inutile mais ça fonctionne  


Tassert : idem mais pas de code inutile        


Tls: pour cette classe, nous avons dabord réfléchis à la structure de la classe (comment articuler notre code, logique)
puis nous avons demandé à chat gpt de nous générer les fonctions une à une
(l'ia ne nous donnait pas de résultats suffisamment clairs avec nos requêtes complexes )
- comment faire une fonction pour parcourir un dossier récursivement ?
- comment découper / manipuler les chemins de fichiers pour qu'ils correspondent à nos besoin dans le TP ?   

Le reste du code a été fait "à la main" + assistance de co pilot 

TropComp :Ici, notre démarche a été similaire à celle de Tls. Nous avons commencé par définir la structure et la logique de la classe. Une fois cette étape conceptuelle achevée, nous avons sollicité ChatGPT pour des fonctions ou des logiques spécifiques. du style ;  Comment filtrer les classes en fonction d'un seuil donné ?Comment trier et comparer les métriques pour identifier les classes suspectes ?
Grâce à ces questions ciblées, nous avons obtenu des fragments de code pertinents que nous avons ensuite intégrés et adaptés à notre logique. Cependant, notamment la gestion des arguments de la ligne de commande et la logique de sortie (affichage terminal ou fichier CSV), a été développée manuellement.
L'un des avantages majeurs de l'utilisation de ChatGPT a été le gain de temps. Plutôt que de passer de longues heures à chercher des solutions sur Stack Overflow ou d'autres forums en ligne, j'ai pu obtenir des réponses rapides et précises directement , ce qui m'a permis de me concentrer davantage sur la logique et la structure du programme.

rapport :L'utilisation de ChatGPT pour cette section a été principalement axée sur la clarification des concepts et la suggestion de méthodes d'analyse. Cela m'a permis de gagner du temps en évitant de longues recherches sur internet pour comprendre certaines métriques ou méthodologies. Toutefois, la rédaction finale, la structure du rapport et les conclusions tirées sont le fruit de mon travail et de ma réflexion personnelle, bien que guidées par les informations obtenues grâce à l'IA.
