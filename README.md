# Alura: Imersão Java

<p align="center">
<img src="http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge"/>
</p>

Projeto interessante realizado na imersão Java da **ALURA**. Ele tras um grande aprendizado 
para quem está começando e um grande desafio àqueles que estão no meio do caminho. Eu, 
estou entre esses dois, e, como parte da minha contribuição, irei descrever, com o pouco 
que sei, como eu passei pelos desafios. 

Espero poder contribuir com aqueles que estão nessa jornada assim como eu.

**Onde me encontrar**

[![Linkedin](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/rodrigo-goncalves-650545146/)

<details>
<summary><h1> Aula 01 </h1></summary>

Tudo começa com a consulta em uma API de filmes, a selecionada foi **imdb**, e como ela 
esperado ela cai, com isso utilizamos a **themoviedb**, e alguns colegas disponibilizaram 
algumas outras. 

Eu tive que utilizar um arquivo estático, estou acompanhando a imersão na empresa e 
meu **eclipse** não quer me ajudar, e não quero perder tempo arrumando o problema dele.

Dito isso, vamos ao que interessa. A Chamada via API pode ser vista no arquivo `AppStickerFromApi`.
Realizar a chama é relativamente simples:

Iniciamos criando a *URI* que iremos utilizar

```java
String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";		
URI uriClient = URI.create(url);
```

**URIS SUJERIDAS**
* https://api.mocki.io/v2/549a5d8b/Top250Movies - Criada por alguns de nossos amigos, 
não me recordo quem.
* https://imdb-api.com/en/API/Top250Movies/ - imdb api top 250 movies, é necessário 
criar uma conta e gerar uma *api key*.
* https://api.themoviedb.org/3/tv/top_rated - themoviedb top rated, é necessário criar 
uma conta e gerar uma *api key*.

Agora criamos um *client* que irá chamar essa *URI*
```java
HttpClient client = HttpClient.newHttpClient();
```

Preparamos a nossa chamada
```java
HttpRequest request = HttpRequest.newBuilder(uriClient).GET().build();
```

E a realizamos com e pegamos somente o "corpo" do retorno:
```java
HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
String responseBody = response.body();
```

O tratamento do retorno foi realizado em um desafio, irei descrevê-lo lá.

## Resumo
1. Criar a URI do serviço que iremos utilizar.
2. Criar um cliente com `HttpClient`.
3. Criar uma requisição com `HttpRequest`.
4. Fazer a requisição com `client.send(request, BodyHandlers.ofString())`.
5. Pegar o *body* do retorno.
6. Tratar o retorno.

<details>
<summary><h2> Desafios aula 01 </h2></summary>

<details>
<summary><h3> Desafio 01 </h3></summary>

<p align="center">
<img src="https://img.shields.io/static/v1?label=ESTATUS&message=N%C3%83O%20INICIADO&color=red&style=for-the-badge"/>
</p>

Consumir o endpoint de filmes mais populares da API do IMDB. Procure também, na 
documentação da API do IMDB, o endpoint que retorna as melhores séries e o que 
retorna as séries mais populares.

</details>

<details>
<summary><h3> Desafio 02 </h3></summary>

<p align="center">
<img src="https://img.shields.io/static/v1?label=ESTATUS&message=FINALIZADO&color=sucess&style=for-the-badge"/>
</p>

Usar sua criatividade para deixar a saída dos dados mais bonitinha: usar emojis 
com código UTF-8, mostrar a nota do filme como estrelinhas, decorar o terminal 
com cores, negrito e itálico usando códigos ANSI, e mais!

**SOLUÇÃO**

Tive alguns problemas com essa demanda, meu terminal não reconhecia os caracteres que 
criam as cores, tive que baixar um plugin para resolver esse problema.

[ANSI Escapes](https://github.com/mihnita/ansi-econsole).

Resolvendo isso, bastou entrar na documentação sujerida pela **Alura**

[ALURA: Decorando terminal cores emojis](https://www.alura.com.br/artigos/decorando-terminal-cores-emojis).

E "codar". Na classe `AppStickerFromFile` criei minhas variáveis que guardarão as cores 
que utilizarei.
```java
final static String NEGRITO = "\u001B[1m";
final static String RESET = "\u001B[0m";
final static String COR_TITULO = "\u001B[38;2;254;181;0m";
final static String FUNDO_TITULO = "\u001B[48;2;234;214;164m";	
final static String COR_LINHA = "\u001B[38;2;178;129;7m";
final static String COR_EMOJI = "\u001B[38;2;164;123;22m";
```

Agora é só brincar com os *prints*
```java
System.out.print(COR_LINHA);
System.out.println("-".repeat(100));  
System.out.print(RESET);

System.out.print(NEGRITO + COR_TITULO);
System.out.print("Título Original:");
System.out.print(RESET + " ");
System.out.print(jsFilme.get("original_title"));

(...)
```

![teminal-pintado](assets/terminal-pintado.png)

</details>

<details>
<summary><h3> Desafio 03 </h3></summary>


<p align="center">
<img src="https://img.shields.io/static/v1?label=ESTATUS&message=FINALIZADO&color=sucess&style=for-the-badge"/>
</p>

Colocar a chave da API do IMDB em algum lugar fora do código como um arquivo 
de configuração (p. ex, um arquivo .properties) ou uma variável de ambiente.

**SOLUÇÃO**

Como eu já resolvi isso com alguns projetos do trabalho, foi relativamente fácil.
Criei a pasta **config** com o arquivo **config.properties** dentro. Neste eu coloquei 
as minhas chaves

```bach
imdb_key = minha-chave-linda
themoviedb_key = minha-chave-linda-dois
```

A classe a baixo abre o arquivo, itera sobre ele pegando todas as entradas e retorna 
um `Map` com as chaves que coloquei no arquivo.

```java
private Map<String, String> getParametrosIntegracao(String nomeArquivoParam) throws Exception {
		if (!nomeArquivoParam.endsWith(".properties")) {
			nomeArquivoParam += ".properties";
		}

		FileInputStream arquivo = new FileInputStream(nomeArquivoParam);
		Properties properties = new Properties();
		properties.load(arquivo);

		Set<Object> keySet = properties.keySet();
		Iterator<Object> iterator = keySet.iterator();
		Map<String, String> parametroMap = new HashMap<String, String>();

		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			parametroMap.put(key, properties.getProperty(key));
		}
		return parametroMap;
	}
```

Existe uma outra classe nesse arquivo que busca por uma *api key* especifica

```java
public void setApiKey(String apiKey) {
		
		File classPath = new File(".");	
		String configPath = classPath.getAbsolutePath() + "/config/";
						
		Map<String, String> properties;
		try {
			properties = getParametrosIntegracao(configPath + "config");
			this.apiKey = properties.get(apiKey);	
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
	}
	
	public String getApiKey() {
		return apiKey;
	}
```

Agora fica simples pegar uma *api key* que está dentro do meu arquivo que configuração, 
basta criar uma instância da classe `UtilProperties`, chamar `setApiKey` passando no 
nome da chave, e buscar o resultado com `getApiKey` 

```java
UtilProperties properties = new UtilProperties();
properties.setApiKey("themoviedb_key");
String url = "https://imdb-api.com/en/API/Top250Movies/" + properties.getApiKey();
```

</details>

<details>
<summary><h3> Desafio 04 </h3></summary>

<p align="center">
<img src="https://img.shields.io/static/v1?label=ESTATUS&message=EM%20DESENVOLVIMENTO&color=important&style=for-the-badge"/>
</p>

Mudar o JsonParser para usar uma biblioteca de parsing de JSON como Jackson ou GSON. 
 
**SOLUÇÃO**

Usei outra biblioteca a *simple parser*

[Exemplo de utilização da biblioteca](https://www.geeksforgeeks.org/parse-json-java/)

Para resolver esse problema criei uma classe `UtilJson` para fazer os tratamentos necessário. 
Lembre-se: **Estou utilizando um arquivo .json pois não estou conseguindo chamar apis 
no meu serviço**

Dito isso. Na classe existe um método que é responsavel por abrir o arquivo .json e 
retornar um **JSONArray**

```java
public JSONArray getArquivoJson(String arquivo) {
		JSONArray jsResult = new JSONArray();
		String pathJsonFile = this.absPath + File.separator + "base-dados" + File.separator + arquivo + ".json";
		
		try {
			Object obj = new JSONParser().parse(new FileReader(pathJsonFile));
			JSONObject jo = (JSONObject) obj;
			
			jsResult = (JSONArray) jo.get("results");
						
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (ParseException e) {			
			e.printStackTrace();
		}
		
		return jsResult;
	}
```

</details>

<details>
<summary><h3> Desafio 05 </h3></summary>

<p align="center">
<img src="https://img.shields.io/static/v1?label=ESTATUS&message=N%C3%83O%20INICIADO&color=red&style=for-the-badge"/>
</p>


Desafio supremo: criar alguma maneira para você dar uma avaliação ao filme, puxando de 
algum arquivo de configuração OU pedindo a avaliação para o usuário digitar no terminal. 

</details>
   
</details>
</details>

<details>
<summary><h1> Aula 02 </h1></summary>

<details>
<summary><h2> Desafios aula 02 </h2></summary>

<details>
<summary><h3> Desafio 01 </h3></summary>

<p align="center">
<img src="https://img.shields.io/static/v1?label=ESTATUS&message=N%C3%83O%20INICIADO&color=red&style=for-the-badge"/>
</p>

Ler a documentação da classe abstrata InputStream.

</details>
<details>
<summary><h3> Desafio 02 </h3></summary>

<p align="center">
<img src="https://img.shields.io/static/v1?label=ESTATUS&message=N%C3%83O%20INICIADO&color=red&style=for-the-badge"/>
</p>

Centralizar o texto na figurinha.

</details>
<details>
<summary><h3> Desafio 02 </h3></summary>

<p align="center">
<img src="https://img.shields.io/static/v1?label=ESTATUS&message=N%C3%83O%20INICIADO&color=red&style=for-the-badge"/>
</p>

Fazer um pacote no Whatsapp e/ou Telegram com as suas próprias figurinhas!

</details>
<details>
<summary><h3> Desafio 04 </h3></summary>

<p align="center">
<img src="https://img.shields.io/static/v1?label=ESTATUS&message=N%C3%83O%20INICIADO&color=red&style=for-the-badge"/>
</p>

Criar diretório de saída das imagens, se ainda não existir.

</details>
<details>
<summary><h3> Desafio 05 </h3></summary>

<p align="center">
<img src="https://img.shields.io/static/v1?label=ESTATUS&message=N%C3%83O%20INICIADO&color=red&style=for-the-badge"/>
</p>

Colocar outra fonte como a Comic Sans ou a Impact, a fonte usada em memes.

</details>
<details>
<summary><h3> Desafio 06 </h3></summary>

<p align="center">
<img src="https://img.shields.io/static/v1?label=ESTATUS&message=N%C3%83O%20INICIADO&color=red&style=for-the-badge"/>
</p>

Colocar uma imagem de você que está fazendo esse curso sorrindo, fazendo joinha!

</details>
<details>
<summary><h3> Desafio 07 </h3></summary>

<p align="center">
<img src="https://img.shields.io/static/v1?label=ESTATUS&message=N%C3%83O%20INICIADO&color=red&style=for-the-badge"/>
</p>

Colocar contorno (outline) no texto da imagem.

</details>
<details>
<summary><h3> Desafio 08 </h3></summary>

<p align="center">
<img src="https://img.shields.io/static/v1?label=ESTATUS&message=N%C3%83O%20INICIADO&color=red&style=for-the-badge"/>
</p>

Tratar as imagens retornadas pela API do IMDB para pegar uma imagem maior ao invés 
dos thumbnails. Opções: pegar a URL da imagem e remover o trecho mostrado durante a aula. 
ou consumir o endpoint de posters da API do IMDB (mais trabalhoso), tratando o JSON retornado. 

</details>
<details>
<summary><h3> Desafio 09 </h3></summary>

<p align="center">
<img src="https://img.shields.io/static/v1?label=ESTATUS&message=N%C3%83O%20INICIADO&color=red&style=for-the-badge"/>
</p>

Fazer com que o texto da figurinha seja personalizado de acordo com as classificações do IMDB. 

</details>
<details>
<summary><h3> Desafio 10 </h3></summary>

<p align="center">
<img src="https://img.shields.io/static/v1?label=ESTATUS&message=N%C3%83O%20INICIADO&color=red&style=for-the-badge"/>
</p>

Desafio supremo usar alguma biblioteca de manipulação de imagens como OpenCV pra 
extrair imagem principal e contorná-la.

</details>

</details>
</details>

<details>
<summary><h1> Aula 03 </h1></summary>

Consumir API da nasa, criar uma API key 

Cliente Http -> String Body

Extrator De Conteúdo -> Nasa IMDb The Movie Db

Conteudo

Alterar o gerar figurinha para aqueles que não possuem foto.

<details>
<summary><h2> Desafios aula 03 </h2></summary>

<details>
<summary><h3> Desafio 01 </h3></summary>

<p align="center">
<img src="https://img.shields.io/static/v1?label=ESTATUS&message=FINALIZADO&color=sucess&style=for-the-badge"/>
</p>

Transformar a classe que representa os conteúdos em um Record, disponível a partir 
do Java 16

**SOLUÇÃO**

Para realizar essa demanda utilizei como referência esse [link](https://www.tutorialspoint.com/java16/java16_record.htm).

A solução foi bem simples. Basta alterar o tipo de classe para record, e remover a visibilidade 
dos atribrutos, dessa forma nosso record ficara:
```java
public record Conteudo(
		
		String titulo,
		String urlImagem,
		String voto
	) {}
```

Em nossa classe main, basta chamar o atributo pelo nome e não pelo get

```java
InputStream inputStream;	
inputStream = new URL(conteudo.urlImagem()).openStream();
String nomeArquivo = conteudo.titulo().replace(":", " -");
String voto = conteudo.voto();
```

</details>
<details>
<summary><h3> Desafio 02 </h3></summary>

<p align="center">
<img src="https://img.shields.io/static/v1?label=ESTATUS&message=FINALIZADO&color=sucess&style=for-the-badge"/>
</p>

riar as suas próprias exceções e usá-las na classe que implementa o cliente HTTP

**SOLUÇÃO**

Não cheguei à esse curso na **Alura**, mas bora tentar alguma coisa. Para isso tomarei 
a [resposta no stakoverflow](https://pt.stackoverflow.com/questions/71670/como-criar-uma-exception-exce%C3%A7%C3%A3o-customizada-em-java)
como base.

Bem, dado a meu baixo conhecimento no assunto, fiz algo bem simples. Creie a classe  
**TrataExecoes**, e coloquei dois métodos dentro dela.

```java
public class TrataExecoes extends RuntimeException{
	
	private static final long serialVersionUID = 1149241039409861914L;
	
	
    public TrataExecoes(String msg){
        super(msg);
    }
    
    public TrataExecoes(String msg, Throwable cause){
        super(msg, cause);
    }

}
```

E a usei no `catch` da classe **ClienteHttp**
```java
} catch (IOException | InterruptedException e) {	
	throw new TrataExecoes("Algo De Errado Não Está Certo" , e);									
} 	
```

</details>
<details>
<summary><h3> Desafio 03 </h3></summary>

<p align="center">
<img src="https://img.shields.io/static/v1?label=ESTATUS&message=FINALIZADO&color=sucess&style=for-the-badge"/>
</p>

Usar recursos do Java 8 e posterior, como Streams e Lambdas, para mapear uma lista 
em uma outra

**SOLUÇÃO**

Apliquei essa técnica em várias partes do código. A ideia é simples, pega o objeto que 
é uma lista, seleciona o método `forEach` dele, esse método irá percorrer todos os elementos 
do objeto e aplicar uma função que criamos em tempo de execução. No meu caso essa função 
adicionrá um conteudo na minha lista.

```java
public List<Conteudo> extraiConteudosNasa(Object json){
		
		JSONArray jsonArray = (JSONArray) json;	
		
		List<Conteudo> conteudos = new ArrayList<>();
		
		jsonArray.forEach((elemento) -> {
			JSONObject conteudo = (JSONObject) elemento;
			conteudos.add(
					new Conteudo(conteudo.get("title").toString(), 
							     conteudo.get("url").toString())
					);
			
		});
		
		return conteudos;
		
	}
```

</details>
<details>
<summary><h3> Desafio 04 </h3></summary>

<p align="center">
<img src="https://img.shields.io/static/v1?label=ESTATUS&message=FINALIZADO&color=sucess&style=for-the-badge"/>
</p>

Criar uma Enum que une, como configurações, a URL da API e o extrator utilizado

**SOLUÇÃO**

Para entender melhor o que foi feito, ler o [artigo](https://receitasdecodigo.com.br/java/enum-no-java)

Na minha implentação, criei o `enun` **ArquivoJson**
```java
public enum ArquivosJson {
	THE_MOVIE_DB("themoviedb-topRated"),
	NASA("nasa-apod");
	
	private String nomeArquivo;
	
	private ArquivosJson(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	
	public String getNomeArquivo() {
		return this.nomeArquivo;
	}

}
```

E em minha classe **AppStickerFromFile** o utilizei da seguinte forma:
```java
String arquivosJson = ArquivosJson.THE_MOVIE_DB.getNomeArquivo();
Object json = utilJson.getArquivoJson(arquivosJson);
```

</details>
<details>
<summary><h3> Desafio 05 </h3></summary>

<p align="center">
<img src="https://img.shields.io/static/v1?label=ESTATUS&message=N%C3%83O%20INICIADO&color=red&style=for-the-badge"/>
</p>

Desafio supremo: consumir outras APIs que contém imagens, como a da Marvel, que é 
bem diferente. Repositório com APIs públicas: [clique aqui](https://github.com/public-apis/public-apis).

</details>
</details>

