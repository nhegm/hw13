import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindQueryWhenSearch() {
        SimpleTask simpleTask = new SimpleTask(5, "Купить рогалик");

        String[] subtasks = {"Убить муху", "Купить яйца", "Забрать дитя"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Купить или не купить?",
                "Купить самокат деду",
                "Во вторник после обеда"
        );

        String query = "Купить";

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};

        Assertions.assertArrayEquals(expected, todos.search(query));
    }

    @Test
    public void shouldNotFindQueryWhenSearch() {
        SimpleTask simpleTask = new SimpleTask(5, "Развесить белье");

        String[] subtasks = {"Убить муху", "Купить яйца", "Забрать дитя"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Купить или не купить?",
                "Купить самокат деду",
                "Во вторник после обеда"
        );

        String query = "Побрить";

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {};

        Assertions.assertArrayEquals(expected, todos.search(query));
    }

    @Test
    public void shouldFindQueryWhenSearchInVariousTasks() {
        SimpleTask simpleTask = new SimpleTask(5, "Развесить белье или воду");

        String[] subtasks = {"Убить муху", "Курить яйца", "Забрать белье"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Купить или не купить?",
                "Подарить муху",
                "Во вторник после обеда"
        );

        String queryS = "Развесить";
        String queryE = "Курить";
        String queryMTopic = "Купить";
        String queryMProject = "Подарить";
        String queryEpicMeeting = "муху";
        String querySimpleMeeting = "или";
        String querySimpleEpic = "белье";

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expectedOnlyS = {simpleTask};
        Task[] expectedOnlyE = {epic};
        Task[] expectedOnlyMT = {meeting};
        Task[] expectedOnlyMP = {meeting};
        Task[] expectedEpicMeeting = {epic, meeting};
        Task[] expectedSimpleMeeting = {simpleTask, meeting};
        Task[] expectedSimpleEpic = {simpleTask, epic};

        Assertions.assertArrayEquals(expectedOnlyS, todos.search(queryS));
        Assertions.assertArrayEquals(expectedOnlyE, todos.search(queryE));
        Assertions.assertArrayEquals(expectedOnlyMT, todos.search(queryMTopic));
        Assertions.assertArrayEquals(expectedOnlyMP, todos.search(queryMProject));
        Assertions.assertArrayEquals(expectedEpicMeeting, todos.search(queryEpicMeeting));
        Assertions.assertArrayEquals(expectedSimpleMeeting, todos.search(querySimpleMeeting));
        Assertions.assertArrayEquals(expectedSimpleEpic, todos.search(querySimpleEpic));

    }
}
