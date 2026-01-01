# 🌦️ SICTR - Sistema de Informação Climática em Tempo Real (JavaFX)

O **SICTR** é uma aplicação desktop desenvolvida em **Java + JavaFX** que permite consultar informações climáticas em tempo real a partir do nome de uma cidade, consumindo uma API externa de clima.

Projeto ideal para **portfólio**, demonstrando consumo de API, JavaFX, Threads (`Task`) e CSS.

---

## 🚀 Funcionalidades

- Consulta climática por cidade  
- Consumo de API REST (Weather API)  
- Execução assíncrona com `Task`  
- Indicador de carregamento (`ProgressIndicator`)  
- Interface gráfica com JavaFX  
- Estilização com CSS  
- Arquitetura organizada (`app` / `service`)  

---

## 🧱 Estrutura do Projeto

```
SICTR-JAVAFX/
│
├── lib/
│   └── json-20230618.jar
│
├── src/
│   ├── app/
│   │   └── ProjetoSICTR.java
│   │
│   ├── service/
│   │   └── ClimaService.java
│   │
│   └── resources/
│       └── style.css
│
├── out/
│
├── api-key.txt
└── README.md
```

---

## ⚙️ Requisitos

- Java JDK 17 ou superior  
- JavaFX SDK 21+  
- Biblioteca JSON (`json-20230618.jar`)  

---

## 🛠️ Compilação (Linux / Mint)

```bash
javac --module-path $PATH_TO_FX \
--add-modules javafx.controls,javafx.graphics,javafx.base \
-cp lib/json-20230618.jar \
-d out \
src/service/ClimaService.java src/app/ProjetoSICTR.java
```

---

## ▶️ Execução

```bash
java --module-path $PATH_TO_FX \
--add-modules javafx.controls,javafx.graphics,javafx.base \
-cp out:lib/json-20230618.jar \
app.ProjetoSICTR
```

---

## 🔑 API Key

Crie um arquivo chamado `api-key.txt` na raiz do projeto com sua chave da API de clima:

```
SUA_CHAVE_DA_API_AQUI
```

---

## 📌 Tecnologias Utilizadas

- Java  
- JavaFX  
- API REST  
- JSON  
- CSS  
- Programação Assíncrona  

---

## 👨‍💻 Autor

Everton Santos  
Projeto desenvolvido para fins educacionais e portfólio.
