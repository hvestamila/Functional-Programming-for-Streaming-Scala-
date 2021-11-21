import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.xml.ScalaXmlSupport._
import akka.http.scaladsl.model.Uri.Query
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.headers._
import akka.http.scaladsl.unmarshalling.Unmarshal

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.xml.NodeSeq

object WikiIntegrationTask {

  def retrieveLatestChanges(): Future[Seq[WikiChange]] = {
    implicit val system = ActorSystem(Behaviors.empty, "SingleRequest")
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.executionContext


    val request = HttpRequest(
      method = HttpMethods.GET,
      uri = "https://en.wikipedia.org/w/api.php?action=feedrecentchanges"
    )

    def sendGet(): Future[Seq[WikiChange]] = {
      val responseFuture: Future[Seq[WikiChange]] = Http().singleRequest(request)
        .map(_.entity)
        .flatMap(Unmarshal(_).to[NodeSeq])
        .map(a => (a \ "channel" \ "item").map(node => WikiChange((node \ "title").text, Uri((node \ "link").text), Uri((node \ "guid").text), (node \ "description").text, LocalDateTime.from(DateTimeFormatter.RFC_1123_DATE_TIME.parse((node \ "pubDate").text)))))

      responseFuture
    }
    sendGet()
  }

  case class WikiChange(title: String, link: Uri, guid: Uri, description: String, pubDate: LocalDateTime)

}