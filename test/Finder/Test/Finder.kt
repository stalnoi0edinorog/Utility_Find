
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Tests {

    @Test
    fun finder(){
        assertEquals((listOf
            ("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input.poetry\\Н.Гумилёв\\И.Бродский\\Пилигримы.txt")),
            FoundFiles().finder(File("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input.poetry"),
                "Пилигримы", true))

        assertEquals((listOf
            ("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input.poetry\\А.Пушкин\\Я памятник себе воздвиг....txt",
            "C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input.poetry\\Н.Гумилёв\\И.Бродский\\Я памятник себе воздвиг....txt")),
            FoundFiles().finder(File("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input.poetry"),
                "Я памятник себе воздвиг...", true))

        assertEquals((listOf
            (".\\input.poetry\\А.Пушкин\\Тадарашка в вас влюблён....txt",
            ".\\input.poetry\\А.Пушкин\\Я вас любил....txt",
            ".\\input.poetry\\А.Пушкин\\Я не люблю твоей Корины....txt",
            ".\\input.poetry\\Н.Гумилёв\\И.Бродский\\Я вас любил....txt")),
            FoundFiles().finder(File("."), "люб", true))

        assertEquals((listOf
            ("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input.poetry\\Н.Гумилёв\\Девочка.txt")),
            FoundFiles().finder(File("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input.poetry\\Н.Гумилёв"),
                "Девочка", false))

        assertEquals((listOf()),
            FoundFiles().finder(File("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input.poetry"),
                "Жираф", true))

        assertEquals((listOf()),
            FoundFiles().finder(File("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input.poetry\\Н.Гумилёв"),
                "Я всегда твердил, что судьба - игра...", false))

        assertEquals((listOf("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input.poetry\\Н.Гумилёв\\И.Бродский\\Я всегда твердил, что судьба - игра....txt")),
            FoundFiles().finder(File("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input.poetry\\Н.Гумилёв"),
                "Я всегда твердил, что судьба - игра...", true))
    }

}