# Documentação da API – Otimizando Relatórios

## Visão Geral

Esta API foi desenvolvida em Java com Spring Boot, seguindo Clean Architecture e padrões SOLID. O banco de dados utilizado é MySQL (Docker). O sistema gerencia produtos, categorias e relatórios de atividades, com endpoints RESTful.

---

## Endpoints Disponíveis

### Produtos

#### 1. Listar Produtos
- **GET** `/api/produtos`
- **Descrição:** Retorna a lista de todos os produtos cadastrados.
- **Resposta:**
```json
[
  {
    "id": 1,
    "nome": "Produto A",
    "sku": "ABC123",
    "descricao": "Descrição do produto",
    "preco": 99.90,
    "quantidadeEstoque": 10,
    "categoria": {
      "id": 1,
      "nome": "Categoria X"
    }
  }
]
```

#### 2. Buscar Produto por Nome ou Código
- **GET** `/api/produtos/search?query=valor`
- **Descrição:** Busca produtos pelo nome ou código (SKU).
- **Resposta:** Igual ao endpoint de listagem.

#### 3. Cadastrar Produto
- **POST** `/api/produtos`
- **Body:**
```json
{
  "nome": "Produto A",
  "sku": "ABC123",
  "descricao": "Descrição do produto",
  "preco": 99.90,
  "quantidadeEstoque": 10,
  "categoriaId": 1
}
```
- **Resposta:** Produto criado (com ID).

#### 4. Editar Produto
- **PUT** `/api/produtos/{id}`
- **Body:** Igual ao cadastro.
- **Resposta:** Produto atualizado.

#### 5. Excluir Produto
- **DELETE** `/api/produtos/{id}`
- **Confirmação:** Requer confirmação do usuário no frontend antes de enviar a requisição.

#### 6. Visualizar Detalhes do Produto
- **GET** `/api/produtos/{id}`
- **Resposta:** Igual ao item de listagem, apenas um objeto.

---

### Categorias

#### 1. Listar Categorias
- **GET** `/api/categorias`
- **Resposta:**
```json
[
  {
    "id": 1,
    "nome": "Categoria X"
  }
]
```

#### 2. Cadastrar Categoria
- **POST** `/api/categorias`
- **Body:**
```json
{
  "nome": "Categoria X"
}
```
- **Resposta:** Categoria criada.

#### 3. Editar Categoria
- **PUT** `/api/categorias/{id}`
- **Body:** Igual ao cadastro.

#### 4. Excluir Categoria
- **DELETE** `/api/categorias/{id}`

---

### Relatórios de Atividade

#### 1. Listar Relatórios
- **GET** `/api/relatorios`
- **Resposta:**
```json
[
  {
    "id": 1,
    "cliente": "Empresa XPTO",
    "ano": 2024,
    "mes": 5,
    "descricao": "Relatório de maio",
    "outrosCampos": "..."
  }
]
```

#### 2. Cadastrar Relatório (Exemplo)
- **POST** `/api/relatorios`
- **Body:**
```json
{
  "cliente": "Empresa XPTO",
  "ano": 2024,
  "mes": 5,
  "descricao": "Relatório de maio"
}
```
- **Resposta:** Relatório criado.

---

## Regras de Negócio e Validações

- **Produto**
  - Nome e código (SKU) são obrigatórios.
  - Código deve ser único.
  - Preço deve ser maior que zero.
  - Quantidade não pode ser negativa.
- **Categoria**
  - Nome obrigatório.
- **Relatório**
  - Ano e mês obrigatórios (mês como inteiro: 1=Janeiro, 12=Dezembro).
  - Cliente obrigatório.

---

## Contratos (Modelos de Dados)

### Produto
```typescript
export interface Produto {
  id: number;
  nome: string;
  sku: string;
  descricao: string;
  preco: number;
  quantidadeEstoque: number;
  categoria: Categoria;
}
```

### Categoria
```typescript
export interface Categoria {
  id: number;
  nome: string;
}
```

### Relatório de Atividade
```typescript
export interface RelatorioAtividade {
  id: number;
  cliente: string;
  ano: number;
  mes: number; // 1 a 12
  descricao: string;
  // outros campos, se houver
}
```

---

## Observações para o Frontend Angular

- Sempre exibir mensagens de erro vindas do backend.
- Validar campos obrigatórios antes de enviar.
- Confirmar ações críticas (exclusão) com o usuário.
- Para o campo mês, usar um select de 1 a 12 (ou nomes dos meses).
- Para busca, usar debounce para evitar múltiplas requisições rápidas.

---

Se precisar de exemplos de respostas de erro, fluxos de autenticação, ou detalhes de algum endpoint específico, me avise!