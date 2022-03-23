package configs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Configs {

    @Value("${sellics.aws.access.key}")
    public String sellicsAwsAccessKey;

    @Value("${sellics.aws.secret.key}")
    public String sellicsAwsSecretKey;

    @Value("${sellics.aws.bucket.name}")
    public String sellicsAwsBucketName;

    @Value("${sellics.aws.file.name}")
    public String sellicsAwsFileName;
}
