package com.gergelydezso.smartlampsdk.connection;

import com.gergelydezso.smartlampsdk.command.CommandCallback;

public interface CommunicationBridge {

  // TODO - CODE_REVIEW - andrei.hegedus|Apr 19, 2013 - isn't sendCommand a better name? Also add some javadoc that describes the role of the params as well.
	public void sendData(String id, int value, CommandCallback callback);

}
