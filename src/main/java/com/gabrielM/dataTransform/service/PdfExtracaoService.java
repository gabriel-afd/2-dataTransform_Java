package com.gabrielM.dataTransform.service;

import java.io.*;
import java.nio.charset.StandardCharsets;




public class PdfExtracaoService {

    public void extrairComTabula(String pdfPath, String csvPath) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(
                "java", "-jar", "lib/tabula-1.0.6-SNAPSHOT-jar-with-dependencies.jar",
                "-p", "all", "-l", "-f", "CSV",
                "-o", csvPath,
                pdfPath
        );
        pb.redirectErrorStream(true);
        Process process = pb.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new IOException("Erro ao executar o Tabula: código " + exitCode);
        }

        corrigirCaracteres(csvPath);
    }

    private void corrigirCaracteres(String csvPath) throws IOException {
        File input = new File(csvPath);
        File temp = new File("temp.csv");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(input), "Windows-1252"));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(temp), StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        }

        if (!input.delete() || !temp.renameTo(input)) {
            throw new IOException("Erro ao aplicar correções de caracteres no CSV.");
        }
    }

    public void substituirSiglas(String csvPath) throws IOException {
        File input = new File(csvPath);
        File temp = new File("temp2.csv");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(input), StandardCharsets.UTF_8));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(temp), StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.replace(",OD", ",Seg. Odontológica")
                        .replace(",AMB", ",Seg. Ambulatorial");
                writer.write(line);
                writer.newLine();
            }
        }

        if (!input.delete() || !temp.renameTo(input)) {
            throw new IOException("Erro ao substituir o CSV com as siglas modificadas.");
        }
    }

}