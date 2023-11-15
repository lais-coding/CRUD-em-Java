package br.com.computadores.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import br.com.computadores.factory.ConnectionFactory;
import br.com.computadores.model.Computador;

public class ComputadoresDAO {

	//ESSA É A CLASSE ONDE CONSTARÃO AS OPERAÇÕES CRUD
	
	
	//1 - (C)RUD - CREATE
	
	
	public void save(Computador computador) {
		
		//1.1 Inserimos em uma variável o comando equivalente a criar no SQL
		
		
		// Os pontos de interrogação são espaços reservados para valores posteriores, isto é, quando a consulta SQL for executada
		 
		// Quando a consulta for executada, a substituição dos pontos de interrogação serão feitas com os valores atribuídos.		
		
		
		String sql = "INSERT INTO computadores (nomeCliente, numeroSerie, problemaRelatado, servicoRealizado) VALUES (?, ?, ?, ?)";
		
		
		
		//1.2 Iniciamos uma conexão
		
		Connection conn = null;
		
		
		//1.3 Essa interface atibui os valores aos parãmetros antes de executar a consulta MySQL
		
		PreparedStatement pstm = null;
		
		//1.4 TENTATIVA DE CONEXÃO AO BANCO DE DADOS
		
		
				try {
					
					
					//1.4.1 Chamada da classe responsável pela conexão ao banco de dados
					conn = ConnectionFactory.createConnectionToMySQL(); 
					
					
					//1.4.2 Preparar uma PreparedStatement para executar uma consulta
					
					pstm = (PreparedStatement) conn.prepareStatement(sql);
					
					//1.4.3 Atribuição dos valores aos espaços reservados
					
					pstm.setString(1, computador.getNomeCliente());
					pstm.setString(2, computador.getNumeroSerie());
					pstm.setString(3, computador.getProblemaRelatado());
					pstm.setString(4, computador.getServicoRealizado());
					
					
					//1.4.5 Execução da query (Como se fosse uma confirmação)
					
					pstm.execute();
					
					System.out.println("Registro salvo com sucesso.");
					
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					
					//1.4.6 Fechar as conexões
					
					try {
						
						if(pstm != null) {
							pstm.close();
						}
					
						if(conn != null) {
							conn.close();
				
						}  
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				
	}
				
				
		
		//2 - C(R)UDE - READ
			
			
				//A ideia é retornar uma lista de registros de computadores
				
				public List<Computador> getComputadores() {
					
					
					//2.1 - ARMAZENA-SE EM UMA STRING O COMANDO SQL PARA EFETUAR UMA CONSULTA DOS REGISTROS SALVOS
					
					String sql = "SELECT  * FROM computadores";
					
					
					
					//2.2 - VARIÁVEL LIST QUE FAZ REFERÊNCIA A TABELA, RETORNANDO EM UM ARRAYLIST
					List<Computador> computadores = new ArrayList();
									
					
			//2.3 - CONEXÃO COM O BANCO DE DADOS
					

					//2.3.1 INÍCIO DE UMA CONEXÃO
					
					Connection conn = null;
					
					
					//2.3.2 Essa interface atibui os valores aos parãmetros antes de executar a consulta MySQL
					
					PreparedStatement pstm = null;
					
					
					//2.3.3 Instância de uma classe que armazena os resultados de uma consulta no banco de dados
					
					ResultSet rset = null;
					
					
					
			//2.4 - Tentar a conexão com o banco de dados
					
					try {
						
						//2.4.1 Chamada da classe responsável pela conexão ao banco de dados
						conn = ConnectionFactory.createConnectionToMySQL();
						
						
						///2.4.2 Preparar uma PreparedStatement para executar uma consulta
						pstm = (PreparedStatement) conn.prepareStatement(sql);
						
						
						//2.4.3 Variável vai receber os resultados da consulta
						rset = pstm.executeQuery();
						
						
						//2.4.5 Os resultados de uma consulta podem ser muitos, portanto, cria-se um laço para percorrê-los
						
					
						while(rset.next()) { //Enquanto houver dado a ser percorrido
							
							
							Computador computador = new Computador(); //A cada encontro, uma instância
							
							
							//Pega a informação do banco, separa as informações, 
							// salvamos no Objeto Contato e adicionamos esses objetos em uma lista para apresentar ao usuáiro
							
							//Atribuímos os valores resultantes a instância
							
							computador.setIdComputador(rset.getInt(1)); //índice da coluna
							computador.setNomeCliente(rset.getString("nomeCliente")); 
							computador.setNumeroSerie(rset.getString("numeroSerie"));
							computador.setProblemaRelatado(rset.getString("problemaRelatado"));
							computador.setServicoRealizado(rset.getString("servicoRealizado"));
							
							
							//Depois adicionamos na lista, para aparecer quando solicitarmos a execução desse método
							computadores.add(computador);
						}
							
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							try {
							if(rset != null) {
								rset.close();
							}
							
							
							if(pstm != null) {
								pstm.close();
							}
							
							if(conn != null) {
								conn.close();
							}
							
							} catch(Exception e) {
								e.printStackTrace();
							}
						
					}
					
					//2.5 - Retornamos a lista
					System.out.println("| LISTA DE COMPUTADORES | ");
				
					
					return computadores;
				
				}
				
				
				

		//3 - CR(U)D - UPDATE
				
				public void update (Computador computador) {
					
					//3.1 - ARMAZENAMOS EM UMA STRING O COMANDO SQL PARA UPDATE
					String sql = "UPDATE computadores set nomeCliente = ?, numeroSerie = ?, problemaRelatado = ?, servicoRealizado = ?  WHERE id = ?";
					
					
					//3.2 - CONEXÃO COM O BANCO DE DADOS
							

							//3.2.1 INÍCIO DE UMA CONEXÃO
							
							Connection conn = null;
							
							
							//3.2.2 Essa interface atibui os valores aos parãmetros antes de executar a consulta MySQL
							
							PreparedStatement pstm = null;
							
							
					//3.3 - TENTATIVA DE CONEXÃO COM O BANCO DE DADOS				
					
					try {
						
						//3.3.1 Chamada da classe responsável pela conexão ao banco de dados
						conn = ConnectionFactory.createConnectionToMySQL();
						
						
						///3.3.2 Preparar uma PreparedStatement para executar uma consulta
						pstm = (PreparedStatement) conn.prepareStatement(sql);
						
						
						//3.3.3 Informamos os valores a serem atualizados no UPDATE
						
						pstm.setString(1, computador.getNomeCliente());
						pstm.setString(2, computador.getNumeroSerie());
						pstm.setString(3, computador.getProblemaRelatado());
						pstm.setString(4, computador.getServicoRealizado());
						
						
						//Qual o id do registro que deseja atualizar?
						
						pstm.setInt(5, computador.getIdComputador());
						
						//Executar essa query, essa solciitação de update
						
						pstm.execute();
						
						System.out.println("Registro atualizado com sucesso.");
						
						
						} catch (Exception e) {
							
							e.printStackTrace();
							
						
					} finally {
						try {
							
							if(pstm != null) {
								pstm.close();
							}
							
							if(conn != null) {
								conn.close();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				
					

		//4 - CRU(D) - DELETE
				
				
				
				public void deleteByID(int id) {
					
					
					//4.1 - ARMAZENAR NA STRING O COMANDO SQL PARA EXCLUSÃO
					
					String sql = "DELETE FROM computadores WHERE id = ?";
					
					//4.2 - CONEXÃO COM O BANCO DE DADOS
					

					//4.2.1 INÍCIO DE UMA CONEXÃO
					
					Connection conn = null;
					
					
					//4.2.2 Essa interface atibui os valores aos parãmetros antes de executar a consulta MySQL
					
					PreparedStatement pstm = null;
					
					
					//4.3 - TENTATIVA DE CONEXÃO COM O BANCO DE DADOS	
					
					try {
						
						//4.3.1 Chamada da classe responsável pela conexão ao banco de dados
						conn = ConnectionFactory.createConnectionToMySQL();
						
						
						///4.3.2 Preparar uma PreparedStatement para executar uma consulta
						pstm = (PreparedStatement) conn.prepareStatement(sql);
						
						//4.3.3 Execução do comando com base no ID fornecido
						pstm.setInt(1, id);
						
						pstm.execute();
						
						System.out.println("Registro deletado com sucesso.");
						
						
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							if(pstm != null) {
								pstm.close();
							}
							
							if(conn != null) {
								conn.close();
							}
						
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						
					}
				}
					
				
				
		
	
	
	
	
	
	
}
