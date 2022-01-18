package ie.dsch.be.notification;

/**
 * @author Bharath
 *
 * @param <T>
 */
public interface Notification<T> {

  /**
 * @return
 */
String getQueue();
  
  /**
 * @return
 */
T getBody();

  /**
 * @return
 */
String checksum();

}
