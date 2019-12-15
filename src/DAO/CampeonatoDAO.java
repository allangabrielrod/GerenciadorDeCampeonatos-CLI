package DAO;

import Entidades.Campeonato;
import Entidades.Competidor;
import Entidades.Partida;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CampeonatoDAO extends AbstratoDAO{
    public boolean cadastraCampeonato(Campeonato c) {
        boolean sucesso;
        try {
            PreparedStatement stmt = conexao.prepareStatement("insert into campeonato (nome, data_cadastro) values " +
                    "(?, date(now()))");
            stmt.setString(1, c.getNome());

            sucesso = stmt.executeUpdate() == 1;
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            sucesso = false;
        }
        return sucesso;
    }

    public List<Campeonato> listaCampeonatos (){
        List<Campeonato> campeonatos = new ArrayList<Campeonato>();
        try {
            PreparedStatement stmt = conexao.prepareStatement("select * from campeonato");
            ResultSet rst = stmt.executeQuery();

            while (rst.next()){
                Campeonato camp = new Campeonato();
                camp.setId(rst.getInt("id"));
                camp.setNome(rst.getString("nome"));
                camp.setDataRegistro(rst.getDate("data_cadastro"));
                campeonatos.add(camp);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return campeonatos;
    }

    public boolean deletarCampeonato(int id){
        boolean sucesso;
        try {
            PreparedStatement stmt = conexao.prepareStatement("delete from campeonato where id = ?");
            stmt.setInt(1, id);
            sucesso = stmt.executeUpdate() == 1;
            stmt.close();
        }
        catch (SQLException e){
            e.printStackTrace();
            sucesso = false;
        }
        return sucesso;
    }

    public List<Competidor> listarParticipantes(int campeonatoId){
        List<Competidor> participantes = new ArrayList<Competidor>();
        try {
            PreparedStatement stmt = conexao.prepareStatement("select distinct c.id, c.nome from competidor c inner join partida p on c.id = p.time_b or p.time_a where p.campeonato_id = ?");
            stmt.setInt(1, campeonatoId);
            ResultSet rst = stmt.executeQuery();

            while (rst.next()) {
                Competidor competidor = new Competidor();

                competidor.setNome(rst.getString("c.nome"));
                competidor.setId(rst.getInt("c.id"));

                participantes.add(competidor);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return participantes;
    }

    public List<Partida> listarPartidas(int idCampeonato){
        List<Partida> partidas = new ArrayList<Partida>();
        try{
            PreparedStatement stmt = conexao.prepareStatement("select p.id, ca.nome, p.score_a, p.score_b, cb.nome, p.campeonato_id, p.data_registro from partida p inner join competidor ca on p.time_a = ca.id inner join competidor cb on cb.id = p.time_b where p.campeonato_id = ?");
            stmt.setInt(1, idCampeonato);

            ResultSet rst = stmt.executeQuery();
            while (rst.next()){
                Competidor compA = new Competidor();
                Competidor compB = new Competidor();
                Partida pt = new Partida(compA,compB);

                pt.setIdPartida(rst.getInt("p.id"));
                pt.setScoreA(rst.getInt("p.score_a"));
                pt.setScoreB(rst.getInt("p.score_b"));
                compA.setNome(rst.getString("ca.nome"));
                compB.setNome(rst.getString("cb.nome"));
                pt.setDataRegistro(rst.getDate("p.data_registro"));

                partidas.add(pt);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return partidas;
    }
}
