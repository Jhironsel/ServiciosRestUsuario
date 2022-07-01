package com.practica.puesto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class UsoRestApi {

	public static void main(String[] args) throws IOException {
		JSONObject user = new JSONObject();

		String url = "http://localhost:8080/postUsuario/";

		user.put("nombres", "Prueba");
		user.put("apellidos", "Prueba");
		user.put("estado", false);
		postUsuario(url, user, "POST");

		url = "http://localhost:8080/updateUsuarioById/1";
		user = new JSONObject();
		user.put("nombres", "Prueba2");
		user.put("apellidos", "Prueba2");
		user.put("estado", true);
		postUsuario(url, user, "PUT");

		url = "http://localhost:8080/getUsuariosById/1";
		restUsuario(url, "GET");

		url = "http://localhost:8080/getUsuarios";
		restUsuario(url, "GET");

		url = "http://localhost:8080/deleteUsuarioById/1";
		restUsuario(url, "DELETE");

	}

	public static String restUsuario(String url, String metodo) throws IOException {
		URL objUrl = new URL(url);

		HttpURLConnection con = (HttpURLConnection) objUrl.openConnection();
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestMethod(metodo);

		StringBuilder sb = new StringBuilder();

		int HttpResult = con.getResponseCode();

		if (HttpResult == HttpURLConnection.HTTP_OK) {
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			br.close();
			System.out.println("" + sb.toString());
		} else {
			System.out.println(con.getResponseMessage());
		}
		return sb.toString();
	}

	public static String postUsuario(String url, JSONObject json, String metodo) throws IOException {
		URL objUrl = new URL(url);

		HttpURLConnection con = (HttpURLConnection) objUrl.openConnection();
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestMethod(metodo);

		OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
		wr.write(json.toString());
		wr.flush();

		// display what returns the POST request

		StringBuilder sb = new StringBuilder();

		int HttpResult = con.getResponseCode();

		if (HttpResult == HttpURLConnection.HTTP_OK) {
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			br.close();
			System.out.println("" + sb.toString());
		} else {
			System.out.println(con.getResponseMessage());
		}
		return sb.toString();
	}
}
