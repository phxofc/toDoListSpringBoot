To-Do List API
Este projeto é uma API para gerenciamento de tarefas (To-Do List), oferecendo funcionalidades como criação de tarefas, listagem de tarefas do usuário e atualização de tarefas.

Endpoints
1. Criar Tarefa
Endpoint: POST /tasks/
Descrição: Cria uma nova tarefa na lista do usuário.
Parâmetros de Entrada: Objeto JSON representando os detalhes da tarefa.
Exemplo de Uso:
json
Copy code
{
  "title": "Estudar Spring Boot",
  "description": "Concluir o tutorial sobre Spring Boot",
  "startAt": "2023-11-13T14:00:00",
  "endAt": "2023-11-13T18:00:00"
}
Resposta de Sucesso: Retorna a tarefa criada com o status HTTP 200 - OK.
2. Listar Tarefas
Endpoint: GET /tasks/
Descrição: Lista todas as tarefas do usuário.
Parâmetros de Entrada: Nenhum.
Resposta de Sucesso: Retorna uma lista de tarefas associadas ao usuário autenticado.
3. Atualizar Tarefa
Endpoint: PUT /tasks/{id}
Descrição: Atualiza os detalhes de uma tarefa específica.
Parâmetros de Entrada:
id: Identificador único da tarefa a ser atualizada.
Objeto JSON representando os novos detalhes da tarefa.
Exemplo de Uso:
json
Copy code
{
  "title": "Estudar Spring Boot - Atualizado",
  "description": "Concluir o tutorial sobre Spring Boot e fazer os exercícios",
  "startAt": "2023-11-13T14:00:00",
  "endAt": "2023-11-13T20:00:00"
}
Resposta de Sucesso: Retorna a tarefa atualizada com o status HTTP 200 - OK.
4. Atualização Parcial de Tarefa
Endpoint: PATCH /tasks/{id}
Descrição: Atualiza parcialmente os detalhes de uma tarefa específica.
Parâmetros de Entrada:
id: Identificador único da tarefa a ser atualizada.
Objeto JSON representando os campos a serem atualizados.
Exemplo de Uso:
json
Copy code
{
  "description": "Atualizar descrição da tarefa"
}
Resposta de Sucesso: Retorna a tarefa atualizada com o status HTTP 200 - OK.
5. Deletar Tarefa
Endpoint: DELETE /tasks/{id}
Descrição: Exclui uma tarefa específica.
Parâmetros de Entrada: id: Identificador único da tarefa a ser excluída.
Resposta de Sucesso: Retorna uma mensagem indicando que a tarefa foi excluída com sucesso com o status HTTP 200 - OK.
Tecnologias Utilizadas
Spring Boot
Banco de Dados (não especificado no código fornecido)
Configuração
O projeto utiliza as anotações do Spring Boot para injeção de dependências e mapeamento de endpoints. Certifique-se de ter as dependências do Spring Boot configuradas corretamente no seu ambiente de desenvolvimento. O banco de dados utilizado não foi especificado, portanto, certifique-se de configurar corretamente o banco de dados de acordo com as necessidades do projeto.
