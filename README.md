# TP1 IFT 3913 

- ROMAN GILLES-LESAGE 20175122
- MAHMOUD LABIDI

## Comment utiliser les .jar 

Tloc : java -jar Tloc.jar "chemin_fichier"

Tassert : java -jar Tassert.jar "chemin_fichier"

Tls : java -jar Tls.jar (à l'intérieur d'un dossier) ou java -jar Tls.jar -o "chemin_dossier_out" "chemin_dossier_in" 

TropComp : 

## Précisions sur le TP : 

Les .jar fonctionnent mais nous les avons testés seulement sur environnement linux / mac.  
Pour la question 3, une imprécision de la consigne nous a amené à prendre une décision,
étant donné que nous ne savions pas si le programme devait prendre en paramètre une entrée de dossier 
dans le cas ou il ne génère pas de csv, nous avons préféré procéder de la manière suivante :   
- si on lance le programme sans argument, il faut que celui ci soit éxécuté à l'emplacement du dossier à analyser
- si on lance le programme avec les arguments optionnels, alors on peut l'éxécuter de n'importe où cela ne change rien

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

TropComp : A REMPLIR MAHMOUD 
