package utc.pokerut.common.messages;

abstract public class Command<M extends Message,T> {

    protected T core;
    protected M message;

    public void setCore(T core) {
        this.core = core;
    }

    public void setMessage(M message) {
        this.message = message;
    }

    abstract public void execute();
}
