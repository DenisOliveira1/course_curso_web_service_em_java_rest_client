package br.edu.denis.rest;

import java.io.IOException;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import br.edu.denis.entidade.Cep;

public class restCliente {
	//variaveis
	
	
	//main
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {	
		new restCliente();
	}
	
	
	//consturtor
	public restCliente() throws JsonParseException, JsonMappingException, IOException {
		System.out.println("Conectando...");	
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://denis-pc:8080/soap-rest-servidor/").path("/rest/cep/getcep/21234567");
		
		String toReturn = target.request().get(String.class);//nao precisa ter a classe cep aqui, pois recebe uma string json
		System.out.println("Resultado: "+toReturn);

		//transformando de json em objeto, agora sim precisa da entidade igual nesse projeto
		ObjectMapper mapper = new ObjectMapper();
		Cep meuCep = mapper.readValue(toReturn, Cep.class);		
		System.out.println("meuCep: "+meuCep.toString());
	}

}
