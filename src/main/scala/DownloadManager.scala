import java.io.File
import scala.collection.JavaConversions._

case class DownloadManager() {

  val downloader = new JsonEndpointRetrieval
  val frontsRegex = "^[ukas]{2}/fronts/.*".r
  val listsRegex = "^[ukas]{2}/lists/.*".r
  val navRegex = "^[ukas]{2}/navigation".r
  val itemsRegex = "items/.*".r
  val searchRegex = "search?.*".r

  def downloadFromFile(filename: String) = {
    val lines = scala.io.Source.fromFile(new File(filename)).getLines().toList
    for (i <- 0 until lines.size) {
      val id = lines(i).stripPrefix(downloader.mapiPrefix)
      executeDownloadById(id)
    }
  }

  def constructURL(id: String): String = {
    downloader.mapiPrefix + id
  }

  def executeDownloadById(id: String) = {
    id match {
      case frontsRegex() => downloader.getFront(constructURL(id))
      case listsRegex() => downloader.getList(constructURL(id))
      case navRegex() => downloader.getNavigation(constructURL(id))
      case itemsRegex() => downloader.getItem(constructURL(id))
      case searchRegex() => downloader.getTagSearchResult(constructURL(id))
      case _ => throw new UnsupportedOperationException("ID: " + id + " - is an unsupported endpoint")
    }
  }

}
