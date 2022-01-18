package ie.dsch.be.preferences;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
public class UserPreferences  {

  @Id
  @GeneratedValue
  private Long id;

  private Long userId;

  private boolean newsletter;

  private boolean shareInfo;

  private boolean notify;
  
  @Override
  public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof UserPreferences)) return false;
      UserPreferences userPreferences = (UserPreferences) o;
         return userPreferences.userId == this.userId;
  }

  @Override
  public int hashCode() {
      return Objects.hash(this.userId);
  }
  
  @Override
  public String toString(){//overriding the toString() method  
	  return "Id" + this.id;  
	 }   

}
