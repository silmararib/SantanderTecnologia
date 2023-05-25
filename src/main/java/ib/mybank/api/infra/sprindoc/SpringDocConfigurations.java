package ib.mybank.api.infra.sprindoc;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MyBank - Internet Banking API")
                        .description("API Rest desenvolvida em uma das etapas do processo de seleção de " +
                                "desenvolvedores backend para o Santander Tecnologia.<br>" +
                                "Trata-se da simulação de um Internet Banking que contempla as operações de cadastro e listagem de clientes, saques e depósitos em sua conta, além da exibição do histórico de transações.")
                        .contact(new Contact()
                                .name("Silmara Ribeiro")
                                .url("https://www.linkedin.com/in/silmaraabribeiro")
                                .email("silmara.ribeiro@gmail.com")));
    }

}
