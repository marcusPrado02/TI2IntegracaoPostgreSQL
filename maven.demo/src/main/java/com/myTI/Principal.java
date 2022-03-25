package com.myTI;

import java.util.Scanner;

public class Principal {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DAO dao = new DAO();
		
		dao.conectar();
        int resp=0;int update=0;
		
        
		do{
		Filme[] filmes = dao.getfilmes();
        System.out.println("====      Menu        === ");	
		System.out.println("(1)Listar  \n");	
		System.out.println("(2)Inserir  \n");	
		System.out.println("(3)Excluir  \n");	
		System.out.println("(4)Atualizar  \n");	
		System.out.println("(5)Sair  \n");

		
		if(resp == 1){
			
		    System.out.println("==== Mostrar filmes === ");		
		    for(int i = 0; i < filmes.length; i++) {
			    System.out.println(filmes[i].toString());
		    }
		}else if(resp == 2){
			
			System.out.println("Digite o nome");
			String nome = sc.nextLine();
			System.out.println("Digite o ano");
			int ano = sc.nextInt();
			System.out.println("Digite o genero");
			String genero = sc.nextLine();
			System.out.println("Digite o estudio");
			String estudio = sc.nextLine();

			Filme filme = new Filme(nome, ano, genero,estudio);
		    if(dao.insertfilme(filme) == true) {
			   System.out.println("Inserção com sucesso -> " + filme.toString());
		    }
		}else if(resp == 3){

            System.out.println("Digite o nome");
			String nome = sc.nextLine();
            dao.excluirfilme(nome);

		}else if(resp == 4){
			//System.out.println("(1)Nome  \n");	
		    System.out.println("(1)Ano  \n");	
		    System.out.println("(2)Genero  \n");	
		    System.out.println("(3)Estudio  \n");
			update = sc.nextInt();

			//if(update == 1){
			System.out.println("Digite o nome");
			String nome = sc.nextLine();

            for(int i=0;i<filmes.length;i++){ 
			    if(update == 1 && filmes[i].getnome() == nome){
				    System.out.println("Digite o ano");
			         int ano = sc.nextInt();
				     filmes[i].setano(ano);
					 dao.atualizarfilme(filmes[i]);

			    }else if(update == 2 && filmes[i].getnome() == nome){
				    System.out.println("Digite o genero");
			        String genero = sc.nextLine();
				    filmes[i].setestudio(genero);
					dao.atualizarfilme(filmes[i]);

			    }else if(update == 3 && filmes[i].getnome() == nome){
				    System.out.println("Digite o estudio");
			        String estudio = sc.nextLine();
				    filmes[i].setestudio(estudio);
					dao.atualizarfilme(filmes[i]);
			    }
		    }

			//filmes.set("nova senha");
		    //dao.atualizarfilme(filmes);
    
		}else if(resp == 5){
			System.out.println(" Encerrando... ");
			dao.close();
			break;
		}

		}while(true);

		
		sc.close();
	}
}