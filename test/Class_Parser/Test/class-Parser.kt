package Class_Parser.Test

import Parser
import CmdLineFormatException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertThrows
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Tests {
   @Test
   fun isCmd(){
      assertTrue(Parser(arrayOf("find", "-d", "../tmp/file1", "люб*", "и?", "нака+зание")).isCmd())
      assertTrue(Parser(arrayOf("find", "-d", "/home/julia/tmp/file1", "люб*", "и?", "нака+зание")).isCmd())
      assertTrue(Parser(arrayOf("find", "-d", "~/tmp/file11", "люб*", "и?", "нака+зание")).isCmd())
      assertTrue(Parser(arrayOf("find", "-ra", "тор*", "купи?", "помидор")).isCmd())
      assertTrue(Parser(arrayOf("find", "-d", "C:\\Users\\Julia", "люб*", "и?", "нака+зание")).isCmd())
      assertTrue(Parser(arrayOf("find", "-r", "-d", "C:\\Users\\Julia", "[^1234]", "мур?", "проверка")).isCmd())
      assertTrue(Parser(arrayOf("find", "В", "конце", "оба", "умрут")).isCmd())
      assertTrue(Parser(arrayOf("find", "-d",".\\", "конце", "оба", "умрут")).isCmd())
      assertTrue(Parser(arrayOf("find", "-r", "-d", ".\\input.poetry\\А.Пушкин", "Собор", "Парижской", "Богоматери")).isCmd())
      assertFalse(Parser(arrayOf("find", "-r", "-d", "В", "конце", "оба", "умрут")).isCmd())
      assertFalse(Parser(arrayOf("find", "-ra", "-d", "C:\\Users\\Julia", "Собор", "Парижской", "Богоматери")).isCmd())
      assertFalse(Parser(arrayOf("find", "-r", "-da", "C:\\Users\\Julia", "Буранный", "полустанок")).isCmd())
      assertFalse(Parser(arrayOf("find", "-r", "-da", "C:\\Use:rs\\Julia", "Человек", "в", "футляре")).isCmd())
      assertFalse(Parser(arrayOf("-r", "-d", "C:\\Users\\Julia", "[^1234]", "мур?", "проверка")).isCmd())
   }

   @Test
   fun r() {
      assertTrue(Parser(arrayOf("find", "-r", "-d", "C:\\Users\\Julia", "Преступление", "и", "наказание")).r())
      assertFalse(Parser(arrayOf("find", "-d", "C:\\Users\\Julia", "Иду", "на", "грозу")).r())
      assertFalse(Parser(arrayOf("find", "-d", "C:\\Users\\Julia", "-r", "Солнечные", "вспышки")).r())
   }

   @Test
   fun directory() {
      assertThrows(CmdLineFormatException::class.java)
      {Parser(arrayOf("find", "-r", "-d", "<\\Users\\Julia\\IdeaProjects","Тихий", "Дон" )).directory()}


      assertEquals(
         File("."),
         Parser(arrayOf("find", "-r", "У", "нас", "всегда", "будет", "Париж")).directory())

      assertThrows(CmdLineFormatException::class.java)
      {Parser(arrayOf("find", "-rа", "-d", "C:\\Users\\Julia\\IdeaProjects", "Старуха", "Изергиль")).directory()}


      assertThrows(CmdLineFormatException::class.java)
      {Parser(arrayOf("find", "-r", "Мистер:", "Исключительный")).directory()}

      assertEquals(
         File("C:\\Users\\Julia\\IdeaProjects"),
         Parser(arrayOf("find", "-r", "-d", "C:\\Users\\Julia\\IdeaProjects", "Тихий", "Дон")).directory())

      assertEquals(
         File("C:\\Users"),
         Parser(arrayOf("find", "-r", "-d", "C:\\Users", "Письма", "незнакомке")).directory())

   }

   @Test
   fun filename() {
      assertEquals("Я вас любил...", Parser(arrayOf("find", "Я", "вас", "любил...")).fileName())
      assertEquals("сумрак*", Parser(arrayOf("find", "-r", "сумрак*")).fileName())
      assertEquals("Чёрная невеста*",
         Parser(arrayOf("find", "-r", "-d", "C:\\Users\\Julia", "Чёрная", "невеста*")).fileName())
      assertEquals("И дольше века длится день",
         Parser(arrayOf("find", "-r", "-d", "C:\\Users\\Julia", "И", "дольше", "века", "длится", "день")).fileName())
      assertEquals("Я+ [вас] люб?ил...", Parser(arrayOf("find", "Я+", "[вас]", "люб?ил...")).fileName())

   }
}

