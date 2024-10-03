package de.jan5366x.rocker.bogie.rover.desktop.remote.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import de.jan5366x.rocker.bogie.rover.desktop.remote.core.RemoteControlAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	private static final Logger LOGGER = LogManager.getLogger(DesktopLauncher.class);

	public static void main (String[] args) {
		LOGGER.info("Rocker-Bogie-Rover Remote Control");
		var config = new Lwjgl3ApplicationConfiguration();
		var foregroundFPS = 60;
		config.setForegroundFPS(foregroundFPS);
		config.setTitle("Remote Control");
		config.setWindowedMode(1800, 1000);
		LOGGER.info("Foreground FPS set to {}", foregroundFPS);
		new Lwjgl3Application(new RemoteControlAdapter(args), config);
	}
}
