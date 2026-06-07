grupoRecursos=rg-azuredevops-docker
regiao=chilecentral
rm=rm553587
nomeACR="javapostgres$rm"
skuACR=Basic
serverACR="$nomeACR.azurecr.io"
az provider register --namespace Microsoft.ContainerInstance
if [ $(az group exists --name $grupoRecursos) = true ]; then
    echo "O grupo de recursos $grupoRecursos já existe"

else
    # Cria o grupo de recursos
    az group create \
        --name $grupoRecursos \
        --location $regiao

    echo "Grupo de recursos $grupoRecursos criado na localização $regiao"
fi

###
### Criação do Azure Container Registry (ACR)
###
# Verifica se o ACR já existe
if az acr show \
    --name $nomeACR \
    --resource-group $grupoRecursos &> /dev/null; then

    echo "O ACR $nomeACR já existe"

else
    # Cria o ACR
    az acr create \
        --resource-group $grupoRecursos \
        --name $nomeACR \
        --sku $skuACR

    echo "ACR $nomeACR criado com sucesso"

    # Habilita o usuário administrador
    az acr update \
        --name $nomeACR \
        --resource-group $grupoRecursos \
        --admin-enabled true

    echo "Usuário administrador habilitado no ACR $nomeACR"
fi

###
### Recuperação das credenciais do ACR
###
#
# Essa parte do script é recomendada apenas para testes e aprendizado
#
# WARNING:
# Esta saída pode comprometer a segurança mostrando credenciais
#
# Recupera as credenciais do usuário administrador
ADMIN_USER=$(az acr credential show \
    --name $nomeACR \
    --query "username" \
    -o tsv)

ADMIN_PASSWORD=$(az acr credential show \
    --name $nomeACR \
    --query "passwords[0].value" \
    -o tsv)

# Cria variáveis de ambiente
export ACR_ADMIN_USER=$ADMIN_USER
export ACR_ADMIN_PASSWORD=$ADMIN_PASSWORD

# Exibe as credenciais
echo "Usuário do ACR:"
echo $ACR_ADMIN_USER

echo "Senha do ACR:"
echo $ACR_ADMIN_PASSWORD
echo "Imagem Docker:"
echo "$serverACR/javapostgres:latest"
