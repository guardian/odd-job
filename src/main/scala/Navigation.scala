/**
 * Created by jhare-winton on 16/04/2015.
 */
case class Navigation(url: String) extends JsonHandler {

  def getNavigation(url: String) = {
    saveJsonFileFromUrl(url)
    println("Saving navigation endpoint")
  }

}
