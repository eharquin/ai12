package utc.pokerut.client.communication.commands;

import utc.pokerut.client.communication.Core;
import utc.pokerut.common.messages.Command;
import utc.pokerut.common.messages.Message;

abstract public class ClientCommand<T extends Message> extends Command<T, Core> {
}
