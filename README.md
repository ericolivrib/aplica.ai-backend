<h1 align="center" id="title">Aplica.ai</h1>

<p align="center"><img src="https://socialify.git.ci/ericolivrib/aplica.ai-backend/image?description=1&amp;font=Inter&amp;language=1&amp;name=1&amp;owner=1&amp;pattern=Brick+Wall&amp;theme=Auto" alt="project-image"></p>

<p id="description">API de gerenciamento de candidaturas com assistente de aplicação com Inteligência Artificial.</p>



<h2>🧐 Funcionalidades</h2>

Abaixo estão algumas das principais funcionalidades do projeto:

*   Autenticação e autorização com Spring Security e JWT
*   Geração de PDF a partir de HTML de currículo
*   Insights de candidaturas utilizando modelo Gemma 3 com Ollama (em desenvolvimento)
*   Cadastro de candidaturas
*   Cadastro de etapas da candidatura (ex.: entrevistas testes etc.)

<h2>🛠️ Instalação:</h2>

<p>1. Utilizando um terminal clone este repositório</p>

```
git clone https://github.com/ericolivrib/aplica.ai-backend.git
```

<p>2. Acesse a pasta raiz do projeto</p>

```
cd ./aplica.ai-backend
```

<p>3. Inicie o container com Docker</p>

```
docker compose up -d
```

<p>4. Execute a API com Maven</p>

```
./mvnw spring-boot:run
```



<h2>💻 Tecnologias</h2>

Tecnologias utilizadas neste projeto:

*   Java
*   Spring Boot
*   Spring Security
*   JWT
*   Thymeleaf
*   Maven
*   PostgreSQL
*   MongoDB
*   Spring Data JPA
*   Hibernate
*   Docker
*   H2
*   Ollama
*   LangChain
*   Gemma 3