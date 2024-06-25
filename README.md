# README PROJET GROUPE 7

## I Compilation sous eclipse
- Importer le projet sur sa machine depuis git :
  - Se placer là ou on veut mettre le projet sur sa machine
  - Lancer la commande "git clone git@gricad-gitlab.univ-grenoble-alpes.fr:michael_perin_private_project/2023_info3_ple/g7.git "
- Lancer eclipse
- Pour importer le fichier sous eclipse : 
  - File -> Import -> Project from Folder or Archive -> Dans la ligne Import source, selectionner le dossier /g7/
  - Appuyer sur Finish

## II Structure du code (M, V, C, B)
### 1- Model
  Le package model s'occupe de la simulation du moteur physique ainsi que de la gestion des entitées en fonction des instructions envoyées par leur automates.
  - La classe Model gère la simulation dans son intégralité en lancant les ste sur chaque entités et en maintenant a jour les liste d'entités.
  - Les Entités sont gérés en une classe principale qui gère tous les comportements communs. Ensuite trois sous classes sont crées pour les joueurs, les obstacles et les mobs :
    - La classe Player ajoute la gestion de densité propre au joueur.
    - Les classes Obstacle et mob servent a séparer pour les faire aparaitre dans la simulation.
  
  - Les actions sont bloquantes et sont donc appelés avec un timer pour en marquer la fin (terminer l'action et liberer l'automate). Les classes *Task sont les taches appélée par les timers pour terminer les actions. Dans le cas de hit par exemple, deux taches sont appelées une a la moitié pour infliger les dégats et une a la fin pour liberer l'automate.
  - ModelConstants contient les constantes de simulation non dépendantes du jeu (vitesse de la simulation et constantes d'ajustement de la physique).

### 2- View
  Le package view s'occupe de l'affichage graphique de la fenetre et de la simulation lié au model a l'aide des avatars.
  - La classe View est la classe principale de l'affichage, elle s'occupe de l'initialisation et de la gestion de tous les elements composants l'affichage ainsi que le calcul des echelles et des positions de la simulation vers l'affichage
  - La classe ViewPort se charge de la gestion de la fenetre et de la camera.
  - La classe Avatar relie les entitées du model avec leur representation graphique dans la vue.
  - La classe SpriteBank charge stock et distribue les images des sprites.
  - La classe Spriteset est une structure pour stocker les elements graphiques d'un avatar (nom, image, couleur)
  - La classe ViewCst contient les constantes de l'affichage chargées à l'initialisation depuis la config.

### 3- Controller
  Le package controller s'occupe de la gestion des evenements lié au interactions de l'utilisateur avec la fenetre et initialise le jeu (model et view).
### 4- Bots/Automaton
  - Le package automaton s'occupe de la gestion des automates des entités ainsi que du lien avec le parser de gal.
  - Le comportement de chaque entité du jeu est régit par un automate.
  - L'association Entité/Automate se fait dans le fichier config. On peut ainsi associer à chaque type d'entité l'automate (écrit en gal) voulu.
  - Le package automaton dispose d'un AutBuilder (constructeur d'automate exécutable à partir d'un AST) qui construit une AutomatonBank (liste d'automates exécutables).
  - Il suffit ensuite au modèle d'appeler le constructeur AutomatonBank() à la création du monde afin de créer de manière unique chaque automate.
  - Ensuite, à la création de chaque entité, une FSM (Final State Machine) est crée (une par entité, qui connait l'entité) stockant l'état courant et aliasant son automate (pour n'en garder qu'une instance même avec plusieurs entités du même type).
  - À chaque step du model, un step de FSM est donc effectué sur chaque entité (disponible). Elle trouve la transition à effectuer (en demandant à l'entité d'en évaluer les conditions) à partir de l'état courant puis demande à l'entité de réaliser l'action associée.

  Particularité du constructeur d'automate : on utilise UNDERSCORE (\_) comme une direction et non une catégorie car la catégorie "tout sauf Void" qu'elle est censée représenter peut être écrite avec une négation de la condition sur la catégorie Void.
  <br>Nous l'utilisons donc pour effectuer un déplacement dans une direction aléatoire : Move(\_) permet donc de remplacer l'expression 12,5% Move(N) / 12,5% Move(NE) / ... / 12,5% Move(NW)

### 5- Config
  Le package Config s'occupe de la gestion des fichiers de configuration et leur interpretation.
  Elle permet de charger la config et contient des méthodes qui donné des nom de parametres, renvoie la bonne valeur dans le bon type;

## III Instruction de lancement
- Pour lancer nos jeux sous eclipse :
  - g7 -> src -> Controller
  - Clic droit sur Main.java
  - Run As -> 1 Java Application
  - Entrer le nom du fichier de config du jeu voulu (ici "config1 pour Divers et "config2" pour Bloons Baloon^^ )
  - Le jeu entré se lance

### Controles des jeux

  - Joueur 1 : 
    - QD deplacements lateraux
    - ZS pour le gonflage/degonflage (mouvement vertical)
    - Space pour Frapper/Tirer
  - Joueur 2 : 
    - Fleches gauche/droite deplacement lateraux
    - Fleches haut/bas pour le gonflage/degonflage (mouvement vertical)
    - Enter pour Frapper/Tirer

### Fichiers de configuration

Le fichier contient 6 champs principaux :
- `View` : les paramètres généraux de la vue et image non liées aux entités.
- `World` : des paramètres du monde a la fois pour la simulation et l'affichage. Taille du monde, image de fond, constantes physiques, densité de spawn, etc.
- `PlayerN` : paramètre du joueur N. Position de départ, vitesse, sprite, taille, durée des actions, constante de physique et de sa gestion.
- `Obstacle` : paramètre des obstacles. Position, taille, sprite, constante de physique.
- `Mobs` : liste de paramètres de chaque mob pour la simulation et l'affichage. Position, taille, sprite, constante de physique, durée des actions, etc.
### Fichiers des automates

- Tous les automates utilisables par notre moteur de jeu se trouvent dans le fichier GalBank.gal dans le dossier gal_bank.
- Tout automate écrit en gal ajouté à ce fichier sera donc parsé et génerera un automate exécutable que l'on pourra associer à un type d'entité dans la config.

Attention : les actions non utilisées dans notre moteur (Take et Pick par exemple, car nous n'avons pas de système de sac à dos) génèreront une exception lors de la création des automates exécutables les contenant. Ces actions n'ont de toute manière aucune correspondance dans le modèle.
<br>De plus, à chaque transition de l'automate est associée au plus une action.

## IV Lien vers la vidéo