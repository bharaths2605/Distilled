package ie.dsch.be.preferences;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserPreferencesConfiguration {

  @Bean
  @ConditionalOnMissingBean(MessageDigest.class)
  public MessageDigest messageDigest() throws NoSuchAlgorithmException {
    return MessageDigest.getInstance("sha1");
  }
  
  @Bean
  public ModelMapper modelMapper() {
      return new ModelMapper();
  }

}
