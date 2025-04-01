package com.gabrielM.dataTransform;

import com.gabrielM.dataTransform.controller.PdfExtracaoController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataTransformApplication {

	public static void main(String[] args) {
		new PdfExtracaoController().executar();
	}

}
