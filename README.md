## Projeto TLS Examples

O projeto TLS Examples mostra como configurar e receber respostas utilizando TLS. 


## Installation

1. Baixe o projeto do repositório Git: https://github.com/alexlirio/tls_examples.git
2. Para criar o pacote war do projeto, execute o seguinte comando maven na pasta raiz do projeto: "mvn clean package".
3. O pacote é criado na pasta do projeto como: "target/tls_examples*.war".


## Configuração Necessária


** No Prompt de Comando ** 
  
1. Gerar o arquivo keystore(arquivo que contém os certificados) com o seguinte comando de exemplo: 

		"%JAVA_HOME%\bin\keytool" -genkey -alias tomcat -keyalg RSA -keystore C:/desenvolvimento/projects/tls_examples/cfg/tls_examples.jks
		
Obs: Este comando solicitará algumas informações necessárias para gerar os certificados privado e publico do keystore. As informações devem ser preenchidas conforme o necessário e utilize a senha de exemplo "123456". Para facilitar a geração e manutenção do keystore, recomendo a utilização da ferramenta KeyStore Explorer(http://keystore-explorer.org).
 
 
** No Tomcat 8 ** 
 
1. Configurar arquivo "conf/server.xml" com os seguintes dados de exemplo: 

		<Connector port="8443" protocol="org.apache.coyote.http11.Http11NioProtocol"
               maxThreads="150" SSLEnabled="true" scheme="https" secure="true"
               clientAuth="false" sslProtocol="TLS"
               keystoreFile="C:/desenvolvimento/projects/tls_examples/cfg/tls_examples.jks" keystorePass="123456" /> 
		 
1. Colocar o arquivo gerado, "target/tls_examples*.war", na pasta "webapps" e levantar o servidor Tomcat. 
 
 
## Utilização

O TLS Examples retorna conteúdo utilizando um canal seguro TLS. Para testes pode-se usar qualquer web browser ou ferramenta que envia mensagens HTTP REST (Ex.: POSTMAN - http://www.getpostman.com/):	


Exemplos de Utilização:

1. Para o HOST(URL), utilize os dados de exemplo abaixo.

		[GET]
		http://127.0.0.1:8080/tls_examples/resource/get
		
Obs: Note que o acesso acontecerá pela url segura "https://127.0.0.1:8443/tls_examples/resource/get", retornando "[HTTP 200] {'result':'success'}". Para forçar a utilização da url segura, no arquivo "web.xml" da aplicação deve conter as seguintes configurações:

		<web-app ...>
			...
			<!-- Para forcar o acesso por SSL -->
			<security-constraint>
				<web-resource-collection>
					<web-resource-name>tls_examples</web-resource-name>
					<url-pattern>/*</url-pattern>
				</web-resource-collection>
				<user-data-constraint>
					<transport-guarantee>CONFIDENTIAL</transport-guarantee>
				</user-data-constraint>
			</security-constraint>
			...
		</web-app> 
 