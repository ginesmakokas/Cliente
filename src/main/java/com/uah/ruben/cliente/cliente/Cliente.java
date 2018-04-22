package com.uah.ruben.cliente.cliente;

import java.util.Date;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import libreria.Telemetria;


/**
 * Hello world!
 *
 */
public class Cliente implements Telemetria
{
	Client client = Client.create();
	String postUrl = "http://localhost:8080/JAX-RS-JSON/rest/pecbbdd/data/post";
	int id=1;
	int pid=111;
	int tarea =1;
    public static void main( String[] args )
    {
		Cliente cliente = new Cliente();
		
		while (true) {
			Date date = new Date();
			int random = (int )(Math.random() * 5 + 2);
			cliente.salvarTarea(cliente.id,cliente.pid,cliente.devolverEstado(random),date, cliente.definirDemasDatos());
			cliente.id++;
			cliente.pid++;
		}
				
	}
	public void salvarTarea(int id, int pid, String estado, Date fechaini, String demas) {
		WebResource webResource = this.client.resource(postUrl);
		
		
		String datos= "{\"id\":  "+ id +",\"pid\":  "+ pid +",\"estado\": \" "+ 
		estado +"\",\"fechaini\": \" "+ fechaini.toString() +"\",\"demas\":"+ demas + " }";
		
		ClientResponse respuesta = webResource.type("application/json").post(ClientResponse.class,datos);
		if(respuesta.getStatus()!=201){
			throw new RuntimeException("Error en HTTP: "+ respuesta.getStatus());
		}
		String resultado = respuesta.getEntity(String.class);
		System.out.println("===================================");
		System.out.println("Respuesta desde el servidor: ");
		System.out.println(resultado);
		System.out.println("");
	}
    public String devolverEstado(int num) {
    	String estado="";
    	switch (num) {
		case 1:{
				estado="Ejecucion";
				break;
			}
		case 2:{
			estado="Pausa";
			break;
		}
		case 3:{
			estado="Detenido";
			break;
		}case 4:{
			estado="Terminando";
			break;
		}case 5:{
			estado="Iniciando";
			break;
		}
		default:
			break;
		}
    	return estado;
    }

    public String getTareaPrincipal(int num) {
    	int ta = num -10;
    	if (ta >0) {
    		return ""+ta;
    	}else {
    		if(num==1) {
    			return ""+0;
    		}else {
    			return "1";
    		}
    	}
    }
    public String getServidor(int num) {
    	int ser = num % 5;
    	String servidor="";
    	switch (ser) {
    	case 0:
    	case 5:{
    		servidor="Servidor0";
    		break;
    	}
    	
		case 1:{
			servidor="Servidor1";
			break;
		}
		case 2:{
			servidor="Servidor2";
			break;
		}
		case 3:{
			servidor="Servidor3";
			break;
		}case 4:{
			servidor="Servidor4";
			break;
		}
		default:
			servidor="Servidor5";
			break;
		}
    	return servidor;
    }
    public String getMaquina(int num) {
    	int maq = num % 5;
    	String maquina="";
    	switch (maq) {
    	case 0:
    	case 5:{
    		maquina="Maquina0";
    		break;
    	}
    	
		case 1:{
			maquina="Maquina1";
			break;
		}
		case 2:{
			maquina="Maquina2";
			break;
		}
		case 3:{
			maquina="Maquina3";
			break;
		}case 4:{
			maquina="Maquina4";
			break;
		}
		default:
			maquina="Maquina5";
			break;
		}
    	return maquina;
    }
    public String getUsuario(int num) {
    	int user = num % 5;
    	String usuario="";
    	switch (user) {
    	case 0:
    	case 5:{
    		usuario="Usuario0";
    		break;
    	}
    	
		case 1:{
			usuario="Usuario1";
			break;
		}
		case 2:{
			usuario="Usuario2";
			break;
		}
		case 3:{
			usuario="Usuario3";
			break;
		}case 4:{
			usuario="Usuario4";
			break;
		}
		default:
			usuario="Usuario5";
			break;
		}
    	return usuario;
    }
    public String getInicio(int num) {
    	if (num % 2 == 0) {
    		return "Auto";
    	}else {
    		return "Manual";
    	}
    }
}
