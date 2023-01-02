package utc.pokerut.client.ihmgame.adapters;

import utc.pokerut.client.ihmgame.GameViewController;
import utc.pokerut.common.dataclass.Chat;

public class ChatAdapter {

    private GameViewController gameViewController;

    public ChatAdapter(GameViewController gameViewController){
        this.gameViewController = gameViewController;
    }

    private Chat chat;

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public void initChat(Chat chat){
        //@TODO: Update game scene in GameViewController
    }

    public void updateChat(Chat chat){
        //@TODO: Update game scene in GameViewController
    }

}
