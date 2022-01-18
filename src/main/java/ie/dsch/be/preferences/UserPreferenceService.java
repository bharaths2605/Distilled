package ie.dsch.be.preferences;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ie.dsch.be.notification.INotifiactionService;
import ie.dsch.be.notification.NotificationException;
import ie.dsch.be.security.SecurityContext;

/**
 * @author Bharath
 *
 */
@Service
public class UserPreferenceService implements IUserPreferenceService {

	private Logger logger = LoggerFactory.getLogger(UserPreferenceService.class);

	private static final String CURRENT_USER = "me";
	
	@Autowired
	private MessageDigest messageDigest;

	@Autowired
	private INotifiactionService notificationService;

	@Autowired
	private UserPreferencesRepository repository;

	@Autowired
	private SecurityContext securityHolder;

	@Autowired
	private UserPreferencesRepository userPreferencesRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<Long> update(Long userId, UserPreferenceDTO userPreferenceDTO)
			throws NotificationException, UserPreferenceException {
		logger.debug("UserPreferenceService:update: Starts");
		Optional<UserPreferences> preferencesRecord = repository.findByUserId(userId);
		if (preferencesRecord.isPresent()) {
			UserPreferences preferences = preferencesRecord.get();
			preferences.setNewsletter(userPreferenceDTO.isNewsletter());
			preferences.setNotify(userPreferenceDTO.isNotify());
			preferences.setShareInfo(userPreferenceDTO.isShareInfo());
			userPreferenceDTO = convertToDto(preferences);
			notificationService.send(userPreferencesChanged(userPreferenceDTO));
			userPreferencesRepository.save(preferences);
			logger.debug("UserPreferenceService:update: UserPreference saved to database");
			return ResponseEntity.status(HttpStatus.OK).body(userPreferenceDTO.getUserId());
		}
		logger.debug("UserPreferenceService:update:UserPreference not Available");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(-1l);

	}

	@Override
	//@Cacheable(value = "UserPreferenceDTO", key = "#id")
	public ResponseEntity<UserPreferenceDTO> findByUserId(String id) throws UserPreferenceException {
		Optional<UserPreferences> user;
		if (id.equals(CURRENT_USER)) {
			user = repository.findByUserId(securityHolder.currentUser().getId());
			if(user.isPresent())
				return ResponseEntity.status(HttpStatus.OK).body(convertToDto(user.get()));
			throw new UserPreferenceException("Id Value Not found in database");
		}
		user = repository.findByUserId(Long.valueOf(id));
		if(user.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(convertToDto(user.get()));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserPreferenceDTO());
	}

	private UserPreferencesChangedNotification userPreferencesChanged(UserPreferenceDTO userPreferences)
			throws UserPreferenceException {
		UserPreferencesChangedNotification event = new UserPreferencesChangedNotification();
		event.setUserPreferenceDTO(userPreferences);
		event.setChecksum(getMessageChecksum(event));
		return event;
	}

	private String getMessageChecksum(UserPreferencesChangedNotification notification) throws UserPreferenceException {
		ObjectMapper objectMapper = new ObjectMapper();
		String replyAsJsonString;
		byte[] hash = null;
		try {
			replyAsJsonString = objectMapper.writeValueAsString(notification.getBody());
			hash = messageDigest.digest(replyAsJsonString.getBytes(StandardCharsets.UTF_8));
		} catch (JsonProcessingException e) {
			throw new UserPreferenceException("error while Calculating Get Message Check sum ");
		}

		return bytesToHex(messageDigest.digest(hash));
	}

	private static String bytesToHex(byte[] hash) {
		StringBuilder hexString = new StringBuilder();

		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}

		return hexString.toString();
	}

	private UserPreferenceDTO convertToDto(UserPreferences preferences) {
		logger.debug("UserPreferenceService:convertToDto:Entity Converted to DTO class");
		return modelMapper.map(preferences, UserPreferenceDTO.class);
	}

	

}
