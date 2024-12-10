object ObservabilityUtils {

  def JVMVersion: String = {
    val version = Option(System.getProperty("java.version")).getOrElse("")
    val majorVersion = version.split("\\D").headOption match { // Extract the numeric prefix before any non-digit
      case Some("1") => version.split("\\.").lift(1).getOrElse("Unknown") // Legacy format like "1.8"
      case Some(v) if v.forall(_.isDigit) => v                            // Modern format like "9", "17", "21"
      case _ => "Unknown"                                                 // Handle invalid formats gracefully
    }
    majorVersion
  }
}