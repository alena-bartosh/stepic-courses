package week_4

object MyPartialFunction extends App {

  val chatbotSimple: PartialFunction[String, String] = {
    case "hello" => "Hi, I'm Bot"
    case "bye" => "Bye-bye"
    case "what's up" => "sup-sup"
  }

  val chatbot = chatbotSimple.lift

  println(chatbot("hello").getOrElse("I am default value"))
  println(chatbot("boss").getOrElse("I am default value"))

  // First way to read input:
  scala.io.Source.stdin.getLines().foreach(line => println(chatbot(line)))

  // Simpler way to read input:
  scala.io.Source.stdin.getLines().map(chatbot).foreach(println)
  // foreach doesn't create any new collections so it will be better for println then map
}
