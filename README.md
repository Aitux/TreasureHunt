# TreasureHunt

TOUJOURS PULL AVANT DE PUSH !!! SINON VOUS RISQUEZ DE SUPPRIMER DES PARTIES DU PROJET QUE D'AUTRE PERSONNES ONT FAITES !

## Importer le projet

### Windows
- installer https://desktop.github.com/

- lancer GitHub Shell

- Ce placer dans le dossier dans lequel on veut pour importer le projet

- Rentrer les commandes suivantes.

- git clone https://github.com/Aitux/TreasureHunt.git

- cd (nomDuDossier)

- git pull

### Linux
- Ouvrir un terminal 

- sudo -s (puis se log en SU)

- git clone https://github.com/Aitux/TreasureHunt.git

- cd NomDuDossier

- git pull


## Commit et Push

### Pour commit

- Après avoir fait des changements

- commit -m "NomDuCommit"

- git log (pour voir si votre commit a bien été créé)

### Pour Push
- git pull

- git push

## En cas de problème de Remote

- git remote -v

### SI RIEN NE S'AFFICHE

#### Rien de grave vous n'avez juste pas configuré vos remotes

- git remote add origin https://github.com/Aitux/TreasureHunt.git

- Et c'est tout :)

### SI IL Y A DEJA LA REMOTE ORIGIN (fetch et push)

- Surement un problème de proxy

- git config --global http.proxy

- verifier que le proxy de Lille1 n'est pas inscrit

#### Si il est présent : 

- git config --global --unset http.proxy

## Sinon RIP

