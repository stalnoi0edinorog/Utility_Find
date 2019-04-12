
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Tests {

    @Test
    fun finder(){
        assertEquals((listOf
            ("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input.poetry\\Н.Гумилёв\\И.Бродский\\Пилигримы.txt")),
            FoundFiles("Пилигримы", true)
                .finder(File("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input.poetry")))

        assertEquals((listOf
            ("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input.poetry\\А.Пушкин\\Я памятник себе воздвиг....txt",
            "C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input.poetry\\Н.Гумилёв\\И.Бродский\\Я памятник себе воздвиг....txt")),
            FoundFiles("Я памятник себе воздвиг...", true)
                .finder(File("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input.poetry")))

        assertEquals((listOf
            (".\\input.poetry\\А.Пушкин\\Тадарашка в вас влюблён....txt",
            ".\\input.poetry\\А.Пушкин\\Я вас любил....txt",
            ".\\input.poetry\\А.Пушкин\\Я не люблю твоей Корины....txt",
            ".\\input.poetry\\Н.Гумилёв\\И.Бродский\\Я вас любил....txt")),
            FoundFiles("люб", true).finder(File(".")))

        assertEquals((listOf
            ("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input.poetry\\Н.Гумилёв\\Девочка.txt")),
            FoundFiles("Девочка", false)
                .finder(File("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input.poetry\\Н.Гумилёв")))

        assertEquals((listOf()),
            FoundFiles("Жираф", true)
                .finder(File("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input.poetry")))

        assertEquals((listOf()),
            FoundFiles( "Я всегда твердил, что судьба - игра...", false)
                .finder(File("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input.poetry\\Н.Гумилёв")))

        assertEquals((listOf
            ("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input.poetry\\Н.Гумилёв\\И.Бродский\\Я всегда твердил, что судьба - игра....txt")),
            FoundFiles("Я всегда твердил, что судьба - игра...", true)
                .finder(File("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input.poetry\\Н.Гумилёв")))
    }

}