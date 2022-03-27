package cigma.glwa.productservice.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class ProductConfig {

    private final ProductRepository productRepository;

    public ProductConfig(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Bean
    CommandLineRunner init(){
        return args -> {
            Random random = new Random();
            for(int i = 0 ;i<100; i++){
                int qte = random.nextInt(100);
                double price = random.nextDouble()*1000;
                String ref = "R00"+i;
                String label = "Product "+i;
                productRepository.save(new Product(ref,label,price,qte));
            }
        };
    }


}
