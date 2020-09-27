package com.sovietcity.Model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sovietcity.Presenter.World;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Серёга on 11.06.2016.
 */
public class EventDB {
    private static final String DATABASE_NAME = "events.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "EventData";
    // Название столбцов
    private static final String EVENT_COLUMN_ID = "_id";
    private static final String EVENT_COLUMN_TEXT = "Text";
    private static final String EVENT_COLUMN_DATE = "Date";
    private static final String EVENT_COLUMN_IS_EVERY_YEAR = "EveryYear";

    // Номера столбцов
    private static final int NUM_COLUMN_ID = 0;
    private static final int NUM_COLUMN_TEXT = 1;
    private static final int NUM_COLUMN_YEAR = 2;
    private static final int NUM_COLUMN_REWARD = 3;
    private static final int NUM_COLUMN_IS_EVERY_YEAR = 4;

    private static final String ANSWER_TABLE_NAME = "AnswerData";
    // Название столбцов
    private static final String ANSWER_COLUMN_ID = "_id";
    private static final String ANSWER_COLUMN_TEXT = "Text";
    private static final String ANSWER_COLUMN_MONEY = "Money";
    private static final String ANSWER_COLUMN_PEOPLE = "People";
    private static final String ANSWER_COLUMN_AUTHORITY = "Authority";

    // Номера столбцов
    private static final int ANSWER_NUM_COLUMN_ID = 0;
    private static final int ANSWER_NUM_COLUMN_TEXT = 1;
    private static final int ANSWER_NUM_COLUMN_MONEY = 2;
    private static final int ANSWER_NUM_COLUMN_PEOPLE = 3;
    private static final int ANSWER_NUM_COLUMN_AUTHORITY = 4;

    private SQLiteDatabase eventSqLiteDatabase;
    private EventPutter eventPutter;

    public EventDB() {
        this.eventPutter = new EventPutter();
    }

    public void setEventSqLiteDatabase(SQLiteDatabase eventSqLiteDatabase) {
        this.eventSqLiteDatabase = eventSqLiteDatabase;
    }

