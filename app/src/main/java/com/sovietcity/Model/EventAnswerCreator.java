package com.sovietcity.Model;

import com.sovietcity.Presenter.World;

/**
 * Created by Серёга on 11.06.2016.
 */
public class EventAnswerCreator {
    public EventAnswer createEventAnswer(final World world, final EventAnswerDataContainer eventAnswerDataContainer) {
        EventAnswer eventAnswer = new EventAnswer();
        eventAnswer.setText(eventAnswerDataContainer.getText());
        eventAnswer.setEventAnswerChangerComponent(new EventAnswerChangerComponent() {
            @Override
            public void function() {
                world.getPopulationManager().getPopulation().getAdultGroup().increasePopulation(eventAnswerDataContainer.getPeople());
                world.getCashManager().addCash(eventAnswerDataContainer.getMoney());


            }
        });
        return eventAnswer;
    }
}
