object Main {

  def main(args: Array[String]): Unit ={
    val homeFront = Front("http://mobile-apps.guardianapis.com/uk/fronts/home")
    homeFront.getFront(homeFront.url)

    val item = Item("http://mobile-apps.guardianapis.com/items/commentisfree/2015/apr/15/britain-rotten-electoral-system-nose-peg-vote-swap-tories-out")
    item.getItem(item.url)

    val nav = Navigation("http://mobile-apps.guardianapis.com/uk/navigation")
    nav.getNavigation(nav.url)

    val search = TagSearch("Putin")
    search.getTagSearchResult(search.searchQuery)
  }

}
