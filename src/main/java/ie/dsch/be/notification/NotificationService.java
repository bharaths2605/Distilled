package ie.dsch.be.notification;

import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import ie.dsch.be.preferences.UserPreferenceDTO;

/**
 * A simple JMS wrapper to send messages/events to the platform event-bus. This class to be assumed to be synchronous
 * and without any retry logic.
 */
@Service
public class NotificationService implements INotifiactionService{

  private final JmsTemplate jmsTemplate;

  public NotificationService(JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }

  /**
   * Send a notification message up to the event-bus.
   * The JMS_DEDUPLICATION_ID will garantee the same message will not be added to the queue during a specific space
   * of time (configuration made at queue level).
   * @param notification notification object with the message info.
   * @throws NotificationException if any error occurs when sending the message
   * @see Notification
   */
  public void send(Notification<UserPreferenceDTO> notification) throws NotificationException {
    try {
      jmsTemplate.convertAndSend(notification.getQueue(), notification.getBody(), message -> {
        message.setStringProperty("JMS_DEDUPLICATION_ID", notification.checksum());
        return message;
      });
    } catch (JmsException ex) {
      throw new NotificationException("error sending message to " + notification.getQueue(),ex);
    }
  }


}
