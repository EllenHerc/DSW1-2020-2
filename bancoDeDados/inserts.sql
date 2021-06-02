INSERT INTO `compravendaimoveis`.`cidade` (`nome`, `uf`) VALUES ('São Carlos', 'SP');
INSERT INTO `compravendaimoveis`.`cidade` (`nome`, `uf`) VALUES ('São Paulo', 'SP');
INSERT INTO `compravendaimoveis`.`cidade` (`nome`, `uf`) VALUES ('Belo Horizonte', 'MG');
INSERT INTO `compravendaimoveis`.`cidade` (`nome`, `uf`) VALUES ('Ribeirão Preto', 'SP');

INSERT INTO `compravendaimoveis`.`usuario` (`email`, `senha`, `papel`) VALUES ('roca@email.com', '123', 'IMOBILIARIA');
INSERT INTO `compravendaimoveis`.`imobiliaria` (`cnpj`, `user_email`, `nome`, `descricao`) VALUES ('06916751000139', 'roca@email.com', 'ROCA IMOVEIS LTDA', 'Apartamentos, casas e demais imóveis');

INSERT INTO `compravendaimoveis`.`usuario` (`email`, `senha`, `papel`) VALUES ('predial@email.com', '123', 'IMOBILIARIA');
INSERT INTO `compravendaimoveis`.`imobiliaria` (`cnpj`, `user_email`, `nome`, `descricao`) VALUES ('05887451000378', 'predial@email.com', 'PREDIAL CENTER CONSULTORIA', 'Tradição e confiança na venda, locação e administração de imóveis em São Carlos');

INSERT INTO `compravendaimoveis`.`usuario` (`email`, `senha`, `papel`) VALUES ('lopes@email.com', '123', 'IMOBILIARIA');
INSERT INTO `compravendaimoveis`.`imobiliaria` (`cnpj`, `user_email`, `nome`, `descricao`) VALUES ('15673605000110', 'lopes@email.com', 'LOPES CONSULTORIA DE IMOVEIS', 'A maior imobiliária do Brasil, e encontre imóveis de todos os tamanhos e para todos os bolsos');

INSERT INTO `compravendaimoveis`.`imovel` (`imobiliaria_cnpj`, `descricao`, `valor`, `cep`, `logradouro`, `numero`, `bairro`, `cidade_id`) VALUES ('06916751000139', 'Casa com 3 quartos à venda em Freguesia Do Ó - SP', '950000', '02835-010', 'Rua Doutor Roberto Zwicker', '91', 'Vila Serralheiro', '2');
INSERT INTO `compravendaimoveis`.`foto` (`url`, `imovel_id`) VALUES ('https://betaimages.lopes.com.br/realestate/med/REO47110/LP87631011.jpg', '1');

INSERT INTO `compravendaimoveis`.`usuario` (`email`, `senha`, `papel`) VALUES ('admin@email.com', '123', 'ADMIN');
