import java.io.File
import java.net.URI

/**
 * Created by jhare-winton on 16/04/2015.
 */
case class TagSearch(searchQuery: String) extends JsonHandler {

  val searchURL = "http://mobile-apps.guardianapis.com/search?query="

  def getTagSearchResult(searchQuery: String) = {
    saveJsonFileFromUrl(searchURL + searchQuery)
    println("Saving " + searchQuery + " tag search endpoint")
  }

  override def generateFileName(url: String) = {
    val fileUri = URI.create(url)
    new File("./cache/", fileUri.getPath + "?query=" + searchQuery + ".json")
  }

}
