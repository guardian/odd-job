import java.io.File
import java.net.URI

import play.api.libs.json.{Json, JsValue}

import scala.io.Source
import scalax.io.{Resource, Output}

/**
 * Created by jhare-winton on 16/04/2015.
 */
abstract class JsonHandler {

  def saveJsonFileFromUrl(url: String): File = {
    val file = generateFileNameAndDeleteExisting(url)
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

  def generateFileNameAndDeleteExisting(url: String) = {
    val file = generateFileName(url)
    if (file.exists())
      file.delete()
    file
  }

  def generateFileName(url: String) = {
    val fileUri = URI.create(url)
    new File("./cache/", fileUri.getPath + ".json")
  }

  def getEndpointAndJson(url: String): JsValue = {
    val file = saveJsonFileFromUrl(url)
    val json = getJsonStringFromFile(file)
    json
  }

}
