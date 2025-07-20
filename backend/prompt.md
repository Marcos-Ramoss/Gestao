# Contexto do Projeto - Otimizando Relatórios

## Visão Geral do Projeto

**Nome:** Otimizando Relatórios  
**Tipo:** Sistema de Gestão de Contratos e Relatórios  
**Tecnologia:** Java 17 + Spring Boot 3.5.3  
**Arquitetura:** Clean Architecture  
**Banco de Dados:** MySQL 8.0  
**Autenticação:** JWT + Spring Security  

## Estrutura do Projeto

### Tecnologias e Dependências

**Core:**
- Java 17
- Spring Boot 3.5.3
- Spring Security
- Spring Data JPA
- MySQL 8.0
- Flyway (migrações)

**Utilitários:**
- Lombok (redução de boilerplate)
- Apache PDFBox 2.0.29 (geração de PDFs)
- Apache POI 5.2.3 (manipulação Excel)
- SpringDoc OpenAPI 2.5.0 (documentação)
- JWT 0.9.1 (autenticação)

### Estrutura de Pacotes (Clean Architecture)

```
src/main/java/com/breth/otimizando_relatorios/
├── adapter/
│   ├── controller/     # Controllers REST
│   └── dto/           # Data Transfer Objects
├── application/
│   └── service/       # Lógica de negócio
├── domain/            # Entidades de domínio
├── infrastructure/
│   ├── entity/        # Entidades JPA
│   └── repository/    # Repositórios
├── config/            # Configurações
└── OtimizandoRelatoriosApplication.java
```

## Funcionalidades Principais

### 1. Sistema de Autenticação
- **Endpoints:** `/auth/login`, `/auth/register`, `/auth/perfil`
- **Segurança:** JWT + BCrypt
- **Entidade:** `UserEntity` (id, username, password, role, nome, cpf)

### 2. Gestão de Contratos
- **Entidade Principal:** `ContratoEntity` (codigoContrato, area)
- **Relacionamentos:** 
  - Muitos para um com `AreaEntity`
  - Um para muitos com `OrdemServicoEntity`
  - Um para muitos com `RecursoEntity`
  - Um para muitos com `ContratoDetalheEntity`
  - Um para muitos com `FaturamentoEntity`

### 3. Gestão de Áreas
- **Entidade:** `AreaEntity` (id, nome)
- **Relacionamento:** Uma área pode ter múltiplos contratos

### 4. Ordens de Serviço
- **Entidade:** `OrdemServicoEntity` (id, codigoContrato, numeroOS)
- **Relacionamento:** Muitas OS por contrato

### 5. Recursos
- **Entidade:** `RecursoEntity` (id, codigoContrato, nome, fatorAjuste)
- **Relacionamento:** Muitos recursos por contrato

### 6. Detalhes de Contrato
- **Entidade:** `ContratoDetalheEntity` (id, codigoContrato, preposto, fiscal, gestor, objeto, processoSEI)
- **Relacionamento:** Um detalhe por contrato

### 7. Faturamento
- **Entidade Principal:** `FaturamentoEntity` (id, codigoContrato, objetivo, valor, numeroMedicao)
- **Entidade Detalhe:** `FaturamentoDetalheEntity` (id, idFaturamento, descricao)
- **Relacionamento:** Um faturamento pode ter múltiplos detalhes

### 8. Feriados
- **Entidade:** `FeriadoEntity` (id, data)
- **Uso:** Controle de dias úteis para relatórios

### 9. Relatórios de Atividade
- **Entidade:** `RelatorioAtividadeEntity`
- **Funcionalidade:** Geração de relatórios em PDF e Excel

## Configuração do Banco de Dados

### Scripts de Migração (Flyway)
- `V1__create_table_relatorio_atividade.sql`
- `V2__create_table_usuario.sql`
- `V3__create_tabelas_contratos.sql`
- `V4__insert_usuarios.sql`

### Estrutura das Tabelas

```sql
-- Tabelas principais
AREA (ID_AREA, NOME)
CONTRATO (CODIGO_CONTRATO, ID_AREA)
USUARIO (ID, USERNAME, PASSWORD, ROLE, NOME, CPF)

-- Tabelas relacionadas
ORDEM_SERVICO (ID, CODIGO_CONTRATO, NUMERO_OS)
RECURSO (ID_RECURSO, CODIGO_CONTRATO, NOME, FATOR_AJUSTE)
CONTRATO_DETALHE (ID_CONTRATO_DETALHE, CODIGO_CONTRATO, PREPOSTO, FISCAL, GESTOR, OBJETO, PROCESSO_SEI)
FATURAMENTO (ID_FATURAMENTO, CODIGO_CONTRATO, OBJETIVO, VALOR, NUMERO_MEDICAO)
FATURAMENTO_DETALHE (ID_FATURAMENTO_DETALHE, ID_FATURAMENTO, DESCRICAO)
FERIADO (ID, DATA)
RELATORIO_ATIVIDADE (tabela existente)
```

## Configuração de Segurança

### SecurityConfig
- **CSRF:** Desabilitado
- **Sessão:** STATELESS (JWT)
- **Endpoints Públicos:**
  - `/auth/**`
  - `/relatorios/exportar/**`
  - `/swagger-ui/**`
  - `/v3/api-docs/**`
  - `/areas/**`
  - `/contratos/**`
  - `/contrato-detalhes/**`
  - `/ordens-servico/**`
  - `/recursos/**`
  - `/faturamentos/**`
  - `/faturamento-detalhes/**`

