package code.slash.EventBus;

import code.slash.Model.Event;

public class EventItemClick {
    private boolean success;
    private Event event;

    public EventItemClick(boolean success, Event event) {
        this.success = success;
        this.event = event;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
