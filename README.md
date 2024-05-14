<div align="center">

![Modern_Royal_Technology_Logo-removebg-preview](https://github.com/Adrianaramss/hercall/assets/111310311/59fc36a3-ad69-465e-a46e-0243ca0f6e98)

</div>

## Projeto final do bootcamp #ElasTech2024



## 💻 Sobre o Projeto
O projeto HerCall é um sistema de gerenciamento de chamados desenvolvido utilizando Java Spring Boot e MySQL como banco de dados. O objetivo principal do sistema é facilitar o processo de solicitação, atribuição e acompanhamento de chamados técnicos em ambientes corporativos ou de suporte técnico.


## 🚀 Funcionalidades do Projeto

✅Painel Funcionario:

- Exibição da tela principal do funcionário: Mostra os chamados em aberto.
- Cadastro de novos chamados: Permite que os funcionários registrem novos chamados no sistema quando os usuários solicitam suporte ou assistência.

  
✅Painel Técnico:

- Exibição da tela principal do técnico: Mostra todos os chamados existentes no sistema, permitindo que os técnicos visualizem e gerenciem todos os chamados, independentemente do estado ou atribuição.
  
- Listagem de prioridades: Exibe uma lista de prioridades disponíveis para os técnicos selecionarem ao lidar com os chamados, permitindo uma classificação adequada das solicitações de suporte.
  
- Listagem de status de chamados: Fornece uma lista dos diferentes estados de chamados (Aguardando técnico, em atendimento, escalado para outro setor, cancelado e finalizado), permitindo que os técnicos atualizem o status dos chamados conforme necessário.


  
✅Painel Admin:

- Exibição de estatísticas mensais de chamados: Essa funcionalidade mostra as estatísticas mensais de chamados em forma de gráficos, permitindo aos administradores visualizar a quantidade de chamados registrados em cada mês.
  
- Exibição do número de chamados abertos, em atendimento e finalizados: Essa funcionalidade exibe informações sobre o número de chamados em diferentes estados, como abertos, em atendimento e finalizados, fornecendo uma visão geral do status atual dos chamados.
  
- Listagem de usuários cadastrados: Permite aos administradores visualizar todos os usuários cadastrados no sistema.
  
- Listagem de setores cadastrados: Similar à listagem de usuários, essa funcionalidade exibe todos os setores cadastrados no sistema, fornecendo informações relevantes sobre cada um deles.
  
- Cadastro e exclusão de setores: Permite aos administradores adicionar novos setores ao sistema ou remover setores existentes, conforme necessário, fornecendo flexibilidade na gestão dos setores da organização.
  
- Cadastro e exclusão de prioridades: Oferece a capacidade de definir e gerenciar diferentes níveis de prioridade para os chamados, permitindo aos administradores configurar as prioridades de acordo com as necessidades da organização.



## 📈 Diagrama


![atualizado_diagrama](https://github.com/Adrianaramss/hercall/assets/111310311/cc1371be-e8b5-4064-a472-91f37a810b17)

## 💻 Tecnologias utilizadas no Projeto
- JavaSpring
- MySQL
- Postman
- Dependencia JDK
- Thymeleaf
- Spring Web
- Spring Data JPA (Java Persistence API)
- Spring Boot DevTools
- Lombok
- Bootstrap
- Hibernate





## 🛰Executar o projeto localmente

```bash
# Clone repositório
git clone https://github.com/Adrianaramss/hercall.git

```
## Execute no IntelliJ

Abra o projeto no IntelliJ IDEA e navega até a pasta src > main > resources, arquivo chamado "application.properties". 
Ao abrir o arquivo "application.properties" e colocar a sua senha de acesso do seu MySQL.

![Captura de Tela (98)](https://github.com/Adrianaramss/hercall/assets/111310311/f5cb549e-0451-4c76-989b-8ed3814f8576)

Ir em src > main > java > hercall > "HercallApplication" e executar o projeto.

![Captura de Tela (99)](https://github.com/Adrianaramss/hercall/assets/111310311/03dd46d5-1f65-44a7-bdcd-fd00254f09f1)

Apos Executar no botão "Play" 

![Captura de Tela (100)](https://github.com/Adrianaramss/hercall/assets/111310311/2ca142a2-8046-4879-88fb-64a67352a948)

## 🛰 Demostração requisições Postman

### 🎯 REGISTRA UM USUÁRIO NO BANCO DE DADOS.

```URL
POST - localhost:8080/usuarios
```
  
```JSON
{
    "nome": "maria",
    "email": "maria13@gmail.com",
    "matricula": "121248",
    "senha": "1234",
    "confirmaSenha": "1234",
    "tipoUsuario": "FUNCIONARIO"    
}
```
Output
```JSON 
{
    "status": 201,
    "message": "Usuário cadastrado com sucesso!",
    "data": {
        "id": 4,
        "nome": "maria",
        "email": "maria13@gmail.com",
        "matricula": "121248",
        "senha": "1234",
        "confirmaSenha": null,
        "tipoUsuario": "FUNCIONARIO"
    }
}
```
### 🎯 LISTAR TODOS OS USUÁRIOS .
```URL
GET - localhost:8080/usuarios
```
  
Output
```JSON 
{
    "status": 200,
    "message": "Listagem de usuários realizada com sucesso!",
    "data": [
        {
            "id": 1,
            "nome": "Funcionario1",
            "email": "funcionario1@example.com",
            "matricula": "123",
            "senha": "senha123",
            "confirmaSenha": null,
            "tipoUsuario": "FUNCIONARIO"
        },
        {
            "id": 2,
            "nome": "Admin1",
            "email": "admin1@example.com",
            "matricula": "456",
            "senha": "senha456",
            "confirmaSenha": null,
            "tipoUsuario": "ADMIN"
        },
        {
            "id": 3,
            "nome": "Tecnico1",
            "email": "tecnico1@example.com",
            "matricula": "789",
            "senha": "senha789",
            "confirmaSenha": null,
            "tipoUsuario": "TECNICO"
        },
        {
            "id": 4,
            "nome": "maria",
            "email": "maria13@gmail.com",
            "matricula": "121248",
            "senha": "1234",
            "confirmaSenha": null,
            "tipoUsuario": "FUNCIONARIO"
        }
    ]
}
```

### 🎯 PROCURAR UM USUARIO PELO SEU ID .
```URL
GET - localhost:8080/usuarios/4
```
  
Output
```JSON 
{
    "status": 200,
    "message": "Detalhamento de usuário realizado com sucesso!",
    "data": {
        "id": 4,
        "nome": "maria",
        "email": "maria13@gmail.com",
        "matricula": "121248",
        "senha": "1234",
        "confirmaSenha": null,
        "tipoUsuario": "FUNCIONARIO"
    }
}
```
### 🎯 EDITAR UM USUARIO PELO SEU ID .
```URL
PUT - localhost:8080/usuarios/4
```
  
Output
```JSON 
{
    "status": 200,
    "message": "Usuário editada com sucesso!",
    "data": {
        "id": 4,
        "nome": "maria",
        "email": "maria1308@gmail.com",
        "matricula": "121248",
        "senha": "1234",
        "confirmaSenha": null,
        "tipoUsuario": "FUNCIONARIO"
    }
}
```
### 🎯 EXCLUIR O USUARIO PELO ID .
```URL
DELETE - localhost:8080/usuarios/4
```
  
Output
```JSON 
{
    "status": 200,
    "message": "Usuário excluído com sucesso!",
    "data": {
        "id": 4,
        "nome": "maria",
        "email": "maria1308@gmail.com",
        "matricula": "121248",
        "senha": "1234",
        "confirmaSenha": null,
        "tipoUsuario": "FUNCIONARIO"
    }
}
```
### 🎯 REGISTRA UM SETOR NO BANCO DE DADOS.

```URL
POST - localhost:8080/setores
```
  
```JSON
{
    "tipoSetor": "Vendas"
}
```
Output
```JSON 
{
    "status": 201,
    "message": "Setor cadastrado com sucesso!",
    "data": {
        "id_setor": 7,
        "tipoSetor": "Vendas"
    }
}
```
### 🎯 LISTAR TODOS OS SETORES.


Output
```JSON 
{
    "status": 200,
    "message": "Listagem de setores realizada com sucesso!",
    "data": [
        {
            "id_setor": 5,
            "tipoSetor": "Departamento pessoal"
        },
        {
            "id_setor": 6,
            "tipoSetor": "Desenvolvimento web"
        },
        {
            "id_setor": 2,
            "tipoSetor": "Financeiro"
        },
        {
            "id_setor": 3,
            "tipoSetor": "Markenting"
        },
        {
            "id_setor": 4,
            "tipoSetor": "RH"
        },
        {
            "id_setor": 1,
            "tipoSetor": "Suporte"
        },
        {
            "id_setor": 7,
            "tipoSetor": "Vendas"
        }
    ]
}
```
### 🎯 PROCURAR  O SETOR PELO ID .
```URL
GET - localhost:8080/setores/2
```
  
Output
```JSON 
{
    "status": 200,
    "message": "Detalhamento de setor realizado com sucesso!",
    "data": {
        "id_setor": 2,
        "tipoSetor": "Financeiro"
    }
}
```
### 🎯 EDITAR UM SETOR PELO SEU ID .
```URL
PUT - localhost:8080/setores/7
```
  ```JSON
{
    "tipoSetor": "COMERCIAL"
}
```
Output
```JSON 
{
    "status": 200,
    "message": "Setor editado com sucesso!",
    "data": {
        "id_setor": 7,
        "tipoSetor": "COMERCIAL"
    }
}

```
### 🎯 EXCLUIR O USUARIO PELO ID .
```URL
DELETE - localhost:8080/setores/7
```
  
Output
```JSON 
{
    "status": 200,
    "message": "Setor excluído com sucesso!",
    "data": {
        "id_setor": 7,
        "tipoSetor": "COMERCIAL"
    }
}
```

### 🎯 REGISTRA UMA PRIORIDADE NO BANCO DE DADOS.

```URL
POST - localhost:8080/prioridades
```
  
```JSON
{
    "tipoPrioridade": "URGENTISSIMO"
}
```
Output
```JSON 
{
    "status": 201,
    "message": "Prioridade cadastrada com sucesso!",
    "data": {
        "id_prioridade": 5,
        "tipoPrioridade": "URGENTISSIMO"
    }
}
```
### 🎯 LISTAR PRIORIDADE.

```URL
GET - localhost:8080/prioridades
```
  
Output
```JSON 
{
    "status": 200,
    "message": "Listagem de prioridades realizada com sucesso!",
    "data": [
        {
            "id_prioridade": 3,
            "tipoPrioridade": "Alta"
        },
        {
            "id_prioridade": 1,
            "tipoPrioridade": "Baixa"
        },
        {
            "id_prioridade": 2,
            "tipoPrioridade": "Média"
        },
        {
            "id_prioridade": 4,
            "tipoPrioridade": "Urgente"
        }
    ]
}
```
## 🤝 Integrantes 
[Anna Karoline](https://www.linkedin.com/in/annakarolinedevmobile/)  
[Adriana Ramos](https://www.linkedin.com/in/adriana-ramss/)  
[Amanda Vidal](https://www.linkedin.com/in/amanda-vidal-557132115/)  
[Daniela Sousa](https://www.linkedin.com/in/daniela-sousa-lima/)  
[Jurineide Souza](https://www.linkedin.com/in/jurineidesouza/) <br>
[Janete Souza](https://www.linkedin.com/in/janete-soares-almeida-souza-5a455022a/)  




