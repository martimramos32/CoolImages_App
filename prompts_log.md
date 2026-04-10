# Prompts Log

## Prompt 1
**Goal:**
Project Setup - Inicializar a estrutura base do projeto Android com Kotlin e XML Views, e adicionar as dependências necessárias (Retrofit, Gson, Glide/Coil) para a API do Unsplash.

**Prompt used:**
"Read the /docs folder to understand the project. Then, execute ONLY Step 1 from docs/08_implementation_plan.md. Do not generate code for the other steps yet."

**Result:**
A IA criou o projeto base(incluindo todas os packages necessários) com sucesso, adicionou as dependências de rede e imagem no build.gradle e configurou o AndroidManifest.xml com as permissões de Internet. Primeiramente tentou construir a app usando a versão 8.2.1 do Gradle, mas depois reparar que esta não era compatível com a minha versão do Java (JDK25), o agente por si mesmo corrigiu o problema e mudou automaticamente para uma versão do Gradle mais recente (9.2.1) compatível com a minha versão do Java.

## Prompt 2
**Goal:**
Data Model - Criar as classes de dados em Kotlin (UnsplashImage, ImageUrls, User) que vão receber e organizar a informação do ficheiro JSON que a API do Unsplash nos vai enviar.

**Prompt used:**
"Read `docs/04_data_model.md` and `docs/07_api_usage.md` to understand the required data structure. Then, execute ONLY Step 2 from `docs/08_implementation_plan.md`. Create the Kotlin data classes needed to parse the Unsplash API JSON response. Generate only the code for these data classes and do not proceed to Step 3."

**Result:**
A IA criou o ficheiro UnsplashImage.kt com as classes de dados corretas para o ID, os URLs da imagem, descrição da imagem e dados do utilizador.