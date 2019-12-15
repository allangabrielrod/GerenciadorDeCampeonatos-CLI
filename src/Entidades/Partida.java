package Entidades;

import java.sql.Date;

public class Partida {
    private int idPartida;
    private int scoreA;
    private int scoreB;
    private Competidor competidorA;
    private Competidor competidorB;
    private Campeonato campeonato;
    private Date dataRegistro;

    public Partida(){

    }

    public Partida(Competidor compA, Competidor compB){
        this.competidorA = compA;
        this.competidorB = compB;
    }

    public int getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }

    public int getScoreA() {
        return scoreA;
    }

    public void setScoreA(int scoreA) {
        this.scoreA = scoreA;
    }

    public int getScoreB() {
        return scoreB;
    }

    public void setScoreB(int scoreB) {
        this.scoreB = scoreB;
    }

    public Competidor getCompetidorA() {
        return competidorA;
    }

    public void setCompetidorA(Competidor competidorA) {
        this.competidorA = competidorA;
    }

    public Competidor getCompetidorB() {
        return competidorB;
    }

    public void setCompetidorB(Competidor competidorB) {
        this.competidorB = competidorB;
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
}
