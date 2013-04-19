package com.gergelydezso.smartlampsdk.connection;

import com.gergelydezso.smartlampsdk.command.CommandCallback;

// TODO - CODE_REVIEW - andrei.hegedus|Apr 19, 2013 - This class should be removed (as well as WireConnection). Don't keep empty implementations just because this feature might be added in the future. It's ok to create an extensible design, but the new extensions should be added only when they are required.
public class WiFiConnection implements CommunicationBridge {

	@Override
	public void sendData(String id, int value, CommandCallback callback) {

	}

}
