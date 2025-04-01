

Este projeto tem como objetivo automatizar a extração, transformação e compactação de dados da tabela **Rol de Procedimentos e Eventos em Saúde** contida em um PDF da ANS (Agência Nacional de Saúde Suplementar). 

---
---

## Projeto em Java

A aplicação foi desenvolvida em Java seguindo o padrão de arquitetura **MVC** (Model-View-Controller).

## Arquitetura MVC

**MVC (Model-View-Controller)** é um padrão de arquitetura de software que separa a aplicação em três camadas:

- **Model (Modelo)**: representa os dados e a lógica de negócio. No projeto, é a classe `Procedimento`, que representa os registros extraídos do PDF.
- **View (Visão)**: não implementada diretamente, pois trata-se de uma aplicação de console. A saída ocorre via terminal.
- **Controller (Controlador)**: é a classe `PdfExtracaoController`, que orquestra a execução do processo, interagindo com as classes de serviço e utilitários.

---

##  Estrutura e Explicação das Classes

### `PdfExtracaoController` (pacote `controller`)
Responsável por iniciar o processo: recebe o caminho do PDF, chama os serviços de extração, transformação e compactação.

### `PdfExtracaoService` (pacote `service`)
Executa a lógica principal:
- Usa o Tabula para extrair dados do PDF.
- Corrige a codificação do CSV para UTF-8.
- Substitui as siglas `OD` por "Seg. Odontológica" e `AMB` por "Seg. Ambulatorial".

### `FileUtil` (pacote `util`)
Realiza a compactação do arquivo CSV para `.zip`.

### `Procedimento` (pacote `model`)
Classe modelo contendo atributos como `codigo`, `descricao` e `tipo`, representando os dados extraídos do PDF.

---

## Funcionalidades

- Extração de dados do PDF utilizando o Tabula.
- Conversão dos dados em um arquivo CSV.
- Substituição das siglas `OD` e `AMB` por suas descrições completas.
- Compactação do CSV em um arquivo `.zip` nomeado como `Teste_{seu_nome}.zip`.

---

##  Dependências Necessárias

**OBS:** Utilize preferencialmente Eclipse ou IntelliJ como IDE

### Dependências Maven (gerenciadas no `pom.xml`)

```xml
<!-- Spring Boot -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter</artifactId>
</dependency>

<!-- Ferramentas de desenvolvimento (Hot Reload) -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-devtools</artifactId>
  <scope>runtime</scope>
  <optional>true</optional>
</dependency>

<!-- Biblioteca de testes -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-test</artifactId>
  <scope>test</scope>
</dependency>

<!-- OpenCSV para manipulação de CSVs -->
<dependency>
  <groupId>com.opencsv</groupId>
  <artifactId>opencsv</artifactId>
  <version>5.10</version>
</dependency>
```

---

### JAR Manual: Tabula

Este projeto depende do Tabula para fazer a extração dos dados do PDF. Ele deve ser baixado manualmente:

- [Download Tabula JAR](https://github.com/tabulapdf/tabula-java/releases)
- Coloque o arquivo `tabula-1.0.6-SNAPSHOT-jar-with-dependencies.jar` dentro da pasta `lib/` na raiz do projeto.


## Como Executar o Projeto Localmente

**OBS:** Utilize preferencialmente Eclipse ou IntelliJ como IDE

1. Clone o repositório:
```bash
git clone https://github.com/gabriel-afd/1-WebScraping.git
```

2. Navegue até o diretório do projeto:
```bash
cd webScraping_Java
```

3. Adicione o JAR do Tabula na pasta `lib/` com o nome:
```
tabula-1.0.6-SNAPSHOT-jar-with-dependencies.jar
```

4. Compile e execute:
```bash
mvn clean package
java -cp target/dataTransform-0.0.1-SNAPSHOT.jar com.gabrielM.dataTransform.controller.PdfExtracaoController
```

5. Insira o caminho completo para o PDF quando solicitado.


---


##  Tecnologias Utilizadas

- Java 22
- Spring Boot 3.4.4
- Maven
- Tabula (via JAR)
- OpenCSV


---

##  Observação Importante

O projeto depende do JAR do **Tabula** para a extração de dados. Certifique-se de tê-lo disponível em `lib/` para que o processo funcione corretamente.

---

## Autor
**Gabriel Medeiros**
[Email](gmedeiros144@gmail.com)






