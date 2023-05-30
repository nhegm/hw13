import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TasksTest {
    @Test
    public void shouldFindQueryInTasks() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Купить молоко", "Купить яйца", " Взять хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );
        String querySimpleTask = "Позвонить";
        String queryEpic = "Купить";
        String queryMeetingTopic = "Выкатка";
        String queryMeetingProject = "НетоБанка";

        boolean expected = true;
        boolean actualS = simpleTask.matches(querySimpleTask);
        boolean actualE = epic.matches(queryEpic);
        boolean actualM1 = meeting.matches(queryMeetingTopic);
        boolean actualM2 = meeting.matches(queryMeetingProject);

        Assertions.assertEquals(expected, actualS);
        Assertions.assertEquals(expected, actualE);
        Assertions.assertEquals(expected, actualM1);
        Assertions.assertEquals(expected, actualM2);
    }

    @Test
    public void shouldNotFindQueryInTasks() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Купить молоко", "Купить яйца", " Взять хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        String querySimpleTask = "Заказать";
        String queryEpic = "Продать";
        String queryMeetingTopic = "приложуха";
        String queryMeetingProject = "СберБанка";

        boolean expected = false;
        boolean actualS = simpleTask.matches(querySimpleTask);
        boolean actualE = epic.matches(queryEpic);
        boolean actualM1 = meeting.matches(queryMeetingTopic);
        boolean actualM2 = meeting.matches(queryMeetingProject);

        Assertions.assertEquals(expected, actualS);
        Assertions.assertEquals(expected, actualE);
        Assertions.assertEquals(expected, actualM1);
        Assertions.assertEquals(expected, actualM2);

    }

}
