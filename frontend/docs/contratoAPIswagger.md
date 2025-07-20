{
  "openapi": "3.0.1",
  "info": {
    "title": "Otimizando Relatórios API",
    "version": "1.0"
  },
  "servers": [
    {
      "url": "http://localhost:8080",
      "description": "Generated server url"
    }
  ],
  "security": [
    {
      "bearerAuth": []
    }
  ],
  "paths": {
    "/recursos/{id}": {
      "get": {
        "tags": [
          "recurso-controller"
        ],
        "operationId": "buscarPorId",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/RecursoResponse"
                }
              }
            }
          }
        }
      },
      "put": {
        "tags": [
          "recurso-controller"
        ],
        "operationId": "atualizar",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/RecursoRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/RecursoResponse"
                }
              }
            }
          }
        }
      },
      "delete": {
        "tags": [
          "recurso-controller"
        ],
        "operationId": "deletar",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK"
          }
        }
      }
    },
    "/ordens-servico/{id}": {
      "get": {
        "tags": [
          "ordem-servico-controller"
        ],
        "operationId": "buscarPorId_1",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/OrdemServicoResponse"
                }
              }
            }
          }
        }
      },
      "put": {
        "tags": [
          "ordem-servico-controller"
        ],
        "operationId": "atualizar_1",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/OrdemServicoRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/OrdemServicoResponse"
                }
              }
            }
          }
        }
      },
      "delete": {
        "tags": [
          "ordem-servico-controller"
        ],
        "operationId": "deletar_1",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK"
          }
        }
      }
    },
    "/feriados/{id}": {
      "get": {
        "tags": [
          "feriado-controller"
        ],
        "operationId": "buscarPorId_2",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/FeriadoResponse"
                }
              }
            }
          }
        }
      },
      "put": {
        "tags": [
          "feriado-controller"
        ],
        "operationId": "atualizar_2",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/FeriadoRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/FeriadoResponse"
                }
              }
            }
          }
        }
      },
      "delete": {
        "tags": [
          "feriado-controller"
        ],
        "operationId": "deletar_2",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK"
          }
        }
      }
    },
    "/faturamentos/{id}": {
      "get": {
        "tags": [
          "faturamento-controller"
        ],
        "operationId": "buscarPorId_3",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/FaturamentoResponse"
                }
              }
            }
          }
        }
      },
      "put": {
        "tags": [
          "faturamento-controller"
        ],
        "operationId": "atualizar_3",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/FaturamentoRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/FaturamentoResponse"
                }
              }
            }
          }
        }
      },
      "delete": {
        "tags": [
          "faturamento-controller"
        ],
        "operationId": "deletar_3",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK"
          }
        }
      }
    },
    "/faturamento-detalhes/{id}": {
      "get": {
        "tags": [
          "faturamento-detalhe-controller"
        ],
        "operationId": "buscarPorId_4",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/FaturamentoDetalheResponse"
                }
              }
            }
          }
        }
      },
      "put": {
        "tags": [
          "faturamento-detalhe-controller"
        ],
        "operationId": "atualizar_4",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/FaturamentoDetalheRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/FaturamentoDetalheResponse"
                }
              }
            }
          }
        }
      },
      "delete": {
        "tags": [
          "faturamento-detalhe-controller"
        ],
        "operationId": "deletar_4",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK"
          }
        }
      }
    },
    "/contratos/{codigoContrato}": {
      "get": {
        "tags": [
          "contrato-controller"
        ],
        "operationId": "buscarPorId_5",
        "parameters": [
          {
            "name": "codigoContrato",
            "in": "path",
            "required": true,
            "schema": {
              "type": "string"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ContratoResponse"
                }
              }
            }
          }
        }
      },
      "put": {
        "tags": [
          "contrato-controller"
        ],
        "operationId": "atualizar_5",
        "parameters": [
          {
            "name": "codigoContrato",
            "in": "path",
            "required": true,
            "schema": {
              "type": "string"
            }
          }
        ],
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/ContratoRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ContratoResponse"
                }
              }
            }
          }
        }
      },
      "delete": {
        "tags": [
          "contrato-controller"
        ],
        "operationId": "deletar_5",
        "parameters": [
          {
            "name": "codigoContrato",
            "in": "path",
            "required": true,
            "schema": {
              "type": "string"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK"
          }
        }
      }
    },
    "/contrato-detalhes/{id}": {
      "get": {
        "tags": [
          "contrato-detalhe-controller"
        ],
        "operationId": "buscarPorId_6",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ContratoDetalheResponse"
                }
              }
            }
          }
        }
      },
      "put": {
        "tags": [
          "contrato-detalhe-controller"
        ],
        "operationId": "atualizar_6",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/ContratoDetalheRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ContratoDetalheResponse"
                }
              }
            }
          }
        }
      },
      "delete": {
        "tags": [
          "contrato-detalhe-controller"
        ],
        "operationId": "deletar_6",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK"
          }
        }
      }
    },
    "/auth/perfil": {
      "get": {
        "tags": [
          "auth-controller"
        ],
        "operationId": "getPerfil",
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/AuthRegisterResponse"
                }
              }
            }
          }
        }
      },
      "put": {
        "tags": [
          "auth-controller"
        ],
        "operationId": "updatePerfil",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/AuthRegisterRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/AuthRegisterResponse"
                }
              }
            }
          }
        }
      }
    },
    "/areas/{id}": {
      "get": {
        "tags": [
          "area-controller"
        ],
        "operationId": "buscarPorId_7",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/AreaResponse"
                }
              }
            }
          }
        }
      },
      "put": {
        "tags": [
          "area-controller"
        ],
        "operationId": "atualizar_7",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/AreaRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/AreaResponse"
                }
              }
            }
          }
        }
      },
      "delete": {
        "tags": [
          "area-controller"
        ],
        "operationId": "deletar_7",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK"
          }
        }
      }
    },
    "/relatorios/upload/{ano}/{mes}": {
      "post": {
        "tags": [
          "relatorio-atividade-controller"
        ],
        "summary": "Upload de múltiplos relatórios em PDF",
        "operationId": "uploadRelatorios",
        "parameters": [
          {
            "name": "ano",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int32"
            }
          },
          {
            "name": "mes",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int32"
            }
          }
        ],
        "requestBody": {
          "content": {
            "multipart/form-data": {
              "schema": {
                "required": [
                  "files"
                ],
                "type": "object",
                "properties": {
                  "files": {
                    "type": "array",
                    "description": "Arquivos PDF dos relatórios",
                    "items": {
                      "type": "string",
                      "format": "binary"
                    }
                  }
                }
              }
            }
          }
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "array",
                  "items": {
                    "$ref": "#/components/schemas/RelatorioAtividadeDomain"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/recursos": {
      "get": {
        "tags": [
          "recurso-controller"
        ],
        "operationId": "listarTodos_1",
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "array",
                  "items": {
                    "$ref": "#/components/schemas/RecursoResponse"
                  }
                }
              }
            }
          }
        }
      },
      "post": {
        "tags": [
          "recurso-controller"
        ],
        "operationId": "criar",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/RecursoRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/RecursoResponse"
                }
              }
            }
          }
        }
      }
    },
    "/ordens-servico": {
      "get": {
        "tags": [
          "ordem-servico-controller"
        ],
        "operationId": "listarTodos_2",
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "array",
                  "items": {
                    "$ref": "#/components/schemas/OrdemServicoResponse"
                  }
                }
              }
            }
          }
        }
      },
      "post": {
        "tags": [
          "ordem-servico-controller"
        ],
        "operationId": "criar_1",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/OrdemServicoRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/OrdemServicoResponse"
                }
              }
            }
          }
        }
      }
    },
    "/feriados": {
      "get": {
        "tags": [
          "feriado-controller"
        ],
        "operationId": "listarTodos_3",
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "array",
                  "items": {
                    "$ref": "#/components/schemas/FeriadoResponse"
                  }
                }
              }
            }
          }
        }
      },
      "post": {
        "tags": [
          "feriado-controller"
        ],
        "operationId": "criar_2",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/FeriadoRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/FeriadoResponse"
                }
              }
            }
          }
        }
      }
    },
    "/faturamentos": {
      "get": {
        "tags": [
          "faturamento-controller"
        ],
        "operationId": "listarTodos_4",
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "array",
                  "items": {
                    "$ref": "#/components/schemas/FaturamentoResponse"
                  }
                }
              }
            }
          }
        }
      },
      "post": {
        "tags": [
          "faturamento-controller"
        ],
        "operationId": "criar_3",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/FaturamentoRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/FaturamentoResponse"
                }
              }
            }
          }
        }
      }
    },
    "/faturamento-detalhes": {
      "get": {
        "tags": [
          "faturamento-detalhe-controller"
        ],
        "operationId": "listarTodos_5",
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "array",
                  "items": {
                    "$ref": "#/components/schemas/FaturamentoDetalheResponse"
                  }
                }
              }
            }
          }
        }
      },
      "post": {
        "tags": [
          "faturamento-detalhe-controller"
        ],
        "operationId": "criar_4",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/FaturamentoDetalheRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/FaturamentoDetalheResponse"
                }
              }
            }
          }
        }
      }
    },
    "/contratos": {
      "get": {
        "tags": [
          "contrato-controller"
        ],
        "operationId": "listarTodos_6",
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "array",
                  "items": {
                    "$ref": "#/components/schemas/ContratoResponse"
                  }
                }
              }
            }
          }
        }
      },
      "post": {
        "tags": [
          "contrato-controller"
        ],
        "operationId": "criar_5",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/ContratoRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ContratoResponse"
                }
              }
            }
          }
        }
      }
    },
    "/contrato-detalhes": {
      "get": {
        "tags": [
          "contrato-detalhe-controller"
        ],
        "operationId": "listarTodos_7",
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "array",
                  "items": {
                    "$ref": "#/components/schemas/ContratoDetalheResponse"
                  }
                }
              }
            }
          }
        }
      },
      "post": {
        "tags": [
          "contrato-detalhe-controller"
        ],
        "operationId": "criar_6",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/ContratoDetalheRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ContratoDetalheResponse"
                }
              }
            }
          }
        }
      }
    },
    "/auth/register": {
      "post": {
        "tags": [
          "auth-controller"
        ],
        "operationId": "register",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/AuthRegisterRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/AuthRegisterResponse"
                }
              }
            }
          }
        }
      }
    },
    "/auth/login": {
      "post": {
        "tags": [
          "auth-controller"
        ],
        "operationId": "login",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/AuthLoginRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "object"
                }
              }
            }
          }
        }
      }
    },
    "/areas": {
      "get": {
        "tags": [
          "area-controller"
        ],
        "operationId": "listarTodos_8",
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "array",
                  "items": {
                    "$ref": "#/components/schemas/AreaResponse"
                  }
                }
              }
            }
          }
        }
      },
      "post": {
        "tags": [
          "area-controller"
        ],
        "operationId": "criar_7",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/AreaRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/AreaResponse"
                }
              }
            }
          }
        }
      }
    },
    "/relatorios/{ano}/{mes}": {
      "get": {
        "tags": [
          "relatorio-atividade-controller"
        ],
        "summary": "Lista todos os relatórios de atividade",
        "operationId": "listarTodos",
        "parameters": [
          {
            "name": "ano",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int32"
            }
          },
          {
            "name": "mes",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int32"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "application/json": {
                "schema": {
                  "type": "array",
                  "items": {
                    "$ref": "#/components/schemas/RelatorioAtividadeDomain"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/relatorios/exportar/{ano}/{mes}": {
      "get": {
        "tags": [
          "relatorio-atividade-controller"
        ],
        "operationId": "exportarRelatorios",
        "parameters": [
          {
            "name": "ano",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int32"
            }
          },
          {
            "name": "mes",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int32"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK"
          }
        }
      }
    }
  },
  "components": {
    "schemas": {
      "RecursoRequest": {
        "type": "object",
        "properties": {
          "codigoContrato": {
            "type": "string"
          },
          "nome": {
            "type": "string"
          },
          "fatorAjuste": {
            "type": "integer",
            "format": "int64"
          }
        }
      },
      "RecursoResponse": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "codigoContrato": {
            "type": "string"
          },
          "nome": {
            "type": "string"
          },
          "fatorAjuste": {
            "type": "integer",
            "format": "int64"
          }
        }
      },
      "OrdemServicoRequest": {
        "type": "object",
        "properties": {
          "codigoContrato": {
            "type": "string"
          },
          "numeroOs": {
            "type": "string"
          }
        }
      },
      "OrdemServicoResponse": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "codigoContrato": {
            "type": "string"
          },
          "numeroOs": {
            "type": "string"
          }
        }
      },
      "FeriadoRequest": {
        "type": "object",
        "properties": {
          "data": {
            "type": "string",
            "format": "date"
          }
        }
      },
      "FeriadoResponse": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "data": {
            "type": "string",
            "format": "date"
          }
        }
      },
      "FaturamentoRequest": {
        "type": "object",
        "properties": {
          "codigoContrato": {
            "type": "string"
          },
          "objetivo": {
            "type": "string"
          },
          "valor": {
            "type": "number"
          },
          "numeroMedicao": {
            "type": "string"
          }
        }
      },
      "FaturamentoResponse": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "codigoContrato": {
            "type": "string"
          },
          "objetivo": {
            "type": "string"
          },
          "valor": {
            "type": "number"
          },
          "numeroMedicao": {
            "type": "string"
          }
        }
      },
      "FaturamentoDetalheRequest": {
        "type": "object",
        "properties": {
          "idFaturamento": {
            "type": "integer",
            "format": "int64"
          },
          "descricao": {
            "type": "string"
          }
        }
      },
      "FaturamentoDetalheResponse": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "idFaturamento": {
            "type": "integer",
            "format": "int64"
          },
          "descricao": {
            "type": "string"
          }
        }
      },
      "ContratoRequest": {
        "type": "object",
        "properties": {
          "codigoContrato": {
            "type": "string"
          },
          "idArea": {
            "type": "integer",
            "format": "int64"
          }
        }
      },
      "ContratoResponse": {
        "type": "object",
        "properties": {
          "codigoContrato": {
            "type": "string"
          },
          "idArea": {
            "type": "integer",
            "format": "int64"
          }
        }
      },
      "ContratoDetalheRequest": {
        "type": "object",
        "properties": {
          "codigoContrato": {
            "type": "string"
          },
          "preposto": {
            "type": "string"
          },
          "fiscal": {
            "type": "string"
          },
          "gestor": {
            "type": "string"
          },
          "objeto": {
            "type": "string"
          },
          "processoSei": {
            "type": "string"
          }
        }
      },
      "ContratoDetalheResponse": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "codigoContrato": {
            "type": "string"
          },
          "preposto": {
            "type": "string"
          },
          "fiscal": {
            "type": "string"
          },
          "gestor": {
            "type": "string"
          },
          "objeto": {
            "type": "string"
          },
          "processoSei": {
            "type": "string"
          }
        }
      },
      "AuthRegisterRequest": {
        "type": "object",
        "properties": {
          "username": {
            "type": "string"
          },
          "password": {
            "type": "string"
          },
          "nome": {
            "type": "string"
          },
          "cpf": {
            "type": "string"
          }
        }
      },
      "AuthRegisterResponse": {
        "type": "object",
        "properties": {
          "username": {
            "type": "string"
          },
          "nome": {
            "type": "string"
          },
          "cpf": {
            "type": "string"
          }
        }
      },
      "AreaRequest": {
        "type": "object",
        "properties": {
          "nome": {
            "type": "string"
          }
        }
      },
      "AreaResponse": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "nome": {
            "type": "string"
          }
        }
      },
      "RelatorioAtividadeDomain": {
        "type": "object",
        "properties": {
          "cliente": {
            "type": "string"
          },
          "ano": {
            "type": "integer",
            "format": "int32"
          },
          "mes": {
            "type": "integer",
            "format": "int32"
          },
          "colaborador": {
            "type": "string"
          },
          "nomeProjeto": {
            "type": "string"
          },
          "horaTotalProjeto": {
            "type": "number",
            "format": "double"
          }
        }
      },
      "AuthLoginRequest": {
        "type": "object",
        "properties": {
          "username": {
            "type": "string"
          },
          "password": {
            "type": "string"
          }
        }
      }
    },
    "securitySchemes": {
      "bearerAuth": {
        "type": "http",
        "name": "bearerAuth",
        "scheme": "bearer",
        "bearerFormat": "JWT"
      }
    }
  }
}