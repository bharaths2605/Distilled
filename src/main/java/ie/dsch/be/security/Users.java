package ie.dsch.be.security;

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
public class Users {

	@Id
	@GeneratedValue
	private String user;

	private Long value;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Users))
			return false;
		Users userValue = (Users) o;

		return userValue.user == this.user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.user);
	}

	@Override
	public String toString() {// overriding the toString() method
		return "Id" + this.user;
	}

}
