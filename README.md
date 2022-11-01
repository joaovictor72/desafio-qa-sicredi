# desafio-qa-sicredi

Algumas observações encontradas: 

# DELETAR UM USUÁRIO
- Ao deletar um usuário, está retornando o código 200, não o 204 como está na documentação
- Ao deletar um usuário que não existe, está retornando o código 200 ao invés do 404

# CRIAR UMA SIMULAÇÃO
-  Ao criar uma simulação com um CPF já existente, está retornando o erro 400 ao invés do 409, com uma mensagem diferente da documentação

Documentação: Erro 409 com a mensagem "CPF já existente"
Retornando: Erro 400 com a mensagem "CPF duplicado"

# CONSULTAR UMA SIMULAÇÃO
- Ao consultar uma simulação de um CPF com restrição, está retornando uma mensagem diferente da documentação:

Documentação: "O CPF
99999999999 possui restrição"

Retornando: "O CPF 97093236014 tem problema"


