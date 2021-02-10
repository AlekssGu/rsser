package lv.gusevs.rsser.configuration;

import org.springframework.stereotype.Component;

@Component
public class SystemSwitch {

	private static final boolean ENABLED = true;
	private static final boolean DISABLED = false;

	public boolean vehicleScraperEnabled() {
		return DISABLED;
	}

	public boolean vehicleWheelScraperEnabled() {
		return ENABLED;
	}

}
