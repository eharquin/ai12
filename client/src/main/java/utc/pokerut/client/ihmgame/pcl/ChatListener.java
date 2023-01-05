package utc.pokerut.client.ihmgame.pcl;

import utc.pokerut.client.ihmgame.adapters.ChatAdapter;
import utc.pokerut.common.dataclass.Chat;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChatListener implements PropertyChangeListener {

    private ChatAdapter chatAdapter;

    @Override
    public void propertyChange(PropertyChangeEvent event){

        if(event != null){

            String action = event.getPropertyName();
            String[] actions = action.split("_");
            Chat chat = (Chat) event.getNewValue();

            switch (actions[0]){
                case "init":
                    chatAdapter.initChat(chat);
                    break;
                case "update":
                    chatAdapter.updateChat(chat);
                    break;
            }
        }
    }

    public ChatListener(ChatAdapter chatAdapter){
        this.chatAdapter = chatAdapter;
    }


}
