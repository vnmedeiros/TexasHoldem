TexasHoldem
===========

Esse arquivo descreve duas formas de se executar o projeto, após realizar o 
download para a maquina local


Executar th.jar:
===============
execute o arquivo “jar/th.jar” como exibido: <br/>
	java -jar th.jar

assim ele aguarda a entrada a ser processada, no caso as linhas com as mãos da partida, exemplo:

Kc 9s Ks Kd 8d 3c 6d 
9c Ah Ks Kd 9d 3c 6d
Ac Qc Ks Kd 9d 3c
9h 5s 
4d 2d Ks 8d 2s 2c 6d
7s Ts Ks Kd 9d

cada linha se refere a uma mão de um jogador, uma linha em branco simboliza o final da entrada, logo ele retorna a solução:

3c  6d  8d  9s  Kc  Ks  Kd ThreeKind (winner) 
3c  6d  9c  9d  Ks  Kd  Ah TwoPair
Ac  Qc  Ks  Kd  9d  3c
9h  5s
2d  2s  2c  4d  6d  8d  Ks ThreeKind
7s  Ts  Ks  Kd  9d

Gerando e executando  bytecodes:
================================
para gerar os bytecodes do projeto faça:

cd src
javac -d ../bin/ kataTexasHold/*.java           #isso vai gerar os .class na pasta bin
cd ../bin/
java kataTexasHold.Game  #executar bytecodes
