package lv.gusevs.rsser.notifiers;

import org.immutables.value.Value;

@Value.Immutable
public interface Notification {

	String imageUrl();

	String subject();

	String message();

	String action();

}
