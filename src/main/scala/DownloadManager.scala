import java.io.File
import scala.collection.JavaConversions._

case class DownloadManager() {

  val downloader = new JsonEndpointRetrieval
  val frontsRegex = "^[ukas]{2}/fronts/.*".r
  val listsRegex = "lists/.*".r
  val navRegex = "^[ukas]{2}/navigation".r
  val itemsRegex = "items/.*".r
  val searchRegex = "search?.*".r

  def downloadFromFile(filename: String): List[String] = {
    val source = scala.io.Source.fromFile(new File(filename))
    val lines = source.getLines().toList
    source.close()
    val finalLines = lines.distinct
    val results = for {
      line <- finalLines
      id = line.stripPrefix(downloader.mapiPrefix)
      result = executeDownloadById(id)
    } yield result
    results.filter(_.isLeft).map(_.left.get)
  }

  def constructURL(id: String): String = {
    downloader.mapiPrefix + id
  }

  def executeDownloadById(id: String): Either[String, Unit] = {
    id match {
      case frontsRegex() => Right(downloader.getFront(constructURL(id)))
      case listsRegex() => Right(downloader.getList(constructURL(id)))
      case navRegex() => Right(downloader.getNavigation(constructURL(id)))
      case itemsRegex() => Right(downloader.getItem(constructURL(id)))
      case searchRegex() => Right(downloader.getTagSearchResult(constructURL(id)))
      case _ => Left("ID: " + id + " - is an unsupported endpoint")
    }
  }

}
