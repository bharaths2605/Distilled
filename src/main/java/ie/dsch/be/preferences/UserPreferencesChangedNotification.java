package ie.dsch.be.preferences;

import ie.dsch.be.notification.Notification;
import lombok.Setter;


@Setter
public class UserPreferencesChangedNotification implements Notification<UserPreferenceDTO> {

  private UserPreferenceDTO userPreferenceDTO;
  private String checksum;

  @Override
  public String getQueue() {
    return "#user-preferences";
  }

  @Override
  public UserPreferenceDTO getBody() {
    return userPreferenceDTO;
  }

  @Override
  public String checksum() {
    return checksum;
  }

  
}