### Autenticação JWT
- **Filtro:** `JwtAuthenticationFilter`
- **Serviço:** `JwtService`
- **UserDetails:** `CustomUserDetailsService`

## Controllers Disponíveis

### AuthController (`/auth`)
- `POST /login` - Autenticação
- `POST /register` - Registro
- `GET /perfil` - Obter perfil
- `PUT /perfil` - Atualizar perfil

### Outros Controllers
- `AreaController` (`/areas`)
- `ContratoController` (`/contratos`)
- `ContratoDetalheController` (`/contrato-detalhes`)
- `OrdemServicoController` (`/ordens-servico`)
- `RecursoController` (`/recursos`)
- `FaturamentoController` (`/faturamentos`)
- `FaturamentoDetalheController` (`/faturamento-detalhes`)
- `FeriadoController` (`/feriados`)
- `RelatorioAtividadeController` (`/relatorios`)

## Configuração de Aplicação

### application.yml
```yaml
spring:
  application:
    name: srv_gestao
  datasource:
    url: jdbc:mysql://localhost:3306/gestao?createDatabaseIfNotExist=true
    username: root
    password: root
  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: true
  flyway:
    enabled: true
    locations: classpath:db/migration

springdoc:
  swagger-ui:
    path: /swagger-ui.html
```

### Docker Compose
```yaml
services:
  mysql:
    image: mysql:8.0
    container_name: mysql-server
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: gestao
    ports:
      - "3306:3306"
    volumes:
      - ./mysql-data:/var/lib/mysql
    restart: always
    command: --lower_case_table_names=1
```

## DTOs Principais

### Autenticação
- `AuthLoginRequest` (username, password)
- `AuthRegisterRequest` (username, password, nome, cpf)
- `AuthResponse` (nome, token)
- `AuthRegisterResponse` (username, nome, cpf)

### Entidades
- `AreaRequest/Response`
- `ContratoRequest/Response`
- `ContratoDetalheRequest/Response`
- `OrdemServicoRequest/Response`
- `RecursoRequest/Response`
- `FaturamentoRequest/Response`
- `FaturamentoDetalheRequest/Response`
- `FeriadoRequest/Response`

## Melhorias Implementadas

### 1. Documentação Swagger Completa
- **Controllers:** Anotados com `@Tag`, `@Operation`, `@ApiResponses`
- **DTOs:** Documentados com `@Schema` e exemplos
- **Parâmetros:** Descritos com `@Parameter`
- **Respostas:** Mapeadas com códigos HTTP apropriados

### 2. Validações Bean Validation
- **DTOs:** Anotados com `@Valid`, `@NotBlank`, `@Size`
- **Controllers:** Validação automática com `@Valid`
- **Mensagens:** Personalizadas em português

### 3. Logging Estruturado
- **SLF4J:** Implementado em controllers e services
- **Níveis:** INFO para operações normais, WARN para alertas, ERROR para erros
- **Contexto:** IDs, nomes e operações logadas

### 4. Tratamento de Erros Global
- **GlobalExceptionHandler:** Tratamento centralizado de exceções
- **Validação:** Erros de Bean Validation mapeados
- **Negócio:** IllegalArgumentException tratada
- **Genérico:** Exception não mapeada com resposta padronizada

### 5. Melhorias no Service
- **Validações:** Verificação de duplicatas, dados obrigatórios
- **Logging:** Operações importantes logadas
- **Tratamento:** Validações de negócio implementadas
- **Documentação:** JavaDoc completo

### 6. Códigos de Status HTTP Apropriados
- **201:** Created para criação
- **200:** OK para operações bem-sucedidas
- **204:** No Content para deleção
- **400:** Bad Request para dados inválidos
- **404:** Not Found para recursos não encontrados
- **500:** Internal Server Error para erros não mapeados

## Pontos de Atenção

### Segurança
- Senhas criptografadas com BCrypt
- JWT para autenticação stateless
- Endpoints sensíveis protegidos

### Banco de Dados
- Migrações controladas com Flyway
- Relacionamentos bem definidos
- Chaves estrangeiras configuradas

### Arquitetura
- Clean Architecture implementada
- Separação clara de responsabilidades
- DTOs para transferência de dados

### Validações e Tratamento de Erros
- Bean Validation implementada
- GlobalExceptionHandler para tratamento centralizado
- Logging estruturado para auditoria
- Respostas de erro padronizadas

## Possíveis Melhorias Futuras

1. **Cache:** Implementar cache para consultas frequentes
2. **Auditoria:** Campos de auditoria (created_at, updated_at)
3. **Testes:** Implementar testes unitários e de integração
4. **Monitoramento:** Health checks e métricas
5. **Configuração:** Externalizar configurações sensíveis
6. **Performance:** Paginação para listagens grandes
7. **Segurança:** Rate limiting e validações adicionais

## Comandos Úteis

### Desenvolvimento
```bash
# Iniciar MySQL
docker-compose up -d

# Executar aplicação
./mvnw spring-boot:run

# Acessar documentação
http://localhost:8080/swagger-ui.html
```

### Build
```bash
# Compilar
./mvnw clean compile

# Testes
./mvnw test

# Package
./mvnw package
```

## Contexto de Desenvolvimento

- **IDE:** IntelliJ IDEA (baseado na pasta .idea)
- **Sistema:** Windows 10
- **Workspace:** /c%3A/work/projetos/7bit/Gestao/backend
- **Shell:** PowerShell

Este contexto deve ser mantido atualizado conforme o projeto evolui. 