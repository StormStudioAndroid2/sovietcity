# SovietSity
this game about soviet sity.
Application in development! For Red Pixel Force.This game was 
This game was created in 2016
You must make soviet buildings, produce food and complete plans. 
Link to Google Play: https://play.google.com/store/apps/details?id=com.sovietcity&hl=en_US
I used component oriented programming
There are four parts of program code:
## Adapters
Adapters -- there are custom adapters for recycling view.
## Models
Models -- there are different managers and component. They are responsible for differen parts of game.
BUILDINGS: BuildManager help to consruct new building. Every building has buildcomponent, that define resources for building and its quantity. There are CustomBuildComponent(this component use with custom-form buildings) and ShemeBuildComponent 
Also every building has FunctionComponent. This is main function of building.  Function component can produce new resource, sell resource for population(for example, foodshop). Some buildings requires delivering resouces to produce material
DELIVERING: you can send resources from one building to another. You must build a road between this buildings, choose first build and set his role as Sender. Also you must choose second building and set his role as Destination. In a few days resources will delivered from sender to destination. For this i writing interfaces ChainDestination and ChainSender. Some function components implements this interfaces. 
POPULATION: there are three classes of population: children, adults, pensioners. Every class has happiness. If you build shops, schools, clinics, level of happiness increase. 
ThreadManager manage main game thread. That thread increase date, perform functions of all FunctionComponents, collect taxes every month.
Game saves realize with java serialization.
## Presenter
Presenter: this folder has DrawerManager. DrawerManager manage camera and draw only visible for player part of map. 
Class World creates and save all managers of game. 
## View
This folder has all android views that create interface of the game