    public void putEvents() {

        eventPutter.putEvent("В СССР проводится перепись населения. Пожалуйста, потратьте некоторую сумму денег, чтобы собрать информацию о ваших жителях", "1959-01-15", false);
        insertEvent();
        eventPutter.putAnswer(1, "Будет исполнено!", -500, 0, 0);
        insertAnswer();
        eventPutter.putEvent("Сегодня была осуществлена первая попытка СССР запустить межпланетную станцию 'Луна-1' в космос. К сожалению, она не смогла сесть на Луну, но собрала множество новых данных", "1959-01-02", false);

        insertEvent();
        eventPutter.putAnswer(2, "Советские люди рано или поздно все равно высадятся на луну!", 0, 0, 0);
        insertAnswer();
        eventPutter.putAnswer(2, "Несмотря на некоторые неудачи, это все равно великое событие для нашей науки !", 0, 0, 0);
        insertAnswer();
        eventPutter.putEvent("Сегодня СССР празднует величайшее событие - Первомай! Вам же следует подготовить этот праздник в вашем городе", "05-01", true);
        insertEvent();
        eventPutter.putAnswer(3, "Все на первомай!", -10000, 0, 0);
        insertAnswer();
        eventPutter.putAnswer(3, "Надо хорошо его отпраздновать!", -1000, 0, 0);
        insertAnswer();
        eventPutter.putAnswer(3, "Давайте будем экономными и не будем тратить деньги на всякую глупость", -100, 0, 0);
        insertAnswer();
        eventPutter.putAnswer(3, "Если честно, я не очень люблю этот праздник...", 0, 0, 0);
        insertAnswer();
        eventPutter.putEvent("С годовщиной Великой Октябрьской Революции, товарищ! В этот великий день вам необходимо провести митинг всех трудящихся. На празднование 7 ноября вам придется выделить некоторую денежную сумму.", "11-07", true);
        insertEvent();

        eventPutter.putAnswer(4, "Всё на годовщину Революции!", -10000, 0, 0);
        insertAnswer();
        eventPutter.putAnswer(4, "Торжественно отпразднуем годовщину Революции!", -1000, 0, 0);
        insertAnswer();
        eventPutter.putAnswer(4, "Да, конечно. Обязательно проведем митинг.", -100, 0, 0);
        insertAnswer();
        eventPutter.putAnswer(4, "Знаете, у нас и денег особо нет...", 0, 0, 0);
        insertAnswer();
        eventPutter.putEvent("«Шестидесятники» - поколение советской интеллигенции, родившееся примерно между 1925 и 1945 годами. " +
                "Большинство «шестидесятников» были из интеллигентской или партийной среды, сформировавшейся в 1920-е годы. Их родители были убеждёнными большевиками, часто участниками Гражданской войны. Вера в идеалы коммунистов была самоочевидной, борьбе за эти идеалы их родители посвятили жизнь. Многие шестидесятники были интернационалистами, сторонниками «мира без границ». Также, шестидесятники почитали и уважали заветы Ленина", "1960-01-02", false);
        insertEvent();
        eventPutter.putAnswer(5, "Настоящие коммунисты!", 0, 0, 0);
        insertAnswer();
        eventPutter.putEvent("Сегодня в Москве проходит Декада культуры и искусства Азербайджана. В столицу съехались лучшие художественные коллективы Республики, признанные мастера и начинающая молодежь. Среди участников Декады и певец Муслим Магомаев. Он покорил всех своим исполнением 'Бухенвальдского набата' и каватины Фигаро' в Дворце Съездов. Кажется, молодой певец обрёл всесоюзную известность.", "196e-03-26", false);
        insertEvent();
        eventPutter.putAnswer(6, "Юноша из Баку покоряет мир!", 0, 0, 0);
        insertAnswer();
        eventPutter.putAnswer(6, "Люди мира, на минуту встаньте! Слушайте, слушайте...", 0, 0, 0);
        insertAnswer();
        eventPutter.putEvent("12 апреля 1961 года в Советском Союзе выведен на орбиту вокруг Земли первый в мире космический корабль-спутник \"Восток\" с человеком на борту. Этим человеком является Гагарин Юрий Алексеевич, летчик Советского Союза. Полёт длился 108 минут и завершился успешнo", "1961-04-12", false);
        insertEvent();
        eventPutter.putAnswer(7, "Ура, товарищи!", 0, 0, 0);
        insertAnswer();
        eventPutter.putEvent("Сегодня в Кремле состоялось расширенное заседание Президиума ЦК, где Суслов и Шёлепин поставили вопрос о смещении лидера страны со всех занижаемых постов. Срочно прибывшему в Москву на заседание Президиума Хрущеву были предъявлены жесткие обвинения в отходе от принципов коллективности руководства, волюнтаризме и грубом администрировании. Против Хрущева выступили почти все члены Президиума, кроме А. И. Микояна. 14 октября состоялся пленум ЦК КПСС, на котором Хрущев был освобожден от обязанностей Первого секретаря ЦК КПСС, члена Президиума ЦК партии, Председателя Совета Министров СССР \"в связи с преклонным возрастом и ухудшением состояния здоровья\". На октябрьском (1964 г.) пленуме ЦК было признано нецелесообразным дальнейшее совмещение обязанностей лидера партии и руководителя правительства. Первым секретарем ЦК КПСС стал Л. И. Брежнев, а Председателем Совета Министров СССР - А. Н. Косыгин.", "1964-10-12", false);
        insertEvent();
        eventPutter.putAnswer(8, "Он не мог править нормально страной.", 0, 0, 0);
        insertAnswer();
        eventPutter.putEvent("В СССР всё большую популярность обретает гимнастический обруч - хулахуп. Обруч стал популярен в 1964 году после фильма «Добро пожаловать, или Посторонним вход воспрещён». В его эпизоде девочка с завистью наблюдает, как другая виртуозно крутит хулахуп, перекидывая его с ноги на ногу. Научиться так же захотелось всем – но упорства (и подходящих условий) для тренировок хватило немногим. Попытки поучиться крутить обруч в малогабаритной квартире заканчивались плачевно – как минимум для мебели. А тренироваться «с нуля» на улице решались лишь те, чей энтузиазм заметно превышал застенчивость.", "1964-11-1", false);
        insertEvent();
        eventPutter.putAnswer(9, " - Хорошо, дорогая, я куплю его тебе. Только дома ничего не поколоти, ладно?", 0, 0, 0);
        insertAnswer();
        eventPutter.putAnswer(9, "Американская пустышка", 0, 0, 0);
        insertAnswer();
        eventPutter.putEvent("В СССР ужесточены меры по борьбе со взяточничеством. За взяточничество в особо крупных размерах установлено наказание от 8 до 15 лет тюремного заключения или расстрел с конфискацией имущества", "1962-02-20", false);
        insertEvent();
        eventPutter.putAnswer(10, "Чтобы я брал взятки?! Никогда!", 0, 0, 0);
        insertAnswer();
        eventPutter.putAnswer(10, "Вы мне угрожаете?", 0, 0, 0);
        insertAnswer();
        eventPutter.putAnswer(10, "Жаль, очень жаль...", 0, 0, 0);
        insertAnswer();
        eventPutter.putEvent("Сегодня тайно на Кубу прибыли баллистические ракеты. Советское правительство собирается их разместить в ответ на американские ракеты в Турции. Население СССР находится в неведении.", "1962-09-08", false);
        insertEvent();
        eventPutter.putAnswer(11, "ОК", 0, 0, 0);
        insertAnswer();
        eventPutter.putEvent("На Кубу прибыла вторая партия баллистических ракет", "1962-09-16", false);
        insertEvent();
        eventPutter.putAnswer(12, "Что же будет дальше?", 0, 0, 0);
        insertAnswer();
        eventPutter.putEvent("Сегодня американский разведчик облетел на самолете Кубу и запечатлел на Кубе советские баллистические ракеты.", "1962-10-15", false);
        insertEvent();
        eventPutter.putAnswer(13, "Ситуация накаляется", 0, 0, 0);
        insertAnswer();
        eventPutter.putEvent("Сегодня Организация Американских Государств приняла резолюцию о блокаде Кубы. Хрущёв заявил, что блокада незаконна, и что любой корабль под советским флагом будет её игнорировать. Он пригрозил, что если советские корабли будут атакованы американскими, ответный удар последует немедленно. Кроме этого Кеннеди выступил с речью по телевизору, где объявил о ракетах на Кубе. В США началась настоящая паника.", "1962-10-24", false);
        insertEvent();
        eventPutter.putAnswer(14, "Американцы провоцируют нас к войне! Но мы им еще покажем Кузькину мать!", 0, 0, 0);
        insertAnswer();
        eventPutter.putEvent("В Москве было 5 часов вечера, когда на Кубе разбушевался тропический шторм. В одно из подразделений ПВО пришло сообщение, что на подлёте к Гуантанамо замечен американский самолёт-разведчик U-2. Начальник штаба зенитного ракетного дивизиона С-75 капитан Антонец позвонил в штаб Плиеву за инструкциями, но того на месте не оказалось. Заместитель командующего ГСВК по боевой подготовке генерал-майор Леонид Гарбуз приказал капитану ждать появления Плиева. Через несколько минут Антонец вновь позвонил в штаб — никто не взял трубку. Когда U-2 был уже над Кубой, Гарбуз сам прибежал в штаб и, не дождавшись Плиева, отдал приказ уничтожить самолёт. По другим сведениям, приказ об уничтожении самолёта-разведчика мог быть отдан заместителем Плиева по ПВО генерал-лейтенантом авиации Степаном Гречко или командиром 27-й дивизии ПВО полковником Георгием Воронковым. Пуск был осуществлён в 10:22 по местному времени. Пилот U-2 майор Рудольф Андерсон погиб...", "1962-10-27", false);
        insertEvent();
        eventPutter.putAnswer(15, "Мир на пороге войны.", 0, 0, 0);
        insertAnswer();
        eventPutter.putEvent("'...мы согласны вывезти те средства с Кубы, которые Вы считаете наступательными средствами. Согласны это осуществить, и заявить в ООН об этом обязательстве. Ваши представители сделают заявление о том, что США, со своей стороны, учитывая беспокойство и озабоченность Советского государства, вывезут свои аналогичные средства из Турции. Давайте договоримся, какой нужен срок для вас и для нас, чтобы это осуществить. И после этого доверенные лица Совета Безопасности ООН могли бы проконтролировать на месте выполнение взятых обязательств...' - из письма Хрущева Кеннеди", "1962-10-28", false);
        insertEvent();
        eventPutter.putAnswer(16, "Кризис закончился.", 0, 0, 0);
        insertAnswer();
        eventPutter.putEvent("Сегодня в Новочеркасске на заводе началась забастовка рабочих. Они требуют повышение зарплаты труда. В Новочеркасск была направлена группа членов Президиума ЦК КПСС. Маршал Малиновский приказал использовать танки.", "1962-07-01", false);
        insertEvent();
        eventPutter.putAnswer(17, "Жестоко подавите восстание", 0, 0, 0);
        insertAnswer();
        eventPutter.putAnswer(17, "Танки? Настоящие социалистическое государство так не поступает!", 0, 0, -10);
        insertAnswer();
        eventPutter.putEvent("В ночь с первого на второе июня в город вошли танки и солдаты. Танки вошли в заводской двор и стали вытеснять ещё остающихся там, не применяя оружие. Среди собравшихся распространился слух, что несколько человек были задавлены гусеницами, и толпа стала бить тяжёлыми предметами по броне, пытаясь вывести из строя танки. В результате ранения получили несколько солдат. Но двор был очищен от митингующих. Ввод в город танков был воспринят народом крайне негативно, и ночью стали распространяться листовки, резко осуждающие нынешние власти и Хрущёва лично.Через некоторое время с завода им. Будённого к центру города двинулась толпа, вначале состоящая из рабочих, но по ходу следования к ней стали присоединяться случайные люди, в том числе и женщины с детьми.Военные попытались не допустить толпу к центру города, перегородив мост через реку Тузлов несколькими танками, БТРами и машинами, но большая часть людей просто перешла реку вброд, а самые решительные перелезали через технику, пользуясь тем, что военные не препятствовали им в этом. Толпа вышла на центральную улицу Московскую, в конце которой располагались здания горкома партии и горисполкома. На этой же улице находились помещения отдела милиции, аппарата уполномоченного УКГБ, Госбанка. Председатель горисполкома Замула и другие руководители предприняли попытку с балкона через микрофон обратиться к подошедшим с призывом прекратить дальнейшее движение и возвратиться на свои рабочие места. Но в стоявших на балконе полетели палки, камни, одновременно из толпы раздавались угрозы. Часть протестующих ворвалась внутрь здания и разбила стёкла окон, двери, повредила мебель, телефонную проводку, сбросила на пол люстры, портреты.\n" + "К зданию горисполкома прибыл начальник Новочеркасского гарнизона генерал-майор Олешко с 50 вооружёнными автоматами военнослужащими внутренних войск, которые, оттесняя людей от здания, прошли вдоль его фасада и выстроились лицом к ним в две шеренги. Олешко с балкона обратился к собравшимся с призывом прекратить погромы и разойтись. Но толпа не реагировала, раздавались различные выкрики, угрозы расправы. После этого военнослужащими из автоматов был произведён предупредительный залп вверх, отчего шумевшие и напиравшие на солдат люди отхлынули назад. Из толпы раздались выкрики: «Не бойтесь, стреляют холостыми», после чего люди вновь ринулись к зданию горкома и к выставленным вдоль него солдатам. Последовал повторный залп вверх и затем был открыт огонь по толпе, в результате чего 10—15 человек остались лежать на площади...", "1962-07-02", false);
        insertEvent();
        eventPutter.putAnswer(18, "Ну и поделом им", 0, 0, 0);
        insertAnswer();
        eventPutter.putAnswer(18, "Не стоит об этом рассказывать людям...", 0, 0, 0);
        insertAnswer();
        eventPutter.putAnswer(18, "Это ужасно", 0, 0, 0);
        insertAnswer();
        eventPutter.putEvent("В СССР проведена десятикратная деноминация рубля. В результате покупательная способность рубля уменьшилась. Вероятно, вы потеряете некоторую часть денег.", "1961-01-01", false);
        insertEvent();
        eventPutter.putAnswer(19, "Очень досадно", -5000, 0, 0);
        insertAnswer();
        eventPutter.putEvent("Сегодня Фидель Кастро, недавно захвативший власть на Кубе, выступил с речью о социалистическом характере революции", "1961-04-16", false);
        insertEvent();
        eventPutter.putAnswer(20, "КУБА - Коммунизм У Берегов Америки", 0, 0, 0);
        insertAnswer();
        eventPutter.putEvent("Сегодня ночью американские войска начали высадку на Кубу. Однако правительство Кубы быстро сориентировалось и направило силы народной полиции из соседних городов. К середине дня десантники были остановлены превосходящими силами Кубы. Потери «кубинской» бригады 2506 составили 114 человек убитыми и 1202 пленными.", "1961-04-17", false);
        insertEvent();
        eventPutter.putAnswer(21, "Viva la cuba!", 0, 0, 0);
        insertAnswer();
    }

