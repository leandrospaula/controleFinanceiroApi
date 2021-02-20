CREATE TABLE usuario 
  ( 
     id    SERIAL, 
     email VARCHAR(100), 
     senha VARCHAR(255), 
     nome  VARCHAR(100), 
     PRIMARY KEY(id) 
  ); 
  
CREATE TABLE mes 
  ( 
     id        SERIAL, 
     mes       INT, 
     ano       INT, 
     usuario   INT, 
     encerrado BOOLEAN DEFAULT false, 
     salario   DECIMAL(10, 2), 
     economia  DECIMAL(10, 2),
     livre     DECIMAL(10,2),
     total_cartao DECIMAL(10,2),
     total_fixo DECIMAL(10,2),
     total_gasto DECIMAL(10,2),
     PRIMARY KEY(id), 
     FOREIGN KEY (usuario) REFERENCES usuario(id) 
  ); 
  
CREATE TABLE despesa 
  ( 
     id    SERIAL, 
     mes   INT, 
     data  INT, 
     nome  VARCHAR(100), 
     valor DECIMAL(10, 2),
     PRIMARY KEY(id), 
     FOREIGN KEY(mes) REFERENCES mes(id) 
  ); 
  
CREATE TABLE cartao 
  ( 
     id      SERIAL, 
     nome    VARCHAR(100), 
     ativo   BOOLEAN DEFAULT true, 
     usuario INT, 
     fechamento INT,
     vencimento INT,
     PRIMARY KEY(id), 
     FOREIGN KEY (usuario) REFERENCES usuario(id) 
  ); 
  
CREATE TABLE despesa_fixa 
  ( 
     id         SERIAL, 
     nome       VARCHAR(100), 
     vencimento INT, 
     usuario    INT, 
     ano        INT, 
     tipo 		VARCHAR(1),
     calculo	VARCHAR(1),
     ativo      BOOLEAN DEFAULT true,
     valor      DECIMAL(10, 2),
     parte      DECIMAL(10, 2),
     PRIMARY KEY (id), 
     FOREIGN KEY(usuario) REFERENCES usuario(id) 
  ); 
  
CREATE TABLE despesa_cartao 
  ( 
     id        SERIAL, 
     nome      VARCHAR(100), 
     cartao    INT, 
     valor     DECIMAL(10, 2), 
     mes       INT, 
     terceiros BOOLEAN DEFAULT false,
     PRIMARY KEY(id), 
     FOREIGN KEY(cartao) REFERENCES cartao(id), 
     FOREIGN KEY(mes) REFERENCES mes(id) 
  ); 
  
CREATE TABLE despesa_fixa_mes 
  ( 
     id           SERIAL, 
     mes          INT, 
     despesa_fixa INT, 
     ativo        BOOLEAN DEFAULT true, 
     valor        DECIMAL(10, 2),
     previsao     DECIMAL(10, 2),
     PRIMARY KEY(id), 
     FOREIGN KEY(mes) REFERENCES mes(id), 
     FOREIGN KEY(despesa_fixa) REFERENCES despesa_fixa(id) 
  ); 
  
INSERT INTO usuario(email,senha,nome) values ('admin@admin.com','$2a$10$Gz9HQYpCLdKGPDEh7xwjDeVBF0Z0RLoyUZf8fE5TKuNTB8f7iAYNG','ADMINISTRADOR');