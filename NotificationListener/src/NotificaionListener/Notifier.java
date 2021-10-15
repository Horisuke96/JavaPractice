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
	
}