    public synchronized EventGame getEventGame(World world, String date) {
        Cursor cursor = eventSqLiteDatabase.query(TABLE_NAME, null, EVENT_COLUMN_DATE + " = ?", new String[]{String.valueOf(date)}, null, null, null);
        if (cursor.moveToFirst()) {
            EventGame eventGame = new EventGame();
            eventGame.setText(cursor.getString(NUM_COLUMN_TEXT));
            int id = cursor.getInt(NUM_COLUMN_ID);
            cursor.close();
            Cursor newCursor = eventSqLiteDatabase.query(ANSWER_TABLE_NAME, null, ANSWER_COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);

            if (newCursor.moveToFirst()) {
                do {
                    EventAnswerDataContainer eventAnswerDataContainer = new EventAnswerDataContainer();
                    eventAnswerDataContainer.setText(newCursor.getString(ANSWER_NUM_COLUMN_TEXT));
                    eventAnswerDataContainer.setAuthority(newCursor.getInt(ANSWER_NUM_COLUMN_AUTHORITY));
                    eventAnswerDataContainer.setMoney(newCursor.getInt(ANSWER_NUM_COLUMN_MONEY));
                    eventAnswerDataContainer.setPeople(newCursor.getInt(ANSWER_NUM_COLUMN_PEOPLE));
                    EventAnswerCreator eventAnswerCreator = new EventAnswerCreator();
                    EventAnswer eventAnswer = eventAnswerCreator.createEventAnswer(world, eventAnswerDataContainer);
                    eventGame.addAnswer(eventAnswer);
                } while (newCursor.moveToNext());
            }
            newCursor.close();
            return eventGame;
        }
        cursor.close();

        return null;
    }

