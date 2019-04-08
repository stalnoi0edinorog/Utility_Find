package Class_Parser.Test

import Parser
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Tests {

   @Test
   fun r() {
      assertTrue(Parser(arrayOf("find", "-r", "-d", "C:\\Users\\Julia", "Преступление", "и", "наказание")).r())
      assertFalse(Parser(arrayOf("find", "-d", "C:\\Users\\Julia", "Иду", "на", "грозу")).r())
   }

   @Test
   fun directory() {
      assertEquals(
         File("C:\\Users\\Julia\\IdeaProjects"),
         Parser(arrayOf("find", "-r", "-d", "C:\\Users\\Julia\\IdeaProjects", "Тихий", "Дон")).directory())

      assertEquals(
         File("C:\\Users"),
         Parser(arrayOf("find", "-r", "-d", "C:\\Users", "Письма", "незнакомке")).directory())

      assertEquals(
         File("."),
         Parser(arrayOf("find", "-r", "У", "нас", "всегда", "будет", "Париж")).directory())

      assertEquals(
         File("."),
         Parser(arrayOf("find", "-r", "У", "нас", "всегда", "будет", "Париж")).directory())
   }

   @Test
   fun filename() {
      assertEquals("Я вас любил...", Parser(arrayOf("find", "Я", "вас", "любил...")).fileName())
      assertEquals("сумрак", Parser(arrayOf("find", "-r", "*сумрак*")).fileName())
      assertEquals("Чёрная невеста",
         Parser(arrayOf("find", "-r", "-d", "C:\\Users\\Julia", "*Чёрная", "невеста*")).fileName())
      assertEquals("И дольше века длится день",
         Parser(arrayOf("find", "-r", "-d", "C:\\Users\\Julia", "И", "дольше", "века", "длится", "день")).fileName())
   }
}
