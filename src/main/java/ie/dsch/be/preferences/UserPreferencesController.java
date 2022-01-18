package ie.dsch.be.preferences;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ie.dsch.be.notification.NotificationException;

@RestController
@RequestMapping("/v1/user-preferences")
public class UserPreferencesController {

	@Autowired
	private IUserPreferenceService userPreferencesService;

	@GetMapping("/{userId}")
	public ResponseEntity<UserPreferenceDTO> get(@PathVariable String userId) throws UserPreferenceException {
		return userPreferencesService.findByUserId(userId);
	}

	@PostMapping("/{userId}")
	public ResponseEntity<Long> update(@PathVariable Long userId, @RequestBody UserPreferenceDTO userPreferenceDTO)
			throws NotificationException, UserPreferenceException {
		return userPreferencesService.update(userId, userPreferenceDTO);

	}


}
