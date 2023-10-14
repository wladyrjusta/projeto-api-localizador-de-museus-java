Localizador de Museus

Bem-vindo ao repositório do projeto **Localizador de Museus**!

Descrição do Projeto

Este é um projeto Java que oferece uma API para busca, criação e consulta de museus em um banco de dados simulado.

Requisitos

- Docker

Executando a Aplicação com Docker

1. Clone o repositório:

   `git clone git@github.com:wladyrjusta/projeto-api-localizador-de-museus-java.git`

2. Navegue até o diretório do projeto:

   `cd localizador-de-museus`

3. Crie uma imagem Docker:

   `docker build -t localizador-de-museus .`

4. Inicie um contêiner Docker com a imagem criada:

   `docker run -p 8080:8080 localizador-de-museus`

A aplicação estará disponível em `http://localhost:8080`.
Nas rotas: `museums`, `museums/id`, `museums/closest?lat=Value&lng=Value&max_dist_km=Value`
`/collections/count/{typesList}`

Contribuindo

Sinta-se à vontade para contribuir. Basta criar um fork, fazer alterações e enviar um pull request.

Licença

Distribuído sob a licença MIT.

Divirta-se explorando e desenvolvendo este projeto! Se precisar de ajuda ou informações adicionais, entre em contato.
