package com.poortoys.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import io.kubernetes.client.openapi.ApiException;

public class AppMain {

    public static void main(String[] args) throws IOException, ApiException {
    	
    	ProcessBuilder processBuilder = new ProcessBuilder();
    	
    	if(System.getProperty("os.name").contains("Windows")) {
    		
    		processBuilder.command("cmd.exe", "/c", "kubectl get no -o json");
    	} else {
    		processBuilder.command("bash", "-c", "kubectl get no -o json");
    		
    	}
    	
    
    	
    	try {

    		Process process = processBuilder.start();

    		StringBuilder output = new StringBuilder();

    		BufferedReader reader = new BufferedReader(
    				new InputStreamReader(process.getInputStream()));

    		String line;
    		while ((line = reader.readLine()) != null) {
    			output.append(line + "\n");
    		}

    		int exitVal = process.waitFor();
    		if (exitVal == 0) {
    			System.out.println("Success!");
    			System.out.println(output);
    			System.exit(0);
    		} else {
    			//abnormal...
    		}

    	} catch (IOException e) {
    		e.printStackTrace();
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    }

}
