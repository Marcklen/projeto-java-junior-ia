# projeto-java-junior-ia

##Run Docker Database

docker run --name projetoatlantico-db -p 5432:5432 -e POSTGRES_DB=projetoatlantico -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=123 -d postgres:10-alpine
