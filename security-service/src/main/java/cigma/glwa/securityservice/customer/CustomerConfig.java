package cigma.glwa.securityservice.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {
    private final CustomerRepository customerRepository;

    public CustomerConfig(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Bean
    public CommandLineRunner init(){
        return args -> {
            for(int i=0;i<10;i++){
                customerRepository.save(new Customer
                        (null,"Client "+i, "client"+i+"@gmail.com"));
            }
        };
    }
}
