package com.gabrielM.dataTransform.controller;

import com.gabrielM.dataTransform.service.PdfExtracaoService;
import com.gabrielM.dataTransform.util.FileUtil;

import java.io.File;
import java.util.Scanner;

public class PdfExtracaoController {

    public void executar(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o caminho do PDF: ");
        String pdfPath = scanner.nextLine();


        String csvPath = "saida.csv";
        String zipPath = "Teste_GabrielMedeiros.zip";

        PdfExtracaoService service = new PdfExtracaoService();
        FileUtil fileUtil = new FileUtil();

        try {
            System.out.println("Extraindo dados com Tabula... ");
            service.extrairComTabula(pdfPath,csvPath);

            System.out.println("Substituíndo siglas...");
            service.substituirSiglas(csvPath);

            System.out.println("Compactando CSV...");
            fileUtil.compactarParaZip(csvPath,zipPath);

            System.out.println("Processo concuído com sucesso! Arquivo gerado: " + zipPath);
        } catch (Exception e){
            System.out.println("Erro durante o processo " + e.getMessage());
        }

        System.out.println("Caminho absoluto: " + new File(zipPath).getAbsolutePath());

    }

}
