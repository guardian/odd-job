import org.scalatest._

class JsonRetrievalTests extends FlatSpec with Matchers with BeforeAndAfterEach {
  "Search query URL" should "produce correct filename" in {
    val filename = JsonEndpointRetrieval().generateFileName("http://mobile-apps.guardianapis.com/search?query=vladimir+putin")
    filename.toString should be ("./cache/search?query=vladimir+putin.json")
  }

  "Fronts URL" should "produce correct filename" in {
    val filename = JsonEndpointRetrieval().generateFileName("http://mobile-apps.guardianapis.com/uk/fronts/home")
    filename.toString should be ("./cache/uk/fronts/home.json")
  }

}
