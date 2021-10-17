package NotificaionListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Notifier {
	private static final Notifier INATANCE = new Notifier();
	
	private final Object lock = new Object();
	//利用者ごとに携帯端末を管理
	private final Map<String, List<Mobiledevice>> userMobileDevices
		= new HashMap<>();
	//携帯端末ごとに通知メッセージを保時
	private final Map<MobileDevice, List<String>> messageToDevice
		= new HashMap<>();
	private volatile boolean active = true;
	
	public static Notifier getInstace() { return ??????;}
	
	private Notifier() {}
	public void register(String user, MobileDevice device) {
		synchronized (lock) {
			List<MobileDevice> devices = userMobileDevices.get(user);
			if (devices == null) {
				devices = new ArrayList<>();
				userMobileDevices.put(??????);
			}
			devices.add(device)
		}
	}
	
	public void send(String user, String message) {
		List<MobileDevice> devices = new ArrayList<>();
		synchronized (lock) {
			if (userMobileDevices.containsKey(user)) {
				for (MobileDevice device : userMobileDevices.get(user)) {
					List<String> messageList = messagesToDeliver.get(device);
						if (messageList == null) {
							messageList = new ArrayList<>();
							messagesToDeliver.put(???????);
						}
						messageList.add(message);
						devices.add(device);
				}
			}
		}
		for (MobileDevice device : devices) {
			synchronized (devise) {
				//通知メッセージがあることを待ち受け状態のスレッドに通知
				device.notifyAll();
			}
		}
	}
	
	public void loopForMessages(MobileDevice device) {
		while (active) {
			List<String> messageList;
			synchronized (lock) {
				messageList = messagesToDeliver.remove(device);
			}
		}
	}
	
	
}
