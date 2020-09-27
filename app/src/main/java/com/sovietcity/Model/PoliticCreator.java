package com.sovietcity.Model;

import com.sovietcity.Presenter.World;

import java.util.ArrayList;

/**
 * Created by Серёга on 25.05.2016.
 */
public class PoliticCreator {
    public ArrayList<Politic> createPolitics(World world) {
        ArrayList<Politic> politics = new ArrayList<>();
        politics.add(addTaxOnChildlessness(world));
        politics.add(createLocalPapers(world));
        politics.add(restoreStachanovMotion(world));
        return politics;
    }

    public Politic addTaxOnChildlessness(final World world) {
        Politic politic = new Politic();
        politic.setName("Налог на бездетность");
        politic.setDescribe("Налог на бездетность существовал в СССР как «Налог на холостяков, одиноких и малосемейных граждан» с ноября 1941 года на основании Указов Президиума Верховного Совета СССР от 21 ноября 1941 г. «О налоге на холостяков, одиноких и бездетных граждан СССР» и от 8 июля 1944 г\n" +
                "Бездетные мужчины от 20 до 50 лет и бездетные замужние женщины от 20 до 45 лет должны были отчислять 6% зарплаты государству. Введя его, мы можем значительно увеличить доходы в казну. Правда, вряд ли это поможет увеличить рождаемость и наверняка испортит настроение людям");
        politic.setPoliticInterface(new PoliticInterface() {
            @Override
            public void doPolitic() {
                world.getPopulationManager().getPopulation().getPopulationDataComponent().setTaxK(world.getPopulationManager().getPopulation().getPopulationDataComponent().getTaxK() * 1.1);
                world.getPopulationManager().getPopulation().getPopulationDataComponent().setBaseChanceBeget(world.getPopulationManager().getPopulation().getPopulationDataComponent().getBaseChanceBeget() + 0.01);

            }
        });
        return politic;
    }

    public Politic createLocalPapers(final World world) {
        Politic politic = new Politic();
        politic.setName("Создание региональных газет");
        politic.setDescribe("Создать региональные газеты, просвещающие население и рассказывающие им о политике нашей великой Партии. С помощью них мы можем пропагандировать идеи коммунизма и уменьшать недовольство");
        politic.setPoliticInterface(new PoliticInterface() {
            @Override
            public void doPolitic() {
                world.getPopulationManager().getPopulation().getAdultGroup().setWealth(world.getPopulationManager().getPopulation().getAdultGroup().getWealth() + 13);
                world.getPopulationManager().getPopulation().getPensionerGroup().setWealth(world.getPopulationManager().getPopulation().getPensionerGroup().getWealth() + 13);
            }
        });
        return politic;
    }

    public Politic restoreStachanovMotion(final World world) {
        Politic politic = new Politic();
        politic.setName("Возродить Стахановское движение");
        politic.setDescribe("В наше время все меньше и меньше вспоминают про Стаханова, основавшее знаменитое движение. Хотя именно он основал в 1935 году движение ударного труда. Возродив движение, мы можем увеличить темпы производства");
        politic.setPoliticInterface(new PoliticInterface() {
            @Override
            public void doPolitic() {
                world.getPopulationManager().getPopulation().getPopulationDataComponent().setPowerK(world.getPopulationManager().getPopulation().getPopulationDataComponent().getPowerK() * 1.25);
            }
        });
        return politic;
    }
}
