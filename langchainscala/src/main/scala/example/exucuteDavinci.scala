package example

import com.theokanning.openai.OpenAiService
import com.theokanning.openai.completion.{CompletionChoice, CompletionRequest}

object ExecuteDavinci extends Greeting {

  def main(args: Array[String]): Unit = {
    if (args.length >= 3) {
      val isJaTranslation = args(0).toBoolean
      val isPointOut = args(1).toBoolean
      val language = args(2)
      
      val prompt = "The following is a conversation with an AI assistant. The assistant is helpful, creative, clever. \nAI: "
      executeDavinci(prompt, isJaTranslation, isPointOut, language)
    } else {
      println("引数が不足しています。引数1: true/false, 引数2: true/false, 引数3: \"ja\"または\"en\" (例: sbt run true false \"ja\")")
    }
  }

  def executeDavinci(prompt: String, isJaTranslation: Boolean, isPointOut: Boolean, language: String): Unit = {
    var updatedPrompt = prompt + "\nHuman: "

    updatedPrompt += "・フリートーク英会話の相手をしてください。"

    updatedPrompt += "・返答は「返事：」という文字列の後に、返事の英文を記述し最後に改行し、"

    if (isJaTranslation) {
      updatedPrompt += "その後「和訳：」という文字列の後に、返事の日本語訳を記述し最後に改行し、"
    }

    if (isPointOut) {
      val languageText = if (language == "ja") "日本語" else "英語"
      updatedPrompt += s"その後「指摘：」という文字列の後に、英文ミスやスペルミスや適切でない単語等の指摘がある場合のみ${languageText}で記述（無い場合は「「指摘：」も記述しない」）し最後に改行し、"
    }

    updatedPrompt += "出力してください。"
   
    // API呼び出し
    val service = new OpenAiService("API-KEY")
    val completionRequest = CompletionRequest(
      prompt = updatedPrompt,
      model = "text-davinci-003",
      maxTokens = 256
    )
    service.createCompletion(completionRequest).getChoices().foreach(println)
  }

}

trait Greeting {
  lazy val greeting: String = "ExecuteDavinci"
}
