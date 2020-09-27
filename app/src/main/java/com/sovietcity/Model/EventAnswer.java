package com.sovietcity.Model;

/**
 * Created by Серёга on 11.06.2016.
 */
public class EventAnswer {
    private EventAnswerChangerComponent eventAnswerChangerComponent;
    private String text;

    public void setText(String text) {
        this.text = text;
    }

    public void setEventAnswerChangerComponent(EventAnswerChangerComponent eventAnswerChangerComponent) {
        this.eventAnswerChangerComponent = eventAnswerChangerComponent;
    }
    public void function() {
        eventAnswerChangerComponent.function();
    }

    public String getText() {
        return text;
    }
}
