/**
 * Created by jhare-winton on 16/04/2015.
 */
case class Item (url: String) extends JsonHandler(url) {

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

}
