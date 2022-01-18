package ie.dsch.be.preferences;

import org.springframework.http.ResponseEntity;

import ie.dsch.be.notification.NotificationException;

/**
 * @author Bharath
 *
 */
public interface IUserPreferenceService {

	/**
	 * @param id
	 * @return
	 * @throws UserPreferenceException 
	 */
	ResponseEntity<UserPreferenceDTO> findByUserId(String id) throws UserPreferenceException;

	/**
	 * @param userId
	 * @param prefs
	 * @return
	 * @throws NotificationException
	 * @throws UserPreferenceException
	 */
	ResponseEntity<Long> update(Long userId, UserPreferenceDTO prefs) throws NotificationException, UserPreferenceException;
}
