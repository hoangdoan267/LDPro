package tamhoang.ldpro4.Telegram;

import org.drinkless.td.libcore.telegram.Client;
import org.drinkless.td.libcore.telegram.TdApi;

public class TelegramClient {
    private static Client client;

    public static Client getClient(Callback paramCallback) {
        Client client = client;
        if (client == null) {
            try {
                client = Client.create(paramCallback, null, null);
            } catch (Exception exception) {}
        } else {
            client.setUpdatesHandler((Client.ResultHandler)exception);
        }
        return client;
    }

    public static interface Callback extends Client.ResultHandler {
        void onResult(TdApi.Object param1Object);
    }
}
