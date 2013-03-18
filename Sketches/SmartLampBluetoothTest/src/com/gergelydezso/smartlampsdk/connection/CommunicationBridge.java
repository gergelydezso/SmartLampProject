package com.gergelydezso.smartlampsdk.connection;

import com.gergelydezso.smartlampsdk.command.CommandCallback;

public interface CommunicationBridge {

	public void sendData(String id, int value, CommandCallback callback);

}
