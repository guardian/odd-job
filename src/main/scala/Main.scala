object Main {

  def main(args: Array[String]): Unit ={
    val homeFront = Front("http://mobile-apps.guardianapis.com/uk/fronts/home")
    homeFront.getFront(homeFront.url)
  }

  //TODO: getList, getItem, getTagSearch, getNavigation

}
