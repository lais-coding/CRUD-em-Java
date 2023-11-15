package br.com.computadores.main;

import java.util.Scanner;

import br.com.computadores.dao.ComputadoresDAO;
import br.com.computadores.model.Computador;

public class main {

	public static void main(String[] args) {
		
		while (true) {
			
			
			ComputadoresDAO computadorDao = new ComputadoresDAO();
			
			Scanner scan = new Scanner(System.in);
			System.out.println("\n | SISTEMA DE GERENCIAMENTO DE COMPUTADORES |");
			System.out.println("Selecione uma das opções desejadas: ");
			System.out.println("1 - CREATE (Criar novo registro); \n 2 - READ (Listar registros) \n 3 - UPDATE (Atualizar registro \n 4 - DELETE (Excluir registro)");
			
			int opc = scan.nextInt();
			
			scan.nextLine();
			
			switch(opc) {
			
			
			//1 - CREATE
			case 1:
				
				
				Computador pc = new Computador();
				
				
				System.out.println("DIGITE O NOME DO CLIENTE: ");
				String nome = scan.nextLine();
				pc.setNomeCliente(nome);
				
				System.out.println("DIGITE O Nº DE SÉRIE DO COMPUTADOR: ");
				String serie = scan.nextLine();
				pc.setNumeroSerie(serie);
				
				System.out.println("DIGITE O PROBLEMA RELATADO: ");
				String problema = scan.nextLine();
				pc.setProblemaRelatado(problema);
				
				System.out.println("DIGITE O SERVIÇO REALIZADO: ");
				String servico = scan.nextLine();
				pc.setServicoRealizado(servico);
				
			
				computadorDao.save(pc);
				
				break;
			
			case 2:
				
				//2 - READ (LISTA DE COMPUTADORES)
			
				for(Computador computador : computadorDao.getComputadores()) {
					System.out.println("ID COMPUTADOR : " +computador.getIdComputador() + "\n NOME DO CLIENTE :  " +computador.getNomeCliente());
					System.out.println("NÚMERO DE SÉRIE : " +computador.getNumeroSerie() + "\n PROBLEMA RELATADO :  " +computador.getProblemaRelatado());
					System.out.println("SERVIÇO REALIZADO : " +computador.getServicoRealizado() + "\n\n ");
				}
				
				break;
				
			case 3:
				
				//3 - UPDATE
				
				Computador pc1 = new Computador();
				
				System.out.println("DIGITE O ID DO PC QUE DESEJA ATUALIZAR: ");
				int id = scan.nextInt();
				pc1.setIdComputador(id);
				
				scan.nextLine();
				
				System.out.println("DIGITE O NOME DO CLIENTE: ");
				String nomeCliente = scan.nextLine();
				pc1.setNomeCliente(nomeCliente);
				
				System.out.println("DIGITE O Nº DE SÉRIE DO COMPUTADOR: ");
				String numSerie = scan.nextLine();
				pc1.setNumeroSerie(numSerie);
				
				System.out.println("DIGITE O PROBLEMA RELATADO: ");
				String problem = scan.nextLine();
				pc1.setProblemaRelatado(problem);
				
				System.out.println("DIGITE O SERVIÇO REALIZADO: ");
				String serv = scan.nextLine();
				pc1.setServicoRealizado(serv);
				
				computadorDao.update(pc1);
				
				break;
				
			case 4:
				
				//4 - DELETE
				
				System.out.println("DIGITE O ID DO PC QUE DESEJA EXCLUIR: ");
				int id2 = scan.nextInt();
				computadorDao.deleteByID(id2);
				break;
				
			case 9:
				
				//9 - FINALIZAÇÃO DO PROGRAMA
				
				System.out.println("Programa finalizado");
				System.exit(0);
				
			default:
				
				System.out.println("ESCOLHER ENTRE AS OPCOES CABIVEIS.");
				
				break;
			}
		}
	}
}

	