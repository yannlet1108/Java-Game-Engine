# Contrat
Groupe 7

## 1) Gameplay

### 1er Jeu : "Divers"
- Jeu de survie/exploration sous-marin
- 2 joueurs indépendants en coopération

#### But du jeu :
Survivre le plus longtemps possible dans l'eau sans mourir ni de manque d'air ni de dégâts des poissons ennemis. Le jeu s'arrête quand les deux joueurs sont morts. Les joueurs peuvent revenir au bateau pour recharger leurs bouteilles d'oxygène.

#### Spécification
- Vue de côté
- Système d'air, une quantité d'oxygène limitée
    - Deux quantités d'oxygène : une pour le gilet, une pour les bouteilles
    - Le gilet des joueurs agit sur leur densité
    - Le remplissage du gilet fait baisser la quantité d'air dans les bouteilles d'oxygène
- Point de départ : bateau à la surface
- Comportement des poissons différent suivant le type de poisson (poisson rouge, requin, ...) qui impacte :
    - Le déplacement (en banc, solitaire, défenseur de bonus...)
    - Le niveau d'agressivité (attaque à vue, légitime défense, ...)
- [facultatif] Bonus qui peuvent apparaître et à récupérer (amélioration des dégats, de la portée d'attaque, bouteille d'oxygène pour remplir ses réserves)

### 2eme Jeu : "Bloons Baloon ^^"
- Jeu de duel aérien (combat de montgolfières)
- 2 joueurs indépendants en compétition

#### But du jeu : 
Détruire la montgolfiere du joueur adverse en lui tirant dessus avec des projectiles. Le jeu s'arrête quand une montgolfière est détruite.

#### Spécification
- Vue de côté
- Les montgolfières peuvent modifier leur densité (chauffage du ballon)
- Les mongolfières tirent des projectiles
- [facultatif] Des oiseaux se déplacent dans le ciel et peuvent s'écraser sur le ballon

## 2) Point commun
- Les deux jeux sont en vue de côté
- Les deux jeux ont un système de densité
  
## 3) MVC
### Moteur
- Génération initiale de terrain fini aléatoire
- Moteur physique en 2D avec densité et viscosité
    - La viscosité est définie initialement en fonction du milieu
    - La densité du milieu est définie initialement et fixe
    - Chaque entité à une densité propre qui peut être modifiée (par elles-mêmes ou d'autres entités)

### Modele
- Systeme métrique

### Vue
- Le cadre suit le centre des deux joueurs
- Le suivi des joueurs est limité par les bords du terrain
- Le zoom s'adapte en fonction de la distance des deux joueurs
    - Taille minimale fixée
    - Taille maximale = taille du terrain
- [facultatif] L'affichage s'adapte à la fenêtre

### Controleur
- Les joueurs sont contrôlés par les touches du clavier (zqsd et flèches + touche pour attaquer/tirer)

## 4) Listes des contraintes
 - Chaque automate a au moins une action (exemple : tout les automates ont une action de déplacement)
 - Les automates peuvent provoquer la disparition d'une entité (exemple : quand un joueur tue un poisson, quand un joueur récupère un bonus)
 - Les automates peuvent provoquer l'apparition d'une entité (exemple : un poisson peut faire apparaitre un autre poisson)
