package ie.dsch.be.notification;

import ie.dsch.be.preferences.UserPreferenceDTO;

public interface INotifiactionService {

	public void send(Notification<UserPreferenceDTO> notification) throws NotificationException;

}
