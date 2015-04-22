object Main {

  def main(args: Array[String]): Unit ={
    val dManager = DownloadManager()
    val downloadErrors = dManager.downloadFromFile("./download.txt")
    if (!downloadErrors.isEmpty)
      throw new RuntimeException(downloadErrors.mkString("\n"))
  }

}
