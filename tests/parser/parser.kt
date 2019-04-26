package parser

import Parser
import CmdLineFormatException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Tests {

   @Test
   fun r() {
      assertTrue(Parser(arrayOf("-r", "-d", "C:\\Users\\Julia", "Преступление и наказание")).r)
      assertFalse(Parser(arrayOf("-d", "C:\\Users\\Julia", "Иду на грозу")).r)
   }

   @Test
   fun directory() {
      assertThrows(CmdLineFormatException::class.java) {Parser(arrayOf("-r", "C:\\", "Письма"))}
      assertThrows(CmdLineFormatException::class.java) {Parser(arrayOf("C:\\", "Письма"))}
      assertThrows(CmdLineFormatException::class.java) {Parser(arrayOf("-m", "-n", "C:\\", "Я вас любил"))}
      assertThrows(CmdLineFormatException::class.java) {Parser(arrayOf("-d", "Письма незнакомке"))}
      assertThrows(CmdLineFormatException::class.java) {Parser(arrayOf("-r", "-m", "-d","C:\\"))}
      assertThrows(CmdLineFormatException::class.java) {Parser(arrayOf("-r", "-d","C:\\", "Онегин", "Тарас Бульба"))}
      assertThrows(CmdLineFormatException::class.java) {Parser(arrayOf("C:\\", "-r", "-d", "Онегин"))}
      assertThrows(CmdLineFormatException::class.java) {Parser(arrayOf( "Онегин", "-r", "-d","C:\\"))}
      assertThrows(CmdLineFormatException::class.java) {Parser(arrayOf("-r", "-d", "C:\\"))}
      assertThrows(CmdLineFormatException::class.java) {Parser(arrayOf("-r", "-d", "Онегин"))}
      assertThrows(CmdLineFormatException::class.java) {Parser(arrayOf("Крош", "Ёжик"))}
      assertThrows(CmdLineFormatException::class.java) {Parser(arrayOf())}

      assertEquals(".", Parser(arrayOf("тор* купи? помидор")).directory)
      assertEquals(".", Parser(arrayOf("-r", "У нас всегда будет Париж")).directory)
      assertEquals("C:\\Users", Parser(arrayOf("-r", "-d", "C:\\Users", "Письма незнакомке")).directory)

      assertEquals("\"../home/julia ju/tmp/file1\"",
         Parser(arrayOf("-d", "\"../home/julia ju/tmp/file1\"", "Письма незнакомке")).directory)

      assertEquals("/home/julia/tmp/file1",
         Parser(arrayOf("-d", "/home/julia/tmp/file1", "люб и? нака+зание")).directory)

      assertEquals("\"C:\\Users\\Julia\\Idea Projects\\SecondTask(Find)\\input poetry\"",
         Parser(arrayOf("-d", "\"C:\\Users\\Julia\\Idea Projects\\SecondTask(Find)\\input poetry\"", "Письма незнакомке"))
            .directory)

      assertEquals(
         "\"C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry\"",
         Parser(arrayOf("-d", "\"C:\\Users\\Julia\\IdeaProjects\\SecondTask(Find)\\input poetry\"", "Письма", "незнакомке"))
            .directory)


      assertEquals("C:\\Users\\Julia\\IdeaProjects",
         Parser(arrayOf("-r", "-d", "C:\\Users\\Julia\\IdeaProjects", "Тихий Дон")).directory)

   }

   @Test
   fun filename() {
      assertEquals("тор* купи? помидор", Parser(arrayOf("тор* купи? помидор")).fileName)
      assertEquals("Я вас любил...", Parser(arrayOf("Я вас любил...")).fileName)
      assertEquals("сумрак*", Parser(arrayOf("-r", "сумрак*")).fileName)

      assertEquals("Чёрная невеста*",
         Parser(arrayOf("-r", "-d", "C:\\Users\\Julia", "Чёрная невеста*")).fileName)

      assertEquals("И дольше века длится день",
         Parser(arrayOf("-r", "-d", "C:\\Users\\Julia", "И дольше века длится день")).fileName)

      assertEquals("Я+ [вас] люб?ил...", Parser(arrayOf("Я+ [вас] люб?ил...")).fileName)

      assertEquals("На холмах Грузии...",
         Parser(arrayOf("-d", "\"C:\\Users\\Julia\\Idea Projects\\SecondTask(Find)\\input poetry\"",
            "На холмах Грузии...")).fileName)
   }
}

