/**
 * Created by jhare-winton on 16/04/2015.
 */
case class Front(url: String) extends JsonHandler {

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

}
