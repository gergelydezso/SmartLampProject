package com.gergelydezso.smartlampsdk;

import android.content.Context;
import android.util.Log;
import com.gergelydezso.smartlampsdk.command.ComponentsBatchBuilder;
import com.gergelydezso.smartlampsdk.command.Command;
import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.command.LedCommand;
import com.gergelydezso.smartlampsdk.command.ServoCommand;
import com.gergelydezso.smartlampsdk.command.SetAllCommand;
import com.gergelydezso.smartlampsdk.command.StatusCommand;
import com.gergelydezso.smartlampsdk.command.filter.BlankCommandFilter;
import com.gergelydezso.smartlampsdk.command.filter.CommandFilterManager;
import com.gergelydezso.smartlampsdk.command.filter.CommandTarget;
import com.gergelydezso.smartlampsdk.command.filter.TimeCommandFilter;
import com.gergelydezso.smartlampsdk.connection.BridgeCreationListener;
import com.gergelydezso.smartlampsdk.connection.BridgeFactory;
import com.gergelydezso.smartlampsdk.connection.ConnectionListener;
import com.gergelydezso.smartlampsdk.connection.ConnectionType;
import com.gergelydezso.smartlampsdk.connection.SmartLampCommunicationBridge;
import com.gergelydezso.smartlampsdk.connection.UserAppContextHolder;

/**
 * SmartLamp class.
 */
public class SmartLamp {

  private LedRGB mLed = null;
  private ServoMotor mServo = null;
  // TODO - CODE REVIEW - andrei|Nov 18, 2013 - remove reference. Add bridge as param to SmartLamp constructor.
  private static SmartLampCommunicationBridge mBridge = null;
  private CommandFilterManager filterManager = null;
  private static boolean isConnected = false;

  private SmartLamp() {
    mServo = new ServoMotor(mBridge);
    mLed = new LedRGB(mBridge);
    filterManager = new CommandFilterManager(new CommandTarget());
    filterManager.setFilter(new TimeCommandFilter());
    filterManager.setFilter(new BlankCommandFilter());
  }

  private static SmartLamp getSmartLamp() {
    return new SmartLamp();
  }

  /**
   * Connect to SmartLamp.
   * 
   * @param activityContext host activity context.
   * @param connectionType type of the connection.
   * @param listener onConnectionReady()/onConnectionFailed()/onConnectionLost
   */
  public static void connect(Context activityContext, ConnectionType connectionType, final ConnectionListener listener) {
    // TODO - CODE REVIEW - andrei|Nov 18, 2013 - I would rename the activityContext param to packageContext. We don't
    // want to reference activities.
    if (!isConnected) {

      UserAppContextHolder contextHolder = new UserAppContextHolder();
      // TODO - CODE REVIEW - andrei|Nov 18, 2013 - Maybe we should make sure we always keep a reference to a package
      // context: activityContext.getApplicationContext()
      contextHolder.setContext(activityContext);
      BridgeFactory factory = new BridgeFactory();
      factory.buildBridge(connectionType, new BridgeCreationListener() {
        @Override
        public void onConnectionReady(SmartLampCommunicationBridge bridge) {
          mBridge = bridge;
          listener.onConnectionReady(getSmartLamp());
          isConnected = true;
        }

        @Override
        public void onConnectionFailed() {
          listener.onConnectionFailed();
          isConnected = false;
          Log.d("SmartLamp", "Unable to connect");
        }

        @Override
        public void onConnectionLost() {
          listener.onConnectionLost();
          isConnected = false;
          Log.d("SmartLamp", "Connection was Lost");
        }
      });
    }
  }

  /**
   * Check teh SmartLamp connection status.
   */
  public boolean isConnected() {
    return isConnected;
  }

  /**
   * Disconnect the SmartLamp.
   */
  public void disconnect() {
    if (isConnected) {
      mBridge.disconnect();
    }
  }

  /**
   * Toggle light.
   * 
   * @param turnLightOn true = on/false = off.
   * @param callback Command callback.
   */
  // TODO - CODE REVIEW - andrei|Nov 18, 2013 - does turnLightOn need to be an Object refrence? Would't a primitive
  // boolean be better? What if someone sends a null?
  public void toggleLight(Boolean turnLightOn, CommandCallback callback) {

    // TODO - CODE REVIEW - andrei|Nov 18, 2013 - these should be public constants constants. Thus the user of the SDK
    // can reference them and make their code depend on them.
    int redMax = 125;
    int greenMax = 255;
    int blueMax = 255;

    int redMin = 0;
    int greenMin = 0;
    int blueMin = 0;

    if (turnLightOn) {
      // TODO - CODE REVIEW - andrei|Nov 18, 2013 - why aren't you calling adjustLedComponent?
      Command ledCommand = new LedCommand(mLed, redMax, greenMax, blueMax, callback);
      ledCommand.setTimeStamp(System.currentTimeMillis());
      filterManager.sendCommand(ledCommand);
    }
    else {
      Command ledCommand = new LedCommand(mLed, redMin, greenMin, blueMin, callback);
      ledCommand.setTimeStamp(System.currentTimeMillis());
      filterManager.sendCommand(ledCommand);
    }

  }

  /**
   * Adjust servo motor position.
   * 
   * @param servoPin ServoMotor identifier.
   * @param value angle of the ServoMotor.
   * @param callback onSuccess()/onError()
   */
  public void adjustServoComponent(ServoMotorEntity servoPin, int value, CommandCallback callback) {
    Command servoCommand = new ServoCommand(mServo, servoPin, value, callback);
    servoCommand.setTimeStamp(System.currentTimeMillis());
    filterManager.sendCommand(servoCommand);
  }

  /**
   * Adjust the LED value.
   * 
   * @param red LedRGB red pin value
   * @param green LedRGB green pin value
   * @param blue LedRGB blue pin value
   * @param callback onSuccess()/onError()
   */
  public void adjustLedComponent(int red, int green, int blue, CommandCallback callback) {
    Command ledCommand = new LedCommand(mLed, red, green, blue, callback);
    ledCommand.setTimeStamp(System.currentTimeMillis());
    filterManager.sendCommand(ledCommand);
  }

  /**
   * Adjust components.
   * 
   * @param batchBuilder batch builder.
   * @param callback command callback.
   */
  public void batchAdjustComponents(ComponentsBatchBuilder batchBuilder, CommandCallback callback) {
    // TODO - CODE REVIEW - andrei|Nov 18, 2013 - is this working? If not it should be private.
    Command batchCommand = new SetAllCommand(mBridge, batchBuilder, callback);
    batchCommand.setTimeStamp(System.currentTimeMillis());
    filterManager.sendCommand(batchCommand);
  }

  /**
   * Get SmartLamp status.
   * 
   * @param callback - onResult()
   */
  public void getStatus(CommandCallback callback) {
    Command statusCommand = new StatusCommand(mBridge, callback);
    statusCommand.setTimeStamp(System.currentTimeMillis());
    filterManager.sendCommand(statusCommand);
  }

}
