package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {
    private String os(){
        return System.getProperty("os.name");
    }
    public String ip(){
        StringBuilder texto = new StringBuilder();
        try {
            String sistema = os();
            Process processo = null;
            if (sistema.contains("Windows")){
                processo = Runtime.getRuntime().exec("ipconfig");
            }
            if (sistema.contains("Linux") || sistema.contains("linux")){
                processo = Runtime.getRuntime().exec("ifconfig");
            }
            assert processo != null;
            InputStream inputStream = processo.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String linha = bufferedReader.readLine();
            String linha1 = " ", linha2 = " ";
            while (linha != null) {
                if (linha.contains("Adaptador Ethernet")) {
                    linha1 = linha;
                }
                if (sistema.contains("Linux")){
                    if (linha.contains("flags")){
                        linha1 = linha;
                    }
                }
                if (linha.contains("IPv4")){
                    String[] vetor = linha.split(" ");
                    int len = vetor.length;
                    for (int i = 0; i < len; i++) {
                        if (vetor[i].contains("IPv4")) {
                            linha2 = vetor[i-1] + " " + vetor[i].replace(".", ": ");
                        }
                        if (vetor[i].contains(":")) {
                            linha2 += " " + vetor[i+1];
                        }
                    }
                }
                if (sistema.contains("Linux")){
                    if (linha.contains("inet ")){
                        String[] vetor = linha.split(" ");
                        int len = vetor.length;
                        for (int i = 0; i < len; i++) {
                            if (vetor[i].contains("inet")) {
                                linha2 = vetor[i] + ": " + vetor[i+1];
                            }
                        }
                    }
                }
                if (!linha1.equals(" ") && !linha2.equals(" ")){
                    texto.append(linha1).append("\n").append(linha2).append("\n");
                    linha1 = " ";
                    linha2 = " ";
                }
                linha = bufferedReader.readLine();
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        } catch (Exception e) {
            System.out.println("Erro ao tentar verificar o IP");
        }
        return texto.toString();
    }
}