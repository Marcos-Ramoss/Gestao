# Sistema de Gestão de Relatórios e Contratos

Sistema completo de gestão de contratos, recursos, faturamento e relatórios de atividade, desenvolvido com arquitetura em camadas seguindo princípios SOLID e Clean Architecture.

## 📋 Visão Geral

Este projeto é uma aplicação full-stack para gestão de contratos e relatórios de faturamento, permitindo o controle completo de áreas, contratos, ordens de serviço, recursos, faturamentos e geração de relatórios em PDF e Excel.

### Tecnologias Principais

**Backend:**
- Java 17
- Spring Boot 3.5.3
- Spring Security + JWT
- Spring Data JPA
- MySQL 8.0
- Flyway (migrações de banco)
- Apache PDFBox 2.0.29 (geração de PDFs)
- Apache POI 5.2.3 (manipulação Excel)
- SpringDoc OpenAPI 2.5.0 (documentação Swagger)
- Lombok

**Frontend:**
- Angular 19
- PrimeNG 19
- TailwindCSS
- Chart.js
- RxJS
- TypeScript

## 🏗️ Arquitetura

O projeto segue os princípios de **Clean Architecture** e **SOLID**, organizando o código em camadas bem definidas:

### Backend (Clean Architecture)

```
backend/src/main/java/com/service/setebit/gestao/
├── adapter/
│   ├── controller/     # Controllers REST (camada de apresentação)
│   └── dto/           # Data Transfer Objects (DTOs de entrada/saída)
├── application/
│   └── service/       # Lógica de negócio (camada de aplicação)
├── domain/            # Entidades de domínio e interfaces de repositório
├── infrastructure/
│   ├── entity/        # Entidades JPA (camada de infraestrutura)
│   ├── repository/    # Implementações de repositório
│   ├── mapper/        # Mappers Entity ↔ DTO
│   └── exception/     # Exceções customizadas
└── config/            # Configurações (Security, CORS, OpenAPI)
```

### Frontend (Angular)

```
frontend/src/app/
├── pages/             # Componentes de páginas
│   ├── auth/          # Autenticação
│   ├── componente/    # Módulos de funcionalidade
│   └── home/          # Dashboard principal
├── core/              # Guards, interceptors
├── services/          # Serviços HTTP
├── repository/        # Camada de acesso a dados (HTTP)
├── dto/               # Interfaces TypeScript (tipagem)
└── shared/            # Componentes compartilhados
```

## 🚀 Funcionalidades Principais

### 1. Sistema de Autenticação
- Login com JWT
- Registro de usuários
- Gerenciamento de perfil
- Proteção de rotas com AuthGuard

### 2. Gestão de Áreas
- CRUD completo de áreas
- Relacionamento com contratos

### 3. Gestão de Contratos
- CRUD completo de contratos
- Relacionamento com áreas, ordens de serviço, recursos, detalhes e faturamentos
- Identificação por código único

### 4. Ordens de Serviço
- CRUD completo de ordens de serviço
- Vinculação com contratos

### 5. Recursos
- CRUD completo de recursos
- Fator de ajuste por recurso
- Vinculação com contratos

### 6. Detalhes de Contrato
- Gestão de informações complementares (preposto, fiscal, gestor, objeto, processo SEI)
- Um detalhe por contrato

### 7. Faturamento
- CRUD completo de faturamentos
- Detalhes de faturamento
- Vinculação com contratos

### 8. Feriados
- Gestão de feriados
- Controle de dias úteis para relatórios

### 9. Relatórios de Atividade
- Geração de relatórios em PDF e Excel
- Upload e processamento de documentos

## 📦 Pré-requisitos

- **Java 17** ou superior
- **Node.js 18+** e npm
- **Docker** e Docker Compose (para banco de dados)
- **Maven 3.6+** (ou usar o wrapper incluído)
- **Angular CLI 19+** (`npm install -g @angular/cli`)

## 🔧 Instalação e Configuração

### 1. Clone o repositório

```bash
git clone <url-do-repositorio>
cd GestaoRelatorios
```

### 2. Configurar Banco de Dados (Backend)

O projeto utiliza Docker Compose para facilitar a configuração do MySQL:

```bash
cd backend
docker-compose up -d
```

Isso iniciará um container MySQL 8.0 na porta 3306 com:
- **Host:** localhost
- **Porta:** 3306
- **Database:** gestao
- **Username:** root
- **Password:** root

### 3. Configurar Backend

```bash
cd backend

# As migrações do Flyway serão executadas automaticamente ao iniciar a aplicação
# Certifique-se de que o MySQL está rodando antes de iniciar o backend

# Para compilar
./mvnw clean install

# Para executar
./mvnw spring-boot:run
```

O backend estará disponível em: `http://localhost:8080`

**Documentação Swagger:** `http://localhost:8080/swagger-ui.html`

### 4. Configurar Frontend

```bash
cd frontend

# Instalar dependências
npm install

# Iniciar servidor de desenvolvimento
ng serve
# ou
npm start
```

O frontend estará disponível em: `http://localhost:4200`

### 5. Configuração de Ambiente

#### Backend (application.yml)

