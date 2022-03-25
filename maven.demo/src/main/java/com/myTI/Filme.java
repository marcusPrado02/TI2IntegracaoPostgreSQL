package com.myTI;

public class Filme{
	private String nome;
	private int ano;
	private String genero;
	private String estudio;

	public String getnome(){ return nome; };
	public int getano(){ return ano; };
	public String getgenero(){ return genero; };
	public String getestudio(){ return estudio; };


	public void setnome(String nome){ this.nome = nome; };
	public void setano(int ano){ this.ano = ano; };
	public void setgenero(String genero){ this.genero = genero; };
	public void setestudio(String estudio){ this.estudio = estudio; };

	public Filme(String nome,int ano,String genero,String estudio) {
		this.nome = nome;
		this.ano = ano;
        this.genero = genero;
		this.estudio = estudio;
	}
	public Filme(){}
	
	
}
