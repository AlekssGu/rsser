package lv.gusevs.rsser.event.subscribers;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Notification {

	private final String imageUrl;

	private final String subject;

	private final String message;

	private final String action;

}
