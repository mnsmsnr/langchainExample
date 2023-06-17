package example

import com.theokanning.openai.OpenAiService
import com.theokanning.openai.completion.{CompletionChoice, CompletionRequest}

object ExecuteDavinci extends Greeting {

  def main(args: Array[String]): Unit = {
    executeDavinci("API-KEY")
  }

  def executeDavinci(apiKey: String): Unit = {
    println(greeting)
    // API呼び出し
    val service = new OpenAiService(apiKey)
    val completionRequest = CompletionRequest(
      prompt = "The following is a conversation with an AI assistant. The assistant is helpful, creative, clever.\nHuman: ここにテキストを入力します \nAI: ",
      model = "text-davinci-003",
      maxTokens = 256
    )
    service.createCompletion(completionRequest).getChoices().foreach(println)
    println("処理last")
  }

}

trait Greeting {
  lazy val greeting: String = "ExecuteDavinci"
}