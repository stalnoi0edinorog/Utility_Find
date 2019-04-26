package finder

import FoundFiles
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Tests {

    @Test
    fun finder(){
        assertEquals((listOf
            ("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry\\Н.Гумилёв\\И.Бродский\\Пилигримы.txt")),
            FoundFiles("Пилигримы", true)
                .finder(File("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry")))

        assertEquals(listOf
            ("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry\\А.Пушкин\\Я памятник воздвиг себе.txt"),
                FoundFiles("Я памятник воздвиг себе", true)
                .finder(File("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry")))

        assertEquals((listOf
            (".\\input poetry\\А.Пушкин\\Тадарашка в вас влюблён.txt",
             ".\\input poetry\\А.Пушкин\\Я вас любил.txt",
             ".\\input poetry\\А.Пушкин\\Я не люблю твоей Корины.txt",
             ".\\input poetry\\Н.Гумилёв\\Влюблённая в дьявола.txt",
             ".\\input poetry\\Н.Гумилёв\\И.Бродский\\Я вас любил.txt",
             ".\\input poetry\\Н.Гумилёв\\Я больше её не люблю.txt")),
            FoundFiles("люб", true).finder(File(".")))

        assertEquals((listOf
            (".\\input poetry\\А.Пушкин\\Тадарашка в вас влюблён.txt",
             ".\\input poetry\\А.Пушкин\\Я вас любил.txt",
             ".\\input poetry\\А.Пушкин\\Я не люблю твоей Корины.txt",
             ".\\input poetry\\Н.Гумилёв\\Влюблённая в дьявола.txt",
             ".\\input poetry\\Н.Гумилёв\\И.Бродский\\Я вас любил.txt",
             ".\\input poetry\\Н.Гумилёв\\Я больше её не люблю.txt")),
            FoundFiles("люб", true).finder(File(".\\input poetry")))

        assertEquals((listOf
            ("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry\\Н.Гумилёв\\Девочка.txt")),
            FoundFiles("Девочка", false)
                .finder(File("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry\\Н.Гумилёв")))

        assertEquals((listOf()),
            FoundFiles("Жираф", true)
                .finder(File("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry")))

        assertEquals((listOf()),
            FoundFiles("Я всегда твердил, что судьба - игра", false)
                .finder(File("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry\\Н.Гумилёв")))

        assertEquals((listOf
            ("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry\\Н.Гумилёв\\И.Бродский\\Я всегда твердил, что судьба - игра.txt")),
            FoundFiles("Я всегда твердил, что судьба - игра", true)
                .finder(File("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry\\Н.Гумилёв")))

        assertEquals((listOf
            ("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry\\А.Пушкин\\Слово милой.txt",
             "C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry\\Н.Гумилёв\\Словом разрушали города.txt")),
            FoundFiles("Словом?\\s(милой|разрушали)", true)
                .finder(File("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry")))

        assertEquals((listOf
            ("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry\\Н.Гумилёв\\Влюблённая в дьявола.txt",
             "C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry\\Н.Гумилёв\\Гиена.txt",
             "C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry\\Н.Гумилёв\\Девочка.txt",
             "C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry\\Н.Гумилёв\\Словом разрушали города.txt",
             "C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry\\Н.Гумилёв\\Я целовал посланья лета.txt")),
            FoundFiles("а.txt$", false)
                .finder(File("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry\\Н.Гумилёв")))

        assertEquals((listOf
            ("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry\\А.Пушкин\\Свободы сеятель пустынный.txt")),
            FoundFiles("^Свободы\\sсеятель\\sпустынный.txt$", true)
                .finder(File("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry")))

        assertEquals((listOf
            ("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry\\А.Пушкин\\Люблю вам сумрак неизвестный.txt")),
            FoundFiles("[а-я]{11}", true)
                .finder(File("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry")))

        assertEquals((listOf
            ("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry\\А.Пушкин\\Если жизнь тебя обманет.txt",
             "C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry\\Н.Гумилёв\\Нет тебя тревожней и капризней.txt")),
            FoundFiles("тебя(?=\\s(обманет|тревожней))", true)
                .finder(File("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry")))

        assertEquals((listOf
            ("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry\\А.Пушкин\\Если жизнь тебя обманет.txt",
             "C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry\\Н.Гумилёв\\И.Бродский\\Пилигримы.txt",
             "C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry\\Н.Гумилёв\\Словом разрушали города.txt")),
            FoundFiles("([Пл]и)+", true)
                .finder(File("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry")))

        assertEquals((listOf
            ("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry\\А.Пушкин\\Люблю вам сумрак неизвестный.txt",
             "C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry\\А.Пушкин\\На холмах Грузии лежит ночная мгла.txt",
             "C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry\\А.Пушкин\\Осеннее утро.txt",
             "C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry\\А.Пушкин\\Погасло дневное светило.txt",
             "C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry\\Н.Гумилёв\\Нет тебя тревожней и капризней.txt",
             "C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry\\Н.Гумилёв\\Я целовал посланья лета.txt")),
            FoundFiles("е..(в|т)", true)
                .finder(File("C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry")))



    }

}