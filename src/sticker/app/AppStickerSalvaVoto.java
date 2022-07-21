package sticker.app;

import java.util.Scanner;

import sticker.common.util.UtilJson;

public class AppStickerSalvaVoto {
	public static void main(String[] args) {
		UtilJson json = new UtilJson();
		Scanner s = new Scanner(System.in);
		
		System.out.println("Digite seu nome: ");
		String nome = s.nextLine();
		
		System.out.println("Digite o filme: ");
		String filme = s.nextLine();
		
		System.out.println("Digite o seu voto: ");
		String voto = s.nextLine();
		
		s.close();
		json.salvarVoto(nome, filme, voto);
	}

}
