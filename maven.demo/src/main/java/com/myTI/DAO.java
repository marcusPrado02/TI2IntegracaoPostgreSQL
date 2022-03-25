package com.myTI;

import java.sql.*;

public class DAO {
	private Connection conexao;
	
	public DAO() {
		conexao = null;
	}
	//esse metodo vai ficar quase igual o do exemplo
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "teste";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conex��o efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexao NAO efetuada com o postgres -- Driver n��o encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexao NAO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	public boolean insertfilme(Filme filme) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO filmes (nome, ano, genero, estudio) "
					       + "VALUES ("+ filme.getnome()+ ", '" + filme.getano() + "', '"  
					       + filme.getgenero() + "', '" + filme.getestudio() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarfilme(Filme filme) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE filme SET ano = '" + filme.getano() + "', genero = '"  
				       + filme.getgenero() + "', estudio = '" + filme.getestudio() + "'"
					   + " WHERE nome = " + filme.getnome();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirfilme(String nome) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM filme WHERE nome = " + nome);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public Filme[] getfilmes() {
		Filme[] filmes = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM filme");		
	         if(rs.next()){
	             rs.last();
	             filmes = new Filme[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                filmes[i] = new Filme(rs.getString("nome"), rs.getInt("ano"), 
					                         rs.getString("genero"), rs.getString("estudio"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return filmes;
	}

	
	public Filme[] getfilmesbyParamNome(String param) {
		Filme[] filmes = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM filme WHERE filme.nome LIKE '"+param+"'");		
	         if(rs.next()){
	             rs.last();
	             filmes = new Filme[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
		                filmes[i] = new Filme(rs.getString("nome"), rs.getInt("ano"), 
                         		                  rs.getString("genero"), rs.getString("estudio"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return filmes;
	}
	public Filme[] getfilmesbyParamAno(String param) {
		Filme[] filmes = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM filme WHERE filme.ano LIKE "+param+"");		
	         if(rs.next()){
	             rs.last();
	             filmes = new Filme[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
		                filmes[i] = new Filme(rs.getString("nome"), rs.getInt("ano"), 
                         		                  rs.getString("genero"), rs.getString("estudio"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return filmes;
	}

	public Filme[] getfilmesbyParamGenero(String param) {
		Filme[] filmes = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM filme WHERE filme.genero LIKE '"+param+"'");		
	         if(rs.next()){
	             rs.last();
	             filmes = new Filme[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
		                filmes[i] = new Filme(rs.getString("nome"), rs.getInt("ano"), 
                         		                  rs.getString("genero"), rs.getString("estudio"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return filmes;
	}

	public Filme[] getfilmesbyParamEstudio(String param) {
		Filme[] filmes = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM filme WHERE filme.estudio LIKE '"+param+"'");		
	         if(rs.next()){
	             rs.last();
	             filmes = new Filme[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
		                filmes[i] = new Filme(rs.getString("nome"), rs.getInt("ano"), 
                         		                  rs.getString("genero"), rs.getString("estudio"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return filmes;
	}
}
