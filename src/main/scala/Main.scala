object Main {

  def main(args: Array[String]): Unit ={
    val dManager = DownloadManager()
    dManager.downloadFromFile("./download.txt")

  }

}