    public ArrayList<EventGame> getEveryYearEvents(World world) {
        ArrayList<EventGame> arrayList = new ArrayList<>();
        Cursor cursor = eventSqLiteDatabase.query(TABLE_NAME, null, EVENT_COLUMN_IS_EVERY_YEAR + " = ?", new String[]{String.valueOf(1)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                EventGame eventGame = new EventGame();
                eventGame.setText(cursor.getString(NUM_COLUMN_TEXT));
                eventGame.setDate(cursor.getString(NUM_COLUMN_YEAR));
                int id = cursor.getInt(NUM_COLUMN_ID);
                Cursor newCursor = eventSqLiteDatabase.query(ANSWER_TABLE_NAME, null, ANSWER_COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);
                if (newCursor.moveToFirst()) {
                    do {
                        EventAnswerDataContainer eventAnswerDataContainer = new EventAnswerDataContainer();
                        eventAnswerDataContainer.setText(newCursor.getString(ANSWER_NUM_COLUMN_TEXT));
                        eventAnswerDataContainer.setAuthority(newCursor.getInt(ANSWER_NUM_COLUMN_AUTHORITY));
                        eventAnswerDataContainer.setMoney(newCursor.getInt(ANSWER_NUM_COLUMN_MONEY));
                        eventAnswerDataContainer.setPeople(newCursor.getInt(ANSWER_NUM_COLUMN_PEOPLE));
                        EventAnswerCreator eventAnswerCreator = new EventAnswerCreator();
                        EventAnswer eventAnswer = eventAnswerCreator.createEventAnswer(world, eventAnswerDataContainer);
                        eventGame.addAnswer(eventAnswer);
                    } while (newCursor.moveToNext());
                }
                newCursor.close();
                arrayList.add(eventGame);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return arrayList;
    }

    public void insertEvent() {
        eventPutter.putEventValues(eventSqLiteDatabase, TABLE_NAME);
    }

    public void insertAnswer() {
        eventPutter.putAnswerValues(eventSqLiteDatabase, ANSWER_TABLE_NAME);

    }


}
