package com.ftj.demo_quixo.model;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanExpression;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public final class Jeu {

    public static Jeu uniqueInstance;
    public static final Pion couleurVide = Pion.BLANC;
    public static final Pion blanc = Pion.BLANC;


   // public Player player1 = new Player("jordan",);
    public Player player1 = new Player("Joueur CROIX", Pion.CROIX);

    public Player player2 = new Player("Joueur ROND", Pion.ROND);


    public final String nomJoeur(Pion pion) {
        if (couleurVide.equals(pion)) {
            return "Aucun";
        } else if (player1.getPion().equals(pion)) {
            return player1.getNomJoueur();
        } else if (player2.getPion().equals(pion)) {
            return player2.getNomJoueur();
        }
        return "inconnu";
    }

    // creation des deux joeur de la partie (nom + couleur)
    private Player joueurCourrant = new Player(player1.getNomJoueur(), player1.getPion());
    private Player prochainJoueur = new Player(player2.getNomJoueur(), player2.getPion());

    // Variable qui va contenir la position de la piece choisi
    private Position pos = new Position(-1,-1);

    // initialisation du plateau de jeu 5*5, de type SimpleObjectProperty pour faciliter le dataBinding en javaFx
    // et donc faciliter la synchronisation lorsqu'il y'a une modificaion
    @SuppressWarnings("unchecked")
    private final SimpleObjectProperty<Pion>[][] plateau = new SimpleObjectProperty[5][5];


    // recuperation du joueur courant
    public Player getJoueurCourrant() {
        return joueurCourrant;
    }

    // isTopPossi retourne vrai si on peut mettre le pion choisi  tout en haut du plateau
    public boolean isTopPossible() {
        return pos.x != -1 && pos.y != 0;
    }
    // isTopPossi retourne vrai si on peut mettre le pion choisi  tout en bas du plateau
    public boolean isBottomPossible() {
        return pos.x != -1 && pos.y != 4;
    }

    // isTopPossi retourne vrai si on peut mettre le pion choisi a gauche du plateau
    public boolean isLeftPossible() {
        return pos.x != 0;
    }

    // isTopPossi retourne vrai si on peut mettre le pion choisi a droite du plateau
    public boolean isRightPossible() {
        return pos.x != 4;
    }
    /** Boolean intermediate expressions to compute the winner */

    private final BooleanExpression[] player1WonLine = new BooleanExpression[5];
    private final BooleanExpression[] player1WonCol = new BooleanExpression[5];
    private final BooleanExpression[] player2WonLine = new BooleanExpression[5];
    private final BooleanExpression[] player2WonCol = new BooleanExpression[5];
    private final BooleanExpression[] player1WonDiagonale = new BooleanExpression[5];
    private final BooleanExpression[] player2WonDiagonale = new BooleanExpression[5];

    public BooleanExpression player1Won;

    public BooleanExpression player2Won;


    private Jeu() {
        // Builds the board with 5*5 neutral dice
        for (int y=0; y<5; ++y)
            for (int x=0; x<5; ++x) {
                plateau[y][x] = new SimpleObjectProperty<>(Pion.BLANC);
            }
        // Prepares the winning conditions
        player2WonDiagonale[0] = plateau[0][0].isEqualTo(player2.getPion());

        player2WonDiagonale[1] = plateau[4][0].isEqualTo(player2.getPion());

        player1WonDiagonale[0] = plateau[0][0].isEqualTo(player1.getPion());

        player1WonDiagonale[1] = plateau[4][0].isEqualTo(player1.getPion());

        for (int i=0; i<5; ++i) {
            player2WonCol[i] = plateau[0][i].isEqualTo(player2.getPion());
            player1WonCol[i] = plateau[0][i].isEqualTo(player1.getPion());

            player2WonLine[i] = plateau[i][0].isEqualTo(player2.getPion());
            player1WonLine[i] = plateau[i][0].isEqualTo(player1.getPion());
        }

        for (int i=1; i<5; ++i) {
            player2WonDiagonale[0] = player2WonDiagonale[0].and(plateau[i][i].isEqualTo(player2.getPion()));
            player2WonDiagonale[1] = player2WonDiagonale[1].and(plateau[4-i][i].isEqualTo(player2.getPion()));

            player1WonDiagonale[0] = player1WonDiagonale[0].and(plateau[i][i].isEqualTo(player1.getPion()));
            player1WonDiagonale[1] = player1WonDiagonale[1].and(plateau[4-i][i].isEqualTo(player1.getPion()));
            for (int j=0; j<5; ++j) {
                player2WonCol[j] = player2WonCol[j].and(plateau[i][j].isEqualTo(player2.getPion()));
                player1WonCol[j] = player1WonCol[j].and(plateau[i][j].isEqualTo(player1.getPion()));

                player2WonLine[j] = player2WonLine[j].and(plateau[j][i].isEqualTo(player2.getPion()));
                player1WonLine[j] = player1WonLine[j].and(plateau[j][i].isEqualTo(player1.getPion()));
            }
        }
        BooleanExpression player1Won = player1WonDiagonale[0].or(player1WonDiagonale[1]);
        BooleanExpression player2Won = player2WonDiagonale[0].or(player2WonDiagonale[1]);

        for (int i = 0; i < 5; i++) {
            player1Won = player1Won.or(player1WonCol[i]).or(player1WonLine[i]);
            player2Won = player2Won.or(player2WonCol[i]).or(player2WonLine[i]);
        }
        this.player1Won = player1Won;
        this.player2Won = player2Won;

    }
    private Jeu(Jeu jeu) {
        this();
        for (int y=0; y<5; ++y)
            for (int x=0; x<5; ++x)
                this.plateau[y][x].set(jeu.plateau[y][x].get());
        if (this.joueurCourrant.getPion() != jeu.joueurCourrant.getPion())
            changePlayer();
    }

    public static Jeu getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Jeu();
        }
        return uniqueInstance;
    }

    public Position getPos() { return  pos ;}

    /*public ObjectBinding<Background> getPlateau(int x, int y) {
        return Bindings.createObjectBinding(() -> new Background(new BackgroundFill(plateau[y][x].get(), new CornerRadii(5), null)), plateau[y][x]);
    }*/

    public SimpleObjectProperty<Pion> getPlateau(int x, int y) {
        return plateau[y][x];
    }

    private void changePlayer() {
        Player p = joueurCourrant;
        joueurCourrant = prochainJoueur;
        prochainJoueur = p;
    }
    public void newGame() {
        for (int y=0; y<5; ++y)
            for (int x=0; x<5; ++x)
                plateau[y][x].set(couleurVide);
        pos.setX(-1); pos.setY(-1);
        if (Math.random()>0.5) {
            changePlayer();
        }
    } // public newGame
    public boolean isChooseAllowed(int x, int y) {
        return (pos.x == -1) // no piece chosen yet
                && (x == 0 || x == 4 || y == 0 || y == 4) // on the side of the board
                && (plateau[y][x].get() != prochainJoueur.getPion()); // neutral or current player's piece
    }

   public void choosePion(int x, int y) {
        if (isChooseAllowed(x,y)) {
            pos.setX(x);
            pos.setY(y);
        } else
            throw new IllegalStateException("Vous ne pouvez pas choisir cette piece");
    }
    public void cancelChoice() {
        pos.setX(-1);
        pos.setY(-1);
    }
    public void insertAt(int x, int y) {

        InsertionContext insertionContextTop = new InsertionContext(new InsertTop());
        InsertionContext insertionContextBottom = new InsertionContext(new InsertBottom());
        InsertionContext insertionContextLeft = new InsertionContext(new InsertLeft());
        InsertionContext insertionContextRight = new InsertionContext(new InsertRight());

        if (pos.getX() == -1) throw new IllegalStateException("You have first to choose a piece to move!");
        if (pos.getX() == x)
            if (pos.getY() == y) throw new IllegalArgumentException("You cannot put the piece where it was!");
            else if (y == 0) insertionContextTop.getInsertion(plateau, pos, joueurCourrant);
            else if (y == 4) insertionContextBottom.getInsertion(plateau, pos, joueurCourrant);
            else throw new IllegalArgumentException("You have to place your piece at one end of the column!");
        else if (pos.getY() == y)
            if (x == 0)  insertionContextLeft.getInsertion(plateau, pos, joueurCourrant);
            else if (x == 4) insertionContextRight.getInsertion(plateau, pos, joueurCourrant);
            else throw new IllegalArgumentException("You have to place your piece at one end of the row!");
        else throw new IllegalArgumentException("You have to play on the same row/column as the piece you chose!");
        cancelChoice();
        changePlayer();
    }
}
