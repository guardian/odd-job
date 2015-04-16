/**
 * Created by jhare-winton on 16/04/2015.
 */
case class List(url: String) extends JsonHandler {

  def getList(url: String) = {
    val json = getEndpointAndJson(url)
    val id = json.\("id")
    println("Saving endpoint ID: "+ id)
  }

}