O arquivo `backend/src/main/resources/application.yml` já está configurado para desenvolvimento local:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/gestao?createDatabaseIfNotExist=true
    username: root
    password: root
```

Para produção, configure as variáveis de ambiente apropriadas.

## 📚 Estrutura de Banco de Dados

O banco de dados é gerenciado pelo **Flyway**, com migrações automáticas. Principais tabelas:

- `usuario` - Usuários do sistema
- `area` - Áreas organizacionais
- `contrato` - Contratos cadastrados
- `contrato_detalhe` - Detalhes complementares dos contratos
- `ordem_servico` - Ordens de serviço
- `recurso` - Recursos vinculados aos contratos
- `faturamento` - Faturamentos
- `faturamento_detalhe` - Detalhes de faturamento
- `feriado` - Feriados cadastrados
- `relatorio_atividade` - Relatórios de atividade

## 🔌 Principais Endpoints da API

### Autenticação
- `POST /auth/login` - Autenticação
- `POST /auth/register` - Registro de usuário
- `GET /auth/perfil` - Obter perfil do usuário autenticado
- `PUT /auth/perfil` - Atualizar perfil

### Áreas
- `GET /areas` - Listar todas as áreas
- `GET /areas/{id}` - Buscar área por ID
- `POST /areas` - Criar área
- `PUT /areas/{id}` - Atualizar área
- `DELETE /areas/{id}` - Deletar área

### Contratos
- `GET /contratos` - Listar todos os contratos
- `GET /contratos/{codigoContrato}` - Buscar contrato por código
- `POST /contratos` - Criar contrato
- `PUT /contratos/{codigoContrato}` - Atualizar contrato
- `DELETE /contratos/{codigoContrato}` - Deletar contrato

### Ordens de Serviço
- `GET /ordens-servico` - Listar todas as OS
- `GET /ordens-servico/{id}` - Buscar OS por ID
- `POST /ordens-servico` - Criar OS
- `PUT /ordens-servico/{id}` - Atualizar OS
- `DELETE /ordens-servico/{id}` - Deletar OS

### Recursos
- `GET /recursos` - Listar todos os recursos
- `GET /recursos/{id}` - Buscar recurso por ID
- `POST /recursos` - Criar recurso
- `PUT /recursos/{id}` - Atualizar recurso
- `DELETE /recursos/{id}` - Deletar recurso

### Faturamento
- `GET /faturamentos` - Listar todos os faturamentos
- `GET /faturamentos/{id}` - Buscar faturamento por ID
- `POST /faturamentos` - Criar faturamento
- `PUT /faturamentos/{id}` - Atualizar faturamento
- `DELETE /faturamentos/{id}` - Deletar faturamento

### Feriados
- `GET /feriados` - Listar todos os feriados
- `GET /feriados/{id}` - Buscar feriado por ID
- `POST /feriados` - Criar feriado
- `PUT /feriados/{id}` - Atualizar feriado
- `DELETE /feriados/{id}` - Deletar feriado

### Relatórios
- `GET /relatorios` - Listar relatórios
- `POST /relatorios` - Criar relatório
- `GET /relatorios/exportar/{id}` - Exportar relatório (PDF/Excel)

> 📖 Para documentação completa da API, acesse `/swagger-ui.html` quando o backend estiver rodando.

## 🛡️ Segurança

- **Autenticação:** JWT (JSON Web Tokens)
- **Senhas:** Criptografadas com BCrypt
- **CORS:** Configurado para desenvolvimento
- **Spring Security:** Proteção de endpoints (exceto `/auth/**`, `/swagger-ui/**`)

## 📝 Documentação Adicional

- [Documentação da API (Backend)](backend/README.md)
- [Contrato da API](backend/docs/contrato_api.md)
- [Swagger/OpenAPI](http://localhost:8080/swagger-ui.html) (quando o backend estiver rodando)

## 🧪 Testes

### Backend
```bash
cd backend
./mvnw test
```

### Frontend
```bash
cd frontend
ng test
```

## 📦 Build para Produção

### Backend
```bash
cd backend
./mvnw clean package
# O JAR será gerado em: target/gestao-0.0.1-SNAPSHOT.jar
java -jar target/gestao-0.0.1-SNAPSHOT.jar
```

### Frontend
```bash
cd frontend
ng build --configuration production
# Os arquivos serão gerados em: dist/
```

## 🔄 Migrações de Banco de Dados

As migrações são gerenciadas automaticamente pelo Flyway ao iniciar a aplicação. Os scripts estão em:
```
backend/src/main/resources/db/migration/
```

## 🏛️ Princípios de Desenvolvimento

Este projeto segue rigorosamente:

- **SOLID** - Single Responsibility, Open/Closed, Liskov Substitution, Interface Segregation, Dependency Inversion
- **Clean Architecture** - Separação clara de responsabilidades
- **DTO Pattern** - Comunicação entre camadas exclusivamente via DTOs
- **Repository Pattern** - Abstração de acesso a dados
- **Dependency Injection** - Inversão de controle
- **Limite de 20 linhas por método** - Métodos pequenos e coesos

## 📞 Contato

(92) 9 8632-1151 - Whats

---

**Desenvolvido com ❤️ seguindo as melhores práticas de desenvolvimento de software.**
