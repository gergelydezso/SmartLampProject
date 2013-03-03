package com.gergelydezso.smartlampsdk.connection;

import com.gergelydezso.smartlampsdk.command.CommandCallback;

public interface CommunicationBridge {

	public void sendData(int value, CommandCallback callback);

}
