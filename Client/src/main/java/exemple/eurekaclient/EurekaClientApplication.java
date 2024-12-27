package exemple.eurekaclient;

import exemple.eurekaclient.entities.Client;
import exemple.eurekaclient.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class EurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }


    @Bean
    CommandLineRunner initializerBaseH2(ClientRepository clientRepository) {
        return args -> {
            clientRepository.save(new Client(null, "Rabab SELIMANI", 22));
            clientRepository.save(new Client(null, "Amal RAMI", 23));
            clientRepository.save(new Client(null, "Agbi Yahya", 22));
        };
    }

}
