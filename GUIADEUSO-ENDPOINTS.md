## Guia de Uso EndPoints - TC4 Streaming
Este documento fornece uma visão geral da API de Streaming, com seus devidos endpoints para:

## 1. Crie vídeos:

`http://localhost:8080/videos`
<br>Solicitação de exemplo:
```
POST /videos
{
    "id": "E123",
    "titulo": "Platoon",
    "descricao": "Description of the video here",
    "url": "https://www.example.com/todomundoempanicoD123",
    "dataDaPublicacao": "2024-01-29",
    "categoria": "War"
}
```
Resposta esperada::
```
200 OK
{
    "id": "E123",
    "titulo": "Platoon",
    "descricao": "Description of the video here",
    "url": "https://www.example.com/todomundoempanicoD123",
    "dataDaPublicacao": "2024-01-29",
    "categoria": "War"
}
```
## 2. Edite vídeos:

`http://localhost:8080/videos/editar/{videoId}`
<br>Solicitação de exemplo:

```
PUT /videos/editar/659b86cb943bd405b9141c09
{
    "id": "659b86cb943bd405b9141c09",
    "titulo": "Avengers",
    "descricao": "Video 7",
    "url": "https://www.example.com/avengers",
    "dataDaPublicacao": "2024-01-29",
    "categoria": "Action"
}
```
Resposta esperada:
```
200 OK
{
    "id": "659b86cb943bd405b9141c09",
    "titulo": "Avengers",
    "descricao": "Video 7",
    "url": "https://www.example.com/avengers",
    "dataDaPublicacao": "2024-01-29",
    "categoria": "Action"
}
```

## 3. Excluir vídeo:
`http://localhost:8080/videos/apagar/{videoId}`
<br>Solicitação de exemplo:

```
DELETE /videos/apagar/123
```
Resposta esperada:
```
200 OK
```

## 4. Consulte vídeos:
Lista geral classificado por data: `http://localhost:8080/videos`
<br>
Por categoria: `http://localhost:8080/videos/categoria?categoria=Adventure`
<br>
Por título:`http://localhost:8080/videos/titulo?titulo=My Video – 9`
<br>
Por data:`http://localhost:8080/videos/data?data=2024-01-10`
<br>
Por título e data: `http://localhost:8080/videos/titulo-data?titulo=Scary Movie&data=2024-01-10`
<br>
Paginação:`http://localhost:8080/videos/pagina-videos?page=5&size=3`

## 5. Curtir/Favoritar vídeos:
`http://localhost:8080/videos/curtir/{videoId}?curtir={value}`
<br>
Solicitação de exemplo:
```
POST /videos/curtir/E123?curtir=1
```
Resposta esperada:
```
200 OK
{
    "id": "E123",
    "titulo": "Platoon",
    "descricao": "Description of the video here",
    "url": "https://www.example.com/todomundoempanicoD123",
    "dataDaPublicacao": "2024-01-29",
    "categoria": "War",
    "totalCurtidas": 8
}
```
## 6. Consulta por Curtidas/Favoritos:

Lista geral do mais curtidos ao menos curtidos:
```
http://localhost:8080/videos/curtidas-lista-desc
```
Lista principal recomendada com limitação conforme necessidade: 
```
http://localhost:8080/videos/top-recomendados?limit=3
```
