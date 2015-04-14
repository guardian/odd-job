import play.api.libs.json._

import scala.io.Source
import sys.process._
import java.net.URL
import java.io.File

object ParentJson {

  def main(args: Array[String]): Unit ={
    saveJsonFileFromUrl("http://mobile-apps.guardianapis.com/uk/fronts/home", "homepage")
    getJsonStringFromFile("homepage.json")
    getFront("homepage.json")
  }

  def saveJsonFileFromUrl(url: String, filename: String) = {
    val filenameFormatted: String = s"$filename.json"
    new URL(url) #> new File(filenameFormatted) !!
  }

  def getJsonStringFromFile(filename: String): JsValue = {
    Json.parse(Source.fromFile(filename).mkString)
  }

  def getFront(filename: String) = {
    val json = getJsonStringFromFile(filename)
    val id = json.\("id")
    println(id)
    val uris: List[String] = (json \ "layout" \\ "uri").map(_.as[String]).toList
    println(uris)
    println(uris.size)

    for (i <- 0 until uris.size) {
      saveJsonFileFromUrl(uris(i), i.toString)
    }
  }

}
