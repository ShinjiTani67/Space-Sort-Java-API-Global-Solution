# Space Sort

## Índice 

- [Integrantes]
- [Descrição]
- [Video]
- [Troubleshooting]

## Integrantes  
- Fernando Shinji Tanigushi RM553587

## Descrição
- Space Sort é uma API-REST Java que tem como proposito ajudar astronautas a catalogar e organizar as suas amostras retiradas do espaço e dar a a opção para que curiosos possam ter acesso a elas ao fim de estudo e pesquisa.

## Troubleshooting
-Caso ocorram problemas durante a execução do projeto, algumas verificações podem ser realizadas:
Verificar se os containers estão em execução: docker ps
Verificar os logs dos containers: docker logs rh-api docker logs rh-postgres
O projeto possui um Healthcheck configurado para o PostgreSQL, que garante que a API Java só será iniciada após o banco de dados estar disponível.
Caso algum erro ocorra na inicialização, recomenda-se reconstruir os containers: docker-compose down docker-compose up --build
