# Aplicação de Gerenciamento de Tarefas - Backend

Este é o backend de uma aplicação de gerenciamento de tarefas. Ele fornece endpoints para criar, listar e atualizar tarefas associadas a usuários.

## Recursos

- Registro de usuário.
- Criação de tarefas com data de início e término.
- Atualização de tarefas existentes.
- Listagem de tarefas.

## Endpoints

### Registro de Usuário

- Método: `POST`
- URL: `https://todolist-backend-java.onrender.com/users/`
- Corpo da solicitação (JSON):

```json
{
  "username": "seu-usuario",
  "password": "sua-senha"
}
```

### Criar uma Tarefa
- Método: POST
- URL: https://todolist-backend-java.onrender.com/tasks
- Corpo da solicitação (JSON):

```json
{
    "description": "Descrição da tarefa",
    "title": "Título",
    "priority": "ALTA",
    "startAt": "2023-10-06T12:30:00",
    "endAt": "2023-10-06T16:40:00",
    "idUser": "7ca9dcf4-33a1-4779-8862-6a41c5864970"

}
```

### Listar Tarefas
- Método: GET
- URL: https://todolist-backend-java.onrender.com/tasks

### Atualizar uma Tarefa
- Método: PUT
- URL: https://todolist-backend-java.onrender.com/tasks/{id}
- Corpo da solicitação (JSON):

```json
{
  "description": "Nova descrição da tarefa",
  "title": "Título atualizado",
  "startAt": "2023-10-22T08:00:00",
  "endAt": "2023-10-23T10:00:00",
  "priority": "MÉDIA",
  "idUser": "072d5315-bb9e-4b9a-9351-cbd41b9e3d37"
}
```

## Exemplo de Uso
- Você pode usar um cliente REST, como Postman, para testar os endpoints da aplicação.

### Contribuindo
- Sinta-se à vontade para contribuir para este projeto. Você pode criar problemas, enviar solicitações de recebimento (pull requests) e colaborar no desenvolvimento.

## License
This project is licensed under the [MIT License](LICENSE).
