package `class-Parser`

import Parser
import CmdLineFormatException
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Tests {
   @Test
   fun isCmd(){
      assertTrue(Parser(arrayOf("find","-d", "\"../home/julia", "ju/tmp/file1\"", "Письма", "незнакомке")).isCmd())
      assertTrue(Parser(arrayOf("find", "-d", "../tmp/file1", "люб*", "и?", "нака+зание")).isCmd())
      assertTrue(Parser(arrayOf("find", "-d", "./tmp/file1", "люб*", "и?", "нака+зание")).isCmd())
      assertTrue(Parser(arrayOf("find", "-d", "/home/julia/tmp/file1", "люб*", "и?", "нака+зание")).isCmd())
      assertTrue(Parser(arrayOf("find", "-d", "~/tmp/file11", "люб*", "и?", "нака+зание")).isCmd())
      assertTrue(Parser(arrayOf("find", "-ra", "тор*", "купи?", "помидор")).isCmd())
      assertTrue(Parser(arrayOf("find", "-d", "C:\\Users\\Julia", "люб*", "и?", "нака+зание")).isCmd())
      assertTrue(Parser(arrayOf("find", "-r", "-d", "C:\\Users\\Julia", "[^1234]", "мур?", "проверка")).isCmd())
      assertTrue(Parser(arrayOf("find", "В", "конце", "оба", "умрут")).isCmd())
      assertTrue(Parser(arrayOf("find", "-r", "-d", ".\\input.poetry\\А.Пушкин", "Собор", "Парижской", "Богоматери")).isCmd())
      assertFalse(Parser(arrayOf("find", "-r", "-d", "В", "конце", "оба", "умрут")).isCmd())
      assertFalse(Parser(arrayOf("find", "-ra", "-d", "C:\\Users\\Julia", "Собор", "Парижской", "Богоматери")).isCmd())
      assertFalse(Parser(arrayOf("find", "-r", "-da", "C:\\Users\\Julia", "Буранный", "полустанок")).isCmd())
      assertFalse(Parser(arrayOf("find", "-r", "-d", "C:\\Use:rs\\Julia", "Человек", "в", "футляре")).isCmd())
      assertFalse(Parser(arrayOf("-r", "-d", "C:\\Users\\Julia", "[^1234]", "мур?", "проверка")).isCmd())
      assertFalse(Parser(arrayOf("-r", "-d", "C:\\Users\\Julia", "[^1234]", "мур?", "проверка")).isCmd())
      assertFalse(Parser(arrayOf("find", "-d", "\"../home/julia", "ju/tmp/file1", "Солнечные", "вспышки")).isCmd())
      assertFalse(Parser(arrayOf("find", "-d", "../home/julia", "ju/tmp/file1\"", "Солнечные", "вспышки")).isCmd())
      assertFalse(Parser(arrayOf("find", "-d", "\"../home/julia","ju/tmp/file1", "-r", "Солнечные", "вспышки")).isCmd())
      assertFalse(Parser(arrayOf("find", "-d", "~./home/julia/tmp/file1", "-r", "Солнечные", "вспышки")).isCmd())
      assertFalse(Parser(arrayOf("find", "-r", "-d", ".\\input.poetry\\А.Пушкин", "Со:бор", "Парижской", "Богоматери")).isCmd())
      assertFalse(Parser(arrayOf("find", "-r", "-d", ".\\input.poetry\\А.Пушкин", "Собор\"", "Парижской", "Богоматери")).isCmd())
      assertFalse(Parser(arrayOf("find", "-r", "-d", ".\\input.poetry\\А.Пушкин", "Собор", "Париж/ской", "Богоматери")).isCmd())

   }

   @Test
   fun r() {
      assertTrue(Parser(arrayOf("find", "-r", "-d", "C:\\Users\\Julia", "Преступление", "и", "наказание")).r())
      assertFalse(Parser(arrayOf("find", "-d", "C:\\Users\\Julia", "Иду", "на", "грозу")).r())
   }

   @Test
   fun directory() {

      assertEquals(".", Parser(arrayOf("find", "-ra", "тор*", "купи?", "помидор")).directory())

      assertEquals("\"../home/julia ju/tmp/file1\"",
         Parser(arrayOf("find","-d", "\"../home/julia", "ju/tmp/file1\"", "Письма", "незнакомке")).directory())

      assertEquals(
         "/home/julia/tmp/file1",
         Parser(arrayOf("find", "-d", "/home/julia/tmp/file1", "люб*", "и?", "нака+зание")).directory())

      assertEquals(
         "\"C:\\Users\\Julia\\Idea Projects\\SecondTask(Find)\\input poetry\"",
         Parser(arrayOf("find","-d", "\"C:\\Users\\Julia\\Idea", "Projects\\SecondTask(Find)\\input", "poetry\"",
            "Письма", "незнакомке")).directory())

      assertEquals(
         "\"C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry\"",
         Parser(arrayOf("find","-d", "\"C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input", "poetry\"",
            "Письма", "незнакомке")).directory())

      assertEquals(".", Parser(arrayOf("find", "-r", "У", "нас", "всегда", "будет", "Париж")).directory())

      assertEquals("C:\\Users\\Julia\\IdeaProjects",
         Parser(arrayOf("find", "-r", "-d", "C:\\Users\\Julia\\IdeaProjects", "Тихий", "Дон")).directory())

      assertEquals("C:\\Users",
         Parser(arrayOf("find", "-r", "-d", "C:\\Users", "Письма", "незнакомке")).directory())
   }

   @Test
   fun filename() {
      assertEquals("-ra тор* купи? помидор", Parser(arrayOf("find", "-ra", "тор*", "купи?", "помидор")).fileName())
      assertEquals("Я вас любил...", Parser(arrayOf("find", "Я", "вас", "любил...")).fileName())
      assertEquals("сумрак*", Parser(arrayOf("find", "-r", "сумрак*")).fileName())

      assertEquals("Чёрная невеста*",
         Parser(arrayOf("find", "-r", "-d", "C:\\Users\\Julia", "Чёрная", "невеста*")).fileName())

      assertEquals("И дольше века длится день",
         Parser(arrayOf("find", "-r", "-d", "C:\\Users\\Julia", "И", "дольше", "века", "длится", "день")).fileName())

      assertEquals("Я+ [вас] люб?ил...", Parser(arrayOf("find", "Я+", "[вас]", "люб?ил...")).fileName())

      assertEquals("На холмах Грузии...",
         Parser(arrayOf("find","-d", "\"C:\\Users\\Julia\\Idea", "Projects\\SecondTask(Find)\\input", "poetry\"",
            "На", "холмах", "Грузии...")).fileName())
   }
}

