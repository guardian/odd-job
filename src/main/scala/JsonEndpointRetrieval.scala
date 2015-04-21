import java.io.File
import java.net.URI

import play.api.libs.json.{Json, JsValue}

import scala.io.Source
import scalax.io.{Resource, Output}

case class JsonEndpointRetrieval() {

  val mapiPrefix = "http://mobile-apps.guardianapis.com/"

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
    if (url.startsWith(mapiPrefix + "search")){
      val searchQuery = url.stripPrefix(mapiPrefix + "search?query=")
      new File("./cache/", fileUri.getPath + "?query=" + searchQuery + ".json")
    }
    else
      new File("./cache/", fileUri.getPath + ".json")
  }

  def getEndpointAndJson(url: String): JsValue = {
    val file = saveJsonFileFromUrl(url)
    val json = getJsonStringFromFile(file)
    json
  }

  def getFront(url: String) = {
    val json = getEndpointAndJson(url)
    val id = json.\("id")
    println("Saving endpoint ID: "+ id)
    val uris = (json \ "layout" \\ "uri").map(_.as[String]).toList
    println("Saving " + uris.size + " uris")
    for (i <- 0 until uris.size) {
      saveJsonFileFromUrl(uris(i))
    }
  }

  def getList(url: String) = {
    val json = getEndpointAndJson(url)
    val id = json.\("id")
    println("Saving endpoint ID: "+ id)
  }

  def getNavigation(url: String) = {
    saveJsonFileFromUrl(url)
    println("Saving navigation endpoint")
  }

  def getItem(url: String) = {
    val json = getEndpointAndJson(url)
    val id = json.\("id")
    println("Saving endpoint ID: "+ id)
    val relatedUris = (json \ "links" \\ "relatedUri").map(_.as[String]).toList
    println("Saving related content uris")
    for (i <- 0 until relatedUris.size) {
      saveJsonFileFromUrl(relatedUris(i))
    }
  }

  def getTagSearchResult(url: String) = {
    val searchQuery = url.stripPrefix("http://mobile-apps.guardianapis.com/search?query=")
    println("Saving " + searchQuery + " tag search endpoint")
    saveJsonFileFromUrl(url)
  }

}