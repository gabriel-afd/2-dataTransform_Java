package com.gabrielM.dataTransform.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil {

    public void compactarParaZip(String csvPath, String zipPath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(zipPath);
             ZipOutputStream zos = new ZipOutputStream(fos);
             FileInputStream fis = new FileInputStream(csvPath)) {

            ZipEntry zipEntry = new ZipEntry(new File(csvPath).getName());
            zos.putNextEntry(zipEntry);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) >= 0) {
                zos.write(buffer, 0, length);
            }

            zos.closeEntry();
        } catch (IOException e){
            System.err.println("Erro ao compactar o arquivo: " + e.getMessage());
            throw e;
        }
    }
}