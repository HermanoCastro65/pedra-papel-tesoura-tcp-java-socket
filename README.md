# pedra-papel-tesoura-tcp-java-socket
  Este projeto tem por objetivo emular um "jogo de pedra papel tesoura" em uma rede local (localhost) por meio de Sockets que promovem a comunicação cliente/servidor através do protocolo TCP. A ideia é ter um host sevidor provendo o serviço para mais dois hosts se conrctarem,  consumindo, entao, este serviço – no caso, o jogo. A dinâmica deste processo se dá no momento em que o host servidor inicia a aplicação, aberta aos primeiros dois jogadores - dois hosts na mesma rede -  que se conectarem. A estes jogadores, basta saber a porta do Socket em que o serviço será disponibilizado, criando assim um canal.
  
  
1- Instale as ferramentas para debugar e compilar o projeto com o link:

[Instalar pacote de desenvolvimento JAVA do Visual Studio Code
](https://code.visualstudio.com/docs/languages/java)


2- Clone este repositório e abra o projeto no VS Code com os comandos:

```bash
git clone https://github.com/HermanoCastro65/pedra-papel-tesoura-tcp-java-socket.git
```
```bash
cd pedra-papel-tesoura-tcp-java-socket
```
```bash
code .
```


3- Rode o servidor passando como argumento a porta desejada como indica a figura:



