# tc4-api-streaming

API desenvolvida para Streaming de Video.

## Workflow

![](fluxo_api_tc4-streaming.png)

## Como rodar esta aplicação springboot

1° - No terminal, rode os seguintes comandos (de preferência na mesma ordem):

```
docker network create redelocal --driver=bridge  
docker-compose up
```

2° - Insira essas variáveis de ambiente na aplicação, basta copiar e colar:

```
SERVER_PORT=8080
SPRING_CONFIG_MONGODB_ADMIN_USERNAME=fiap
SPRING_CONFIG_MONGODB_ADMIN_PASSWORD=fiap123
SPRING_CONFIG_MONGODB_SERVER=mongodb
SPRING_CONFIG_MONGODB_URI=mongodb://fiap:fiap123@localhost:27017/
```

> **_NOTA:_**  Ao rodar a aplicação, para acessar o swagger da api basta acessar a seguinte url a depender da porta escolhida
> na variável SERVER_PORT. Ex: http://localhost:8080/swagger-ui/index.html#/

## Squad
Grupo 21

## Repositório

- [tc4-api-streaming](https://github.com/paulohto/fiap-tc4/tree/refact01)

## Commits Guideline

Nós possuimos regras e padrões sobre como as nossas mensagens de commit devem ser formatadas. Isso nós oferece uma
melhor experiência na hora de acompaharmos o history do projeto.

Utilizamos o padrão de [conventional commits](https://www.conventionalcommits.org/), logo todos os commits neste
repositório deverão seguir essa convenção.

### Formato do Commit

Cada mensagem de commit pode conter um **header**, um **body** e um **footer**. O header possui um formato especial
que pode conter um **type**, uma **task** (task ou história do jira) e um **subject**.

```
<type>(<task>): <subject>
<body>
<footer>
```

O **type** e o **subject** são obrigatórios.

Exemplo:

`docs: change README about CICD`

### Tipos de Commits

| Tipo    | Função                                                                      |
| ------- | --------------------------------------------------------------------------- |
| _feat_  | Uma nova implementação / feature                                            |
| _build_ | Modificações que afetam as ferramentas de build                             |
| _ci_    | Modificações nos arquivos e nos scripts de configuração de CI               |
| _docs_  | Modificações em documentações e afins                                       |
| _fix_   | Correção de um bug                                                          |
| _perf_  | Modificações de código para otimizar performance                            |
| _impr_  | Modificações que não necessariamente é um fix ou nova feature               |
| _style_ | Mudanças que não modifiquem lógica (white-space, formatting, prettier, etc) |
| _test_  | Testes que foram adicionados ou corrigidos                                  |
| _chore_ | Uma modificação geral que não se enquandra em nenhum outro padrão           |