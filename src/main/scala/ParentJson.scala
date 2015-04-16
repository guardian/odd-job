import play.api.libs.json._

import scala.io.Source
import java.io.File
import java.net.URI
import scalax.io.{Resource, Output}

object ParentJson {

  def main(args: Array[String]): Unit ={
    getFront("http://mobile-apps.guardianapis.com/uk/fronts/home")
  }

  def saveJsonFileFromUrl(url: String): File = {
    val file = generateFile(url)
    if (!file.exists())
      file.getParentFile().mkdirs()
    val content = scala.io.Source.fromURL(url).mkString
    val output:Output = Resource.fromFile(file)
    output.write(content)
    file
  }

  def getJsonStringFromFile(file: File): JsValue = {
    Json.parse(Source.fromFile(file).mkString)
  }

  def generateFile(url: String) = {
    val fileUri = URI.create(url)
    new File("./cache/", fileUri.getPath + ".json")
  }

  def getFront(url: String) = {
    val file = saveJsonFileFromUrl(url)
    val json = getJsonStringFromFile(file)
    val id = json.\("id")
    println("Saving endpoint ID: "+ id)
    val uris: List[String] = (json \ "layout" \\ "uri").map(_.as[String]).toList
    println("Saving " + uris.size + " uris")
    for (i <- 0 until uris.size) {
      saveJsonFileFromUrl(uris(i))
    }
  }

  //TODO: getList, getItem, getTagSearch, getNavigation

}
