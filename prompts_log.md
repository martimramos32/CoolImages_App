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

## Prompt 3
**Goal:**
API Service Setup com Segurança - Criar a ligação à internet com o Retrofit, configurando a aplicação para ler a chave de acesso a partir do ficheiro seguro `local.properties` usando a ferramenta interna `BuildConfig`.

**Prompt used:**
"Read `docs/07_api_usage.md` and the data classes created in Step 2. Then, execute ONLY Step 3 from `docs/08_implementation_plan.md`. Create the Retrofit API interface to fetch random images. IMPORTANT SECURITY REQUIREMENT: Do not hardcode the API key. Configure the `app/build.gradle` file to read a property named `UNSPLASH_ACCESS_KEY` from the `local.properties` file and expose it via a `BuildConfig` field. Then, use this `BuildConfig` variable in the Retrofit interface for the Authorization header. Generate only the code for this step and do not proceed to Step 4."

**Result:**
A IA configurou o ficheiro build.gradle para ler a chave e criou a interface do Retrofit utilizando o BuildConfig, mantendo a chave secreta fora do código fonte para manter a segurança da nossa aplicação.

## Prompt 4
**Goal:**
Repository e ViewModel - Implementar a arquitetura MVVM criando um Repositório para gerir as chamadas à API e um ViewModel com LiveData para armazenar o estado de carregamento e a lista de imagens.

**Prompt used:**
"Review the code generated in Steps 2 and 3. Then, execute ONLY Step 4 from `docs/08_implementation_plan.md`. Create the `ImageRepository` class to handle data fetching from the API. Then, create the `MainViewModel` class. The ViewModel must use `LiveData` to expose two things to the UI: a List of `UnsplashImage` objects, and a `Boolean` representing the loading state (true when fetching, false when done). Generate only the code for this step and do not proceed to Step 5."

**Result:**
A IA criou o ImageRepository para fazer o pedido ao Unsplash, o MainViewModel com LiveData para guardar a lista de fotografias e o estado da barra de carregamento e o RetrofitClient para configurar o Retrofit, conectando o Gson e o OkHttp(logging interceptor que é bastante útil para fazer debug de erros da API, caso existam).

## Prompt 5
**Goal:**
UI Layout Design - Desenhar a interface visual em XML. Criar o ecrã principal com a lista e o carregamento, e criar o design individual que cada fotografia vai ter dentro da lista.

**Prompt used:**
"Review the project structure. Then, execute ONLY Step 5 from `docs/08_implementation_plan.md`. Create the XML layout for `activity_main.xml` including a `RecyclerView`, a `ProgressBar` for loading, and a `SwipeRefreshLayout` for the refresh action. Also, create a separate XML layout file (e.g., `item_image.xml`) for the individual items in the RecyclerView, which should contain an `ImageView` for the photo and a `TextView` for the author's name. Strictly use standard Android XML Views. DO NOT use Jetpack Compose. Generate only the XML code for this step and do not proceed to Step 6."

**Result:**
A IA gerou o activity_main.xml com o SwipeRefreshLayout e o RecyclerView, e também criou o item_image.xml para definir a aparência individual de cada fotografia na lista.