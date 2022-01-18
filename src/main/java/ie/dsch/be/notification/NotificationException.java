package ie.dsch.be.notification;

/**
 * @author Bharath
 *
 */
public class NotificationException extends Exception {

private static final long serialVersionUID = 1L;

/**
 * @param message
 */
public NotificationException(String message, Throwable e) {
    super(message, e);
  }
}
