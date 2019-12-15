package DAO;

import Entidades.Partida;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PartidaDAO extends AbstratoDAO {
    public boolean cadastrarPartida(Partida p){
        boolean sucesso;
        try {
            PreparedStatement stmt = conexao.prepareStatement("insert into partida (data_registro, time_a, time_b, campeonato_id) values \n" +
                    "\t(date(now()),?,?,?)");
            stmt.setInt(1, p.getCompetidorA().getId());
            stmt.setInt(2, p.getCompetidorB().getId());
            stmt.setInt(3, p.getCampeonato().getId());
            sucesso = stmt.executeUpdate() == 1;
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
            sucesso = false;
        }
        return sucesso;
    }

    public boolean atualizarScore(int scoreA, int ScoreB, int IdPartida){
        boolean sucesso;
        try {
            PreparedStatement stmt = conexao.prepareStatement("update partida set score_a = ?, score_b = ? where id = ?");
            stmt.setInt(1, scoreA);
            stmt.setInt(2, ScoreB);
            stmt.setInt(3, IdPartida);

            sucesso = stmt.executeUpdate() == 1;
            stmt.close();
        }
        catch (SQLException e){
            e.printStackTrace();
            sucesso = false;
        }
        return sucesso;
    }
}
