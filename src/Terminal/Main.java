package Terminal;

import DAO.CampeonatoDAO;
import DAO.CompetidorDAO;
import DAO.PartidaDAO;
import Entidades.Campeonato;
import Entidades.Competidor;
import Entidades.Partida;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void escreveMenu(){
        System.out.println("1 - Adicionar Competidor");
        System.out.println("2 - Adicionar Campeonato");
        System.out.println("3 - Adicionar Partida");
        System.out.println("4 - Atualiza score da partida");
        System.out.println("5 - Banir Competidor pelo id");
        System.out.println("6 - Deletar Campeonato");
        System.out.println("7 - Listar dados de todos os competidores registrados");
        System.out.println("8 - Listar ID e Nome de todos os campeonatos registrados");
        System.out.println("9 - Listar Partidas de um Campeonato");
        System.out.println("10 - Listar Equipes que participaram do campeonato");
        System.out.println("0 - Encerrar.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean florentin = true;

        while(florentin){
                escreveMenu();
                System.out.print("Digite a Opção desejada: ");
                int opc = scanner.nextInt();

                switch (opc){
                    case 1: // Adiciona Competidor
                        Competidor comp = new Competidor();
                        CompetidorDAO compDAO = new CompetidorDAO();

                        System.out.print("Digite o nome do time: ");
                        comp.setNome(scanner.next());

                        if(compDAO.cadastrarCompetidor(comp)){
                            System.out.println("Competidor Registrado com Sucesso!");
                        }
                        else {
                            System.out.println("Ocorreu algum erro");
                        }
                        compDAO.close();
                        break;

                    case 2: //Adiciona Campeonato
                        Campeonato camp = new Campeonato();
                        CampeonatoDAO campDAO = new CampeonatoDAO();

                        System.out.print("Digite o nome do Campeonato: ");
                        camp.setNome(scanner.next());

                        if(campDAO.cadastraCampeonato(camp)) {
                            System.out.println("Campeonato regsitrado com sucesso!");
                        }
                        else {
                            System.out.println("Ocorreu algum erro");
                        }
                        campDAO.close();
                        break;

                    case 3: //Adiciona Partida
                        Partida p = new Partida();
                        PartidaDAO pDAO = new PartidaDAO();
                        Campeonato cmp = new Campeonato();
                        Competidor a = new Competidor();
                        Competidor b = new Competidor();

                        System.out.print("Digite o id do campeonato: ");
                        int campeonatoId = scanner.nextInt();
                        cmp.setId(campeonatoId);

                        System.out.print("Digite o id do primeiro competidor: ");
                        int competidorId = scanner.nextInt();
                        a.setId(competidorId);

                        System.out.print("Digite o id do segundo competidor: ");
                        int competidorId2 = scanner.nextInt();
                        b.setId(competidorId2);

                        p.setCompetidorA(a);
                        p.setCompetidorB(b);
                        p.setCampeonato(cmp);
                        if(pDAO.cadastrarPartida(p)){
                            System.out.println("Partida registrada com sucesso!");
                        }
                        else {
                            System.out.println("Ocorreu algum erro");
                        }
                        pDAO.close();
                        break;
                    case 4: //Atualiza Score
                        PartidaDAO ptDAO = new PartidaDAO();

                        System.out.print("Digite o id da partida: ");
                        int idPartida = scanner.nextInt();

                        System.out.print("Digite o Score do time A: ");
                        int scoreA = scanner.nextInt();

                        System.out.print("Digite o Score do time B: ");
                        int scoreB = scanner.nextInt();

                        if(ptDAO.atualizarScore(scoreA,scoreB,idPartida)) {
                            System.out.println("Score atualizado com sucesso!");
                        }
                        else {
                            System.out.println("Ocorreu algum erro");
                        }
                        ptDAO.close();
                        break;
                    case 5: //Banir competidor pelo id
                        Competidor compBan = new Competidor();
                        CompetidorDAO compBanDAO = new CompetidorDAO();

                        System.out.print("Digite o id do competidor: ");
                        int compId = scanner.nextInt();

                        compBan.setId(compId);

                        if (compBanDAO.banir(compBan)) {
                            System.out.println("Competidor Banido! ");
                        }
                        else {
                            System.out.println("Ocorreu algum erro");
                        }
                        compBanDAO.close();
                        break;
                    case 6://Deletar Campeonato
                        CampeonatoDAO campDelDAO = new CampeonatoDAO();
                        System.out.print("Digite o id do campeonato: ");
                        int idCamp = scanner.nextInt();

                        if(campDelDAO.deletarCampeonato(idCamp)) {
                            System.out.println("Campeonato deletado com sucesso!");
                        }
                        else {
                            System.out.println("Algum erro ocorreu");
                        }

                        campDelDAO.close();
                        break;

                    case 7: //Listar ID e Nome de todos os competidores registrados
                        CompetidorDAO compLista = new CompetidorDAO();

                        if(compLista.listaCompetidores().isEmpty()){
                            System.out.println("Nenhum Competidor foi cadastrado.");
                        }
                        else {
                            System.out.println("==========================");
                            System.out.println("ID | Nome | Data de cadastro");
                            for (int i = 0; i < compLista.listaCompetidores().size(); i++) {
                                System.out.println(compLista.listaCompetidores().get(i).getId() + " - " +
                                        compLista.listaCompetidores().get(i).getNome() + " - " +
                                        compLista.listaCompetidores().get(i).getDataCadastro());
                            }
                            System.out.println("==========================");
                        }
                        compLista.close();
                        break;
                    case 8: //Listar Campeonatos Registrados
                        CampeonatoDAO cmpLista = new CampeonatoDAO();
                        List<Campeonato> lista = cmpLista.listaCampeonatos();

                        if (lista.isEmpty()){
                            System.out.println("Nenhum campeonato registrado.");
                        }
                        else {
                            System.out.println("============================");
                            System.out.println("ID | Nome | Data de Registro");
                            for (int i=0;i < lista.size();i++){
                                System.out.println(lista.get(i).getId() + " - " + lista.get(i).getNome()+ " - " + lista.get(i).getDataRegistro());
                            }
                            System.out.println("============================");
                        }
                        cmpLista.close();
                        break;

                    case 9: //Listar Partidas do Campeonato
                        CampeonatoDAO campLista = new CampeonatoDAO();

                        System.out.print("Digite o id do campeonato: ");
                        int idCampeonato = scanner.nextInt();
                        List<Partida> partidas = campLista.listarPartidas(idCampeonato);

                        if(partidas.isEmpty()){
                            System.out.println("Nenhuma partida foi cadastrada.");
                        }
                        else {
                            System.out.println("=============================================================================================");
                            System.out.println("ID | ID Time A | Score A | Score B | ID Time B | Data Registro da Partida");
                            for (int i = 0; i < campLista.listarPartidas(idCampeonato).size(); i++) {
                                System.out.println(campLista.listarPartidas(idCampeonato).get(i).getIdPartida() + " - " +
                                        campLista.listarPartidas(idCampeonato).get(i).getCompetidorA().getNome() + " - " +
                                        campLista.listarPartidas(idCampeonato).get(i).getScoreA() + " - " +
                                        campLista.listarPartidas(idCampeonato).get(i).getScoreB()+ " - " +
                                        campLista.listarPartidas(idCampeonato).get(i).getCompetidorB().getNome() +" - " +
                                        campLista.listarPartidas(idCampeonato).get(i).getDataRegistro());
                            }
                            System.out.println("=============================================================================================");
                        }
                        campLista.close();
                        break;
                    case 10: //Listar Equipes que participaram do campeonato
                        CampeonatoDAO cmpDAO = new CampeonatoDAO();
                        System.out.print("Digite o ID do Campeonato: ");
                        int campID = scanner.nextInt();

                        List<Competidor> participantes = cmpDAO.listarParticipantes(campID);

                        if (participantes.isEmpty()){
                            System.out.println("Não houveram participantes.");
                        }
                        else {
                            System.out.println("===============================");
                            System.out.println("ID | Nome");
                            for (int i=0;i < participantes.size(); i++) {
                                System.out.println(participantes.get(i).getId() + " - " + participantes.get(i).getNome());
                            }
                            System.out.println("===============================");
                        }
                        break;
                    default:
                        florentin = false;
                        break;
                }

        }
    }
}
