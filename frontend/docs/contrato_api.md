# Documentação da API - Otimizando Relatórios

## Sumário
- [Endpoints](#endpoints)
- [Objetos de Dados](#objetos-de-dados)
- [Entidades](#entidades)
- [Serviços](#serviços)
- [Autenticação e Autorização (JWT)](#autenticacao-e-autorizacao-jwt)
- [Observações Gerais](#observações-gerais)

---

## Endpoints

### CRUDs Gerais

#### Feriado
- `POST /feriados` — criar feriado
- `GET /feriados` — listar todos
- `GET /feriados/{id}` — buscar por id
- `PUT /feriados/{id}` — atualizar
- `DELETE /feriados/{id}` — deletar

**Request:**
```json
{
  "data": "2025-12-25"
}
```
**Response:**
```json
{
  "id": 1,
  "data": "2025-12-25"
}
```

#### Área
- `POST /areas` — criar área
- `GET /areas` — listar todas
- `GET /areas/{id}` — buscar por id
- `PUT /areas/{id}` — atualizar
- `DELETE /areas/{id}` — deletar

**Request:**
```json
{
  "nome": "TI"
}
```
**Response:**
```json
{
  "id": 1,
  "nome": "TI"
}
```

#### Contrato
- `POST /contratos` — criar contrato
- `GET /contratos` — listar todos
- `GET /contratos/{codigoContrato}` — buscar por código
- `PUT /contratos/{codigoContrato}` — atualizar
- `DELETE /contratos/{codigoContrato}` — deletar

**Request:**
```json
{
  "codigoContrato": "C-001",
  "idArea": 1
}
```
**Response:**
```json
{
  "codigoContrato": "C-001",
  "idArea": 1
}
```

#### Contrato Detalhe
- `POST /contrato-detalhes` — criar detalhe
- `GET /contrato-detalhes` — listar todos
- `GET /contrato-detalhes/{id}` — buscar por id
- `PUT /contrato-detalhes/{id}` — atualizar
- `DELETE /contrato-detalhes/{id}` — deletar

**Request:**
```json
{
  "codigoContrato": "C-001",
  "preposto": "João",
  "fiscal": "Maria",
  "gestor": "Carlos",
  "objeto": "Serviço X",
  "processoSei": "123456"
}
```
**Response:**
```json
{
  "id": 1,
  "codigoContrato": "C-001",
  "preposto": "João",
  "fiscal": "Maria",
  "gestor": "Carlos",
  "objeto": "Serviço X",
  "processoSei": "123456"
}
```

#### Ordem de Serviço
- `POST /ordens-servico` — criar OS
- `GET /ordens-servico` — listar todas
- `GET /ordens-servico/{id}` — buscar por id
- `PUT /ordens-servico/{id}` — atualizar
- `DELETE /ordens-servico/{id}` — deletar

**Request:**
```json
{
  "codigoContrato": "C-001",
  "numeroOs": "OS-01"
}
```
**Response:**
```json
{
  "id": 1,
  "codigoContrato": "C-001",
  "numeroOs": "OS-01"
}
```

#### Recurso
- `POST /recursos` — criar recurso
- `GET /recursos` — listar todos
- `GET /recursos/{id}` — buscar por id
- `PUT /recursos/{id}` — atualizar
- `DELETE /recursos/{id}` — deletar

**Request:**
```json
{
  "codigoContrato": "C-001",
  "nome": "Recurso A",
  "fatorAjuste": 2
}
```
**Response:**
```json
{
  "id": 1,
  "codigoContrato": "C-001",
  "nome": "Recurso A",
  "fatorAjuste": 2
}
```

#### Faturamento
- `POST /faturamentos` — criar faturamento
- `GET /faturamentos` — listar todos
- `GET /faturamentos/{id}` — buscar por id
- `PUT /faturamentos/{id}` — atualizar
- `DELETE /faturamentos/{id}` — deletar

**Request:**
```json
{
  "codigoContrato": "C-001",
  "objetivo": "Faturar mês 5",
  "valor": 10000.50,
  "numeroMedicao": "MED-01"
}
```
**Response:**
```json
{
  "id": 1,
  "codigoContrato": "C-001",
  "objetivo": "Faturar mês 5",
  "valor": 10000.50,
  "numeroMedicao": "MED-01"
}
```

#### Faturamento Detalhe
- `POST /faturamento-detalhes` — criar detalhe
- `GET /faturamento-detalhes` — listar todos
- `GET /faturamento-detalhes/{id}` — buscar por id
- `PUT /faturamento-detalhes/{id}` — atualizar
- `DELETE /faturamento-detalhes/{id}` — deletar

**Request:**
```json
{
  "idFaturamento": 1,
  "descricao": "Serviço executado"
}
```
**Response:**
```json
{
  "id": 1,
  "idFaturamento": 1,
  "descricao": "Serviço executado"
}
```

---

## Objetos de Dados

- **FeriadoRequest**: `{ "data": "2025-12-25" }`
- **FeriadoResponse**: `{ "id": 1, "data": "2025-12-25" }`

(Demais objetos seguem exemplos anteriores.)

---

## Observações Gerais
- O campo `data` deve ser enviado no formato `YYYY-MM-DD`.
- Todos os endpoints exigem autenticação JWT (exceto /auth/**).
- Os endpoints de CRUD retornam os dados criados/atualizados no formato dos DTOs de response.
- Para dúvidas sobre campos obrigatórios, consulte os exemplos de request acima.
- O contrato pode ser expandido conforme novas funcionalidades forem adicionadas. 