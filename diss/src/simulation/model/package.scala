package simulation

package object model {
  sealed trait EventType {
    val dropEvent: DropEvent
  }
  case object LoidEidolonEvent {
    val dropEvent = DropEvent("loidEidolon.csv")
  }
  case object TeralystCapturedEvent {
    val dropEvent = DropEvent("teralyst.csv")
  }
  case object GantulystCapturedEvent {
    val dropEvent = DropEvent("gantulyst.csv")
  }
  case object HydrolystCapturedEvent {
    val dropEvent = DropEvent("hydrolyst.csv")
  }
}
