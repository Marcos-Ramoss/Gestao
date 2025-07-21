# Erros Comuns no Desenvolvimento

Este documento tem como objetivo registrar os principais erros, armadilhas e problemas recorrentes encontrados durante o desenvolvimento do projeto, bem como suas soluções ou recomendações para evitar que se repitam.

## Como usar
- Sempre que encontrar um erro, registre aqui com uma breve descrição, causa e solução.
- Consulte este arquivo antes de implementar novas funcionalidades ou corrigir bugs.

---

## Lista de Erros Comuns

### 1. Problemas com Formulários e Validação
- **Erro:** Usar `<select>` HTML nativo com ReactiveFormsModule
  - **Causa:** O Angular não reconhece corretamente o valor selecionado, causando falha na validação.
  - **Solução:** Utilize componentes como `p-dropdown` do PrimeNG para integração total com o ReactiveFormsModule.

### 2. Redirecionamento em Modais
- **Erro:** Usar `router.navigate()` dentro de modais para fechar e atualizar listagens
  - **Causa:** O modal não fecha corretamente e a navegação pode causar recarregamento desnecessário da página.
  - **Solução:** Emita eventos para o componente pai fechar o modal e atualizar a lista localmente.

### 3. Mensagens de Sucesso/Erro
- **Erro:** Não exibir feedback visual após operações de CRUD
  - **Causa:** Usuário não sabe se a ação foi concluída com sucesso ou falhou.
  - **Solução:** Utilize o `MessageService` e o `ToastModule` do PrimeNG para exibir mensagens de sucesso e erro.

### 4. Modal de Detalhes Abrindo Vazio
- **Erro:** Modal de detalhes de contrato abre, mas não exibe nada (fica vazio).
  - **Causa:** O ciclo de vida do Angular não garante chamada de ngOnInit em componentes standalone usados via *ngIf em modais. O filtro por codigoContrato não é refeito ao abrir o modal.
  - **Solução:** Usar ngOnChanges para recarregar os detalhes sempre que o codigoContrato mudar, garantindo que a lista seja atualizada ao abrir o modal.

### 5. Uso de window.confirm para Confirmações
- **Erro:** Usar `window.confirm()` para confirmações de exclusão em componentes como ContratoDetalheListComponent e FaturamentoListComponent
  - **Causa:** Quebra a consistência visual do sistema, pois o `window.confirm` usa o estilo nativo do navegador, diferente dos modais PrimeNG usados no resto da aplicação.
  - **Solução:** Substituir por modais de confirmação usando `p-dialog` do PrimeNG, seguindo o padrão já implementado em outros componentes como ContratoListComponent e OrdemServicoListComponent.

### 6. Inconsistência nos Modais de Confirmação de Exclusão
- **Erro:** Diferentes estilos e estruturas para modais de confirmação de exclusão entre componentes
  - **Causa:** Cada desenvolvedor implementa o modal de confirmação de forma diferente, criando inconsistência visual.
  - **Solução:** Padronizar todos os modais de confirmação seguindo o template: ícone de aviso, mensagem clara, botões "Cancelar" e "Excluir" com cores apropriadas (outlined para cancelar, danger para excluir).

---

## Template para novos erros

### X. [Título do erro]
- **Erro:** [Descreva o erro]
- **Causa:** [Explique a causa]
- **Solução:** [Explique a solução ou workaround] 