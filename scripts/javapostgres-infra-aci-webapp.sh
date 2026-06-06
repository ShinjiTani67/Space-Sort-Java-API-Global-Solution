### Variáveis
grupoRecursos=rg-azuredevops-docker

# Região
regiao=chilecentral

# RM
rm=rm553587

# Azure Container Registry (ACR)
nomeACR="javapostgres$rm"
serverACR="$nomeACR.azurecr.io"

# Imagem Docker
imageACR="$serverACR/javapostgres:latest"

# Credenciais do ACR
userACR=$(az acr credential show \
    --name $nomeACR \
    --query "username" \
    -o tsv)

passACR=$(az acr credential show \
    --name $nomeACR \
    --query "passwords[0].value" \
    -o tsv)

# Nome do Azure Container Instance
nomeACI="javapostgres$rm"

# Plano App Service
planService="planJavaPostgres"

# SKU do plano
sku=F1

# Nome do Web App
appName="javawebapp$rm"

# Porta da aplicação Spring Boot
port=8080

###
### PostgreSQL
###
postgresServer="postgres$rm"
postgresDB="appdb"
postgresUser="postgresadmin"
postgresPassword="SenhaForte123!"

###
### Registrar Container Instance
###
az provider register --namespace Microsoft.ContainerInstance

###
### Azure Container Instance (ACI)
###

# Verifica se o ACI já existe
if az container show \
    --name $nomeACI \
    --resource-group $grupoRecursos &> /dev/null; then

    echo "O ACI $nomeACI já existe"

else

    az container create \
        --resource-group $grupoRecursos \
        --name $nomeACI \
        --image $imageACR \
        --cpu 1 \
        --memory 1 \
        --os-type Linux \
        --registry-login-server $serverACR \
        --registry-username $userACR \
        --registry-password $passACR \
        --dns-name-label $nomeACI \
        --restart-policy Always \
        --ports $port \
        --environment-variables \
            SPRING_DATASOURCE_URL="jdbc:postgresql://$postgresServer.postgres.database.azure.com:5432/$postgresDB?sslmode=require" \
            SPRING_DATASOURCE_USERNAME="$postgresUser@$postgresServer" \
            SPRING_DATASOURCE_PASSWORD="$postgresPassword"

    echo "ACI $nomeACI criado com sucesso"
fi

###
### Plano App Service
###

if az appservice plan show \
    --name $planService \
    --resource-group $grupoRecursos &> /dev/null; then

    echo "O plano $planService já existe"

else

    az appservice plan create \
        --name $planService \
        --resource-group $grupoRecursos \
        --is-linux \
        --sku $sku \
        --location $regiao

    echo "Plano $planService criado com sucesso"
fi

###
### Web App
###

if az webapp show \
    --name $appName \
    --resource-group $grupoRecursos &> /dev/null; then

    echo "O Web App $appName já existe"

else

    az webapp create \
        --resource-group $grupoRecursos \
        --plan $planService \
        --name $appName \
        --deployment-container-image-name $imageACR

    echo "Web App $appName criado com sucesso"
fi

###
### Configuração do Container
###

az webapp config container set \
    --name $appName \
    --resource-group $grupoRecursos \
    --docker-custom-image-name $imageACR \
    --docker-registry-server-url https://$serverACR \
    --docker-registry-server-user $userACR \
    --docker-registry-server-password $passACR

echo "Container configurado"

###
### Configurações da aplicação
###

az webapp config appsettings set \
    --resource-group $grupoRecursos \
    --name $appName \
    --settings \
        WEBSITES_PORT=$port \
        SPRING_DATASOURCE_URL="jdbc:postgresql://$postgresServer.postgres.database.azure.com:5432/$postgresDB?sslmode=require" \
        SPRING_DATASOURCE_USERNAME="$postgresUser@$postgresServer" \
        SPRING_DATASOURCE_PASSWORD="$postgresPassword"

echo "Variáveis configuradas"

###
### Reinicia o Web App
###

az webapp restart \
    --name $appName \
    --resource-group $grupoRecursos

echo "Web App reiniciado"

###
### URLs finais
###

echo "Aplicação:"
echo "https://$appName.azurewebsites.net"

echo "Swagger:"
echo "https://$appName.azurewebsites.net/swagger-ui/index.html"

echo "Imagem Docker:"
echo "$imageACR"
