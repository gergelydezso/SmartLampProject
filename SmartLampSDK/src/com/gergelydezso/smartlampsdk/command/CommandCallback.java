package com.gergelydezso.smartlampsdk.command;

public interface CommandCallback {

	void onSuccess();

	void onError(Exception e);

}
