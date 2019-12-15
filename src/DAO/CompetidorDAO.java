package DAO;

import Entidades.Competidor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompetidorDAO extends AbstratoDAO {

    public boolean cadastrarCompetidor(Competidor c){
        boolean sucesso = false;
        try {
            PreparedStatement stmt = conexao.prepareStatement("insert into competidor (nome, data_cadastro) values (?, date(now()))");
            stmt.setString(1, c.getNome());
            sucesso = stmt.executeUpdate() == 1;
            stmt.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return sucesso;
    }

    public boolean banir (Competidor c){
        boolean sucesso;
        try {
           PreparedStatement stmt = conexao.prepareStatement("Delete from competidor where id=?");
           stmt.setInt(1, c.getId());
           sucesso = stmt.executeUpdate() == 1;
           stmt.close();
        }
        catch (SQLException e){
            e.printStackTrace();
            sucesso = false;
        }
        return sucesso;
    }

    public List<Competidor> listaCompetidores (){
        List<Competidor> competidores = new ArrayList<Competidor>();
        try {
            PreparedStatement stmt = conexao.prepareStatement("select * from competidor");

            ResultSet rst  = stmt.executeQuery();

            while (rst.next()){
                Competidor comp = new Competidor();
                comp.setId(rst.getInt("id"));
                comp.setNome(rst.getString("nome"));
                comp.setDataCadastro(rst.getDate("data_cadastro"));
                competidores.add(comp);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return competidores;
    }
}
