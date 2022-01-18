package ie.dsch.be.preferences;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bharath
 *
 */
@Data
@NoArgsConstructor
public class UserPreferenceDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Long id;

	/**
	 * 
	 */
	private Long userId;

	/**
	 * 
	 */
	private boolean newsletter;

	/**
	 * 
	 */
	private boolean shareInfo;

	/**
	 * 
	 */
	private boolean notify;
}
